package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.formats.CSVOutputFormat
import java.io.FileWriter


/**
 * Represents an output in CSV format. For example: <br>
 * <i>
 *     State,Representatives<br>
 *     Alabama,7<br>
 *     Arizona,10<br>
 *     Alaska,1<br>
 *     etc.
 * </i>
 */
class CSVOutputFile(
    val outputFilename: String
):OutputSource {
    init {
        require(outputFilename.endsWith(".csv")) {"Cannot write to a non-csv file: $outputFilename"}

    }
    override fun writeToOutput(representation: Representation) {
        val writer = FileWriter(outputFilename)
        val fileContents = representation.toFormattedString(CSVOutputFormat())
        writer.write(fileContents)
    }
}