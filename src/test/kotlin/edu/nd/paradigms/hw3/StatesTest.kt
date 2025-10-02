package edu.nd.paradigms.hw3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StatesTest {
    val ohio: State = State("Ohio", 300)
    val virginia: State = State("Virginia", 150)
    val delaware: State = State("Delaware", 50)

    private lateinit var states: States

    @BeforeEach
    fun setUp() {
        val initialStateList = listOf(ohio, virginia, delaware)
        states = States(initialStateList)
    }

    @Test
    fun add_newState_valid() {
        val md = State("Maryland", 125)

        states.add(md)

        val statesSet = states.statesList
        assertEquals(4, statesSet.size)
        assertTrue(statesSet.contains(md))
        assertTrue(states.statesNames.contains("Maryland"))
    }

    @Test
    fun add_newState_invalidDuplicateName() {
        val virginia2 = State("Virginia", 125)

        val exception: DuplicateStateNameException = assertThrows(
            DuplicateStateNameException::class.java) { states.add(virginia2) }
        assertEquals(exception.duplicateState, virginia2)

        val postStates = states.statesList
        assertEquals(3, postStates.size)
        assertFalse(postStates.contains(virginia2))
    }

    @Test
    fun getTotalPopulation() {
        assertEquals(500, states.totalPopulation)
    }

    @Test
    fun getAverageRepresentation_evenlyDivisible() {
        assertEquals(50.0, states.getAverageRepresentation(10), 1e-14)
    }

    @Test
    fun getAverageRepresentation_notDivisible() {
        assertEquals(71.4285714, states.getAverageRepresentation(7), 1e-7)
    }

    @Test
    fun getQuotas_evenlyDivisible() {
        val quotas = states.getQuotas(50.0)
        assertEquals(3, quotas.size)
        assertEquals(6.0, quotas[ohio]!!, 1e-14)
        assertEquals(3.0, quotas[virginia]!!, 1e-14)
        assertEquals(1.0, quotas[delaware]!!, 1e-14)
    }

    @Test
    fun getQuotas_notDivisible() {
        val quotas = states.getQuotas(71.4285714)
        assertEquals(3, quotas.size)
        assertEquals(4.2, quotas[ohio]!!, 1e-7)
        assertEquals(2.1, quotas[virginia]!!, 1e-7)
        assertEquals(0.7, quotas[delaware]!!, 1e-7)
    }

    @Test
    fun getRoundedDownQuotas_evenlyDivisible() {
        val roundedDownQuotas = states.getRoundedDownQuotas(50.0)
        assertEquals(3, roundedDownQuotas.size)
        assertEquals(6, roundedDownQuotas[ohio])
        assertEquals(3, roundedDownQuotas[virginia])
        assertEquals(1, roundedDownQuotas[delaware])
    }

    @Test
    fun getRoundedDownQuotas_notDivisible() {
        val roundedDownQuotas = states.getRoundedDownQuotas(71.4285714)
        assertEquals(3, roundedDownQuotas.size)
        assertEquals(4, roundedDownQuotas[ohio])
        assertEquals(2, roundedDownQuotas[virginia])
        assertEquals(0, roundedDownQuotas[delaware])
    }

    @Test
    fun getRemainders_evenlyDivisible() {
        val roundedDownQuotas = states.getRemainders(50.0)
        assertEquals(3, roundedDownQuotas.size)
        assertEquals(0.0, roundedDownQuotas[ohio])
        assertEquals(0.0, roundedDownQuotas[virginia])
        assertEquals(0.0, roundedDownQuotas[delaware])
    }

    @Test
    fun getRemainders_notDivisible() {
        val quotas = states.getRemainders(71.4285714)
        assertEquals(3, quotas.size)
        assertEquals(0.2, quotas[ohio]!!, 1e-7)
        assertEquals(0.1, quotas[virginia]!!, 1e-7)
        assertEquals(0.7, quotas[delaware]!!, 1e-7)
    }
}