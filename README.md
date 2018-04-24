# Profiler 
-----

Is a example of how to create a pluggable library using spring boot auto-configuration


### Configuration

You can add those properties in you application.properties, spring will guarantee that those properties, will be filled
in the correct object, and were they will be read and be used to configure the behavior of the library

```properties
# boolean value, sets if its going to log in sout (false will try to use Sl4j)
profiler.printToSout=true\
# boolean value, sets if its going to log the properties from the methods
profiler.logProperties=true
```