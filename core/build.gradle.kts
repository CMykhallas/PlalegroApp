plugins {
    // Módulo puro Kotlin (sem Android)
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("java")
    id("jacoco")
plugins {
    id("com.github.triplet.play") version "3.9.1"
}

play {
    serviceAccountCredentials.set(file("play-credentials.json"))
    track.set("internal") // pode ser internal, beta, production
    defaultToAppBundles.set(true) // publica AAB por padrão
}

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    // Testes com Kotlin e JUnit 5
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // gera relatório após testes
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }
}
