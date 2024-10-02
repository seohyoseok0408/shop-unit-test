plugins {
    id("java")
}

group = "edu.sm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Lombok 의존성 추가
    implementation("org.projectlombok:lombok:1.18.28")  // 최신 버전 확인 후 사용
    annotationProcessor("org.projectlombok:lombok:1.18.28") // Lombok 애너테이션 처리기 추가

    // MySQL Connector 의존성 추가 (Kotlin DSL 형식)
    implementation("mysql:mysql-connector-java:8.0.31")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}