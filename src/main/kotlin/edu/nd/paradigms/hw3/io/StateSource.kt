package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.States
import java.io.IOException

/**
 * Describes a means of retrieving the input state name and population data.
 */
interface StateSource {
    /**
     * Returns a group of {@link State}s from a data source
     * @return a  {@link States} object
     */
    @Throws(IOException::class)
    fun getStates(): States
}