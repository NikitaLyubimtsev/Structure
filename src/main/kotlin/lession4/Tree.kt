package lession4

class Tree {

    private var root: Node? = null

    fun exist(value: Int): Boolean {
        root?.let {
            find(it, value)?.let {
                return true
            }
        } ?: return false
    }

    private fun find(node: Node, value: Int): Node? {
        if (node.value == value) {
            return node
        } else {
            for (child: Node in node.children) {
                val result: Node? = find(child, value)
                result?.let {
                    return it
                }
            }
        }
        return null
    }

    inner class Node(var value: Int) {
        lateinit var children: List<Node>
    }
}
