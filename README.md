# Perpick
> [바로 가기: https://perpick.org](https://perpick.org)

<img src="https://github.com/user-attachments/assets/c68b3040-58c0-49b7-9bcf-2c0852025bf7" alt="홈 이미지" height="300"/>

## Tech Stack
### Develop
* Java 17
* SprinBoot 3.2.3
* Mysql 8.0.33
* Spring-Data-JPA

### Configuration Management
* Git
* Flyway
* Amazon ECR

## Architecture
![perpick-infra-pick drawio-6](https://github.com/user-attachments/assets/72581877-f61c-4e98-9ce9-beb27f7aa6b0)

## Configuration
* env
  1. 루트 폴더 내에 .env 파일 생성   
  2. application.yaml 내 정의된 환경 변수에 대해 값 설정   
  3. Intellij - Run > Edit Configurations
     - Environment variables 에 .env 파일 경로를 입력  

* profile (Intellij - **Run > Edit Configurations**)
  * 개발 환경
    * Active Profile: `local`
  * 운영 환경
    * Active Profile: `dev`
