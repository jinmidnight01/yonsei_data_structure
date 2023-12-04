/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */


public final class PrefixMin implements IPrefixMin{
    /*
     * you may declare additional variables here
     */
    Node root;

    PrefixMin() {
        /*
         * implement your constructor here.
         */
        root = new Node(null, null, null);
    }

    @Override
    public int getValue(YonseiString key) {
        /*
         * Function input:
         *  + a YonseiString object key
         *
         * Does:
         * if there exists an entry with the given key, returns the value associated with the given key.
         * otherwise, returns Integer.MIN_VALUE.
         */
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            int characterNumber = key.charAt(i);
            if (cur.children[characterNumber] == null) {
                return Integer.MIN_VALUE;
            }
            cur = cur.children[characterNumber];
        }

        if (cur.value == null) {
            return Integer.MIN_VALUE;
        }
        return cur.value;
    }

    @Override
    public void insert(YonseiString key, int value) {
        /*
         * Function input:
         *  + an entry of the pair (key, value)
         *
         * Does:
         * if there does not exist an entry with the given key, insert the given entry.
         * otherwise, do nothing.
         */
        Node cur = root;
        boolean isExist = true;
        for (int i = 0; i < key.length(); i++) {
            int characterNumber = key.charAt(i);
            if (cur.children[characterNumber] == null) {
                if (i == key.length() - 1) {
                    cur.children[characterNumber] = new Node(characterNumber, value, cur);
                } else {
                    cur.children[characterNumber] = new Node(characterNumber, null, cur);
                }
                isExist = false;
            }
            cur = cur.children[characterNumber];
            cur.count++;
        }

        if (isExist && cur.value != null) {
            return;
        } else if (isExist) {
            cur.value = value;
        }

        while (cur != root) {
            if (cur.minValue != null) {
                if (cur.minValue > value) {
                    cur.minValue = value;
                } else {
                    break;
                }
            } else {
                cur.minValue = value;
            }
            cur = cur.parent;
        }
    }

    @Override
    public void remove(YonseiString key) {
        /*
         * Function input:
         *  + a YonseiString object key
         *
         * Does:
         * if there exists an entry with the given key, then remove that entry.
         * otherwise, do nothing.
         */
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            int characterNumber = key.charAt(i);
            if (cur.children[characterNumber] == null) {
                return;
            }
            cur = cur.children[characterNumber];
        }

        while (cur.count == 1) {
            cur.parent.children[cur.characterNumber] = null;
            cur = cur.parent;
        }

        // Update minValue fields upwards in the trie

    }

    @Override
    public void clear() {
        /*
         * Function input:
         *  none
         *
         * Does:
         * removes all entries from this instance.
         */
        root = new Node(null, null, null);
    }

    @Override
    public int getMin(YonseiString query) {
        /*
         * Function input:
         *  + a YonseiString object query
         *
         * Does:
         * returns the minimum value over all entries which have the query as a proper prefix of the entry's key.
         * if there are no such entries, then return Integer.MIN_VALUE.
         */
        Node cur = root;
        for (int i = 0; i < query.length(); i++) {
            int characterNumber = query.charAt(i);
            if (cur.children[characterNumber] == null) {
                return Integer.MIN_VALUE;
            }
            cur = cur.children[characterNumber];
        }
        return cur.minValue;
    }

    private static class Node {
        Integer characterNumber;
        Integer value;
        Node parent;
        Node[] children;
        Integer minValue;
        Integer count;

        Node(Integer characterNumber, Integer value, Node parent) {
            this.characterNumber = characterNumber;
            this.value = value;
            this.parent = parent;
            this.children = new Node[256];
            this.minValue = null;
            this.count = 0;
        }
    }
}
