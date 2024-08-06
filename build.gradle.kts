plugins {
    val springBootVersion = "3.3.2"
    val springBootDependencyManagementVersion = "1.1.6"
    val kotlinVersion = "1.9.24"

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springBootDependencyManagementVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

group = "kr.movements"
version = "0_0_1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
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
    annotationProcessor("org.projectlombok:lombok")

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")
//    runtimeOnly("com.h2database:h2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("io.jsonwebtoken:jjwt-api:${DependencyVersion.JJWT}")
    implementation("io.jsonwebtoken:jjwt-impl:${DependencyVersion.JJWT}")
    implementation("io.jsonwebtoken:jjwt-jackson:${DependencyVersion.JJWT}")
    implementation("com.google.code.gson:gson:${DependencyVersion.GSON}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${DependencyVersion.SPRINGDOC}")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.security:spring-security-test")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

object DependencyVersion {
    const val JJWT = "0.12.6"
    const val GSON = "2.11.0"
    const val SPRINGDOC = "2.6.0"
}
