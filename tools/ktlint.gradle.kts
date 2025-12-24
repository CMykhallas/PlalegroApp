plugins {
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

ktlint {
    version.set("0.50.0")
    verbose.set(true)
    android.set(false)
    outputColorName.set("RED")
}
