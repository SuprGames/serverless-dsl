package io.suprgames.serverless

/**
 * WebSocketConnector annotation
 *
 * @property name
 *              The name of the connector, optional, if not provided (or empty) will use the Lambda class name
 * @property route
 *              The route where the WS connector will be listening to
 */
annotation class WebSocketConnector(val name: String = "", val route: String)