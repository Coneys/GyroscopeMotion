import Versions.android_arch_comp
import Versions.android_support

object Versions {
    const val kotlin = "1.2.50"
    const val android_support = "1.0.0-alpha1"
    const val android_gradle_plugin = "3.3.1-alpha03"
    const val android_arch_comp = "2.0.0-alpha1"
    const val navigation = "1.0.0-alpha01"
    const val ktx = "1.0.0-alpha1"
    const val design = "1.0.0-alpha1"
    const val anko = "0.10.5"
    const val junit = "4.12"
    const val espresso = "3.0.2"
    const val rx_kotlin = "2.2.0"
    const val rx_android = "2.0.1"
    const val rx_binding = "2.1.0"
    const val koin = "1.0.0-beta-3"
    const val permission = "0.9.5"
    const val moshi = "1.6.0"
    const val retrofit = "2.4.0"
    const val courutines = "0.23.3"
    const val courutines_retrofit_adapter = "1.0.0"
    const val okhttp = "3.10.0"
    const val expandable_recycler_view = "3.0.0-RC1"


}

object Dependencies {
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val android_support_appcompat = "androidx.appcompat:appcompat:${Versions.android_support}"
    val android_support_design = "com.google.android.material:material:${Versions.design}"
    val android_support_recycler_view = "androidx.recyclerview:recyclerview:${Versions.android_support}"
    val card_view = "androidx.cardview:cardview:${Versions.android_support}"


    val ktx = "androidx.core:core-ktx:${Versions.ktx}"


    val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rx_kotlin}"
    val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"

    val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    val room_rx_java = "androidx.room:room-rxjava2:$android_arch_comp"
    val room = "androidx.room:room-common:$android_arch_comp"
    val room_runtime = "androidx.room:room-runtime:$android_arch_comp"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.0"


}

object KoinDependencies {
    val koin = "org.koin:koin-android:${Versions.koin}"
    val koin_android_scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koin_view_model = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object NetworkDependencies {
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:${Versions.courutines_retrofit_adapter}"
    val retrofit_moshi_converter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
}


object TestDependencies {
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val espresso_contrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso}"
    val espresso_intents = "com.android.support.test.espresso:espresso-intents:${Versions.espresso}"
    val junit = "junit:junit:${Versions.junit}"
    val koin = "org.koin:koin-test:${Versions.koin}"
    val hamcrest = "org.hamcrest:hamcrest-junit:2.0.0.0"

}

object Plugins {
    val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
}

object CoroutinesDependencies {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.courutines}"
}

object KaptDependencies {
    val room = "androidx.room:room-compiler:$android_arch_comp"
}
object AnnotationDependencies{
    val base = "androidx.annotation:annotation:$android_support"
}