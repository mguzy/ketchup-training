import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "pl.mateuszguzy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.nd4j:nd4j-native-platform:0.9.1")
    compile("joda-time:joda-time:2.10.1")
    compile("no.tornado:tornadofx:1.7.18")
    compile("org.processing:core:3.3.7")
    compile("junit:junit:4.12")
    compile("org.nd4j:nd4j-api:0.9.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}