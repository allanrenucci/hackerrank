import java.io.*;
import java.util.*;

public class Stacks {

    /** First n primes */
    public static int[] primes(int n) {
        int found = 0;
        int[] primes = new int[n];

        boolean[] search = new boolean[n];
        search[0] = true;

        int nextPrime = 0;

        while (found < n) {
            while (!search[nextPrime]) {
                nextPrime++;
            }

            for (int i = nextPrime; i < search.length; ++i)


            primes[found++] = nextPrime;
        }

        return null;
    }

    // https://www.hackerrank.com/challenges/waiter
    public static void waiter() {

    }


    public static void maximumElement() {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        for (int i = 0; i < n; ++i) {
            int type = scan.nextInt();
            switch (type) {
                case 1:
                    int x = scan.nextInt();
                    stack.addFirst(x);

                    Integer max = maxStack.peekFirst();
                    if (max == null || x >= max) {
                        maxStack.addFirst(x);
                    }

                    break;

                case 2:
                    if (stack.removeFirst().equals(maxStack.peekFirst())) {
                        maxStack.removeFirst();
                    }
                    break;

                case 3:
                    System.out.println(maxStack.peekFirst());
                    break;
            }
        }
    }

    public static void balancedBrackets() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Deque<Character> stack = new ArrayDeque<>();

        label: for(int i = 0; i < n; ++i) {
            stack.clear();
            String s = in.next();

            for (int j = 0; j < s.length(); ++j) {
                char c = s.charAt(j);

                switch(c) {
                    case '(':
                    case '{':
                    case '[':
                        stack.addFirst(c);
                        break;

                    case ')':
                        if (stack.isEmpty() || stack.removeFirst() != '(') {
                            System.out.println("NO");
                            continue label;
                        }
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.removeFirst() != '{') {
                            System.out.println("NO");
                            continue label;
                        }
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.removeFirst() != '[') {
                            System.out.println("NO");
                            continue label;
                        }
                        break;
                }
            }

            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
