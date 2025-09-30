package edu.nd.paradigms.hw3.formats

import edu.nd.paradigms.hw3.Representation

/**
 * Describes a particular way to display a representation as a text {@link String}
 */
interface RepresentationFormat {
    /**
     * Generates a formatted string to display the results of an apportionment
     * @param representation {@link Representation} - the results of an apportionment
     * @return a formatted {@link String}
     */
    fun getFormattedString(representation: Representation): String
}