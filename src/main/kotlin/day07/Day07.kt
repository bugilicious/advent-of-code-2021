import kotlin.math.abs

fun main() {
    with(Day07) {
        println(solvePart1())
        println(solvePart2())
    }
}

object Day07 : PuzzleSolver {

    private val crabPositions = readInput("day07/Day07")[0].split(',').map { it.toInt() }

    override fun solvePart1() = crabPositions.calculateMinimumFuelConsumption(::simpleFuelConsumptionCalculation)
    override fun solvePart2() = crabPositions.calculateMinimumFuelConsumption(::advancedFuelConsumptionCalculation)

    private fun simpleFuelConsumptionCalculation(crabPosition: Int, targetPosition: Int) =
        abs(crabPosition - targetPosition)

    private fun advancedFuelConsumptionCalculation(crabPosition: Int, targetPosition: Int) =
        (1..abs(crabPosition - targetPosition)).sum()

    private inline fun List<Int>.calculateMinimumFuelConsumption(
        fuelConsumptionCalculation: (crabPosition: Int, targetPosition: Int) -> Int
    ) =
        (0..maxOf { it })
            .map { targetPosition -> map { fuelConsumptionCalculation(it, targetPosition) }.sum() }
            .minByOrNull { it }
            ?: 0
}
