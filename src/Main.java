import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<int[][]> final_Input_Matrix;
        ReadFromFile readFromFile = new ReadFromFile(Constants.fileName);
        final_Input_Matrix = readFromFile.read_values();
        Iterator<int[][]> itr = final_Input_Matrix.iterator();
        while(itr.hasNext()){
            MatrixComputations matrixComputations = new MatrixComputations(itr.next());
            matrixComputations.count_Number_Of_TOTAL_Islands();
            matrixComputations.count_Number_Of_Closed_Islands();
            matrixComputations.count_Number_Of_Distinct_Islands();        }
          }
}