package edu.nd.paradigms.hw3

import kotlin.math.floor

class States(
    initialStates: List<State> = listOf()
): Iterable<State> {
    private val _statesList: MutableList<State> = initialStates.toMutableList()
    /**
     * Returns an unmodifiable view of the set of states.
     */
    val statesList: List<State>
        get() = _statesList


    private val _stateNames: HashSet<String> = statesList.map { it.name }.toHashSet()
    val statesNames: Set<String>
        get() = _stateNames

    /**
     * Add a new state to the group of states
     * @param state the new {@link State} to add
     */
    fun add(state: State) {
        if (_stateNames.contains(state.name)) {
            throw DuplicateStateNameException(state, this)
        }
        _statesList.add(state)
        _stateNames.add(state.name)
    }

    /**
     * Computes the number of states in the collection.
     * @return the number of states
     */
    val size get() = _statesList.size

    /**
     * Returns a set of state names in the collection.
     * @return an unmodifiable set containing the names of all states
     */
    val isEmpty get() = _statesList.isEmpty()
    val isNotEmpty get() = _statesList.isNotEmpty()

    /**
     * Returns the total population of the list of states
     * @return the combined population of every state
     */
    val totalPopulation: Int
        get() {
            // TODO: Replace the code below with a functional sequence
            // return _states.   ...
            var sum = 0
            for (state in _statesList) {
                sum += state.population
            }
            return sum
        }

    /**
     * Returns the average population per seat in US House of Representatives for the group of states
     * @param numberOfSeats the total number of seats in the House of Representatives
     * @return the average number of residents per seat for all states combined
     */
    fun getAverageRepresentation(numberOfSeats: Int): Double =
        totalPopulation.toDouble() / numberOfSeats

    /**
     * Get the quota for each state, or the expected number of seats allocated to the state based on
     * a particular divisor.
     * @param divisor the population per seat
     * @return A mapping of states to their floating-point quotas
     */
    fun getQuotas(divisor: Double): Map<State, Double> {
        // TODO - replace the code in this method with a stream
        // return states.   ...
        val quotas = mutableMapOf<State, Double>()
        for (state in _statesList) {
            quotas[state] = state.population / divisor
        }
        return quotas
    }

    /**
     * Does the same thing as {@link States#getQuotas(double)} but rounds down the result to an integer
     * @param divisor divisor the population per seat
     * @return A mapping of states to their floating-point quotas always rounded *down*. So a quota of 4.99 would be
     * set equal to 4
     */
    fun getRoundedDownQuotas(divisor: Double): Map<State, Int> {
        val quotas = getQuotas(divisor)
        // TODO - replace the code in this method with a stream
        // return quotas. OR states. (your choice - both are possible)   ...
        val roundedDownQuotas = mutableMapOf<State, Int>()
        for (state in quotas.keys) {
            roundedDownQuotas[state] = quotas[state]!!.toInt()
        }
        return roundedDownQuotas
    }

    /**
     * Get the remainders of the quotes from {@link States#getQuotas(double)}
     * @param divisor divisor the population per seat
     * @return A mapping of states to their floating point remainders. For example, if a state has s quota of 4.73, then
     * that state maps to 0.73 in this output.
     */
    fun getRemainders(divisor: Double): Map<State, Double> {
        val quotas = getQuotas(divisor)
        val remainders = mutableMapOf<State, Double>()
        for (state in quotas.keys) {
            remainders[state] = quotas[state]!! - floor(quotas[state]!!)
        }
        return remainders
    }



    override fun iterator() = statesList.iterator()

    override fun toString(): String {
        return "States(states=$statesList)"
    }

}