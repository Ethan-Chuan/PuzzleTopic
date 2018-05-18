package tw.chuan.ethan;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ethan
 */
public class Permutation {
    private final int[] arr;
    protected List<int[]> permu;
    
    public Permutation(int[] arrInt) {
        this.arr = arrInt;
    }

    public int[] getArr() {
        return arr;
    }

    public List<int[]> getPermu() {
        return permu;
    }
    
    
    public Permutation runAllPermu(){
        permu = new ArrayList<>();
        //Arrays.sort(arr);
        while(true){
            storeData(arr);
            
            int i; //From back to front, record a small value subscript in a sequence of values.
            int j; //From the back to the front, record the first number greater than i.
            
            for(i=arr.length-2; i>=0; i--){
                if(arr[i]<arr[i+1])
                    break;
                else if(i==0)
                    return this;
            }
            for(j=arr.length-1; j>i; j--){
                if(arr[j] > arr[i])
                    break;
            }
            swap(arr, i, j);
            reverse(arr, i+1, arr.length-1);
        }
    }
    
    public void storeData(int[] arrInt){
        permu.add(arrInt.clone());
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(int[] arr, int i, int j) {
        while(i<j)
            swap(arr, i++, j--);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int[] arrInt : permu){
            sb.append("{ ");
            for(int i=0; i<arrInt.length-1; i++){
                sb.append(arrInt[i]).append(", ");
            }
            sb.append(arrInt[arrInt.length-1]).append(" }\n");
        }
        return sb.toString();
    }
    
    
}
