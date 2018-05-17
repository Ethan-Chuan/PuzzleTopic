
import java.util.ArrayList;
import java.util.List;
import tw.chuan.ethan.Permutation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ethan
 */
public class MagicPermu extends Permutation{
    final int order, sum;
    private List<int[]> posPandiagonal;
    int count = 0;
    
    public MagicPermu(int[] arrInt) {
        super(arrInt);
        this.order = (int)Math.sqrt(arrInt.length);
        this.sum = (1 + this.order*this.order)*this.order/2;
        genPosPandiagonal();
    }

    public List<int[]> getPosPandiagonal() {
        return posPandiagonal;
    }
    
    @Override
    public void storeData(int[] arrInt) {
        if(isRowColumnMatched(arrInt) && isPandiagonalMatched(arrInt)){
            
            System.out.println("------ " + (++count) + " ------");
            for(int i=0;i<order;i++){
                for(int j=0;j<order;j++){
                    System.out.print(arrInt[i*order+j] + "\t");
                }
                System.out.println();
            }
            System.out.println();
                
            System.out.println();
            this.permu.add(arrInt.clone());
        }
    }
    
    final void genPosPandiagonal(){
        posPandiagonal = new ArrayList<>(2*order);
        for(int i=0;i<order;i++){
            int[] forwardslash = new int[order];
            int[] backslash = new int[order];
            forwardslash[0] = i;
            backslash[0] = order-i-1;
            for(int j=1;j<order;j++){
                forwardslash[j] = forwardslash[j-1] + (order + 1);
                backslash[j] = backslash[j-1] +  (order - 1);
                int start = j*order;
                int end = (j+1)*order - 1;
                if(forwardslash[j]>end)
                    forwardslash[j] -= order;
                if(backslash[j]<start)
                    backslash[j] += order;
            }
            posPandiagonal.add(forwardslash);
            posPandiagonal.add(backslash);
        }
    }
    
    boolean isRowColumnMatched(int[] arrInt){
        int row=0, column=0;
        for(int i=0;i<order;i++){
            int ir = i*order;
            for(int j=0;j<order;j++){
                // Row
                row += arrInt[ir+j];
                // Column
                column += arrInt[i+j*order];
            }
            if(row!=this.sum || column!=this.sum)
                return false;
            // Reset
            row = 0;
            column = 0;
        }
        return true;
    }
    
    boolean isPandiagonalMatched(int[] arrInt){
        
        for(int[] arrPos : posPandiagonal){
            int pandiagonal = 0;
            for(int i : arrPos){
                pandiagonal += arrInt[i];
            }
            if(pandiagonal!=this.sum)
                return false;
        }
        /*
        int pandiagonal = 0;
        for(int i : posPandiagonal.get(0)){
            pandiagonal += arrInt[i];
        }
        if(pandiagonal!=this.sum)
            return false;
        pandiagonal = 0;
        for(int i : posPandiagonal.get(1)){
            pandiagonal += arrInt[i];
        }
        if(pandiagonal!=this.sum)
            return false;*/
        return true;
    }
}
