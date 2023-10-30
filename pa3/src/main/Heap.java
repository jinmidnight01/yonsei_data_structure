/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Heap<T extends Comparable> implements IHeap<T> {
    /*
     * you may declare variables here
     */
    Node<T> root;
    Node<T> bottomLeftMost;
    Node<T> end;
    int size;
    int height;

    Heap() {
        /*
         * implement your constructor here.
         */
        root = null;
        bottomLeftMost = null;
        end = null;
        size = 0;
        height = 0;
    }

    @Override
    public T min() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * returns the minimum entry without removing it
         */
        if (root != null) {
            return root.key;
        }
        return null;
    }

    @Override
    public T removeMin() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * returns the minimum entry and removes it
         */
        if (root == null) return null;
        T min = root.key;
        swap(root, end);
        Node<T> temp = end;
        if (temp.parent == null) {
            clear();
        } else if (temp == bottomLeftMost) {
            bottomLeftMost = temp.parent;
            temp.parent.leftChild = null;
            temp.prev.next = null;
            end = temp.prev;
            height -= 1;
            size -= 1;
        } else if (temp.parent.rightChild == null) {
            temp.parent.leftChild = null;
            temp.prev.next = null;
            end = temp.prev;
            size -= 1;
        } else {
            temp.parent.rightChild = null;
            temp.prev.next = null;
            end = temp.prev;
            size -= 1;
        }
        heapify(root);
        return min;
    }

    @Override
    public void insert(T entry) {
        /*
         * Function input:
         *  + entry: entry to insert into heap.
         *
         * Does:
         * inserts the entry into the heap.
         */
        Node<T> newNode = new Node<>(entry);
        putNewNode(newNode);

        while (newNode.parent != null && newNode.key.compareTo(newNode.parent.key) < 0) {
            swap(newNode, newNode.parent);
            newNode = newNode.parent;
        };
    }

    @Override
    public void clear() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * removes all entries of the heap.
         */
        root = null;
        bottomLeftMost = null;
        end = null;
        size = 0;
        height = 0;
    }

    @Override
    public int getSize() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * returns how many entries are currently in the heap.
         */
        return size;
    }

    @Override
    public boolean isEmpty() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * returns true if the heap has no entries. Otherwise returns false.
         */
        return size == 0;
    }

    @Override
    public void merge(Heap<T> otherHeap) {
        /*
         * Function input:
         *  + otherHeap: heap to merge with.
         *
         * Does:
         * merge the otherHeap with this heap.
         * the result of merging should be in this heap.
         * you may assume that the otherHeap will not be used afterwards,
         * so you can change the heap structure of the otherHeap.
         * moreover, you may assume that the entries in the two heap are to be disjoint.
         */
        Node<T> otherHeapTemp = otherHeap.root;
        while (otherHeapTemp != null) {
            Node<T> newNode = new Node<>(otherHeapTemp.key);
            putNewNode(newNode);
            otherHeapTemp = otherHeapTemp.next;
        }

        Node<T> temp = end;
        while (temp != null) {
            heapify(temp);
            temp = temp.prev;
        }
    }

    private static class Node<T extends Comparable> {
        T key;
        Node<T> parent;
        Node<T> leftChild; Node<T> rightChild;
        Node<T> prev; Node<T> next;

        public Node(T key) {
            this.key = key;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
            this.prev = null;
            this.next = null;
        }
    }

    private void putNewNode(Node<T> newNode) {
        if (root == null) {
            root = newNode;
            bottomLeftMost = newNode;
        } else {
            Node<T> temp = end;
            if (Math.pow(2, height + 1) == size + 1) {
                bottomLeftMost.leftChild = newNode;
                newNode.parent = bottomLeftMost;
                bottomLeftMost = newNode;
                height += 1;
            } else {
                if (temp.parent.rightChild == null) {
                    temp.parent.rightChild = newNode;
                    newNode.parent = temp.parent;
                } else {
                    temp.parent.next.leftChild = newNode;
                    newNode.parent = temp.parent.next;
                }
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        end = newNode;
        size += 1;
    }

    private void heapify(Node<T> node) {
        if (node == null) return;
        if (node.leftChild == null && node.rightChild == null) return;
        else if (node.rightChild == null) {
            if (node.leftChild.key.compareTo(node.key) < 0) {
                swap(node.leftChild, node);
                heapify(node.leftChild);
            }
        } else {
            if (node.leftChild.key.compareTo(node.rightChild.key) <= 0) {
                if (node.leftChild.key.compareTo(node.key) < 0) {
                    swap(node.leftChild, node);
                    heapify(node.leftChild);
                }
            } else {
                if (node.rightChild.key.compareTo(node.key) < 0) {
                    swap(node.rightChild, node);
                    heapify(node.rightChild);
                }
            }
        }
    }

    private void swap(Node<T> node1, Node<T> node2) {
        T temp = node1.key;
        node1.key = node2.key;
        node2.key = temp;
    }
}
