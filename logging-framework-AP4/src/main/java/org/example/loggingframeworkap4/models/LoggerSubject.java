package org.example.loggingframeworkap4.models;

import java.util.*;

public class LoggerSubject {

    private final Map<LogCategoryType, List<LoggerObserver>> categoryWiseLoggerObservers = new EnumMap<>(LogCategoryType.class);

    // add
    public void addObserver(LogCategoryType type, LoggerObserver observer) {
        categoryWiseLoggerObservers.putIfAbsent(type, new ArrayList<>());
        categoryWiseLoggerObservers.get(type).add(observer);
    }

    public void notifyObservers(LogCategoryType type, String message) {
        if (categoryWiseLoggerObservers.containsKey(type)) {
            for(LoggerObserver observer : categoryWiseLoggerObservers.get(type)) {
                observer.logMessage(message);
            }
        }
    }


}
