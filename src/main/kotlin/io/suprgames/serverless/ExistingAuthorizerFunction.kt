package io.suprgames.serverless

/**
 * Existing Authorizer Function
 *
 * Hook for an existing Authorizer function, to be used along with the class defining the lambda it authorises
 *
 * If you want to implement a new Authorizer function use [AuthorizerFunction]
 *
 * @property arn
 *              The arn of the existing Authorizer function
 * @property type
 *              The type of the Authorizer Function
 * @property identitySources
 *              List of elements that are used by the authorizer and if they are not present, then the authorizer
 *              automatically returns forbidden. The valid types are Header, Query String, Stage Variable, and Context.
 *              Examples:
 *                  A header called 'Authorization' -> "method.request.header.Authorization",
 *                  A Query string parameter called "code" -> "method.request.querystring.code"
 *                  The SourceIP from the context identity -> "context.identity.sourceIp"
 *
 * @property identityValidationExpression
 *              A validation expression for the incoming identity token, only calls the lambda if there is a match
 * @property ttl
 *              Valid cache time for the authorizer, if you do not want to cache use 0
 * @property managedExternally
 *              Indicates the lambda is managed externally, for example in a different AWS account
 */
annotation class ExistingAuthorizerFunction(val arn: String,
                                            val type: AuthorizerFunctionType = AuthorizerFunctionType.TOKEN,
                                            val identitySources: Array<String> = [],
                                            val ttl: Long = 300,
                                            val identityValidationExpression: String = "",
                                            val managedExternally: Boolean = false)