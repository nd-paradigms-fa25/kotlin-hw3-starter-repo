package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

/**
 * Displays apportionment sorted by state name in alphabetical order by state name
 */
class AlphabeticalFormat: RepresentationFormat {
    private val sortedTableFormat = SortedTableFormat()
    override fun getFormattedString(representation: Representation): String {
        val comparator = Comparator.comparing{ state: State -> state.name.lowercase() }
        return sortedTableFormat.getSortedTableString(representation, comparator)
    }
}