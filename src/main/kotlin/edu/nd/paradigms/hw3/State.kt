package edu.nd.paradigms.hw3

/**
 * Represents a state
 * @param name The name of the state
 * @param population The number of residents in the state for apportionment purposes
 */
data class State(
    val name: String,
    val population: Int,
)
