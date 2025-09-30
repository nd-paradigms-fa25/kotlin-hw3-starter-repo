package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.algorithms.ApportionmentMethod
import edu.nd.paradigms.hw3.io.StateSource

class Apportionment(
    val stateSource: StateSource,
    val apportionmentMethod: ApportionmentMethod,
    val representatives: Int = DEFAULT_REPRESENTATIVES
) {
    val representation: Representation
        get() {
            val states = stateSource.getStates()
            if (states.isEmpty || states.totalPopulation <= 0) {
                throw UnsolvableApportionmentException(
                    "Cannot apportion representatives, as no input states have a positive population"
                )
            }
            return apportionmentMethod.getRepresentation(states, representatives)
        }
}