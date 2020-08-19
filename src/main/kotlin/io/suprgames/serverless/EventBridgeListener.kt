package io.suprgames.serverless

import kotlin.reflect.KClass

annotation class EventBridgeListener(val name: String = "", val eventBusArn: String, val eventToListen: KClass<*>)