package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation

/**
 * Generates the text for outputting the result of an apportionment ({@link Representation}) to a CSV file
 * sorted alphabetically.
 */
class CSVOutputFormat: RepresentationFormat {
    /**
     * Generates a formatted CSV string from the given Representation for writing to a file.
     *
     * @param representation the Representation containing the states and their apportioned number of seats
     * @return a formatted CSV string with states and their corresponding seat allocation
     */
    override fun getFormattedString(representation: Representation): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("State,Representation\n")
        val sortedStates = representation.states.sortedBy { state -> state.name }
        for (state in sortedStates) {
            stringBuilder.append("${state.name},${state.population}\n")
        }
        return stringBuilder.toString()
    }
}