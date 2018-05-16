
import tw.chuan.ethan.Permutation;

/**
 *
 * @author Ethan
 */
public class MagicSquare {

    public static void main(String[] args) {
        int[] a = {1,2,3};
        Permutation permu = new Permutation(a);
        permu.runPermutation();
        System.out.println(permu.runPermutation().toString());
    }
    
}
