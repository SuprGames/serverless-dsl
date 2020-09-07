package io.suprgames.serverless

import kotlin.reflect.KClass

/**
 * Event bridge listener lambda annotation.
 *
 * @property name
 *              The name of the listener, optional, if not provided (or empty) will use the Lambda class name
 * @property eventBusArn
 *              The ARN of the existing EventBridge bus we will hook to
 * @property eventToListen
 *              The class of the event to listen to, it will be used to define the listening rule
 */
annotation class EventBridgeListener(val name: String = "", val eventBusArn: String, val eventToListen: KClass<*>)