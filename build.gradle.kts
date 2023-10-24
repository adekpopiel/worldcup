plugins {
    id("java")
}

group = "com.adekpopiel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}