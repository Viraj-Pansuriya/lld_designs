package org.example.loggingframeworkap4.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

public abstract class AbstractLogger {


    protected String applicationName;
    protected int level;
    private AbstractLogger nextLogger;

    protected AbstractLogger(int level){
        this.level = level;
        this.applicationName = "logging-framework-AP4";
    }
    protected void setNextLogger(AbstractLogger logger) {
        this.nextLogger = logger;
    }


    protected void doLogChaining(int level, String message , LoggerSubject loggerSubject){
        if(level >= this.level){
            logMessage(level , message , loggerSubject);
        }
        if(nextLogger != null){
            nextLogger.doLogChaining(level,message , loggerSubject);
        }
    }

    public abstract void logMessage(int level , String message, LoggerSubject loggerSubject);

}
