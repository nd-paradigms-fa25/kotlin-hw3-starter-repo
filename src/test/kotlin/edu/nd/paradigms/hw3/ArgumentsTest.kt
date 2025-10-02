package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.algorithm.HamiltonMethod
import edu.nd.paradigms.hw3.algorithm.JeffersonMethod
import edu.nd.paradigms.hw3.formats.AlphabeticalFormat
import edu.nd.paradigms.hw3.formats.DisplayOrder
import edu.nd.paradigms.hw3.formats.PopulationFormat
import edu.nd.paradigms.hw3.io.CSVInputFile
import edu.nd.paradigms.hw3.io.CSVOutputFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.toPath

class ArgumentsTest {
    val TEST_CSV_LOCATION = "csv_test_files${File.separator}states.csv";
    val classLoader: ClassLoader = this.javaClass.classLoader!!

    @Test
    fun argumentsTest_inputFile_defaultReps_noOptions() {
        val resourcePath = classLoader.getResource(TEST_CSV_LOCATION)!!.toURI().toPath().toString()
        val args = arrayOf(resourcePath)
        val arguments = Arguments(args)

        assertEquals(DEFAULT_REPRESENTATIVES, arguments.getRepresentatives())

        val stateSource = arguments.getStateSource()
        assertTrue(stateSource is CSVInputFile)
        val csvStateSource = stateSource as CSVInputFile
        assertTrue(csvStateSource.filename.endsWith(TEST_CSV_LOCATION))

        val apportionmentMethod = arguments.getApportionmentMethod()
        assertTrue(apportionmentMethod is JeffersonMethod)

        val representationFormat = arguments.getRepresentationFormat()
        assertTrue(representationFormat is AlphabeticalFormat)

        assertNull(arguments.getOutputSource())
    }

    @Test
    fun argumentsTest_inputFile_defaultReps_hamilton() {
        val resourcePath = classLoader.getResource(TEST_CSV_LOCATION)!!.toURI().toPath().toString()
        val args = arrayOf(resourcePath, "--hamilton")
        val arguments = Arguments(args)

        assertEquals(DEFAULT_REPRESENTATIVES, arguments.getRepresentatives())

        val stateSource = arguments.getStateSource()
        assertTrue(stateSource is CSVInputFile)
        val csvStateSource = stateSource as CSVInputFile
        assertTrue(csvStateSource.filename.endsWith(TEST_CSV_LOCATION))

        val apportionmentMethod = arguments.getApportionmentMethod()
        assertTrue(apportionmentMethod is HamiltonMethod)

        val representationFormat = arguments.getRepresentationFormat()
        assertTrue(representationFormat is AlphabeticalFormat)

        assertNull(arguments.getOutputSource())
    }

    @Test
    fun argumentsTest_inputFile_repsSpecified_outPutFilePresent() {
        val resourcePath = classLoader.getResource(TEST_CSV_LOCATION)!!.toURI().toPath().toString()
        val args = arrayOf(resourcePath, "100", "--out", "output.csv")
        val arguments = Arguments(args)

        assertEquals(100, arguments.getRepresentatives())

        val stateSource = arguments.getStateSource()
        assertTrue(stateSource is CSVInputFile)
        val csvStateSource = stateSource as CSVInputFile
        assertTrue(csvStateSource.filename.endsWith(TEST_CSV_LOCATION))

        val apportionmentMethod = arguments.getApportionmentMethod()
        assertTrue(apportionmentMethod is JeffersonMethod)

        val representationFormat = arguments.getRepresentationFormat()
        assertTrue(representationFormat is AlphabeticalFormat)

        assertNotNull(arguments.getOutputSource())
        val outputSource = arguments.getOutputSource()!! as CSVOutputFile
        assertEquals("output.csv", outputSource.outputFilename)
    }

    @Test
    fun argumentsTest_inputFile_repsSpecified_populationFormat_descendingDefault() {
        val resourcePath = classLoader.getResource(TEST_CSV_LOCATION)!!.toURI().toPath().toString()
        val args = arrayOf(resourcePath, "100", "--population")
        val arguments = Arguments(args)

        assertEquals(100, arguments.getRepresentatives())

        val stateSource = arguments.getStateSource()
        assertTrue(stateSource is CSVInputFile)
        val csvStateSource = stateSource as CSVInputFile
        assertTrue(csvStateSource.filename.endsWith(TEST_CSV_LOCATION))

        val apportionmentMethod = arguments.getApportionmentMethod()
        assertTrue(apportionmentMethod is JeffersonMethod)

        val representationFormat = arguments.getRepresentationFormat()
        assertTrue(representationFormat is PopulationFormat)
        val populationFormat = representationFormat as PopulationFormat
        assertEquals(DisplayOrder.DESCENDING, populationFormat.displayOrder)

        assertNull(arguments.getOutputSource())
    }

    @Test
    fun argumentsTest_inputFile_defaultReps_populationFormat_ascendingSpecified() {
        val resourcePath = classLoader.getResource(TEST_CSV_LOCATION)!!.toURI().toPath().toString()
        val args = arrayOf(resourcePath, "--population", "--ascending")
        val arguments = Arguments(args)

        assertEquals(DEFAULT_REPRESENTATIVES, arguments.getRepresentatives())

        val stateSource = arguments.getStateSource()
        assertTrue(stateSource is CSVInputFile)
        val csvStateSource = stateSource as CSVInputFile
        assertTrue(csvStateSource.filename.endsWith(TEST_CSV_LOCATION))

        val apportionmentMethod = arguments.getApportionmentMethod()
        assertTrue(apportionmentMethod is JeffersonMethod)

        val representationFormat = arguments.getRepresentationFormat()
        assertTrue(representationFormat is PopulationFormat)
        val populationFormat = representationFormat as PopulationFormat
        assertEquals(DisplayOrder.ASCENDING, populationFormat.displayOrder)

        assertNull(arguments.getOutputSource())
    }
}