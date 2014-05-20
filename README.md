WSS
===

Exam repo


The project should be buildable using the standard *maven package* command.

To run the build project simply run *java -jar target/wallstreetsystems-exam-0.0.1-SNAPSHOT.jar*

The application takes from classpath file input-list.txt. Inside is a list of files to handle.
Each file will be handled separately by the Batch Job. Currently the files from input/ directory are inside of input-list.txt.
The output will be in the output/ directory. Currently the tests will also try to execute the job and therefore
after *mvn package* the output directory will contain already the results.

If you want to run some other file as an input, it has to be specified in the input-list.txt file. It is also possible to
specify custom list of input files using the *input-file-url* property. To check how to set this property, see the
Externalized configuration in the
[Spring Boot reference](http://docs.spring.io/spring-boot/docs/1.0.2.RELEASE/reference/htmlsingle/#boot-features-external-config).
For instance check the *Accessing command line properties* chapter there.
