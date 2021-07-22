# EpubToolbox

Desktop utility for packing, unpacking and validating epub files

![Toolbox Hero Shot](http://biggerconcept.com/images/epubtoolbox/hero.png)

## Synopsis

The epub toolbox is a handy desktop utility for a basket of epub file management activities. The toolbox provides point and click access to packing and unpacking utilities (zip and unzip respectively), the [idpf epub checker](github.com/idpf/epubcheck), custom validators (image size check and file size check) and provides utilities to remove OS Artefacts (.DS_Store files and thumbs.db files) from zipped epub files.

### Packing and Unpacking

The pack and unpack features are wrappers around the [Zip4J](http://www.lingala.net/zip4j/) library, and handle the zipping and unzipping of non DRM protected epub files. Simply select the pack ![pack icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-PackIcon.png?raw=true) or unpack ![unpack icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-UnpackIcon.png?raw=true) buttons (on the top left) and follow the prompts. You'll be asked to choose your input file, then choose your output file, and the toolbox handles the rest! The pack action will also ensure that the mimetype file is uncompressed at the top of the archive as per the epub standard.

### Validators

The toolbox includes 3 validators which can be accessed from the main toolbar:

- ![epub check icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-CheckIcon.png?raw=true) EpubCheck validator
- ![file size check icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-SizeCheckIcon.png?raw=true) File Size validator
- ![image size check icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-ImageCheckIcon.png?raw=true) Image Size validator

The epubcheck validator is a wrapper arround the [IDPF epub checker](github.com/idpf/epubcheck) which is a standard check of epub validity used by most epub retailers. The results will appear in the console view underneath the toolbar.

Both the file size validator and the image size validators are provided as additional checks for best practice. 

The file size validator reports on any html files that are larger than 300kb, and the reason that's included is because epubs with chapters larger than this are usually quite slow on epub readers such as the iPad. Although chapters with files larger than 300kb are likely to be deemed as valid by most retailers - I'd suggest you consider splitting the chapter at this point.

The image size validator reports on images larger than 4.2 million pixels in size. Although some retailers accept (or perhaps encourage) epubs with images larger than this (for high definition readers), Apple does not. Ensure your epubs pass this check if you intend to publish on the iBookstore.

Finally there is an all checks button ![all checks icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-AllCheckIcon.png?raw=true) which runs all of the aforementioned checks on the selected file one after the other.

### Utilities

Provided in the dropdown on the right are some utilities for removing unwanted artefacts from epub files.

- OS Artefact removal utility
- iTunesMetadata.plist removal utility

The OS Artefact removal utility will remove any `.DS_Store` or `thumbs.db` files which are erroneously zipped up into epub files. Similarly the iTunesMetadata.plist removal utility will remove the `iTunesMetadata.plist` file which is automatically added to epubs once they're opened in iTunes (for side loading onto an iPad).

Finally the Pick Utility ![pick utility icon](https://github.com/andrewbigger/epubtoolbox/blob/main/app/src/main/resources/icons/Toolbox-PickIcon.png?raw=true) allows you to provide a comma separated list of filenames that you'd like to select from a folder of epubs. 

### Transactional Features

The second toolbar provides a number of transactional features which allow you to work across collections of epubs.

#### Transaction recording and playback

Transaction recording allows you to record a set of steps and replay them. This is useful for any work which requires the repeated packing and checking of epubs. To use this simply hit record, and then do something as you normally would. Once you've hit the end of your action hit Stop. To repeat the recorded action hit Play, and the recorded transaction will replay as it was. 

__NB:__ Recordings don't include the repeat action (which is different - see below), and will override any files which exist.

#### Repeat

The repeat function is a little bit different, it simply repeats the last action again. Handy if you need to run the one validator multiple times on the same file.

#### Collection Mode

Collection mode will change the behaviour of all of the validators and utilties. Instead of selecting 1 file, you will be prompted to select a folder of epubs. Any action you subsequently take will be applied to all epubs in that folder.

#### Console logging and output

Finally you'll notice a clear console and export log button. The clear console will erase any output of any previous transactions. Conversely the export log button allows you to save the console output to a text file for later use.

## Getting Started

### Requirements

Before we get cracking you'll need a Mac or a PC with the following spec:

- OS Windows 7 or later or Mac OSX 10.6 or later
- 500MB of disk space
- 128MB of RAM

If you wish to fork this project you'll also require Java:

- Oracle JDK 1.8
- Java FX
- Maven 3 or later

### Installation

#### Mac Install

1. Download the compiled DMG by selecting the Mac OS link from the bottom of [http://apps.biggerconcept.com/epubtoolbox](http://apps.biggerconcept.com/epubtoolbox)
2. Mount the DMG file
3. Drag and drop the EpubToolbox onto the Applications link

You can also download launchers tuned to the [alpha](http://apps.biggerconcept.com/dist/alpha/epubtoolbox/EpubToolbox-1.0.dmg) or [beta](http://apps.biggerconcept.com/dist/beta/epubtoolbox/EpubToolbox-1.0.dmg) channels for testing.

#### Windows Install

1. Download the compiled msi by selecting the Windows link from the bottom of [http://apps.biggerconcept.com/epubtoolbox](http://apps.biggerconcept.com/epubtoolbox)
2. Double click the msi anx follow the installation wizard instructions.

#### Debian Linux install

1. Download the .deb package from the bottom of [http://apps.biggerconcept.com/epubtoolbox](http://apps.biggerconcept.com/epubtoolbox)
2. Open a terminal and cd to the download folder i.e.:

```bash
cd ~/
```

3. Install package using dpkg i.e.:

```bash
sudo dpkg -i epubtoolbox-1.0.deb
```

#### Fork and make your own

- SSH: `git clone git@bitbucket.org/biggerconcept/epubtoolbox.git`
- HTTPS `git clone https://bitbucket.org/biggerconcept/epubtoolbox.git`

### Compiling the application

There are 2 projects included within this repo:

- app
- launcher

The launcher is a [fxlauncher](https://github.com/edvin/fxlauncher) class, which bootstraps the application, and downloads any updates from the remote server. __Do not place any code you wish to update in the launcher project__ as it will not be updated along with the app. The launcher works by maintaining an application manifest locally, and checking a manifest on the remote site for changes. See the fxlauncher documentation for more on how this works.

The app project is where all the magic happens. 

To compile you'll first need to build the launcher:

```bash
cd launcher
mvn clean package
```

Once the launcher is built you can now build the application package:

```bash
cd app
mvn clean package
```

As part of the build, the surefire plugin will execute all JUnit tests (which are covered in detail below).

### Building the installer

The installer is a binary of the fxlauncher aforementioned. Building this binary can take some time, and must be done on the target platform (i.e. Mac OS `.app` bundles need to be built on a Mac). Before you do this you need to ensure that the launcher is built (see above).

To build an installer for mac osx:

```bash
cd app
mvn clean install -Dbin=dmg
```

To build an installer for windows:

```bash
cd app
mvn clean install -Dbin=msi
```

To build an installer for debian linux:

```bash
cd app
mvn clean install -Dbin=dpkg
```

## Deploying the application

To deploy the application (without building the binary - which places the new classes on the webserver), you first need to ensure that the wagon plugin is correctly configured within the `pom.xml` file.

You may deploy the application regardless of whether you're on a PC, Mac or Linux (it just sends the class files to the webserver).

```bash
cd app
mvn clean deploy -Dchannel=alpha # channel can be alpha|beta|stable
```

If you wish to deploy the application binary as well, you can do so by chaining the `-Dbin=osx` command on the end:

```bash
cd app
mvn clean deploy -Dchannel=alpha -Dbin=osx # bin can be dmg|msi|dpkg
```

## Tests

Unit tests are [Junit tests](http://junit.org/junit4/), and are kept in the `test/` package in both the `app` and `launcher` projects. The tests are executed post build, and so should be invoked from maven:

```bash
mvn build
```

There is a single suite of UI tests powered by Junit in kahoots with [TestFX](https://github.com/TestFX/TestFX). This test suite loads the UI, performs an action and evaluates the response. 

__NB:__ The UI tests are not executed as part of the mvn build, as there seems to be an issue with running them concurrently. For now you must invoke that test manually.

Describe and show how to run the tests with code examples.

## Issues and Contributions

Please [raise an issue](https://bitbucket.org/biggerconcept/epubtoolbox/issues/new) if you have trouble with the toolbox, or suggestions for its future development.

Alternatively if you wish to fork this project and make me a pull request, please feel free. I appreciate submissions which include Junit tests, and will call you awesome if you include a UI test as well (not mandatory - I'm still ironing that bit out).

## License

This project is covered by the [MIT licence](https://opensource.org/licenses/MIT), you can read it [here](https://github.com/andrewbigger/epubtoolbox/blob/main/LICENSE.md).
