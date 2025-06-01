import java.util.*;

public class MyHashMapApp {
    static class MyHashMap {
        class Entry {
            int key, value;
            Entry next;
            Entry(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size = 10007;
        private Entry[] table;

        public MyHashMap() {
            table = new Entry[size];
        }

        private int hash(int key) {
            return key % size;
        }

        public void put(int key, int value) {
            int index = hash(key);
            Entry head = table[index];
            Entry curr = head;
            while (curr != null) {
                if (curr.key == key) {
                    curr.value = value;
                    return;
                }
                curr = curr.next;
            }
            Entry newNode = new Entry(key, value);
            newNode.next = head;
            table[index] = newNode;
        }

        public int get(int key) {
            int index = hash(key);
            Entry curr = table[index];
            while (curr != null) {
                if (curr.key == key) return curr.value;
                curr = curr.next;
            }
            return -1;
        }

        public void remove(int key) {
            int index = hash(key);
            Entry curr = table[index];
            Entry prev = null;
            while (curr != null) {
                if (curr.key == key) {
                    if (prev == null) table[index] = curr.next;
                    else prev.next = curr.next;
                    return;
                }
                prev = curr;
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyHashMap map = new MyHashMap();
        while (true) {
            System.out.println("Choose operation: 1. put 2. get 3. remove 4. exit");
            int op = sc.nextInt();
            if (op == 1) {
                System.out.println("Enter key and value:");
                int key = sc.nextInt();
                int value = sc.nextInt();
                map.put(key, value);
            } else if (op == 2) {
                System.out.println("Enter key to get value:");
                int key = sc.nextInt();
                System.out.println("Value: " + map.get(key));
            } else if (op == 3) {
                System.out.println("Enter key to remove:");
                int key = sc.nextInt();
                map.remove(key);
            } else {
                break;
            }
        }
    }
}
