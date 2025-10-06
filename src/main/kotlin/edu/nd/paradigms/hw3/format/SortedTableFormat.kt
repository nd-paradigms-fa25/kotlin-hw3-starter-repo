package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

class SortedTableFormat{
    fun getSortedTableString(
        representation: Representation,
        comparator: Comparator<State>,
    ):String {
        val states = ArrayList(representation.states.toList())
        val headerRow = "%-15s|%19s |%5s\n".format("State", "Population", "Reps")
        // TODO - replace the rest of the code in this method with a stream
        // As a hint, you no longer need to use a StringBuilder
        // You can use the Collectors.joining method to concatenate strings
        // return headerRow + states...
        states.sortWith(comparator)
        val stringBuilder = StringBuilder()
        for (state in states) {
            stringBuilder.append("%-15s|%19s |%5s\n".format(
                state.name, state.population, representation.getSeats(state)
            ))
        }
        return headerRow + stringBuilder.toString()
    }
}