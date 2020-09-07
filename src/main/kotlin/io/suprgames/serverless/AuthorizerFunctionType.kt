package io.suprgames.serverless

/**
 * Types for the Authorizer Functions
 *  TOKEN:
 *      The authorizer checks the validity of a given JWT token
 *
 *  REQUEST:
 *      The authorizer contains custom logic that depends in the request parameters
 *
 *  COGNITO_USER_POOLS:
 *      The authorizer function will use a COGNITO_USER_POOL, requires to provide the ARN of the pool
 */
enum class AuthorizerFunctionType {
    TOKEN,
    REQUEST,
    COGNITO_USER_POOLS
}