package io.suprgames.serverless

annotation class AuthorizerFunction(val name: String,
                                    val type: AuthorizerFunctionType = AuthorizerFunctionType.TOKEN,
                                    val identitySources: Array<String> = [],
                                    val identityValidationExpression: String = "",
                                    val ttl: Long = 300)

