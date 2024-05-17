plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.Mascari4615"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // 추가, 프로젝트에서 사용하는 다양한 라이브버리 간의 버전 충돌 문제가 발생할 수 있는 것을 방지
    // 의존성 조합간 충돌 문제가 없는 검증된 버전 정보 조합을 제공
    implementation("org.springframework.boot:spring-boot-starter-parent:2.2.0.RELEASE")

    // Spring MVC를 사용한 REST 서비스를 개발하는데 사용
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")

    // Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    testImplementation("org.springframework.security:spring-security-test")

    // JUnit, Hamcrest, Mockito를 포함한 스프링 어플리케이션의 테스트 기능을 제공
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // runtimeOnly("com.h2database:h2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    // QR, https://github.com/zxing/zxing
    implementation("com.google.zxing:core:3.5.3")
    implementation("com.google.zxing:javase:3.5.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
