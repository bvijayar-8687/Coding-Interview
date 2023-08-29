import java.util.*;

public class MatrixComputations {
    private int[][] initial_array;
    boolean[][] check_Visits;
    private int row_length = Constants.ZERO;
    private int total_Islands ;
    private int column_length = Constants.ZERO;
    private Map<Integer,List<Integer[][]>> temp = new HashMap<>();

    MatrixComputations(int[][] input_array){
        this.initial_array = input_array;
        this.row_length = input_array!=null && input_array.length>Constants.ZERO? input_array.length : Constants.ZERO;
        this.column_length = input_array[Constants.ZERO].length>Constants.ZERO? input_array[Constants.ZERO].length : Constants.ZERO;
        this.total_Islands = Constants.ZERO;
        this.check_Visits = new boolean[this.row_length][this.column_length];
    }

    private boolean check_For_Boundary_Conditions(int row_index, int column_index){
        if(row_index < Constants.ZERO){
            return  Constants.FALSE_VALUE;
        }else if(column_index < Constants.ZERO){
            return  Constants.FALSE_VALUE;
        }else if(row_index > this.row_length -Constants.ONE){
            return  Constants.FALSE_VALUE;
        } else if(column_index > this.column_length -Constants.ONE ){
            return Constants.FALSE_VALUE;
        }
        return Constants.TRUE_VALUE;
    }

    private void search_Inside_Matrix(int[][] input_array, int row_index, int col_index) {
           int[][] positions = new int[this.row_length][this.column_length];
           if(input_array[row_index][col_index] != Constants.ONE){
               return;
           }else if(!this.check_For_Boundary_Conditions(row_index,col_index)){
               return;
           }else if(this.check_Visits[row_index][col_index] == Constants.TRUE_VALUE){
               return;
           }
           else if(input_array[row_index][col_index] == Constants.ONE){
               this.check_Visits[row_index][col_index] = Constants.TRUE_VALUE;
               search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index);
               search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index);
               search_Inside_Matrix(input_array,row_index,col_index+Constants.ONE);
               search_Inside_Matrix(input_array,row_index,col_index-Constants.ONE);
               search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index+Constants.ONE);
               search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index-Constants.ONE);
               search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index-Constants.ONE);
               search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index+Constants.ONE);
           }
     }
    public void count_Number_Of_TOTAL_Islands(){
            for(int i=Constants.ZERO;i<row_length;i++){
                for(int j=Constants.ZERO;j<column_length;j++){
                    if(initial_array[i][j] == Constants.ONE){
                        this.total_Islands ++ ;
                        search_Inside_Matrix(initial_array,i,j);
                    }
                }
            }
      }

      public int count_Number_Of_Distinct_Islands(){
        return Constants.ZERO;
      }

      public int count_Number_Of_Closed_Islands(){
        return  Constants.ZERO;
      }
    }
