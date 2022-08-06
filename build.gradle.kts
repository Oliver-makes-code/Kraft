import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "olivermakesco.de"
version = "0.1.0"

repositories {
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.ktor:ktor-network:2.0.3")

    implementation("io.github.kgpu:kgpu:0.1.0-SNAPSHOT")
    implementation("io.github.kgpu:kcgmath:0.1.0-SNAPSHOT")
    implementation("io.github.kgpu:kshader:0.1.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

application {
    mainClass.set("MainKt")
}
