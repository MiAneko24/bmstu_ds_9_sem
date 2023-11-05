import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.platform.jvm.JvmPlatforms
import org.springframework.boot.gradle.tasks.bundling.BootJar

//val springVersion: String by project
val ebeanVersion: String by project
val log4jVersion: String by project
val junitVersion: String by project
val postgresVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.9.20"
    kotlin("kapt") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.20"
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.20"
    id("io.ebean") version "13.23.2"

}

group = "ru.bmstu.mianeko.personsbackend"
version = "1.0-SNAPSHOT"
kotlin {
    jvmToolchain(17)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:$postgresVersion")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-core")

    implementation("io.ebean:ebean:$ebeanVersion")
    implementation("io.ebean:ebean-querybean:$ebeanVersion")
    implementation("io.ebean:querybean-generator:$ebeanVersion")
    implementation("io.ebean:ebean-spring-txn:$ebeanVersion")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
}

application {
    mainClass.set("ru.bmstu.mianeko.personbackend.PersonBackendApplicationKt")
}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "17"
//}

tasks.withType<BootJar> {
//    targetJavaVersion.set(JavaVersion.VERSION_17)
    archiveClassifier.set("all")
}
