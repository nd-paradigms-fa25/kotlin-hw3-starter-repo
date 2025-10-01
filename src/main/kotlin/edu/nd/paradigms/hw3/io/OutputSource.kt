package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.Representation
import java.io.IOException

/**
 * Describes an output source to send the results of the apportionment to
 */
interface OutputSource {
    /**
     * Writes the results of the apportionment algorithm to some output source
     * @param representation {@link Representation} the results of an apportionment algorithm
     * @throws IOException if there is any error in writing to the output source
     */
    @Throws(IOException::class)
    fun writeToOutput(representation: Representation)
}