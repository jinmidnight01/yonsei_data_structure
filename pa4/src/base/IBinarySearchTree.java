public interface IBinarySearchTree<T extends Comparable> {
    boolean contains(T entry);
    void put(T entry);
    void remove(T entry);
    void clear();
    int getSize();
    T getNext(T threshold);
    T getPrev(T threshold);
}
