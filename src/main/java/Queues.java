import java.util.*;

public class Queues {
    private static List<Integer> primes = primesLessThan(1001);


//    // solve given n, find min y = max(a, b) where n = a * b
//    private static int solve(int n) {
//        ArrayList<Integer> primeFactors = new ArrayList<Integer>();
//
//        int left = n;
//
//        for (int prime: primes) {
//            if (prime > left) {
//                break;
//            }
//
//            while (left % prime == 0) {
//                left /= prime;
//                primeFactors.add(prime);
//            }
//        }
//
//        int min = n;
//        int a = 1;
//        int b = n;
//
//        for (int factor : primeFactors) {
//            a *= factor;
//            b /= factor;
//            min = Math.min(Math.max(a, b), min);
//        }
//
//        return min;
//    }

    private static List<Integer> primesLessThan(int n) {
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

    // solve given n, find min y = max(a, b) where n = a * b
    private static int solve(int n) {
        int min = n;

        for (int i = 2, max = (int) Math.sqrt(n); i <= max; ++i) {
            if (n % i == 0) {
                min = Math.min(n / i, min);
            }
        }

        return min;
    }

    static class NWithStep {
        int n, step;

        public NWithStep(int n, int step) {
            this.n = n;
            this.step = step;
        }
    }

    // www.hackerrank.com/challenges/down-to-zero-ii
    public static void downToZero() {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        label: for (int i = 0; i < q; ++i) {
            int n = in.nextInt();

            BitSet visited = new BitSet();
            Deque<NWithStep> toVisit = new ArrayDeque<>();
            toVisit.addLast(new NWithStep(n, 0));

            while(!toVisit.isEmpty()) {
                NWithStep visiting = toVisit.removeFirst();

                if (visiting.n == 0) {
                    System.out.println(visiting.step);
                    continue label;
                }

                if (visited.get(visiting.n)) {
                    continue;
                }

                visited.set(visiting.n);

                for (int j = 2, max = (int) Math.sqrt(visiting.n); j <= max; ++j) {
                    if (visiting.n % j == 0) {
                        toVisit.addLast(new NWithStep(visiting.n / j, visiting.step + 1));
                    }
                }

                toVisit.addLast(new NWithStep(visiting.n - 1, visiting.step + 1));
            }
        }
    }

    public static void main(String[] args) {
        downToZero();
    }
}
