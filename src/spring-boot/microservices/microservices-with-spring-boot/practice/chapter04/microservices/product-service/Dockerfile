# Sử dụng multi-stage build để giảm kích thước image cuối cùng
# Stage 1: Build stage
FROM eclipse-temurin:21-jre-ubi9-minimal as builder

WORKDIR extracted

COPY ./build/libs/*.jar app.jar

# Sử dụng lệnh layertools để tách các lớp của ứng dụng
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Final stage
FROM eclipse-temurin:21-jre-ubi9-minimal

WORKDIR application

# Sao chép các lớp đã tách từ stage trước
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/application/ ./

EXPOSE 8080

# Chạy ứng dụng bằng lớp JarLauncher
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]