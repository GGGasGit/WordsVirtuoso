fun evenFilter(numbers: Set<Int>): Set<Int> {
    return numbers.toMutableSet().filter { it % 2 == 0 }.toSet()
}