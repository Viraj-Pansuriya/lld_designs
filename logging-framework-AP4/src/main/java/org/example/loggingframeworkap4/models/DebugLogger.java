package org.example.loggingframeworkap4.models;

import org.example.loggingframeworkap4.utils.LoggerUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level) {
        super(level);
    }

    @Override
    public void logMessage(int level, String message , LoggerSubject loggerSubject) {
        message = LoggerUtils.generateLogMessage(message , super.applicationName , this.getClass().getName() , LogCategoryType.DEBUG);
        loggerSubject.notifyObservers(LogCategoryType.DEBUG , message);
    }
}
