fun solution(elements: MutableSet<Int>, element: Int): MutableSet<Int> {
    return if (elements.contains(element)) mutableSetOf() else elements
}