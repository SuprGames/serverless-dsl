# Serverless K-DSL

Serverless K-DSL is a small library that allows the generation of some of the Serverless Framework code that needs to be manually written.

The current version of Serverless K-DSL supports the generation of the following type of functions:

* HTTP Functions
* WebSocket Connectors
* Sqs Consumers
* EventBridge Listeners
* Authorizer Lambda Functions:
  * Token and Request functions
  * ExistingAuthorizerFunctions

The library provides a set of classes that can be used to annotate your own classes.

## Definitions and components examples

***Considerations***
The parameter `name` is present in all the components, but it is optional, and if it is not provided, a name following the naming convention for the Lambdas will be created from the Handler class name, this is a lower case name split with dashes in the middle.

Ex:
`RegisterPlayerHandler` will generate `register-player-handler`
`Hello` will generate `hello`

We will omit the name in most of the examples for simplicity.


### HTTP Functions:
Serverless K-DSL supports the generation of HTTP functions in Serverless, the methods GET, POST, PUT and DELETE are supported. 
*If no method is provided GET will be used*

```
package io.suprgames.player

@HttpFunction(name = "register-player", method = HttpMethod.POST, path = "player/register")
class RegisterPlayerHandler : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```

### WebSocket Connectors
```
package io.suprgames.game

@WebSocketConnector(route = "game-action")
class GameActionConnector : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```

The common WebSocket routes ($connect, $disconnect and $default) are provided in a convinient companion object in WebSocketRoutes:

`@WebSocketConnector(route = WebSocketRoutes.CONNECT)`

### Sqs Consumer
```
package io.suprgames.game

@SqsConsumer(sqsArn = "\${self:provider.environment.sqsArn}")
class GameQueueConsumer : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```

*Note that providing a reference to an environment variable that already exisis in the serverless-base.yml is supported, the only thing to consider is that, since you are writting ${bla bla bla} and this looks like Kotlin code you will need to skip the $ symbol how we did in the example.*

### Event Listeners
We support the connection to EventBridge to Listen to events depending on their type, so we can implement a typical Event-Driven communication in our systems.

```
package io.suprgames.game

@EventBridgeListener(eventBusArn = "\${self:provider.environment.eventBusArn}"
                     eventToListen = GameStartRequestedEvent::class)
class GameStartListener  : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```

**Note 1** Providing a reference to an environment variable that already exisist in the serverless-base.yml is supported, the only thing to consider is that, since you are writting ${bla bla bla} and this looks like Kotlin code you will need to skip the $ symbol how we did in the example.

**Note 2** The event to listen is transformed as a detail-type pattern with the complete class qualified name. This is possible because when we publish the event in EventBridge we MAKE SURE that the Detail-Type attribute is filled with the Event qualified name

### Lambda Authorizers
Depending on having already in place the Lambda Authorizer in the system or we are generating it we will define it in a different way.

#### When the Lambda Authorizer doesn't already exist:

1) Create the Lambda Authorizer function and annotate it with the `AuthorizerFunction` annotation
```
package io.suprgames.auth

const val PLAYER_LOGGED_AUTHORIZATION = "player-logged-auth"

@AuthorizerFunction(name = PLAYER_LOGGED_AUTHORIZATION, ttl = 300, type = AuthorizerFunctionType.REQUEST, identitySources = ["method.request.header.Authorization"])
class PlayerLoggedAuthorization : RequestHandler<Map<String, Any>, Map<String, Any>> {

// Code here

}
``` 
**Note** The `name` field is **mandatory** for authorization functions since it needs to be referenced from the HTTP Function

2) Reference the Authorizer from the HTTP Function that requires the Authorization

```
package io.suprgames.games

@HttpFunction(name = "register-player", method = HttpMethod.GET, path = "player/listGames", authorizer = PLAYER_LOGGED_AUTHORIZATION")
class ListPlayerGames : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```
**Note** The most recommended way to write the reference is by using a constant, that way we are sure we about do not have typos

#### When the lambda exists already:

We need to add the annotation ExistingAuthorizerFunction to the class where we have the HTTPFunction.
```
package io.suprgames.games

@ExistingAuthorizerFunction(arn = "xxx:xxx:Lambda-Name", ttl = 300, identitySources = ["method.request.header.Authorization"], type = AuthorizerFunctionType.REQUEST)
@HttpFunction(name = "register-player", method = HttpMethod.GET, path = "player/listGames")
class ListPlayerGames : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```
**Note** The authorizer field is not required in the HttpFunction annotationbecause we are annotating the class explicitly  

