# spring-boot-ec2-aws
Spring boot deploy EC2 (AWS)


Dưới đây là hướng dẫn triển khai (deploy) dự án Spring Boot lên EC2 của AWS, từng bước một. 
Mình sẽ chia thành 2 phần: chuẩn bị môi trường và triển khai ứng dụng.

✅ Phần 1: Chuẩn bị môi trường EC2
1.1. Tạo EC2 instance
Vào AWS EC2 Console

Launch Instance https://console.aws.amazon.com/ec2/

Chọn Amazon Machine Image (AMI): Ubuntu 22.04 LTS (hoặc bản gần nhất)

Instance type: chọn t2.micro (miễn phí nếu dùng Free Tier)

Tạo hoặc chọn Key Pair (dùng .pem file để SSH)

Cấu hình Security Group:

Mở port 22 (SSH)

Mở port 8080 (ứng dụng Spring Boot mặc định)

(Tùy) Mở port 80/443 nếu dùng nginx hoặc HTTPS sau này

1.2. SSH vào EC2
```ssh -i "your-key.pem" ubuntu@your-ec2-public-ip```

✅ Phần 2: Cài đặt môi trường cho Spring Boot
2.1. Cài Java (nếu chưa có)
```sudo apt update
sudo apt install openjdk-17-jdk -y   # hoặc jdk11 nếu bạn cần
java -version```
2.2. Cài Git (nếu cần clone code)
```sudo apt install git -y```

✅ Phần 3: Build và deploy Spring Boot app
Option A: Build local rồi upload .jar lên EC2
Bước 1: Build .jar file
Tại local project:

```./mvnw clean package    # hoặc ./gradlew build```
File jar thường nằm ở target/myapp.jar

Bước 2: Upload file .jar lên EC2
```scp -i "your-key.pem" target/myapp.jar ubuntu@your-ec2-public-ip:/home/ubuntu/```
Bước 3: Chạy ứng dụng
```cd /home/ubuntu
java -jar myapp.jar```
Option B: Clone source code và build trực tiếp trên EC2 (ít phổ biến hơn)
```sudo apt install maven -y
git clone <repo-url>
cd <project-dir>
./mvnw clean package
java -jar target/myapp.jar```

✅ Phần 4: Chạy ứng dụng như service (khuyên dùng)
Tạo file service để app tự chạy lại khi EC2 restart:

```sudo nano /etc/systemd/system/myapp.service
Nội dung file:

```[Unit]
Description=My Spring Boot App
After=network.target

[Service]
User=ubuntu
ExecStart=/usr/bin/java -jar /home/ubuntu/myapp.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target```
Chạy service:
```sudo systemctl daemon-reexec
sudo systemctl daemon-reload
sudo systemctl start myapp
sudo systemctl enable myapp```

✅ Phần 5: Kiểm tra và xác nhận
Mở trình duyệt và truy cập: http://<your-ec2-public-ip>:8080

Nếu dùng domain, trỏ domain về EC2 IP (A record)

Cân nhắc dùng nginx reverse proxy + HTTPS (Let’s Encrypt) nếu cần production

✅ Phần 6: Một số công cụ hỗ trợ
Tên	Chức năng
tmux / screen	Giữ tiến trình chạy khi SSH disconnect
nginx	Reverse proxy + Load balancing
Certbot	Cấp HTTPS miễn phí
Docker	Nếu bạn muốn đóng gói Spring Boot trong container

✅ Gợi ý phương án tốt nhất
→ Build .jar ở local, rồi upload lên EC2, dùng systemd để chạy như service là phương án dễ triển khai, dễ maintain nhất nếu bạn mới làm quen với AWS.
