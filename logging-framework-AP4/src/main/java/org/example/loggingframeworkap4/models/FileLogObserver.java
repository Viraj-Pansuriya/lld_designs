package org.example.loggingframeworkap4.models;

public class FileLogObserver implements LoggerObserver{
    @Override
    public void logMessage(String message) {

        System.out.println("FileLogObserver : " + message);

    }
}
