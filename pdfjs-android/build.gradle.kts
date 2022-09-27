// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
//    id 'com.android.application' version '7.2.2' apply false
//    id 'com.android.library' version '7.2.2' apply false
//    id 'org.jetbrains.kotlin.android' version '1.7.10' apply false
}

//task clean (type: Delete) {
//    delete rootProject . buildDir
//}
tasks.create<Delete>("clean") {
    delete(
        fileTree(rootProject),
        fileTree("."),
        fileTree(buildDir)
    )
}
//task.register<Delete>("clean") {
//    delete(rootProject)
//    delete(".")
//    delete(buildDir)
//}