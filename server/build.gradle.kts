plugins {
    kotlin("jvm") version "1.9.25"
    application
}

application {
    mainClass.set("com.pLg.server.ApplicationKt")
}

dependencies {
    implementation(project(":shared"))
    implementation("io.ktor:ktor-server-core:2.3.9")
    implementation("io.ktor:ktor-server-netty:2.3.9")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.9")
}
