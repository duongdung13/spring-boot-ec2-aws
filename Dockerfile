# Dùng image Java chính thức
FROM eclipse-temurin:21-jdk-alpine

# Tạo thư mục chứa app
WORKDIR /app

# Copy file JAR vào container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose cổng 8080
EXPOSE 8080

# Lệnh khởi chạy app
ENTRYPOINT ["java", "-jar", "app.jar"]
