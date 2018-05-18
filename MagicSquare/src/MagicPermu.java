
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    DataOutputStream dout;
    final Path path;
    
    public MagicPermu(int[] arrInt) {
        super(arrInt);
        this.order = (int)Math.sqrt(arrInt.length);
        this.sum = (1 + this.order*this.order)*this.order/2;
        genPosPandiagonal();
        String fileName = "Magic Square " + this.order + " order.txt";
        path = Paths.get(fileName);
        if(!Files.exists(path)){
            System.out.println("File is not found.\nCreating a new file.\n");
            try {
                Files.createFile(path);
            } catch (IOException ex) {
                System.out.println("Creating file failed.\n");
            }
        }else{
            System.out.println("File is existed.\n");
        }
    }
    
    public MagicPermu(int[] arrInt, int count){
        this(arrInt);
        this.count = count;
    }

    public List<int[]> getPosPandiagonal() {
        return posPandiagonal;
    }
    
    @Override
    public void storeData(int[] arrInt) {
        if(isRowColumnMatched(arrInt) && isPandiagonalMatched(arrInt)){
            StringBuilder sb = new StringBuilder();
            sb.append("---------- ").append(++count).append(" ----------\n");
            for(int i=0;i<order;i++){
                for(int j=0;j<order;j++){
                    sb.append(arrInt[i*order+j]).append("\t");
                }
                sb.append("\n");
            }
            sb.append("\n");
            System.out.println(sb);
            
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(path.toFile(), true))) {
                dos.writeBytes(sb.toString());
            }catch (FileNotFoundException ex) {
                System.out.println("File is not found.\nCreating Stream is failed.\n");
            }catch (IOException ex) {
                System.out.println("IOException :" + ex);
            }
            
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
