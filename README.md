# The splash

a spectral hash code

this library is the current reference implementation for the splash. Splash stands for the spectra hash code and is an unique identifier independent of acquisition or processing. It basically tries to ensure that you can easily tell if two spectra are identical, similar or very different. Based on several criteria.

You can access it as a REST service, at http://splash.fiehnlab.ucdavis.edu

Or use one any of the available implementations, which should have been validated against the REST validation service.

# usage

## java api:
To generate a new splash, please utilize the following:


```
    Splash splash = SplashFactory.create();
    Spectrum spectrum = new SpectrumImpl(Arrays.asList(new Ion(100.0, 50)), SpectraType.MS);
    String splash = splash.splashIt(spectrum);
```

Alternatively, you can also utilize the following code, to directly splash a spectra, if it's accessible as a string representation.

```
    String splash = SplashUtils.splash("10:123.12 12:123.11 13:22 14:212",SpectraType.MS);
```

We are also providing an easy way to connect a listener to the splashing algorithm, so that you can inspect the different blocks, before they are hashed. This can be done with directly adding a SplasListener to your Splash instance or alternativly using the util like this

```
    String splash = SplashUtils.splash("10:123.12 12:123.11 13:22 14:212",SpectraType.MS,new SplashListener(){
            @Override
            public void eventReceived(SplashingEvent e) {
            }

            @Override
            public void complete(Spectrum spectrum, String splash) {
                
            }
        });

```

## scala api:

TODO

## C# api:

To generate a splash you need to add a reference to the Splash.dll to your project then add the following 'using' statement:
```
using NSSplash;
```

To get the hash for a given spectrum you can call:
```
Splash splasher = new Splash();
string hash = splasher.splashIt(new Spectrum("5.0000001:1.0 5.0000005:0.5 10.02395773287:2.0 11.234568:.10", SpectrumType.MS));
```

## C++ api:

TODO

## R api:

First install the R package splashR from this repository.

To generate a splash you need to load the R library and call `getSplash()`
on a dataframe or matrix with two numeric columns containing m/z and intensity:

```
    ## The caffeine example from the paper
    library(splashR)

    ## The caffeine example from the paper
    caffeine <- cbind(mz=c(138.0641, 195.0815),
                      intensity=c(71.59, 261.7))
    getSplash(caffeine)

```

## Python api:

TODO

## rest service:

the documentation for the REST service, is available as a dedicated index page, once you start the REST server. If you like to use the official webservice, you can find it at http://splash.fiehnlab.ucdavis.edu


## validation tool:

As part of the splash specificitation, we are providing a simple validation tool, in the validation folder.

to run this tool please build the distribution and afterwards run

```
 java -jar validation-VERSION.jar
```

this will present you with the usage for this tool. 

### validation example

An example to validate a file against the reference implementation and saving the output to a file would be

```
java -jar validation/target/validation-1.3-SNAPSHOT.jar -c -s 2 -t ms ./base-dataset/spectra/notsplashed/test-set-v1.csv base-dataset/spectra/test-set-with-splash-v1.csv
```

The specified flags in the example mean:

* k = which column is your generated splash
* o = which column is your optional origin
* s = which column is your spectra
* t = what is your spectra type
* T = what is your seperator, ',' in this case
* X = debug messages

The input and output files are specified as arguments.

* input.csv your input file
* output.csv your output file

The format for a spectra must be:

```
ion:intensity ion:intensity
```

you can also use the same tool, to easily splash a file of spectra, using the reference algorithm. To only report duplicates, sort the output, etc.

# building

## Java/Scala

```
mvn clean install
```

will build your project, run all the tests and you can find the build jar files, in the target directories of the project.

## Python

TODO

## C++

TODO

## C# 

### Requirements:
    - Mono MDK (Download from: http://www.mono-project.com/download/)
    - (Optional) MonoDevelop IDE (Download from: http://www.monodevelop.com/download/)
### Building:
The easiest way to build the project is using MonoDevelop, open the IDE and load the solution (<download folder>/csharp/splash.sln).
On the 'Solution Explorer' (left panel), right click 'splash' and select 'Build splash', if there are no errors you will see a 'Build successful' message.

#Contributing

if you like to contribute to this project, please feel free to contact me.
