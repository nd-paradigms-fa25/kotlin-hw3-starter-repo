package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.algorithm.ApportionmentMethod
import edu.nd.paradigms.hw3.algorithm.HamiltonMethod
import edu.nd.paradigms.hw3.algorithm.JeffersonMethod
import edu.nd.paradigms.hw3.format.AlphabeticalFormat
import edu.nd.paradigms.hw3.format.DisplayOrder
import edu.nd.paradigms.hw3.format.PopulationFormat
import edu.nd.paradigms.hw3.format.RepresentationFormat
import edu.nd.paradigms.hw3.io.CSVInputFile
import edu.nd.paradigms.hw3.io.CSVOutputFile
import edu.nd.paradigms.hw3.io.OutputSource
import edu.nd.paradigms.hw3.io.StateSource

/**
 * This class handles parsing the program's command line arguments to configure the apportionment, including
 * identifying the input source, selecting the algorithm, number of representatives, and determining how output
 * is generated.
 * @param argsArr - the command line arguments the program is run with
 * @throws IllegalArgumentException if command line arguments are empty
 */
class Arguments(
    argsArr: Array<String>,
) {
    companion object {
        /** The position in the args array of the required input filename argument */
        const val INPUT_FILENAME_POSITION = 0
        /** The position in the args array of the optional representatives argument */
        const val REPRESENTATIVES_ARGUMENT_POSITION = 1
    }

    init {
        require(argsArr.isNotEmpty()) { "No Command line arguments provided." }
    }

    val args: List<String> = argsArr.toList()

    /**
     * Returns an assembled Apportionment object using {@link StateSource}, {@link ApportionmentMethod}, and
     * the target number of representatives
     * @return [Apportionment]
     */
    fun getApportionment(): Apportionment {
        return Apportionment(
            stateSource = getStateSource(),
            apportionmentMethod = getApportionmentMethod(),
            representatives = getRepresentatives()
        )
    }

    /**
     * Returns the StateSupplier with access to the data source that the state population data is pulled from.
     * @return {@link StateSource}
     * @throws IllegalStateException if unsupported file format used
     */
    fun getStateSource(): StateSource {
        val filename = args[INPUT_FILENAME_POSITION]
        if (filename.lowercase().endsWith(".csv")) {
            return CSVInputFile(filename)
        }
        throw IllegalStateException(
            """
                Unsupported input file: $filename
                    This program currently supports the following input file formats:
                        - .csv files
                
                """.trimIndent()
        )
    }

    /**
     * Returns the number of representatives to apportion for the House of Representatives. By default, the House
     * of Representatives has 435 representatives. This is an optional position argument.
     * @return number of representatives to allocate
     * @throws IllegalArgumentException if non-positive number of representatives passed in.
     * @see REPRESENTATIVES_ARGUMENT_POSITION
     */
    fun getRepresentatives(): Int {
        if (args.size <= REPRESENTATIVES_ARGUMENT_POSITION) {
            return DEFAULT_REPRESENTATIVES
        }
        return try {
            args[REPRESENTATIVES_ARGUMENT_POSITION].toInt()
        } catch (_: NumberFormatException) {
            DEFAULT_REPRESENTATIVES
        }
    }

    /**
     * Gets the apportionment algorithm to use. By default, we use [JeffersonMethod], but
     * the [HamiltonMethod] can be selected with the --hamilton flag in the command line arguments
     * @return [ApportionmentMethod]
     */
    fun getApportionmentMethod(): ApportionmentMethod {
        return if (args.contains("--hamilton")) {
            HamiltonMethod()
        } else JeffersonMethod()
    }

    /**
     * Returns how to display the apportionment to the console.
     * @return the [RepresentationFormat] used to display to console
     */
    fun getRepresentationFormat(): RepresentationFormat {
        return if (args.contains("--population")) {
            if (args.contains("--ascending")) {
                PopulationFormat(DisplayOrder.ASCENDING)
            } else {
                PopulationFormat(DisplayOrder.DESCENDING)
            }
        } else {
            AlphabeticalFormat()
        }
    }

    /**
     * Gets the OutputSource if specified by the user for outputting to a file or other resource.
     * @return an [OutputSource] if the arguments specify one, otherwise return null if no output source specified
     * @throws IllegalArgumentException if output file is an unsupported format (currently only CSV supported)
     */
    fun getOutputSource(): OutputSource? {
        val outFlagIndex = args.indexOf("--out")
        if (outFlagIndex == -1) { return null }
        require(args.size > outFlagIndex + 1)

        val outputFilename = args[outFlagIndex + 1]
        require(outputFilename.lowercase().endsWith(".csv")) {"""
            Unsupported input file: $outputFilename
                    This program currently supports the following input file formats:
                        - .csv files
        """.trimIndent()}

        return CSVOutputFile(outputFilename)
    }
}