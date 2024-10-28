plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.20" // Замените на актуальную версию Kotlin
}

group = "org.korsnaike"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("com.charleskorn.kaml:kaml:0.61.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation(kotlin("reflect"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}