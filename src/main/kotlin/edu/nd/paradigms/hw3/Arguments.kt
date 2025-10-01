package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.algorithms.ApportionmentMethod
import edu.nd.paradigms.hw3.algorithms.HamiltonMethod
import edu.nd.paradigms.hw3.algorithms.JeffersonMethod
import edu.nd.paradigms.hw3.formats.AlphabeticalFormat
import edu.nd.paradigms.hw3.formats.DisplayOrder
import edu.nd.paradigms.hw3.formats.PopulationFormat
import edu.nd.paradigms.hw3.formats.RepresentationFormat
import edu.nd.paradigms.hw3.io.CSVInputFile
import edu.nd.paradigms.hw3.io.CSVOutputFile
import edu.nd.paradigms.hw3.io.OutputSource
import edu.nd.paradigms.hw3.io.StateSource


class Arguments(
    argsArr: Array<String>,
) {
    companion object {
        const val INPUT_FILENAME_POSITION = 0
        const val REPRESENTATIVES_ARGUMENT_POSITION = 1
    }

    init {
        require(argsArr.isNotEmpty()) { "No Command line arguments provided." }
    }

    val args: List<String> = argsArr.toList()

    fun getApportionment(): Apportionment {
        return Apportionment(
            stateSource = getStateSource(),
            apportionmentMethod = getApportionmentMethod(),
            representatives = getRepresentatives()
        )
    }

    fun getStateSource(): StateSource {
        val filename = args[INPUT_FILENAME_POSITION]
        if (filename.lowercase().endsWith(".csv")) {
            return CSVInputFile(filename)
        }
        throw IllegalArgumentException(
            """
                Unsupported input file: $filename
                    This program currently supports the following input file formats:
                        - .csv files
                
                """.trimIndent()
        )
    }

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

    fun getApportionmentMethod(): ApportionmentMethod {
        return if (args.contains("--hamilton")) {
            HamiltonMethod()
        } else JeffersonMethod()
    }

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