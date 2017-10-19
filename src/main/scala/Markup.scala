object Markup {
	sealed trait Tree {
		def iterator: Iterator[Char]
	}

	class Value(s: String) extends Tree {
		def iterator: Iterator[Char] = s.iterator
	}

	class Tag(tpe: String, inners: List[Tree]) extends Tree {
		def iterator: Iterator[Char] = new Iterator[Char] {
			val innersIterator = inners.iterator
			var current: Iterator[Char] = Iterator.empty


			def hasNext: Boolean = current.hasNext || innersIterator.hasNext

			def next(): Char = {
				if (!current.hasNext) {
					current = innersIterator.next().iterator
				}
				current.next()
			}
		}
	}

	def main(args: Array[String]): Unit = {
		val t1 = new Value("Hello World")
		val t2 = new Tag("doc",
			List(
				new Value("Hello"),
				new Tag("b", List(new Value(" "))),
				new Value("World")
			)
		)
		val t3 = new Tag("i", List(t2, new Value("!")))

		assert(t1.iterator.sameElements(t2.iterator))
		assert(!t2.iterator.sameElements(t3.iterator))	
	}
}