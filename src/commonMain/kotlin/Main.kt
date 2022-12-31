class Primes : Sequence<Long> {
	var next: Sequence<Long> = generateSequence(3L) { it + 2L }
	override operator fun iterator() = object : Iterator<Long> {
		override fun hasNext() = true
		override fun next(): Long {
			val head = next.first()
			next = next.filter { it % head != 0L }
			return head
		}
	}
}

fun main(){
	Primes().zipWithNext().filter { it.second - it.first == 2L }.take(500).forEachIndexed { i, it -> println("$i: $it") }
}