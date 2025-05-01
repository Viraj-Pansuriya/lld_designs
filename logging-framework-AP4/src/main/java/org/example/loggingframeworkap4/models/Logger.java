package org.example.loggingframeworkap4.models;

public class Logger {


    private static Logger loggerInstance;
    private static AbstractLogger chainOfLogger;
    private static LoggerSubject loggerSubject;

    private Logger() {}

    public static Logger getInstance(){
        synchronized(Logger.class){
                if(loggerInstance == null) {
                    loggerInstance = new Logger();
                    chainOfLogger = LogManager.getChainOfLogger();
                    loggerSubject = LogManager.getLoggerSubject();
                }
            }
        return loggerInstance;
    }

    private void logMessage(int level, String message){
        chainOfLogger.doLogChaining(level,message , loggerSubject);
    }

    public void info(String message){
        logMessage(1,message);
    }

    public void error(String message){
        logMessage(2,message);
    }

    public void debug(String message){
        logMessage(3,message);
    }

}
