# QR Code Generator

A Spring Boot application that generates QR codes and stores them in AWS S3. This project demonstrates the integration of Google's ZXing library for QR code generation and AWS S3 for storage.

## Prerequisites
- Java 21 JDK
- Maven
- Docker
- AWS Account with S3 access
-AWS CLI configured with appropriate credentials

## Environment Variables
Create a .env file in the project root with the following variables:
```
AWS_ACCESS_KEY_ID=your_access_key
AWS_SECRET_ACCESS_KEY=your_secret_key
AWS_REGION=your_region
AWS_BUCKET_NAME=your_bucket_name
```

##Running the Application
Build the project:
```
mvn clean package
```
Run the application:]
```
mvn spring-boot:run
```

## Docker Deployment
Build the Docker image:
```
docker build -t qrcode-generator:X.X . 
```
Run the container:
```
docker run --env-file .env -p 8080:8080 qrcode-generator:X.X 
```
## AWS S3 Configuration
1. Create an S3 bucket in your AWS account
2. Update the AWS_BUCKET_NAME in your .env file or Docker run command
3. Ensure your AWS credentials have appropriate permissions to access the S3 bucket

