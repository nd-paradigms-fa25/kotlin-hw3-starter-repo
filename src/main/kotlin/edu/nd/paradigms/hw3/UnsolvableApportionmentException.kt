package edu.nd.paradigms.hw3

/**
 * Thrown when a combination of state populations and target number of representatives makes it impossible
 * to use a particular Apportionment Algorithm.
 */
class UnsolvableApportionmentException(errorMessage: String)
    : IllegalStateException(errorMessage)
