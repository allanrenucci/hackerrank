
public class LinkedLists {
    static class Node {
        int data;
        Node next;
        Node prev;
    }

    Node mergeLists(Node headA, Node headB) {
        Node last = new Node();
        Node head = last;

        while (headA != null && headB != null) {
            Node node = new Node();

            if (headA.data < headB.data) {
                node.data = headA.data;
                headA = headA.next;
            } else {
                node.data = headB.data;
                headB = headB.next;
            }

            last.next = node;
            last = node;
        }

        if (headA != null) {
            last.next = headA;
        } else if (headB != null) {
            last.next = headB;
        }

        return head.next;
    }

    Node mergeLists2(Node headA, Node headB) {
        if (headA != null && headB != null) {
            Node node = new Node();

            if (headA.data < headB.data) {
                node.data = headA.data;
                node.next = mergeLists2(headA.next, headB);
            } else /* headB.data <= headA.data */ {
                node.data = headB.data;
                node.next = mergeLists2(headA, headB.next);
            }

            return node;
        } else {
            return (headA != null) ? headA : headB;
        }
    }

    int findMergeNode(Node headA, Node headB) {
        Node last = headA;

        while (last.next != null) {
            last = last.next;
        }

        last.next = headB;

        return findCycle(headA).data;
    }

    Node findCycle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    Node SortedInsert(Node head, int data) {
        Node node = new Node();
        node.data = data;

        if (head == null) {
            return node;
        }

        if (head.data > data) {
            node.next = head;
            head.prev = node;
            return node;
        }

        Node current = head;
        Node next = head.next;

        while (next != null && next.data < data) {
            current = next;
            next = next.next;
        }

        current.next = node;
        node.prev = current;
        node.next = next;

        if (next != null) {
            next.prev = node;
        }

        return head;
    }
}
