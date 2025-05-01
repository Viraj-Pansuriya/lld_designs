package org.example.loggingframeworkap4;

import org.example.loggingframeworkap4.models.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingFrameworkAp4Application {

    public static void main(String[] args) {
        SpringApplication.run(LoggingFrameworkAp4Application.class, args);

        Logger logger = Logger.getInstance();
        logger.error("Hello World");



    }

}
