package org.example.loggingframeworkap4.models;

public class ConsoleLogObserver implements LoggerObserver{


    @Override
    public void logMessage(String message) {
        System.out.println("Console: "+ message);
    }
}
