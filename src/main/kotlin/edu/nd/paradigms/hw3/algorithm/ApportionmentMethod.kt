package edu.nd.paradigms.hw3.algorithm

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.States

interface ApportionmentMethod {
    fun getRepresentation(
        states: States,
        representatives: Int = edu.nd.paradigms.hw3.DEFAULT_REPRESENTATIVES
    ): Representation
}