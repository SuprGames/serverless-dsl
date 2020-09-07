package io.suprgames.serverless

/**
 * AuthorizerFunction
 *
 * Definition of a new Authorizer function, to be used annotating the class that implements the new Authorizer
 *
 * If you want to use as an Authorizer an existing function use [ExistingAuthorizerFunction]
 *
 * @property name
 *              The name of the Authorizer function
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
 */
annotation class AuthorizerFunction(val name: String,
                                    val type: AuthorizerFunctionType = AuthorizerFunctionType.TOKEN,
                                    val identitySources: Array<String> = [],
                                    val identityValidationExpression: String = "",
                                    val ttl: Long = 300)

