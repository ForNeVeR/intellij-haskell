// SPDX-FileCopyrightText: 2026 haskeletor contributors <https://github.com/ForNeVeR/haskeletor>
//
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    id("scala")
    alias(libs.plugins.intellij.platform)
}

group = "intellij.haskell"
version = providers.gradleProperty("pluginVersion").get()

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation(libs.scala.library)

    implementation(libs.spray.json)
    implementation(libs.snakeyaml)
    implementation(libs.scaffeine)
    implementation(libs.directories)
    implementation(libs.fastparse)

    testImplementation(libs.scalatest)
    testImplementation(libs.scalatestplus.junit)

    intellijPlatform {
        intellijIdeaCommunity(providers.gradleProperty("platformVersion"))
        bundledPlugin("com.intellij.java")
        instrumentationTools()
        testFramework(TestFrameworkType.Platform)
    }
}

sourceSets {
    main {
        scala {
            srcDirs("src/main/scala", "gen")
        }
    }
    test {
        scala {
            srcDirs("src/test/scala")
        }
    }
}

intellijPlatform {
    pluginConfiguration {
        id = "intellij.haskell"
        name = "IntelliJ-Haskell"
        version = providers.gradleProperty("pluginVersion")
        ideaVersion {
            sinceBuild = providers.gradleProperty("pluginSinceBuild")
            untilBuild = provider { null }
        }
    }
}

tasks {
    withType<ScalaCompile> {
        scalaCompileOptions.additionalParameters = listOf(
            "-target:jvm-17", "-deprecation", "-feature", "-unchecked"
        )
    }
    withType<JavaCompile> {
        options.release.set(17)
    }
    test {
        useJUnit()
        workingDir = project.projectDir
    }
}
