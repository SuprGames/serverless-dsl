# Serverless K-DSL

Serverless K-DSL is a small library that allows the generation of some of the Serverless Framework code that needs to be manually written.

The current version of Serverless K-DSL supports the generation of the following type of functions:

* HTTP Functions
* WebSocket Connectors
* Sqs Consumers
* EventBridge Listeners

The library provide a set of classes that can be used to annotate your own classes.

## Definitions and components examples

***Considerations***
The parameter `name` is present in all the components but it is optional, and if it is not provided, a name following the naming convention for the Lambdas will be created from the Handler class name, this is a lower case name split with dashes in the middle.

Ex:
`RegisterPlayerHandler` will generate `register-player-handler`
`Hello` will generate `hello`

We have ommited the name in most of the examples for simplicity.


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

Will generate the following entry in the Serverless.yml file when the generation task is executed
```
  register-player:
    handler: io.suprgames.player.RegisterPlayerHandler
    events:
      - http:
          path: player/register
          method: post
```

### WebSocket Connectors

```
package io.suprgames.game

@WebSocketConnector(route = "game-action")
class GameActionConnector : RequestHandler<Map<String, Any>, ApiGatewayResponse> {

// Code here

}
```

Will generate the following entry in the Serverless.yml file when the generation task is executed
```
  game-action-connector:
    handler: io.suprgames.game.GameActionConnector
    events:
      - websocket:
          route: game-action
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

Will generate the following entry in the Serverless.yml file when the generation task is executed
```
  game-queue-consumer:
    handler: io.suprgames.game.GameQueueConsumer
    events:
      - sqs:
          arn: ${self:provider.environment.sqsArn}
```

*Note that providing a reference to an environment variable that already exisist in the serverless-base.yml is supported, the only thing to consider is that, since you are writting ${bla bla bla} and this looks like Kotlin code you will need to skip the $ symbol how we did in the example.*

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

Will generate the following entry in the Serverless.yml file when the generation task is executed
```
  game-start-listener:
    handler: io.suprgames.games.GameStartListener
    events:
      - eventBridge:
          eventBus: ${self:provider.environment.eventBusArn}
          pattern:
            detail-type:
              - 'io.suprgames.games.GameStartRequestedEvent'
```

**Note 1** Providing a reference to an environment variable that already exisist in the serverless-base.yml is supported, the only thing to consider is that, since you are writting ${bla bla bla} and this looks like Kotlin code you will need to skip the $ symbol how we did in the example.

**Note 2** The event to listen is transformed as a detail-type pattern with the complete class qualified name. This is possible because when we publish the event in EventBridge we MAKE SURE that the Detail-Type attribute is filled with the Event qualified name

## Manual execution

// I need to fill this section, the functionality is implemented already

## Integration with Gradle

// I need to fill this section, the functionality is implemented already

## Integration with GithubActions

// I need to fill this section, the functionality is implemented already
