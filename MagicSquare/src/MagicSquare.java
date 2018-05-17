
/**
 *
 * @author Ethan
 */
public class MagicSquare {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        MagicPermu permu = new MagicPermu(a);
        System.out.println(permu.order + ", " + permu.sum);
        for(int[] arrInt : permu.getPosPandiagonal()){
            for(int i :  arrInt){
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println();
        permu.runAllPermu();
        System.out.println("count : " + permu.count);
    }
    
}
