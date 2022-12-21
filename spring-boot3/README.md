# Spring Boot 3 미리 톺아보기

- Spring Boot 3.0.0-RC1 사용
  - https://spring.io/blog/2022/10/20/spring-boot-3-0-0-rc1-available-now
- GraalVM Native Image

### New Features 중 내가 생각하는 중요한 기능
- More Flexible Auto-configuration for Spring Data JDBC
- Log4j2 Enhancements
  - https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/features.html#features.logging.log4j2-extensions
- Logback Configuration in a Native Image
  - logback.xml and logback-spring.xml can now be used to configure logging in an application that is compiled to a GraalVM native image.
- Hibernate 6.1.4.Final
- trailing slash matching configuration option has been deprecated


### GraalVM Native Image  
- @Profile not supported

### GraalVm on Windows
- https://medium.com/graalvm/using-graalvm-and-native-image-on-windows-10-9954dc071311
- https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.2.0