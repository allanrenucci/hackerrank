object RotatedSortedArray {

  def main(args: Array[String]): Unit = {
    val sorted = 1 to 100 by 3

    assert(search(sorted, 1) == 0)
    assert(search(sorted, 2) == -1)
    assert(search(sorted, 7) == 2)

    val rotated = RotatedView.Left(sorted, 5)
    assert(search(rotated, 16) == 0)
    assert(search(rotated, 2) == -1)
    assert(search(rotated, 25) == 3)
  }


  // 4 5 6 1 2 3
  def search2(seq: Seq[Int], value: Int): Int = {
    var low = 0
    var high = seq.length - 1

    while (low <= high) {
      var mean = (low + high) / 2

      if (seq(mean) == value) {
        return mean

      } else if (value < seq(mean)) {
        if (seq(low) <= value) {
          high = mean - 1
        } else {
          low = mean + 1
        }
      } else /* value > seq(mean) */ {
        if (value <= seq(high)) {
          low = mean + 1
        } else {
          high = mean - 1
        }
      }
    }

    -1 // value not found
  }

  /** Returns the index of `value` in `array`, -1 if not found */
  def search(seq: Seq[Int], value: Int): Int = {
    val pivot = findPivot2(seq)
    val rotated = RotatedView.Left(seq, pivot)
    val index = binarySearch(rotated, value)

    if (index == -1) -1
    else (index + pivot) % seq.length
  }

  // 4 5 6 1 2 3
  def findPivot2(seq: Seq[Int]): Int = {
    var low = 0
    var high = seq.length - 1

    while (seq(low) > seq(high)) {
      val mean = (low + high) / 2

      // left sorted
      if (seq(mean) > seq(high)) {
        low = mean + 1
      } else {
        high = mean
      }
    }

    low
  }

  def findPivot(seq: Seq[Int]): Int = {
    var low = 0
    var high = seq.length - 1

    while (low + 1 < high) {
      val mean = (low + high) / 2

      if (seq(low) > seq(mean)) {
        high = mean
      } else if (seq(mean) > seq(high)) {
        low = mean
      } else {
        return 0
      }
    }

    if (seq(low) > seq(high)) high
    else 0 // not found
  }

  def binarySearch(seq: Seq[Int], value: Int): Int = {
    var low = 0
    var high = seq.length - 1

    while (low <= high) {
      val mean = (low + high) / 2

      if (value < seq(mean)) {
        high = mean - 1
      } else if (value > seq(mean)) {
        low = mean + 1
      } else /* value == seq(mean) */{
        return mean
      }
    }

    -1 // value not found
  }

  object RotatedView {
    def Left[T](underlying: Seq[T], by: Int): Seq[T] = new Seq[T] {
      private val _length = underlying.length
      private val _by     = if (by < 0) by % length + length else by

      override def length: Int = _length

      override def apply(idx: Int): T = {
        if (idx >= length)
          throw new IndexOutOfBoundsException(idx.toString)

        underlying.apply((idx + _by) % length)
      }

      override def iterator: Iterator[T] =
        Iterator.range(0, length).map(apply)
    }

    def Right[T](seq: Seq[T], by: Int): Seq[T] = Left(seq, -by)
  }


}
