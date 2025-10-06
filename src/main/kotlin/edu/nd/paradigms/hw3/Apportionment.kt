package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.algorithm.ApportionmentMethod
import edu.nd.paradigms.hw3.io.StateSource

/**
 * This class processes the Apportionment by getting the list of states from the [StateSource] and calling the
 * [ApportionmentMethod] to get the [Representation].
 * @param stateSource the data source for state information
 * @param apportionmentMethod the apportionment algorithm to use
 * @param representatives the number of representatives to allocate
 */
class Apportionment(
    val stateSource: StateSource,
    val apportionmentMethod: ApportionmentMethod,
    val representatives: Int = DEFAULT_REPRESENTATIVES
) {
    /**
     * Get the results of the apportionment [Representation] object
     */
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