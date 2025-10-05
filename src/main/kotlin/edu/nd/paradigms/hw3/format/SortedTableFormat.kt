package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.State

class SortedTableFormat{
    fun getSortedTableString(
        representation: Representation,
        comparator: Comparator<State>,
    ):String {
        val states = ArrayList(representation.states.toList())
        states.sortWith(comparator)
        val stringBuilder = StringBuilder()
        val headerRow = "%-15s|%19s |%5s\n".format("State", "Population", "Reps")
        for (state in states) {
            stringBuilder.append("%-15s|%19s |%5s\n".format(
                state.name, state.population, representation.getSeats(state)
            ))
        }
        return stringBuilder.toString()
    }
}