WSS
===

Exam repo


The project should be buildable using the standard maven package command.
To run the build project simply run java -jar target/wallstreetsystems-exam-0.0.1-SNAPSHOT.jar
The application takes from classpath file input-list.txt, there is list of files to handle. Each file will be handled separately by the Batch Job. Currently the files from input/* will be handled. The output will be in the output/ directory. Currently the tests will also try to execute the job and therefore after mvn package the output directory will contain already results.
If you want to run some other file as an input, it has to be specified in the input-list.txt file. It is also possible to specify custom list of input files using the input-file-url property. To check how to set this property, see - Externalized configuration in the Spring Boot reference. For instance check the Accessing command line properties chapter there.
