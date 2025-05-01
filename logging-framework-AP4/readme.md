

## singleton pattern 

Logger : our main logger class. It is a singleton and can be accessed from anywhere in the code.
LogManager : it manages all loggers, we have one LogManager per application.

LogCategoryType : the type of category (INFO, ERROR, DEBUG etc)

## chain of responsibility pattern

AbstractLogger : abstract base class for all concrete loggers.
InfoLogger : logs info messages 
ErrorLogger : logs error messages 
DebugLogger : logs debug messages


## observer pattern

LoggerSubject : our main subject class. to which different observers will subscribe
LoggerObserver : an observer interface that logs messages

ConsoleLogObserver : logs messages to console
FileLogObserver : logs messages to file