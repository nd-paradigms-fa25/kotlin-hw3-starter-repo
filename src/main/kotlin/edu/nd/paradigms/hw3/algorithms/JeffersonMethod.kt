package edu.nd.paradigms.hw3.algorithms

import edu.nd.paradigms.hw3.Representation
import edu.nd.paradigms.hw3.States
import edu.nd.paradigms.hw3.UnsolvableApportionmentException

class JeffersonMethod: ApportionmentMethod {
    override fun getRepresentation(
        states: States,
        representatives: Int
    ): Representation {
        require(states.isNotEmpty && states.totalPopulation > 0) { "No states provided! Cannot generate Representation map!" }
        require(representatives > 0) { "Number of representatives must be greater than zero!" }

        var highDivisor = states.getAverageRepresentation(representatives)
        var lowDivisor = 0.0

        repeat(100) {
            val midDivisor = (highDivisor + lowDivisor) / 2.0
            val roundedDownQuotas = states.getRoundedDownQuotas(midDivisor)
            val representation = Representation(roundedDownQuotas)

            if (representation.allocatedSeats > representatives) {
                lowDivisor = midDivisor
            } else if (representation.allocatedSeats < representatives) {
                highDivisor = midDivisor
            } else {
                return representation
            }
        }
        throw UnsolvableApportionmentException(
            "Unsolvable Jefferson Apportionment with the given parameters; it is likely that is is an unresolvable tie"
        )
    }
}