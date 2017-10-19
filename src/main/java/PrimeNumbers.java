import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class PrimeNumbers {

    private static List<Integer> primesLessThan(int n) {
        boolean[] isPrime = new boolean[n];
        java.util.Arrays.fill(isPrime, true);
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i < n; ++i) {
            if (isPrime[i]) {
                primes.add(i);

                for (int j = i * i; j < n && j > 0; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        return primes;
    }

    private static List<Integer> primesLessThanV2(int n) {
        BitSet isNotPrime = new BitSet(n);
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i < n; ++i) {
            if (!isNotPrime.get(i)) {
                primes.add(i);

                for (int j = i * i; j < n && j > 0; j += i) {
                    isNotPrime.set(j);
                }
            }
        }

        return primes;
    }

    public static List<Integer> firstNPrimes(int n) {
        int size = 1024;
        boolean[] isComposite = new boolean[size];
        List<Integer> primes = new ArrayList<>();


        for (int i = 2;;) {
            for (; i < size; ++i) {
                if (!isComposite[i]) {
                    // Prime found

                    primes.add(i);

                    for (int j = i * i; j < size && j > 0 /* detect overflow from (i * i) */; j += i) {
                        isComposite[j] = true;
                    }

                    if (primes.size() >= n) {
                        return primes;
                    }
                }
            }

            // Resize array
            size *= 2;
            boolean[] tmp = new boolean[size];
            System.arraycopy(isComposite, 0, tmp, 0, isComposite.length);
            isComposite = tmp;

            // populate new array
            for (int prime: primes) {
                // first multiple of prime after i
                int multiple = i + prime - 1;
                multiple -= multiple % prime;

                for (int j = multiple; j < size; j += prime) {
                    isComposite[j] = true;
                }
            }
        }
    }



    public static void main(String[] args) {
        int[] ns = {100, 1000, 10000, 100000, 100000, 1000000};

        for (int n : ns) {
            System.out.println("Primes less than " + n + ": " + primesLessThanV2(n).size());
        }
    }
}
