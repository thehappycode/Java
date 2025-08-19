/*
## Logging framework
CẤU TRÚC CỦA LOGGING:
    - Date and time: Ngày và giờ của logging.
    - Log level: Mức độ của logging. Nó gồm các giá trị FATAL, ERROR, WARN, INFO, DEBUG, và TRACE.
    - Process ID: Process ID của ứng dụng.
    - Separator: Separator là (---) để xác định bắt dầu message thật của log.
    - Thread name: Tên Thread thực hiện logging. Một ứng dụng Spring Boot chứa nhiều threads.
    - Logger name: Tên viết tắt của class.
    - Message: Message thật của log.

GHI LOG RA FILE
Trong Spring Boot cho bạn cấu hnfh để ghi ra file theo:
    - logging.file.name: Sinh file theo tên cấu hình. Nếu truyền cả đường dẫn vào cấu hình thì sẽ tạo file theo đường dẫn đó.
    - loging.file.path. Sinh file có tên mặc định spring.log với đường dẫn cấu hình.

## Log4j2
- Thêm dependency vào file porm.xml
- Tạo file cấu hình resources/log4j2.xml
- Khi sử dụng thì thêm Logger, LoggerFactory trong thư viện org.slf4j.*s
*/


package com.thehappycode.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingApplication {

    private static Logger logger = LoggerFactory.getLogger(LoggingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
        logger.info("-> LoggingApplication started successfully with Log4j2 configuration");
	}

}
