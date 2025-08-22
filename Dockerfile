# 1단계: Maven 빌드
FROM maven:3.9-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2단계: WAR 배포
FROM tomcat:9.0-jdk17
# 기본 Tomcat 웹앱 경로에 WAR 파일 복사 (기본 ROOT 앱으로 덮어쓰기)
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 9090
CMD ["catalina.sh", "run"]
