object Triangles {
	type Edge = (Int, Int)

	case class Triangle(a: Edge, b: Edge, c: Edge) {
		def contains(e: Edge): Boolean = ???
	}

	def split(t: Triangle): List[Triangle] = {
		val Triangle(a @ (ax, ay), b @ (bx, by), c @ (cx, cy)) = t

		val topLeft = ((ax + bx) / 2, (ay + by) / 2)
		val topRight = ((ax + cx) / 2, (ay + cy) / 2)
		val bottom = ((bx + cx) / 2, (by + cy) / 2)

		List(Triangle(a, topLeft, topRight), Triangle(topLeft, b, bottom), Triangle(topRight, bottom, c))
	}

  def drawTriangles(n: Int): Unit = {
  	
  }
}