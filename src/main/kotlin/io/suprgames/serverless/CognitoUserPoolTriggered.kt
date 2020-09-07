package io.suprgames.serverless

/**
 * Identifies and marks a lambda that will be triggered by a CognitoUserPool event.
 *
 * Important, you only can hook one Pool and it needs to exist already
 *
 * @property name
 *              The name of the listener, optional, if not provided (or empty) will use the Lambda class name
 * @property pool
 *              Pool name
 * @property trigger
 *              CognitoUserPool event that triggers the lambda
 * @property existing
 *              Indicates if the pool already exists or you created it in this project, by default "true"
 */
annotation class CognitoUserPoolTriggered(val name: String = "",
                                          val pool: String,
                                          val trigger: CognitoPoolTriggers,
                                          val existing: Boolean = true) {
}