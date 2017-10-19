import java.util.Scanner;

public class Trees {

    // www.hackerrank.com/challenges/swap-nodes-algo
    static void swapNodesAlgo() {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());

        Node[] nodes = new Node[n + 1];

        for (int i = 1; i <= n; ++i) {
            nodes[i] = new Node();
            nodes[i].index = i;
        }

        for (int i = 1; i <= n; ++i) {
            String[] childs = scan.nextLine().split(" ");
            int left = Integer.parseInt(childs[0]);
            int right = Integer.parseInt(childs[1]);
            nodes[i].left = (left == -1) ? null : nodes[left];
            nodes[i].right = (right == -1) ? null : nodes[right];
        }

        Node head = nodes[1];

        int t = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < t; ++i) {
            int k = Integer.parseInt(scan.nextLine());
            swap(head, 1, k);
            printTree(head);
            System.out.println();
        }
    }

    static class Node {
        int index;
        Node left;
        Node right;
    }

    static void printTree(Node root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.index + " ");
            printTree(root.right);
        }
    }

    static void swap(Node root, int depth, int k) {
        if (root != null) {
            if (depth % k == 0) {
                Node left = root.left;
                root.left = root.right;
                root.right = left;
            }

            swap(root.left, depth + 1, k);
            swap(root.right, depth + 1, k);
        }
    }

}
