package edu.nd.paradigms.hw3.algorithm

import edu.nd.paradigms.hw3.*
import org.junit.jupiter.api.Test
import java.util.Set
import kotlin.test.assertEquals

internal class JeffersonMethodTest {
    @Test
    fun getRepresentation() {
        val DE: State = State("Delaware", 989948)
        val MD: State = State("Maryland", 6177224)
        val PA: State = State("Pennsylvania", 13002700)
        val VA: State = State("Virginia", 8631393)
        val WV: State = State("West Virginia", 1793716)
        
        val states: States = States(listOf(DE, MD, PA, VA, WV))
        val jeffersonMethod: JeffersonMethod = JeffersonMethod()
        val representation: Representation = jeffersonMethod.getRepresentation(states, 25)
        
        assertEquals(25, representation.allocatedSeats)
        assertEquals(5, representation.size)
        
        assertEquals(0, representation.getSeats(DE))
        assertEquals(5, representation.getSeats(MD))
        assertEquals(12, representation.getSeats(PA))
        assertEquals(7, representation.getSeats(VA))
        assertEquals(1, representation.getSeats(WV))
    }
}