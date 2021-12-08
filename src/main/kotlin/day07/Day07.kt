import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    val crabPositions = readInput("day07/Day07")[0].split(',').map { it.toInt() }

    measureTimeMillis {
        println(crabPositions.calculateMinimumFuelConsumption(::part1))
    }.also { println("execution time: $it ms") }
    measureTimeMillis {
        println(crabPositions.calculateMinimumFuelConsumption(::part2))
    }.also { println("execution time: $it ms") }
}

inline fun List<Int>.calculateMinimumFuelConsumption(
    fuelConsumptionCalculation: (crabPosition: Int, targetPosition: Int) -> Int
) =
    (0..maxOf { it })
        .map { targetPosition -> map { fuelConsumptionCalculation(it, targetPosition) }.sum() }
        .minByOrNull { it }
        ?: 0

private fun part1(crabPosition: Int, targetPosition: Int) = abs(crabPosition - targetPosition)
private fun part2(crabPosition: Int, targetPosition: Int) = (1..abs(crabPosition - targetPosition)).sum()
