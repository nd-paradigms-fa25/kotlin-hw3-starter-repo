package edu.nd.paradigms.hw3

/** CONSTANTS */
/**
 * The default number of representatives in the US House of Representatives
 */
const val DEFAULT_REPRESENTATIVES = 435

/**
 * Main method for running from command line arguments
 * @param args the command line arguments from terminal
 */
fun main(args: Array<String>) {
    val arguments = Arguments(args)
    val apportionment = arguments.getApportionment()
    val representation = apportionment.representation

    val outputSource = arguments.getOutputSource()
    outputSource?.writeToOutput(representation)

    val format = arguments.getRepresentationFormat();
    println(representation.toFormattedString(format))
}