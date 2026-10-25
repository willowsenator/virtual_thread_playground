plugins {
    id("java")
}

group = "com.willowsenator.spring"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_24
    targetCompatibility = JavaVersion.VERSION_24
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("ch.qos.logback:logback-classic:1.6.5")
}

tasks.test {
    useJUnitPlatform()
}