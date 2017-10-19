import java.io.*;
import java.util.*;

public class Arrays {

    public static void main(String[] args) {
        //arrayLeftRotation2();
        //dynamicArray();
        //array2DS();
        //arrayDS();
        int[] array = {12, 10, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        mergeSort(array);
        System.out.println(java.util.Arrays.toString(array));
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    private static void mergeSort(int[] arr, int start, int end, int[] tmp) {
        if (end <= start) {
            return;
        }

        int mid = (start + end) / 2;
        mergeSort(arr, start, mid, tmp);
        mergeSort(arr, mid + 1, end, tmp);
        merge(arr, start, end, tmp);
    }

    private static void merge(int[] arr, int start, int end, int[] tmp) {
        int mid = (start + end) / 2;

        int leftEnd = mid;
        int rightEnd = end;
        int left = start;
        int right = mid + 1;
        int index = left;

        while (left <= leftEnd && right <= rightEnd) {
            if (arr[left] <= arr[right]) {
                tmp[index] = arr[left];
                left += 1;
            } else {
                tmp[index] = arr[right];
                right += 1;
            }

            index += 1;
        }

        System.arraycopy(arr, left, tmp, index, leftEnd - left + 1);
        System.arraycopy(arr, right, tmp, index, rightEnd - right + 1);
        System.arraycopy(tmp, start, arr, start, end - start + 1);
    }

    //https://www.hackerrank.com/challenges/sparse-arrays
    public static void sparseArrays() {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

        int n = Integer.parseInt(line);

        HashMap<String, Integer> strings = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String string = scan.nextLine();
            int count = strings.getOrDefault(string, 0);
            strings.put(string, count + 1);
        }

        line = scan.nextLine();
        int q = Integer.parseInt(line);

        for (int i = 0; i < q; i++) {
            String query = scan.nextLine();
            System.out.println(strings.getOrDefault(query, 0));
        }
    }

    //https://www.hackerrank.com/challenges/array-left-rotation
    public static void arrayLeftRotation() {
        Scanner scan = new Scanner(System.in);
        String[] firstLine = scan.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int d = Integer.parseInt(firstLine[1]);
        String[] array = scan.nextLine().split(" ");
        String[] rotated = new String[array.length];

        for (int i = 0; i < array.length; ++i) {
            rotated[i] = array[(i + d) % array.length];
            System.out.print(rotated[i] + " ");
        }
    }

    //https://www.hackerrank.com/challenges/array-left-rotation
    public static void arrayLeftRotation2() {
        Scanner scan = new Scanner(System.in);
        String[] line = scan.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int d = Integer.parseInt(line[1]);
        String[] array = scan.nextLine().split(" ");

        for (int i = 0; i < d; i++) {
            String tmp = array[0];
            System.arraycopy(array, 1, array, 0, array.length - 1);
            array[array.length - 1] = tmp;
        }

        for (String i: array) {
            System.out.print(i + " ");
        }
    }

    public static void dynamicArray() {
        Scanner scan = new Scanner(System.in);
        String[] line = scan.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int q = Integer.parseInt(line[1]);
        ArrayList<Integer>[] seqList = new ArrayList[n];

        for (int i = 0; i < seqList.length; ++i) {
            seqList[i] = new ArrayList<>();
        }

        int lastAns = 0;

        for (int i = 0; i < q; ++i)  {
            line = scan.nextLine().split(" ");
            int type = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            int y = Integer.parseInt(line[2]);

            int index = (x ^ lastAns) % n;

            if (type == 1) {
                seqList[index].add(y);
            } else {
                ArrayList<Integer> seq  = seqList[index];
                lastAns = seq.get(y % seq.size());
                System.out.println(lastAns);
            }
        }
    }

    // https://www.hackerrank.com/challenges/arrays-ds
    public static void arrayDS() {
        Scanner scan = new Scanner(System.in);
        int N = Integer.parseInt(scan.nextLine());
        String ns[] = scan.nextLine().split(" ");

        for (int i = ns.length - 1; i > 0; i--) {
            System.out.print(ns[i] + " ");
        }

        System.out.println(ns[0]);
    }

    // https://www.hackerrank.com/challenges/2d-array
    public static void array2DS() {
        Scanner scan = new Scanner(System.in);
        int[][] matrix = new int[6][6];

        for (int i = 0; i < 6; ++i) {
            String[] line = scan.nextLine().split(" ");

            for (int j = 0; j < 6; ++j) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        int max = -9 * 7;

        for (int i = 0; i <= 3; ++i) {
            for (int j = 0; j <= 3; ++j) {
                int sum = 0;
                sum += matrix[i][j];
                sum += matrix[i][j + 1];
                sum += matrix[i][j + 2];
                sum += matrix[i + 1][j + 1];
                sum += matrix[i + 2][j];
                sum += matrix[i + 2][j + 1];
                sum += matrix[i + 2][j + 2];
                max = Math.max(max, sum);
            }

        }

        System.out.println(max);
    }
}
