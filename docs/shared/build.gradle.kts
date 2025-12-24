plugins {
    kotlin("multiplatform") version "1.9.25"
    kotlin("plugin.serialization") version "1.9.25"
}

kotlin {
    jvm() // Android e JVM backend
    js(IR) {
        browser()
        nodejs()
        binaries.library()
    }
    // opcional: targets nativos
    // ios()
    // macosX64()
    // linuxX64()

    sourceSets {
        val kotlinSerializationVersion = "1.7.3"
        val coroutinesVersion = "1.8.1"
        val ktorVersion = "2.3.9"

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-java:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val jvmTest by getting

        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val jsTest by getting
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xjsr305=strict",
        "-Xexplicit-api=strict"
    )
}

publishing {
    publications {
        // Configure if you plan to publish to a Maven repo.
    }
}
