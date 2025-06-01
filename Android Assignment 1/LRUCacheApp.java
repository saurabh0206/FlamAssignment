import java.util.*;

public class LRUCacheApp {
    static class LRUCache {
        class Node {
            int key, value;
            Node prev, next;
            Node(int k, int v) {
                key = k;
                value = v;
            }
        }

        int capacity;
        Map<Integer, Node> map;
        Node head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            remove(node);
            insert(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) remove(map.get(key));
            if (map.size() == capacity) remove(tail.prev);
            insert(new Node(key, value));
        }

        private void insert(Node node) {
            map.put(node.key, node);
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void remove(Node node) {
            map.remove(node.key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cache capacity:");
        int capacity = sc.nextInt();
        LRUCache lru = new LRUCache(capacity);
        while (true) {
            System.out.println("Choose operation: 1. put 2. get 3. exit");
            int op = sc.nextInt();
            if (op == 1) {
                System.out.println("Enter key and value:");
                int key = sc.nextInt();
                int value = sc.nextInt();
                lru.put(key, value);
            } else if (op == 2) {
                System.out.println("Enter key to get value:");
                int key = sc.nextInt();
                int val = lru.get(key);
                System.out.println("Value: " + val);
            } else {
                break;
            }
        }
    }
}
