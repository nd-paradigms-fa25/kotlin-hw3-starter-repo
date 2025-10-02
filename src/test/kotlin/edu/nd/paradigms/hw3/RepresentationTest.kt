package edu.nd.paradigms.hw3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RepresentationTest {
    val ohio: State = State("Ohio", 1234567890)
    val virginia: State = State("Virginia", 987654321)

    @Test
    fun getRepresentatives_notPresent() {
        val rep = Representation()

        assertEquals(0, rep.getSeats(ohio))
    }

    @Test
    fun getRepresentatives_present() {
        val initialMap = mapOf(ohio to 5)
        val rep = Representation(initialMap)

        assertEquals(5, rep.getSeats(ohio))
    }

    @Test
    fun setSeats_notPresent() {
        val rep = Representation()

        rep.setSeats(ohio, 5)

        assertEquals(5, rep.getSeats(ohio))
    }

    @Test
    fun setSeats_present() {
        val initialMap = mapOf(ohio to 10)
        val rep = Representation(initialMap)

        rep.setSeats(ohio, 5)

        assertEquals(5, rep.getSeats(ohio))
    }

    @Test
    fun addRepresentatives_notPresent() {
        val rep = Representation()

        rep.addSeats(ohio, 5)

        assertEquals(5, rep.getSeats(ohio))
    }


    @Test
    fun addRepresentatives_present() {
        val initialMap = mapOf(ohio to 10)
        val rep = Representation(initialMap)

        rep.addSeats(ohio, 5)

        assertEquals(15, rep.getSeats(ohio))
    }

    @Test
    fun getAllocatedSeats_notEmpty() {
        val initialMap = mapOf(ohio to 5, virginia to 10)
        val rep = Representation(initialMap)
        assertEquals(15, rep.allocatedSeats)
    }

    @Test
    fun getAllocatedSeats_Empty() {
        val rep = Representation()
        assertEquals(0, rep.allocatedSeats)
    }
}