<div align="center"><img src="https://github.com/manolodd/openlrae/raw/develop/src/main/resources/com/manolodominguez/openlrae/logo/logo_openlrae.png" alt="OpenLRAE logo" width="400"/></div>

[![Maven Central](https://img.shields.io/maven-central/v/com.manolodominguez/openlrae)](https://search.maven.org/artifact/com.manolodominguez/openlrae/0.3/jar)

# PROJECT STATUS (Tested from JDK 9 to 15)

- Master branch [![Build Status](https://img.shields.io/travis/manolodd/openlrae/master.svg)](https://travis-ci.org/manolodd/openlrae?branch=development)

- Development branch [![Build Status](https://img.shields.io/travis/manolodd/openlrae/develop.svg)](https://travis-ci.org/manolodd/openlrae?branch=develop)

# THE PROJECT

<b>OpenLRAE</b> is a multiplatform Licensing Risks Analysis Engine. It's a java library that allow knowing the risk induced by the bill of components you have included in your project, the way they are bound, the kind of distribution you're going to do of your project, and so on.

After decades working in public, private and academic sector, assesing hundreds of platforms from an opensource perspective, I realized that most of the existing tools try to detect the components that have been included in a project and, then, they try to infer their licenses. Only after that (that brings a lots of false positives and negatives) a license analysis is done.

This approach part from the point of view that a development team doesn't know and control the bill of components of their projects, and are not beware of the licensing risks their use imply.

Also, there are lots of potential risks derived from licensing; not only licensing incompatibilities but maintenance risks, risks in bussiness terms, etc.

OpenLRAE approach is different in the sense that developement teams have to know the bill of components they're using, their versions, their licenses and all kind of things related to the use of third parties components in their own projects.

With this kind of knowledge, teams are able to use OpenLRAE to get a dashboard of licensing risks that can help them in decission making.

# LICENSE

## Latest snapshot version being developed:
 
- <b>OpenLRAE 0.5-SNAPSHOT</b> (development branch) - Apache-2.0.

## Binary releases:

- <b>OpenLRAE 0.4</b> (current, master branch) - Apache-2.0.
- <b>OpenLRAE 0.3</b> - Apache-2.0.
- <b>OpenLRAE 0.2</b> - Apache-2.0.
- <b>OpenLRAE 0.1</b> - Apache-2.0.

# PEOPLE BEHIND OPENLRAE

## Author:
    
 - Manuel Domínguez-Dorado - <ingeniero@ManoloDominguez.com>
   
Please, refer always to the project home page at:

 - http://openlrae.manolodominguez.com/

# ARTIFACTS AVAILABILITY

You can download latest compiled stable releases from the releases section of this repository. Also, since release 0.3 OpenLRAE is in Maven Central so you can add it as a dependecy in your Maven project inserting the following in your pom.xml:
```console
<dependency>
  <groupId>com.manolodominguez</groupId>
  <artifactId>openlrae</artifactId>
  <version>0.4</version>
</dependency>
```
For othe project builders (graddle, buildr...) see the next link in Maven Central: https://search.maven.org/artifact/com.manolodominguez/openlrae/0.4/jar

# COMPILING FROM SOURCES

If you want to test new features (please, do it and give feedback), you will need to compile the project from the current snapshot being developed. Follow these steps:

Clone the OpenLRAE repo: 
```console
git clone https://github.com/manolodd/openlrae.git
```
Choose the "development" branch, compile the code and obtain a binary jar including all you need (you will need to install Maven before):
```console
cd openlrae
git checkout development
mvn package
```
The jar file will be located in "target" directory.
```console
cd target
```
Now, pick te artifact you need. It could be:
```console
openlrae-{YourVersion}.jar
```
if you are going to use a project builder in your project (maven, graddle) that retrieves dependencies at compile time, or
```console
openlrae-{YourVersion}-with-dependencies.jar
```
if you want openlrae and all its dependencies in a bundle.

You can also run the latter to do some things: to know which things are supported in the library, to run a console based analysis, etc.
```console
java -jar openlrae-{YourVersion}-with-dependencies.jar
```

# HOW TO USE THE LIBRARY

OpenLRAE is quite simple to use. Its API is very reduced.

First, define some component that wil be included into your project as dependencies. Specify their names, versions and licenses. This example has two components, but you could add the number you want.
```console
Component component1 = new Component("Component 1 name", "Component 1 version", SupportedLicenses.APACHE_2_0);
Component component2 = new Component("Component 2 name", "Component 2 version", SupportedLicenses.GPL_2_0_OR_LATER);
```
Second, create a component binding for each component. This is used to know whether the component is linked statically or dynamically in the project and also to know if the project uses the component a lot or only a little.
```console
ComponentBinding componentBinding1 = new ComponentBinding(component1, SupportedLinks.DYNAMIC, SupportedComponentWeights.LOW);
ComponentBinding componentBinding2 = new ComponentBinding(component2, SupportedLinks.DYNAMIC, SupportedComponentWeights.HIGH);
ComponentBinding componentBinding3 = new ComponentBinding(component3, SupportedLinks.STATIC, SupportedComponentWeights.NEAR_LOW);
```
Third, create the project. You have to specify a project name and version, the license (or licenses) you want to use for the project and the kind of redistribution you are going to do of the project. Also, add the first component bindings to it. In this example only a project license is specified, but if you want to dual license the project (or even more), there is a Project method to add more.
```console
Project project = new Project("Project name", "project version", SupportedLicenses.GPL_2_0_OR_LATER, SupportedRedistributions.SOFTWARE_PACKAGE_OR_SAAS, componentBinding1);
```
Then, add the other component bindings to the project.
```console
project.addComponentBinding(componentBinding2);
```
At this point, you have a well-defined project. You could also do the same using only two lines of code if you provide a JSON specification of the project. There is a Project constructor for that. 

Once the project is defined, what risks would you to measure? OpenLRAE supports several risks related to licensing. Create one risk analyser for each risk you want to evaluate. In this example we use two (their name are self-explainatory).
```console
RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense riskAnalyser1 = new RiskAnalyserLicensesOfComponentsIncompatibleWithProjectLicense(project);
RiskAnalyserLimitedSetOfPotentialComponentsLicenses riskAnalyser2 = new RiskAnalyserLimitedSetOfPotentialComponentsLicenses(project);
```
And you will need a license riks analysis engine that will do the job. So create it and add the risk analysers you have defined above.
```console
LicenseRiskAnalysisEngine engine = new LicenseRiskAnalysisEngine(riskAnalyser1);
engine.addRiskAnalyser(riskAnalyser2);
```
And... that's all. Whenever you want, run the analysis and collect the result.
```console
RiskAnalysisResult[] resultSet = engine.analyse();
```
You could also transform the result into a JSON String report (there are other options). There is a reports factory to help you with this task.
```console
String JSONReport = ReportsFactory.getInstance().getReportAsBeautifiedJSONString(project, resultSet);
```
Now, print the report, send over a REST service, store it in a file or whatever you want.
```console
System.out.println("This is the analysis report:\n\n"+JSONReport);
```

# Current features

## Supported risks

OpenLRAE is about licensing risks. But not only license compatibility risks. There are some other risks related to the license of projects and components that are important and could be considered. The following paragraphs show you the risks OpenLRAE support at the moment. And, for all of them, OpenLRAE is able to analyse your current project and give you the risk exposure level, and also the risk impact related to this type of risk, in terms of "the effort needed to reduce the risk exposure to 0%".

#### Having uncompatibilities between the components licenses and the project licenses

Sometimes we include a component in a project thinking in the functionality of this component but without being aware of the legal problems that it could lead to. Specially when we are realeasing our own project under a specific license and the components you have included are not compatible with it. If your project has a risks exposure greater than 0%, you are in legal troubles you have to solve right now. You have to keep this risk at 0%.

#### Having a limited set of potential project licenses to choose because of the components licenses

It is sometimes important to keep the possibility of changing our project license in the future. Perhaps because it is needed in order to include our project in a bigger one, perhaps to support a change in the bussines model surrounding the project, etc. So, keep in mind that the licenses of the components you are using will determine the licenses you can use in the future for the project. Keep this risk low if you want to have the opportunity to change your project license among a wide variety.

#### Having a limited set of potential component licenses to choose because of the project license

This is similar to the previous risk. If you want to fix your project license, this license will determine the components licenses you can use as dependencies now and in the future. You are not going to develop a project from scratch. You are going to use components, probably opensource components, to build your project. It is a typical mistake to establish a project license and later realize that most of the components that we would like to use are incompatible with it. Keep this risk low if you want to have the opportunity to include third parties components in your project with wide variety of licenses.

#### Having an obsolete set of components licenses

New versions of licenses usually appear to cover new situations or correct situations that were not taken into account when they were designed and have appeared over time. Keeping a set of components in your project with licenses in very old versions can make your project not adapt well to these new situations.

#### Having an unfasionable set of component licenses

A trendy license is a license whose use is growing up. It does not means necessarily that a wide set of components use this license right now, but it points to this will happen in the mid-term. So, if you are starting a new project right now it seems a better choice to chose trendy licenses (but not very spreaded now) than unfashionable licenses (that can be used by a lot of components right now, but will be lesser spreaded in the future). Keep this risk low if your project is starting or if you want to do a deep refactor of it.

## Supported license analysis

There are some aspects associated to a license that are used by OpenLRAE to compute some risks. They are easy to understand. But in order to do a deep risks assesment where license compatiblities are involved, OpenLRAE has a base of knowledge (which grows exponentially) that defines how, where and under what conditions a given component (released under a given license) can be included in a project (released also under one, two or more licenses). The following matrixes shows a summary of which kind of situations OpenLRAE is able to analyse.

<div align="center"><img src="https://raw.githubusercontent.com/manolodd/openlrae/develop/src/main/resources/com/manolodominguez/openlrae/supportedanalysis/dynamic.jpg" alt="Supported analysis for dynamic linking" width="100%"/></div>
<div align="center"><img src="https://raw.githubusercontent.com/manolodd/openlrae/develop/src/main/resources/com/manolodominguez/openlrae/supportedanalysis/static.jpg" alt="Supported analysis for static linking" width="100%"/></div>

# THIRD-PARTY COMPONENTS

OpenLRAE uses third-party components each one of them having its own OSS license. License compatibility has been taken into account to allow OpenLRAE be released under its current OSS licence. They are:

- slf4j-api 2.0.0-alpha1 - MIT - https://www.slf4j.org
- slf4j-simple 2.0.0-alpha1 - MIT - https://www.slf4j.org
- mjson 1.4.1 - Apache-2.0 - http://bolerio.github.io/mjson
- junit-jupiter-engine 5.7.0 - EPL-2.0 - https://junit.org/junit5

Thanks folks!

# WHAT CAN YOU CONTRIBUTE?

Lot of things. Most people doesn't have the possibility to contribute code. But there are lots of other thigs that are very important too:

 - Source code.
 - Translations.
 - Documentation. Independently of your native language, there is an <b>OpenLRAE</b> user that also have the same native language. I've not the possibility of documenting everything, but if you do that, share it!
 - Presentations.
 - Legal knowledge. OpenLRAE is built from lots and lots of analysis of software licenses. I'm not speaking about coding, but about legal analysis.
 - Ideas.

#### Thanks for contributing.
