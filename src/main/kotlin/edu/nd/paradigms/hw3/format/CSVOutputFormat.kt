package edu.nd.paradigms.hw3.format

import edu.nd.paradigms.hw3.Representation

class CSVOutputFormat: RepresentationFormat {
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