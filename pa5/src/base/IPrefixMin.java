public interface IPrefixMin {
    public int getValue(YonseiString key);
    public void insert(YonseiString key, int value);
    public void remove(YonseiString key);
    public void clear();
    public int getMin(YonseiString query);
}
