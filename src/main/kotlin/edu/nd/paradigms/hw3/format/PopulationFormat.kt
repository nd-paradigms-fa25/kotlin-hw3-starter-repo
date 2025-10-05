package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

class PopulationFormat (
    val displayOrder: DisplayOrder = DisplayOrder.DESCENDING
): RepresentationFormat {
    private val sortedTableFormat = SortedTableFormat()

    override fun getFormattedString(representation: Representation): String {
        var comparator = Comparator<State>.comparing(State::population)
        if (displayOrder === DisplayOrder.DESCENDING) {
            comparator = comparator.reversed()
        }
        return sortedTableFormat.getSortedTableString(representation, comparator)
    }

}