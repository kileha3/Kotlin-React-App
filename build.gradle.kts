plugins {
    id ("org.jetbrains.kotlin.js") version "1.4.30"
}

val group = "co.tz.furahitech"
val version = "1.0-0"

repositories {
    jcenter()
    mavenCentral()
    maven ("https://dl.bintray.com/kotlin/kotlin-js-wrappers")

}

dependencies {
    val kotlinJsVersion = "pre.145-kotlin-1.4.30"
    testImplementation ("org.jetbrains.kotlin:kotlin-test-js")
    implementation ("org.jetbrains","kotlin-react","17.0.1-$kotlinJsVersion")
    implementation ("org.jetbrains","kotlin-react-router-dom","5.2.0-$kotlinJsVersion")
    implementation ("org.jetbrains","kotlin-react-dom","17.0.1-$kotlinJsVersion")
    implementation ("org.jetbrains","kotlin-styled","5.2.1-$kotlinJsVersion")
    implementation ("org.jetbrains","kotlin-redux","4.0.5-$kotlinJsVersion")
    implementation ("org.jetbrains","kotlin-react-redux","7.2.2-$kotlinJsVersion")
    implementation ("com.ccfraser.muirwik","muirwik-components","0.6.7")
    implementation(npm("sql.js", "^1.4.0"))
    implementation(npm("fs", "^0.0.2"))
    implementation ("io.ktor:ktor-client-core:1.5.2")
}

kotlin {
    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}