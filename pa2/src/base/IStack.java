public interface IStack<T> {
    T peek();
    T pop();
    void push(T item);
    void clear();
    int getSize();
    boolean isEmpty();
}
