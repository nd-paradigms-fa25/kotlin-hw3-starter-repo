package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

/**
 * Display states sorted by population.
 */
class PopulationFormat (
    val displayOrder: DisplayOrder = DisplayOrder.DESCENDING
): RepresentationFormat {
    private val sortedTableFormat = SortedTableFormat()

    /**
     * Returns a string of states sorted by population in either descending or descending order, depending on
     * the object's configuration.
     * @param representation {@link Representation} - the results of an apportionment
     * @return a formatted {@link String}
     * @see SortedTableFormat#getSortedTableString(Representation, Comparator)
     */
    override fun getFormattedString(representation: Representation): String {
        var comparator = Comparator<State>.comparing(State::population)
        if (displayOrder === DisplayOrder.DESCENDING) {
            comparator = comparator.reversed()
        }
        return sortedTableFormat.getSortedTableString(representation, comparator)
    }

}