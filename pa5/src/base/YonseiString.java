import java.util.Random;

public class YonseiString {
    int[] characters;
    Random random = new Random();

    YonseiString(int[] mychars){
        characters = mychars;
    }

    public int charAt(int pos){
        if(pos < 0 || pos >= characters.length) return random.nextInt();
        return characters[pos];
    }

    public int length(){
        return characters.length;
    }
}
