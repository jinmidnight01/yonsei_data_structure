public interface ICLL<T> {
	public int size();
	public boolean isEmpty();
	public T getHead();
	public void rotate(Direction direction);
	public void insert(T element);
	public T delete();
	public boolean find(T target);
}
