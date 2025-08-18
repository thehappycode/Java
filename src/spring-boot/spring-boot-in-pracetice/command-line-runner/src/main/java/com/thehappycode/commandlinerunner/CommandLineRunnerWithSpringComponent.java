package com.thehappycode.commandlinerunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class CommandLineRunnerWithSpringComponent implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public void run(String... args) throws Exception {
        System.out.println();

            logger.info("-> Sử dụng CommandLineRunner với Spring Component bên ngoài main class");
            for (int idx = 0; idx < args.length; idx++) {
                logger.info("-> Argument" + idx + " = " +args[idx] );
            }

    }
}
