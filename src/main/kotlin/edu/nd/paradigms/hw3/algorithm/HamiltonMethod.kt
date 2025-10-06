package edu.nd.paradigms.hw3.algorithm

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.States
import edu.nd.paradigms.hw3.State
import java.util.Map

/**
 * Represents the apportionment method proposed by Alexander Hamilton, also called
 * <a href="https://en.wikipedia.org/wiki/Quota_method">the quota method</a>
 */
class HamiltonMethod: ApportionmentMethod {
    /**
     * Performs an apportionment using the Hamilton method.
     * @param states the group of {@link States} to allocate representatives to
     * @param representatives the number of seats in Congress to allocate
     * @return {@link Representation} containing the allocation of representative to each state
     */
    override fun getRepresentation(
        states: States,
        representatives: Int
    ): Representation {
        require(states.isNotEmpty && states.totalPopulation > 0) { "No states provided! Cannot generate Representation map!" }
        require(representatives > 0) { "Number of representatives must be greater than zero!" }

        val divisor = states.getAverageRepresentation(representatives)
        val initialRepresentation = states.getRoundedDownQuotas(divisor)
        val representation = Representation(initialRepresentation)
        val remainders = states.getRemainders(divisor)
        val remainingRepresentatives = representatives - representation.allocatedSeats

        // TODO - replace the following section of code down to "end TODO" with a functional sequence that adds the bonus representatives
        // Hint: remainders. ...
        val remainderEntries = ArrayList(remainders.entries.toList())
        remainderEntries.sortWith(Map.Entry.comparingByValue<State, Double>().reversed())
        for (i in 0..< remainingRepresentatives) {
            val state = remainderEntries[i].key
            representation.addSeats(state, 1)
        }
        // end TODO

        return representation
    }
}