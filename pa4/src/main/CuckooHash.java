/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

import java.util.Random; // you can use Random in this file.

public final class CuckooHash implements ICuckooHash{
    int a1, a2, b1, b2, N; // do not change the signatures
    /*
     * you may declare additional variables here
     */
    double threshold;
    int chainLength;
    Node[] table1;
    Node[] table2;
    LinkedList elements;

    CuckooHash(double threshold, int chainLength, int N) {
        /*
         * implement your constructor here.
         */
        Random rand = new Random();
        this.a1 = rand.nextInt(1, N);
        this.a2 = rand.nextInt(1, N);
        this.b1 = rand.nextInt(0, N);
        this.b2 = rand.nextInt(0, N);
        this.N = N;
        this.threshold = threshold;
        this.chainLength = chainLength;
        this.table1 = new Node[N];
        this.table2 = new Node[N];
        this.elements = new LinkedList();
    }

    @Override
    public boolean contains(int x) {
        /*
         * Function input:
         *  + an integer x
         *
         * Does:
         * returns true if x is in the hash table. returns false otherwise.
         */
        if (table1[hash1(x)] != null && table1[hash1(x)].key == x) {
            return true;
        }
        return table2[hash2(x)] != null && table2[hash2(x)].key == x;
    }

    @Override
    public void delete(int x) {
        /*
         * Function input:
         *  + an integer x
         *
         * Does:
         * if x is in the hash table, deletes x from the hash table.
         * otherwise, do nothing.
         */
        if (!contains(x)) {
            return;
        }

        if (table1[hash1(x)] != null && table1[hash1(x)].key == x) {
            elements.remove(table1[hash1(x)]);
            table1[hash1(x)] = null;
        } else {
            elements.remove(table2[hash2(x)]);
            table2[hash2(x)] = null;
        }
    }

    @Override
    public void insert(int x) {
        /*
         * Function input:
         *  + an integer x
         *
         * Does:
         * if x is not in the hash table, inserts x to the hash table according to the behavior specification.
         * otherwise, do nothing.
         */
        if (contains(x)) {
            return;
        }

        Node element = new Node(x);
        elements.add(element);
        if (elements.size >= 2 * N * threshold) {
            resize();
            return;
        }
        int chain = 0;
        chain = put(element, chain);
        if (chain >= chainLength) {
            resize();
        }
    }

    private void resize() {
        Random rand = new Random();
        N *= 2;
        a1 = rand.nextInt(1, N);
        a2 = rand.nextInt(1, N);
        b1 = rand.nextInt(0, N);
        b2 = rand.nextInt(0, N);
        table1 = new Node[N];
        table2 = new Node[N];

        LinkedList elementsTemp = elements;
        elements = new LinkedList();

        Node cur = elementsTemp.head;
        while (cur != null) {
            insert(cur.key);
            cur = cur.next;
        }
    }

    private int put(Node element, int chain) {
        int hash1 = hash1(element.key);
        if (table1[hash1] == null) {
            table1[hash1] = element;
        } else {
            chain++;
            if (chain >= chainLength) {
                return chain;
            }
            Node temp = table1[hash1];
            table1[hash1] = element;
            int hash2 = hash2(temp.key);
            if (table2[hash2] == null) {
                table2[hash2] = temp;
            } else {
                chain++;
                if (chain >= chainLength) {
                    return chain;
                }
                Node temp2 = table2[hash2];
                table2[hash2] = temp;
                return put(temp2, chain);
            }
        }
        return chain;
    }

    // implemented for you. do not change this method.
    @Override
    public HashParameter getParams() {
        return new HashParameter(a1, a2, b1, b2, N);
    }

    private int hash1(int x) {
        return Math.floorMod(a1 * x + b1, N);
    }

    private int hash2(int x) {
        return Math.floorMod(a2 * x + b2, N);
    }

    private static class LinkedList {
        Node head;
        Node tail;
        int size;

        LinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public void add(Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            size++;
        }

        public void remove(Node node) {
            if (node == head) {
                head = head.next;
            } else if (node == tail) {
                tail = tail.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }

    }

    private static class Node {
        int key;
        Node next;
        Node prev;
        Node(int key) {
            this.key = key;
            this.next = null;
            this.prev = null;
        }
    }
}
