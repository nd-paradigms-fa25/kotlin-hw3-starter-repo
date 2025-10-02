package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.States
import edu.nd.paradigms.hw3.State
import java.io.File
import kotlin.math.max

/**
 * Represents an input CSV file for states. Specifically, this file must contain the column headers:
 * <ul>
 *     <li>"State" - the column containing the names of the states</li>
 *     <li>"Population" - the column containing the populations of each state</li>
 * </ul>
 * See sample_inputs/part1_input.csv and sample_inputs/part2_input.csv for examples
 */
class CSVInputFile(
    val filename: String
): StateSource {

    val csvFile = File(filename)

    init {
        require(filename.endsWith(".csv"))
        require(csvFile.exists())
    }

    lateinit var headings: ColumnHeadings

    /**
     * Retrieves state data from a CSV file. See sample_inputs/states.csv for an example
     * @return a {@link List} of {@link State} objects
     */
    override fun getStates(): States {
        val bufferedReader = csvFile.bufferedReader()
        val headerLine = bufferedReader.readLine()
        headings = ColumnHeadings(headerLine)
        var lineNumber = 2
        var line = bufferedReader.readLine()
        val states = States()
        while (line != null) {
            val state = getStateFromLine(line, lineNumber)
            if (state != null) { states.add(state) }
            lineNumber++
            line = bufferedReader.readLine()
        }
        return states
    }

    private fun getStateFromLine(line: String, lineNumber: Int): State? {
        val lineData = line.split(",")
        if (lineData.size < max(headings.stateColumnIndex, headings.populationColumnIndex)) {
            println("Warning: Skipping Line# $lineNumber has bad format -- not enough columns: $line")
            return null
        }
        val stateName = lineData[headings.stateColumnIndex]
        var population: Int
        try {
            population = lineData[headings.populationColumnIndex].toInt()
        } catch (_: NumberFormatException) {
            println("Warning: Skipping Line# $lineNumber population is not an integer: ${lineData[headings.populationColumnIndex]}")
            return null
        }
        if (population < 0) {
            println("Warning: Skipping Line# $lineNumber population is negative: $population - skipping line")
            return null
        }
        return State(stateName, population)
    }
}