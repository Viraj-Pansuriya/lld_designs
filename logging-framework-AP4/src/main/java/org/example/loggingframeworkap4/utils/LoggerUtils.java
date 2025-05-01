package org.example.loggingframeworkap4.utils;

import lombok.experimental.UtilityClass;
import org.example.loggingframeworkap4.models.LogCategoryType;

import java.time.LocalDateTime;

@UtilityClass
public class LoggerUtils {


    public static String generateLogMessage(String message , String applicationName , String className , LogCategoryType logCategoryType) {
        String threadName = Thread.currentThread().getName();

        return LocalDateTime.now() + " "  + logCategoryType.name() + " " + Thread.currentThread().threadId()
                + " --- [" + applicationName + "] [" + threadName + "] " + className + " : " + message;
    }
}
