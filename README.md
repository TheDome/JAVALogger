# JAVALogger

A simple logging Framework written in Java.

# Usage  

To use the Framework, let your class extend the LoggableObject class. In this class are several methods to print logs. The logs are prefixed by the LogLevel from the message and are colored.  
An example could be: ```[ INFO ] Starting...```  

# Implementation

In the class that extends the LoggableObject class are some new methods. These are ```INFO, DEBUG, PANIC, TRACE, ERROR```
There will send the string to log to the Framework.

There is also the option to send objects. This looks like:
``` java
DEBUG("This is my {} debug string!", "first");
```
The output will look like: ```[ DEBUG ] This is my first debug string```
