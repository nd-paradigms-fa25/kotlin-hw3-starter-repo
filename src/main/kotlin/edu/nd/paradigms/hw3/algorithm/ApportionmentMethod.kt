package edu.nd.paradigms.hw3.algorithm

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.States

/**
 * Describes an algorithm for generating an apportionment, an allocation of seats in the US House of Representatives
 * to the states based on population (represented by {@link Representation}.
 */
interface ApportionmentMethod {
    /**
     * Gets the results of an apportionment for a group of states and a particular target number of representatives
     * @param states the group of {@link States} to allocate representatives to
     * @param seats the number of seats in Congress to allocate
     * @return {@link Representation} containing the allocation of representative to each state
     */
    fun getRepresentation(
        states: States,
        representatives: Int = edu.nd.paradigms.hw3.DEFAULT_REPRESENTATIVES
    ): Representation
}