package io.suprgames.serverless

/**
 * The Minion Function it is simply a Lambda that is not listening to any event, they are just there to be invoked by
 * another function that it is in hurry and need to answer but doesn't have the time to process.
 *
 * Some kind of "Step Function" but not that fancy
 */
annotation class MinionFunction(val name: String = "")