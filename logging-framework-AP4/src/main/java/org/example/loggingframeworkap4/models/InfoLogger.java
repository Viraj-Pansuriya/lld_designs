package org.example.loggingframeworkap4.models;

import org.example.loggingframeworkap4.utils.LoggerUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


public class InfoLogger extends AbstractLogger {
    public InfoLogger(int level) {
        super(level);
    }

    @Override
    public void logMessage(int level, String message , LoggerSubject loggerSubject) {
        message = LoggerUtils.generateLogMessage(message , super.applicationName , this.getClass().getName() , LogCategoryType.INFO);
        loggerSubject.notifyObservers(LogCategoryType.INFO, message);
    }
}
