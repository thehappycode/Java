/*
Ý NGHĨA: Sử dụng CommandLineRunner.Nếu bạn cần thực hiện custom code trong lúc startup ứng dụng Spring Boot.
CÁH SỬ DỤNG: Chúng ta có 3 cách sử dụng CommandLineRunner.
    - Cách 1: Sử dụng trực tiếp với main class.
    - Cách 2: Sử dụng Spring Bean bên trong main class.
    - Cách 3: Sử dụng Spring Component bên ngoài main class.
    Sử dụng annotation @Oder để sắp xếp thứ tự thực hiện khi có nhiều CommandLineRunner trong ứng dụng.
*/

package com.thehappycode.commandlinerunner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
// TODO: Sử dụng CommandLineRunner triển khai trực tiếp trong main class
@Order(1)
public class CommandLineRunnerApplication implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {
        System.out.println("-> Start application");
		SpringApplication.run(CommandLineRunnerApplication.class, args);
        System.out.println("-> End application");
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        logger.info("-> Sử dụng CommandLineRunner triển khai trong main class");

        for (int idx = 0; idx < args.length; idx++) {
            logger.info("-> Argument" + idx + " = " +args[idx] );
        }
    }

    @Order(2)
    @Bean
    public CommandLineRunner commandLineRunnerWithSpringBean(){
        System.out.println();
        return args -> {
            logger.info("-> Sử dụng CommandLineRunner với Spring Bean trong main class");
            for (int idx = 0; idx < args.length; idx++) {
                logger.info("-> Argument" + idx + " = " +args[idx] );
            }
        };
    }
}
