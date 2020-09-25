package io.suprgames.serverless

object AlterEgoRuntimes {
    const val NODEJS_12 = "nodejs12.x"
    const val NODEJS_10 = "nodejs10.x"
}

typealias AlterEgoRuntime = String

/**
 * The AlterEgo is an extra annotation that can be used to indicate that certain method/class handler implementation is
 * not the Kotlin one, and it is something specific in other language
 *
 * By default any method/class annotated with AlterEgo will try to match a Node12 function that is placed in the same
 * path the Kotlin file is BUT under src/main/js instead of src/main/kotlin
 *
 * The name of the auto-generated handler will be something like the following, so you need to respect this in the JS
 * handler created
 *
 *   If our AlterEgo Kotlin class is io.suprgames.cognito.Cognito then this alter ego handler will be hooked up:
 *      src/main/js/io/suprgames/cognito/Cognito.handler
 *
 *   If our AlterEgo Kotlin annotated method is io.suprgames.cognito.Cognito::methodName this will resolve:
 *      src/main/js/io/suprgames/cognito/Cognito.methodName
 *
 * If you have your own path, you can use that one instead
 *
 * The implementation of the method/class will be ignored by Serverless Framework and will be hook to the AlterEgo
 *
 * At the moment only Node12 and Node10 are supported
 *
 * AlterEgos doesn't require any kind of implementation, they are just purely place holders
 *
 */
annotation class AlterEgo(val jsProjectPath: String = "js-serverless", val runtime: AlterEgoRuntime = AlterEgoRuntimes.NODEJS_12, val handlerPath: String = "")

/**
 * Annotation used as a marker for the classes that hold methods, it is optional, not required at all and it is just
 * meant to indicate our IDE that the class is used
 */
annotation class AlterEgoHolder