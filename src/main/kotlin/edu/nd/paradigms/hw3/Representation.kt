package edu.nd.paradigms.hw3

import edu.nd.paradigms.hw3.format.RepresentationFormat

class Representation(
    initialRepresentation: Map<State, Int> = mapOf()
): Iterable<State> {
    private val _representation = initialRepresentation.toMutableMap()

    /**
     * An immutable copy of the mapping of states to representative allocation
     */
    val representation: Map<State, Int>
        get() = _representation

    val states: States
        get() = States(_representation.keys.toList())

    /**
     * Gets the number of states in the representation
     * @return the number of states, <b>not</b> the number of seats!
     */
    val size: Int
        get() = representation.size

    val allocatedSeats: Int
        get() {
            //TODO: replace with function sequence
            // Your code should start
            // return representation.
            var allocatedSeats = 0
            for (stateSeats in representation.values) {
                allocatedSeats += stateSeats
            }
            return allocatedSeats
        }

    /**
     * Sets the number of seats for a particular {@link State}, overwriting any previous value if present
     * @param state the state to set the number of seats for
     * @param seats the number of seats to allocate to the state
     */
    fun setSeats(state: State, seats: Int) {
        _representation[state] = seats
    }

    /**
     * Adds the number of seats to a particular state. Note that this behaves the same as
     * {@link Representation#setSeats(State, int)} when the state is not present in the representation
     * @param state the {@link State} to add seats to
     * @param seats the number of seats to add to that state
     */
    fun addSeats(state: State, seats: Int) {
        _representation[state] = seats + (_representation[state] ?: 0)
    }

    /**
     * Get the number of seats for a particular state. Returns zero if the state isn't present.
     * @param state the {@link State} to get the seats for.
     * @return the number of seats assigned to the state, or zero if the state is not present.
     */
    fun getSeats(state: State): Int = _representation[state] ?: 0

    /**
     * Returns a formatted {@link String} to give a textual representation of the Representation object
     * @param format {@link RepresentationFormat}
     * @return a formatted {@link String}
     */
    fun toFormattedString(format: RepresentationFormat): String = format.getFormattedString(this)

    /**
     * Returns an iterator over the set of {@link State}s in the Representation
     * @return an {@link Iterator}
     */
    override fun iterator(): Iterator<State> = _representation.keys.iterator()

    /**
     * Gets a string representation of the underlying data.
     * @return {@link String}
     */
    override fun toString(): String {
        return "Representation(representation=$representation)"
    }
}