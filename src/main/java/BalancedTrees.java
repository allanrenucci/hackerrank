public class BalancedTrees {
    static class Node {
        int val;
        int ht;
        Node left;
        Node right;
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            Node node = new Node();
            node.val = val;
            node.ht = 0;
            return node;
        } else if (val < root.val) {
            root.left = insert(root.left, val);
            root.ht = computeHeight(root);
            root = balance(root);

        } else if (val > root.val) {
            root.right = insert(root.right, val);
            root.ht = computeHeight(root);
            root = balance(root);
        }

        return root;
    }

    static Node balance(Node root) {
        if (!isBalanced(root)) {
            int bfLeft = balanceFactor(root.left);

            if (bfLeft == -1) {
                root = leftRightCase(root);
            } else if (bfLeft == 1) {
                root = leftLeftCase(root);
            } else {
                int bfRight = balanceFactor(root.right);

                if (bfRight == 1) {
                    root = rightLeftCase(root);
                } else if (bfRight == -1) {
                    root = rightRightCase(root);
                }
            }
        }

        return root;
    }

    static Node leftRightCase(Node root) {
        Node n5 = root;
        Node n3 = n5.left;
        Node n4 = n3.right;

        Node a = n3.left;
        Node b = n4.left;
        Node c = n4.right;
        Node d = n5.right;

        n3.left = a;
        n3.right = b;
        n4.left = n3;
        n4.right = c;
        n5.left = n4;
        n5.right = d;


        // compute height bottom up
        n3.ht = computeHeight(n3);
        n4.ht = computeHeight(n4);
        n5.ht = computeHeight(n5);

        return leftLeftCase(n5);
    }

    static Node leftLeftCase(Node root) {
        Node n5 = root;
        Node n4 = n5.left;
        Node n3 = n4.left;

        Node a = n3.left;
        Node b = n3.right;
        Node c = n4.right;
        Node d = n5.right;

        n3.left = a;
        n3.right = b;
        n4.left = n3;
        n4.right = n5;
        n5.left = c;
        n5.right = d;

        // compute height bottom up
        n3.ht = computeHeight(n3);
        n5.ht = computeHeight(n5);
        n4.ht = computeHeight(n4);

        return n4;
    }

    static Node rightLeftCase(Node root) {
        Node n3 = root;
        Node n5 = n3.right;
        Node n4 = n5.left;

        Node a = n3.left;
        Node b = n4.left;
        Node c = n4.right;
        Node d = n5.right;

        n3.left = a;
        n3.right = n4;
        n4.left = b;
        n4.right = n5;
        n5.left = c;
        n5.right = d;

        // compute height bottom up
        n5.ht = computeHeight(n5);
        n4.ht = computeHeight(n4);
        n3.ht = computeHeight(n3);

        return rightRightCase(n3);
    }

    static Node rightRightCase(Node root) {
        Node n3 = root;
        Node n4 = n3.right;
        Node n5 = n4.right;

        Node a = n3.left;
        Node b = n4.left;
        Node c = n5.left;
        Node d = n5.right;

        n3.left = a;
        n3.right = b;
        n4.left = n3;
        n4.right = n5;
        n5.left = c;
        n5.right = d;

        // compute height bottom up
        n3.ht = computeHeight(n3);
        n5.ht = computeHeight(n5);
        n4.ht = computeHeight(n4);

        return n4;
    }

    static boolean isBalanced(Node root) {
        if (root == null) {
            return true;
        } else {
            int bf = balanceFactor(root);
            return  bf >= -1 && bf <= 1;
        }
    }

    static int balanceFactor(Node root) {
        return (root == null) ? 0 : height(root.left) - height(root.right);
    }

    static int height(Node node) {
        return (node == null) ? -1 : node.ht;
    }

    static int computeHeight(Node node) {
        return 1 + java.lang.Math.max(height(node.left), height(node.right));
    }
}
