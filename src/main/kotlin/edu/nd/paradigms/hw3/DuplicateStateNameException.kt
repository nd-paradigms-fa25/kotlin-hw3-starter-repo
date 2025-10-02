package edu.nd.paradigms.hw3

/**
 * Thrown when a state with a duplicate name is added to a {@link States} collection
 */
class DuplicateStateNameException(
    val duplicateState: State,
    val states: States
)
    : IllegalStateException("""
        Attempted to add duplicate state name ${duplicateState.name} to States set:
            $states
        """.trimIndent()
    )