package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.format.CSVOutputFormat
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
 * @param outputFilename the location of the file to write the output to
 */
class CSVOutputFile(
    val outputFilename: String
):OutputSource {
    init {
        require(outputFilename.endsWith(".csv")) {"Cannot write to a non-csv file: $outputFilename"}

    }

    /**
     * Writes the apportionment results to the output csv file showing the state name and the allocated representatives
     * @param representation {@link Representation} the results of an apportionment algorithm
     */
    override fun writeToOutput(representation: Representation) {
        val writer = FileWriter(outputFilename)
        val fileContents = representation.toFormattedString(CSVOutputFormat())
        writer.write(fileContents)
    }
}