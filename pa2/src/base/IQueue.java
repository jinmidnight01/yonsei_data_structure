public interface IQueue<T> {
    T peek();
    T dequeue();
    void enqueue(T item);
    void clear();
    int getSize();
    boolean isEmpty();
}
