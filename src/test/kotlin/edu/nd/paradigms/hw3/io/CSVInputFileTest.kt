package edu.nd.paradigms.hw3.io

import edu.nd.paradigms.hw3.States
import edu.nd.paradigms.hw3.State
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.util.*

import org.junit.jupiter.api.Assertions.*;

class CSVInputFileTest {
    @Test
    @Throws(IOException::class)
    fun getStates() {
        val testCSVFile = "csv_test_files" + File.separator + "states.csv"
        val stateCSVFile = CSVInputFile(getResource(testCSVFile)!!)
        
        val states: States = stateCSVFile.getStates()
        assertIterableEquals(
            listOf(
                    State("Delaware", 989948),
                    State("Maryland", 6177224),
                    State("Pennsylvania", 13002700),
                    State("Virginia", 8631393),
                    State("West Virginia", 1793716)
                )
            , states.statesList
        )
    }
    
    private fun getResource(resourceName: String?): String? {
        val classLoader = javaClass.classLoader
        try {
            return Objects.requireNonNull(classLoader.getResource(resourceName)).toURI().getPath()
        } catch (e: Exception) {
            throw RuntimeException("Error! The resource was unable to be loaded.")
        }
    }
    
}