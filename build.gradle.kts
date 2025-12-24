// build.gradle.kts na raiz do projeto

// Declaração de plugins globais (aplicados nos módulos quando necessário)
plugins {
    id("com.android.application") version "8.7.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.25" apply false
    id("com.google.dagger.hilt.android") version "2.52" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.25" apply false
}

// Configuração de repositórios globais
allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// Configuração de tarefas globais
tasks.register<Delete>("clean") {
    description = "Remove todos os arquivos de build gerados."
    group = "build"
    delete(rootProject.buildDir)
}

// Configuração extra para builds consistentes
gradle.startParameter.showStacktrace = ShowStacktrace.ALWAYS
gradle.startParameter.warningMode = WarningMode.All

// Definição de versões globais (pode ser usado nos módulos)
ext.apply {
    set("composeBomVersion", "2024.10.00")
    set("material3Version", "1.3.0")
    set("navigationComposeVersion", "2.8.2")
    set("hiltVersion", "2.52")
}
