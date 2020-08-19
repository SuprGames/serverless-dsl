import com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.3.71"
    id("com.github.johnrengelman.shadow") version ("6.0.0")
}

group = "io.suprgames"

description = "Serverless-KDSL"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    transform(Log4j2PluginsCacheFileTransformer::class.java)
}

repositories {
    mavenCentral()
}

tasks.build {
    finalizedBy(getTasksByName("shadowJar", false))
}

dependencies {
    api(kotlin("stdlib-jdk8"))
}
