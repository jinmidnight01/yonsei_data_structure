public interface ICuckooHash {
    boolean contains(int x);
    void delete(int x);
    void insert(int x);
    HashParameter getParams();
}
