class BitManipulation {
	static boolean bitParity(int x) {
		x = x ^ (x >> 16);
		x = x ^ (x >> 8);
		x = x ^ (x >> 4);
		x = x ^ (x >> 2);
		x = x ^ (x >> 1);

		return (x & 1) == 0;
	}

	public static void main(String[] args) {
		System.out.println(bitParity(0));
		System.out.println(bitParity(1));
		System.out.println(bitParity(0xFFFF));
		System.out.println(bitParity(0xFFFE));
	}
}