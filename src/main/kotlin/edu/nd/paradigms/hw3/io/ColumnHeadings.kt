package edu.nd.paradigms.hw3.io

class ColumnHeadings{
    companion object {
        const val STATE_NAME_COLUMN_LABEL = "State"
        const val STATE_POPULATION_COLUMN_LABEL = "Population"
    }

    /** The number of columns in the heading */
    var size: Int = -1
        private set

    /** The column index of the "State" column containing state names */
    var stateColumnIndex: Int = -1
        private set


    var populationColumnIndex: Int = -1
        private set

    constructor(headerRow: String) {
        val columnLabels = headerRow.split(",")
        size = columnLabels.size
        for (i in columnLabels.indices) {
            val heading = columnLabels[i].trim()
            if (heading.equals(STATE_NAME_COLUMN_LABEL, ignoreCase = true) && stateColumnIndex == -1) {
                stateColumnIndex = i
            }
            if (heading.equals(STATE_POPULATION_COLUMN_LABEL, ignoreCase = true) && populationColumnIndex == -1) {
                populationColumnIndex = i
            }
        }
        check(stateColumnIndex != -1) {"Missing column header for State. Column headers: $columnLabels"}
        check(populationColumnIndex != -1) {"Missing column header for Population. Column headers: $columnLabels"}
    }

}