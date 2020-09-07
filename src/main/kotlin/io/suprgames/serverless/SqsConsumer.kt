package io.suprgames.serverless

/**
 * SQS Consumer annotation
 *
 * @property name
 *              The name of the consumer, optional, if not provided (or empty) will use the Lambda class name
 * @property sqsArn
 *              The ARN of the SQS Queue we will consume
 */
annotation class SqsConsumer(val name: String = "", val sqsArn: String)