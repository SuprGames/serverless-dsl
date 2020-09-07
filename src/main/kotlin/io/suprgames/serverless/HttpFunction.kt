package io.suprgames.serverless

/**
 * API Gateway HTTP listener lambda annotation
 *
 * @property name
 *              The name of the listener, optional, if not provided (or empty) will use the Lambda class name
 * @property method
 *              The HTTP method that will be implemented
 * @property path
 *              The path where the lambda will listen
 * @property cors
 *              Boolean indicating if we allow all cors or not
 * @property authorizer
 *              Name of the authorizer to use
 */
annotation class HttpFunction(val name: String = "",
                              val method: HttpMethod = HttpMethod.GET,
                              val path: String,
                              val cors: Boolean = true,
                              val authorizer: String = "")