import java.util.*;

/*
class Permutations<T> implements Iterator<T[]> {

	private final T[] repr;

	public Permutations(T[] repr) {
		this.repr = java.util.Arrays.copyOf(repr, repr.length);
	}


	public boolean hasNext() {
		return false;
	}

	public T[] next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		return null;
	}
}
*/

class Permutations implements Iterator<int[]> {

	public Permutations(int n) {
		
	}


	public boolean hasNext() {
		return false;
	}

	public int[] next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		return null;
	}
}