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
