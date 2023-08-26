import kotlin.random.Random

public fun main(args: Array<String>) {

    val ar = Array<Int>(20) { Random.nextInt(1, 1000) }

    println(ar.contentToString())
    sort(ar)
    println(ar.contentToString())

}

    fun sort(array: Array<Int>) {
        for (i in array.size / 2 -1 downTo 0) {
            heapify(array, array.size, i)
        }

        for (i in array.size -1 downTo 0) {
            val temp: Int = array[0]
            array[0] = array[i]
            array[i] = temp

            heapify(array, i, 0)
        }
    }

    fun heapify(array: Array<Int>, heapSize: Int, rootIndex: Int) {
        var largest: Int = rootIndex
        val leftChild: Int = 2 * rootIndex + 1
        val rightChild: Int = 2 * rootIndex + 2

        if (leftChild < heapSize && array[leftChild] > array[largest]) {
            largest = leftChild
        }

        if (rightChild < heapSize && array[rightChild] > array[largest]) {
            largest = rightChild
        }
        if (largest != rootIndex) {
            val temp: Int = array[rootIndex]
            array[rootIndex] = array[largest]
            array[largest] = temp

            heapify(array, heapSize, largest)
        }
    }
