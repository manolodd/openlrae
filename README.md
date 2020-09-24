<div align="center"><img src="https://github.com/manolodd/openlrae/raw/develop/src/main/resources/com/manolodominguez/openlrae/logo/logo_openlrae.png" alt="OpenLRAE logo" width="400"/></div>

# PROJECT STATUS (Tested from JDK 9 to 15)

- Master branch [![Build Status](https://img.shields.io/travis/manolodd/openlrae/master.svg)](https://travis-ci.org/manolodd/openlrae?branch=development)

- Development branch [![Build Status](https://img.shields.io/travis/manolodd/openlrae/develop.svg)](https://travis-ci.org/manolodd/openlrae?branch=develop)

# THE PROJECT

<b>OpenLRAE</b> is a Licensing Risks Analysis Engine, multiplatform and mutilanguage. It's a java library that allow knowing the risk induced by the bill of components you have included in your project, the way they are bound, the kind of distribution you're going to do of your project, and so on.

After decades working in public and private and academic sector, assesing hundreds of platforms from an Opensource perspective I realized that most of the existing tools try to detect the components that have been included in a project and, then, they try to infer their licenses. Only after that (that brings a lots of false positives and a lot of false negatives) a license analysis is done.

This approach part from the point of view that a development team doesn't know and control the bill of components of their projects, and are not beware of the licensing risks their use imply.

Also, there are lots of potential risks derived from licensing; not only licensing incompatibilities but maintenance risks, risks in bussiness terms, etc.

OpenLRAE approach is that developement teams have to know the bill of components they're using, their versions, their licenses and all kind of things related to the use of third parties components in their own projects.

With this kind of knowledge, teams are able to use OpenLRAE to get a dashboard of licensing risks that can help them in decission making.

# LICENSE

## This release:
 
- <b>OpenLRAE 0.3</b> is released under the terms of Apache License, Version 2.0.

## Previous releases:

- <b>OpenLRAE 0.2</b> was released under the terms of Apache License, Version 2.0.
- <b>OpenLRAE 0.1</b> was released under the terms of Apache License, Version 2.0.

# PEOPLE BEHIND OPENLRAE

## Author:
    
 - Manuel Domínguez-Dorado - <ingeniero@ManoloDominguez.com>
   
Please, refer always to the project home page at:

 - http://openlrae.manolodominguez.com/

# COMPILING FROM SOURCES

The best option is to download latest compiled stable releases from the releases section of this repository. However, if you want to test new features (please, do it and give feedback), you will need to compile the project from sources. Follow these steps:

 - Clone the OpenLRAE repo: 
```console
git clone https://github.com/manolodd/openlrae.git
```
 - Compile the code and obtain a binary jar including all you need (you will need to install Maven before):
```console
cd openlrae
mvn package
```
 - The jar file will be located in "target" directory.
```console
cd target
```
- Now, pick te artifact you need:
```console
openlrae-{YourVersion}.jar
```
if you are going to use a project builder in your project (maven, graddle) that will supply dependencies at compile time, or
```console
openlrae-{YourVersion}-with-dependencies.jar
```
If you want openlrae and all its dependencies in a bundle.

You can also run the latter to do some things: to know wich things are supported in the librery, to run a buil-in risk analysis example, etc.
```console
java -jar openlrae-{YourVersion}-with-dependencies.jar
```

# THIRD-PARTY COMPONENTS

OpenLRAE uses third-party components each one of them having its own OSS license. License compatibility has been taken into account to allow OpenLRAE be released under its current OSS licence. They are:

- slf4j-api 2.0.0-alpha1 - MIT - https://www.slf4j.org
- slf4j-simple 2.0.0-alpha1 - MIT - https://www.slf4j.org
- mjson 1.4.1 - Apache-2.0 - http://bolerio.github.io/mjson/

Thanks folks!

# HOW TO CONTRIBUTE

OpenLRAE is opensource software. I encourage you to modify it as much as possible; but I would like you to send this modifications back and, hence, became an OpenLRAE contributor. In this way, all the people will benefit from them as you are doing downloading and using OpenLRAE now.

If you want to contribute to OpenLRAE project, follow these instructions:

 - Log in to your GitHub account.
 - Look for OpenLRAE project.
 - Select the <b>development branch</b> of OpenLRAE and create a fork in your own GitHub repository.
 - Clone <b>your</b> OpenLRAE repository to your PC or laptop.
 - Do all modifications in local, file additions or deletions, modifications, commits...
 - Push your modifications to <b>your</b> remote github OpenLRAE repository.
 - Go again to yout GitHub account, choose your OpenLRAE repository and click on the tab "Pull requests".
 - Then, click on the green button at the right "New pull request". This will guide you to make a pull request (send your modifications on your own OpenLRAE repository to OpenLRAE main repository from where you did your fork at the beginning). 
 - Choose the development branch of manolodd/OpenLRAE as base branch to merge to. Then "Create pull request" (give a title and a description, please).
 - That's all; I will have your contribution and I will try to merge it into the development branch of OpenLRAE. Please, comment your contribution as much as possible; I have to be able to understand your contribution.

REMEMBER!!!! all your contributions have to be compatible with Apache License, Version 2.0 and you have to own all rights on them. And no source code contributions has to be compatible to Creative Commons - By.

# WHAT CAN YOU CONTRIBUTE?

Lot of things. Most people doesn't have the possibility to contribute code. But there are lots of other thigs that are very important too:

 - Source code.
 - Translations.
 - Documentation. Independently of your native language, there is an <b>OpenLRAE</b> user that also have the same native language. I've not the possibility of documenting everything, but if you do that, share it!
 - Presentations.
 - Legal knowledge. OpenLRAE is built from lots and lots of analysis of software licenses. I'm not speaking about coding, but about legal analysis.
 - Ideas.

#### Thanks for contributing.
