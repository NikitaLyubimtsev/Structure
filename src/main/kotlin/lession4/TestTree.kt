package lession4

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun main() {

    val tree = Tree()
    tree.exist(1)

    val rbtree = RedBlackTree()
    val reader = BufferedReader(InputStreamReader(System.`in`))
    try {
        while (true) {
            try {
                val value: Int = Integer.parseInt(reader.readLine())
                rbtree.add(value)
                println("finish")
                print("hasNext? Yes 1. Enter choice: ")
                if (Integer.parseInt(reader.readLine()) == 1) {
                    rbtree.remove(5)
                }
            } catch (ignore: Exception) {

            }
        }
    } catch (e: IOException) {
        throw RuntimeException(e)
    } finally {
        reader.close()
    }
}