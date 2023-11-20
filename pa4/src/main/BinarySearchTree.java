/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class BinarySearchTree<T extends Comparable> implements IBinarySearchTree<T> {
    /*
     * you may declare variables here
     */
    Node<T> root;
    int size;

    BinarySearchTree() {
        /*
         * implement your constructor here.
         */
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean contains(T entry) {
        /*
         * Function input:
         *  + entry: target entry.
         *
         * Does:
         * returns true if the binary search tree contains the given entry. otherwise returns false.
         */
        return search(root, entry) != null;
    }

    private Node<T> search(Node<T> node, T entry) {
        if (node == null || entry.compareTo(node.key) == 0) {
            return node;
        }
        if (entry.compareTo(node.key) < 0) {
            return search(node.left, entry);
        }
        return search(node.right, entry);
    }

    @Override
    public void put(T entry) {
        /*
         * Function input:
         *  + entry: entry to put in the BST.
         *
         * Does:
         * if entry is not in the binary search tree, inserts entry into the binary search tree.
         * otherwise do nothing.
         */
        root = insert(root, entry);
    }

    private Node<T> insert(Node<T> node, T entry) {
        if (node == null) {
            size++;
            return new Node<T>(entry);
        }
        if (entry.compareTo(node.key) < 0) {
            node.left = insert(node.left, entry);
        } else if (entry.compareTo(node.key) > 0) {
            node.right = insert(node.right, entry);
        } else {
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));

        int gapValue = gapValue(node);
        if (gapValue > 1 && entry.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }
        if (gapValue < -1 && entry.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }
        if (gapValue > 1 && entry.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (gapValue < -1 && entry.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    @Override
    public void remove(T entry) {
        /*
         * Function input:
         *  + entry: entry to remove from the BST.
         *
         * Does:
         * if entry is in the binary search tree, removes entry from the binary search tree.
         * otherwise do nothing.
         */
        root = delete(root, entry);
    }

    private Node<T> delete(Node<T> node, T entry) {
        if (node == null) {
            return node;
        }

        if (entry.compareTo(node.key) < 0) {
            node.left = delete(node.left, entry);
        } else if (entry.compareTo(node.key) > 0) {
            node.right = delete(node.right, entry);
        } else {
            if (node.left == null || node.right == null) {
                Node<T> temp;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }
                node = temp;
                size--;
            } else {
                Node<T> cur = node.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                node.key = cur.key;
                node.right = delete(node.right, cur.key);
            }
        }

        if (node == null) {
            return node;
        }

        int gapValue = gapValue(node);
        if (gapValue > 1 && gapValue(node.left) >= 0) {
            return rightRotate(node);
        }
        if (gapValue > 1 && gapValue(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (gapValue < -1 && gapValue(node.right) <= 0) {
            return leftRotate(node);
        }
        if (gapValue < -1 && gapValue(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    @Override
    public void clear() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * removes all entries from the binary search tree.
         */
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * returns the number of entries in the binary search tree.
         */
        return size;
    }

    @Override
    public T getNext(T threshold) {
        /*
         * Function input:
         *  + threshold: comparison object.
         *
         * Does:
         * returns the element with the minimum key that is greater than the key of the given threshold object.
         */
        T min = null;
        Node<T> cur = root;
        while (cur != null) {
            if (cur.key.compareTo(threshold) > 0) {
                if (min == null || cur.key.compareTo(min) < 0) {
                    min = cur.key;
                }
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return min;
    }

    @Override
    public T getPrev(T threshold) {
        /*
         * Function input:
         *  + threshold: comparison object.
         *
         * Does:
         * returns the element with the maximum key that is no greater than the key of the given threshold object.
         */
        T max = null;
        Node<T> cur = root;
        while (cur != null) {
            if (cur.key.compareTo(threshold) <= 0) {
                if (max == null || cur.key.compareTo(max) > 0) {
                    max = cur.key;
                }
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return max;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private int gapValue(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        Node<T> rightChild = leftChild.right;

        leftChild.right = node;
        node.left = rightChild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;

        return leftChild;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        Node<T> leftChild = rightChild.left;

        rightChild.left = node;
        node.right = leftChild;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;

        return rightChild;
    }

    private static class Node<T> {
        T key;
        int height;
        Node<T> left, right;

        Node(T key) {
            this.key = key;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }
}
