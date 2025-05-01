package org.example.loggingframeworkap4.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogManager {
    public static AbstractLogger getChainOfLogger(){
        AbstractLogger infoLogger = new InfoLogger(1);
        AbstractLogger errorLogger = new ErrorLogger(2);
        AbstractLogger debugLogger = new DebugLogger(3);

        infoLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        return infoLogger;
    }

    public static LoggerSubject getLoggerSubject() {

        LoggerObserver consoleLogger = new ConsoleLogObserver();
        LoggerObserver fileLogger = new FileLogObserver();

        LoggerSubject loggerSubject = new LoggerSubject();
        loggerSubject.addObserver(LogCategoryType.INFO, consoleLogger);
        loggerSubject.addObserver(LogCategoryType.INFO, fileLogger);
        loggerSubject.addObserver(LogCategoryType.ERROR, consoleLogger);
        loggerSubject.addObserver(LogCategoryType.DEBUG, consoleLogger);
        return loggerSubject;
    }
}
