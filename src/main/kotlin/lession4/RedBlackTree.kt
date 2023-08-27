package lession4

class RedBlackTree {

    private var root: Node? = null

    fun add(value: Int): Boolean {
        return if (root === null) {
            root = Node(value, Color.BLACK)
            true
        } else {
            val result = addNode(root!!, value)
            root = rebalance(root!!)
            root?.color = Color.BLACK
            result
        }
    }

    private fun addNode(node: Node, value: Int): Boolean {
        if (node.value == value) {
            return false
        } else {
            if (node.value > value) {
                node.leftChild?.let {
                    val result = addNode(it, value)
                    node.leftChild = rebalance(it)
                    return result
                } ?: run {
                    node.leftChild = Node(value, Color.RED)
                    return true
                }
            } else {
                node.rightChild?.let {
                    val result = addNode(it, value)
                    node.rightChild = rebalance(it)
                    return result
                } ?: run {
                    node.rightChild = Node(value, Color.RED)
                    return true
                }
            }
        }
    }

    private fun rebalance(node: Node): Node {
        var result = node
        do {
            var needRebalance = false
            result.let { res ->
                res.rightChild?.let {
                    if (it.color === Color.RED && (res.leftChild === null || res.leftChild?.color === Color.BLACK)) {
                        needRebalance = true
                        result = rightSwap(result)
                    }
                }
                res.leftChild?.let {
                    if (it.color === Color.RED) {
                        it.leftChild?.let { left ->
                            if (left.color === Color.RED) {
                                needRebalance = true
                                result = leftSwap(result)
                            }
                        }
                        res.rightChild?.let { right ->
                            if (right.color === Color.RED) {
                                needRebalance = true
                                colorSwap(result)
                            }
                        }
                    }
                }
            }
        }
        while (needRebalance)
        return result
    }

    private fun rightSwap(node: Node): Node {
        node.rightChild?.let {
            val between = it.leftChild
            it.leftChild = node
            node.rightChild = between
            it.color = node.color
            node.color = Color.RED
            return it
        } ?: throw RuntimeException("Not Right child!")
    }

    private fun leftSwap(node: Node): Node {
        node.leftChild?.let {
            val between = it.rightChild
            it.rightChild = node
            node.leftChild = between
            it.color = node.color
            node.color = Color.RED
            return it
        } ?: throw RuntimeException("Not left child!")
    }

    private fun colorSwap(node: Node) {
        node.rightChild?.color = Color.BLACK
        node.leftChild?.color = Color.BLACK
        node.color = Color.RED
    }

    class Node(var value: Int, var color: Color) {

        var leftChild: Node? = null
        var rightChild: Node? = null

        override fun toString(): String {
            return "Node{value=$value, color=$color}"
        }

    }

    enum class Color {
        RED, BLACK
    }
}
