package com.thehappycode.configurations;

public class ConfigDataFile {

    public  static void run() {
        String text = """
                TODO: Mục này hướng dẫn sử dụng các cấu hình từ file trong /resources.
                File sử dụng với hai định dạng: *.properties và *.yml
                
                Build source -> chạy lệnh mvn clean install
                Listing 2.5 Executing the application JAR file
                java -jar config-data-file-0.0.1-SNAPSHOT.jar
                
                Listing 2.6 Running a Spring Boot application with a different configuration file name
                java -jar config-data-file-0.0.1-SNAPSHOT.jar --spring.config.name=sbip
                
                Listing 2.7 Executing a Spring Boot application with spring.config.location property
                java -jar target\\config-data-file-0.0.1-SNAPSHOT.jar --spring.config.location=C:\\sbip\\repo\\ch02\\config-data-file\\data\\sbip.yml
                
                Listing 2.8 Starting a Spring Boot application with an optional property file
                java -jar target\\config-data-file-0.0.1-SNAPSHOT.jar --spring.config.location=optional:C:\\sbip\\repo\\ch02\\config-data-file\\data\\sbip1.yml
                """;

        System.out.println(text);
    }
}
