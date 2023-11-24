
plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("junit:junit:4.13.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation ("org.xerial:sqlite-jdbc:3.44.0.0")
    implementation ("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    runtimeOnly("org.slf4j:slf4j-nop:2.0.9")
}
tasks.test {
    useJUnitPlatform()
}