package challenge_eighteen

class Node(
    val item: String,
    val next: Node? = null // if this is set to null there are no more items in the list.
)

fun getDescription(node: Node): String? {
    val nextNodeDescription = when (node.next) {
        null -> "null"
        else -> getDescription(node.next)
    }

    return node.item + " " + nextNodeDescription
}