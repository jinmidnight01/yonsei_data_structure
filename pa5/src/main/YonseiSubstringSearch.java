/*
 * Name: Jinhyo Park
 * Student ID #: 2018121022
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class YonseiSubstringSearch implements IYonseiSubstringSearch {
    /*
     * you may declare variables here
     */
    YonseiString text;

    YonseiSubstringSearch(YonseiString t) {
        /*
         * implement your constructor here.
         */
        text = t;
    }

    @Override
    public int countPattern(YonseiString p) {
        /*
         * Function input:
         *  + p: pattern to match in the target string.
         *
         * Does:
         * returns the number of occurrences of the pattern as a substring of the text given from the constructor.
         */
        int count = 0;
        int[] pi = computePrefixFunction(p);
        int q = 0;
        for(int i=0; i<text.length(); i++){
            while(q > 0 && p.charAt(q) != text.charAt(i)){
                q = pi[q-1];
            }
            if(p.charAt(q) == text.charAt(i)){
                q++;
            }
            if(q == p.length()){
                count++;
                q = pi[q-1];
            }
        }
        return count;
    }

    private int[] computePrefixFunction(YonseiString p){
        int[] pi = new int[p.length()];
        pi[0] = 0;
        int k = 0;
        for(int q=1; q<p.length(); q++){
            while(k > 0 && p.charAt(k) != p.charAt(q)){
                k = pi[k-1];
            }
            if(p.charAt(k) == p.charAt(q)){
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }

}
