package contains

fun <T> List<T>.simpleContains(element: T): Boolean {
    this.forEach {
        if (it == element) return true
    }
    return false
}