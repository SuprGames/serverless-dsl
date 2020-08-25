package io.suprgames.serverless

//
//This annotation is to be placed in the HttpFunction annotated class if the Authorizer is an existing one
//
annotation class ExistingAuthorizerFunction(val arn: String,
                                            val type: AuthorizerFunctionType = AuthorizerFunctionType.TOKEN,
                                            val identitySources: Array<String> = [],
                                            val ttl: Long = 300,
                                            val identityValidationExpression: String = "",
                                            val managedExternally: Boolean = false)