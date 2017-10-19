class SimpleTextEditor {
  private val buffer = new Array[Char](1000000)
  private var size = 0
  private var opHistory = List.empty[Op]

  sealed trait Op
  case class Append(length: Int) extends Op
  case class Delete(deleted: String) extends Op

  def append(chars: String): Unit = {
    chars.foreach { c =>
      buffer(size) = c
      size += 1
    }

    opHistory = Append(chars.length) :: opHistory
  }

  def delete(length : Int): Unit = {
    size -= length
    opHistory = Delete(buffer.subSequence(size, size + length).toString) :: opHistory
  }


  def print(k: Int): Unit =
    println(buffer(k - 1))

  def undo(): Unit = {
    val head :: tail = opHistory
    head match {
      case Append(length) =>
        size -= length

      case Delete(deleted) =>
        deleted.foreach { c =>
          buffer(size) = c
          size += 1
        }
    }

    opHistory = tail
  }
}

object SimpleTextEditor {
  def main(args: Array[String]): Unit = {
    val scan = new java.util.Scanner(System.in)
    val q = scan.nextInt()
    val editor = new SimpleTextEditor

    for (_ <- 0 until q) {
      val tpe = scan.nextInt()
      tpe.toInt match {
        case 1 =>
          val w = scan.next()
          editor.append(w)

        case 2 =>
          val k = scan.nextInt()
          editor.delete(k)

        case 3 =>
          val k = scan.nextInt()
          editor.print(k)

        case 4 =>
          editor.undo()
      }
    }
  }
}
