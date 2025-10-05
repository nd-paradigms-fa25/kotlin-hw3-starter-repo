package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

class AlphabeticalFormat: RepresentationFormat {
    private val sortedTableFormat = SortedTableFormat()
    override fun getFormattedString(representation: Representation): String {
        val comparator = Comparator.comparing{ state: State -> state.name.lowercase() }
        return sortedTableFormat.getSortedTableString(representation, comparator)
    }
}