package io.suprgames.serverless

/**
 * All the available Cognito Pool Triggers
 * More information about them:
 *
 * https://docs.aws.amazon.com/cognito/latest/developerguide/cognito-user-identity-pools-working-with-aws-lambda-triggers.html
 *
 * https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-cognito-userpool-lambdaconfig.html
 *
 * In short the most interesting ones are:
 *  * PostAuthentication: Trigger when the user has been signed in.
 *  * PostConfirmation: Triggers when a new user is confirmed.
 */
enum class CognitoPoolTriggers {
    CreateAuthChallenge,
    CustomMessage,
    DefineAuthChallenge,
    PostAuthentication,
    PostConfirmation,
    PreAuthentication,
    PreSignUp,
    PreTokenGeneration,
    UserMigration,
    VerifyAuthChallengeResponse
}