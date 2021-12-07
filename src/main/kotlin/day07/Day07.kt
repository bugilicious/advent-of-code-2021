import kotlin.math.abs

fun main() {
    val crabPositions = readInput("day07/Day07_test")[0].split(',').map { it.toInt() }

    fun calculateMinimumFuelConsumption(fuelConsumptionCalculation: (crabPosition: Int, targetPosition: Int) -> Int) =
        (0..crabPositions.maxOf { it })
            .map { targetPosition -> crabPositions.map { fuelConsumptionCalculation(it, targetPosition) }.sum() }
            .minByOrNull { it }
            ?: 0

    println(calculateMinimumFuelConsumption(::part1))
    println(calculateMinimumFuelConsumption(::part2))
}

private fun part1(crabPosition: Int, targetPosition: Int) = abs(crabPosition - targetPosition)
private fun part2(crabPosition: Int, targetPosition: Int) = (1..abs(crabPosition - targetPosition)).sum()
