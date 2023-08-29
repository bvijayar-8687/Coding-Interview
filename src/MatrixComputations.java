import java.util.*;
import java.util.stream.Collectors;

public class MatrixComputations {
    private int[][] initial_array;
    boolean[][] check_Visits;
    private int row_length = Constants.ZERO;
    private int total_Islands ;
    private int column_length = Constants.ZERO;
    private List<ExtractedInfo> temp = new ArrayList<>();

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
           ExtractedInfo extractedInfo ;
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
               extractedInfo = new ExtractedInfo(row_index,col_index,Constants.TRUE_VALUE,this.total_Islands,input_array[row_index][col_index]);
               this.temp.add(extractedInfo);
               this.search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index);
               this.search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index);
               this.search_Inside_Matrix(input_array,row_index,col_index+Constants.ONE);
               this.search_Inside_Matrix(input_array,row_index,col_index-Constants.ONE);
               this.search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index+Constants.ONE);
               this.search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index-Constants.ONE);
               this.search_Inside_Matrix(input_array,row_index+Constants.ONE,col_index-Constants.ONE);
               this.search_Inside_Matrix(input_array,row_index-Constants.ONE,col_index+Constants.ONE);
           }
     }

    public void count_Number_Of_TOTAL_Islands(){
            for(int i=Constants.ZERO;i<row_length;i++){
                for(int j=Constants.ZERO;j<column_length;j++){
                    if(initial_array[i][j] == Constants.ONE){
                        this.total_Islands ++ ;
                        this.search_Inside_Matrix(initial_array,i,j);
                    }
                }
            }
            System.out.println("Total Number of Islands: " + this.total_Islands);
      }

      public int count_Number_Of_Distinct_Islands() {
          int total_count = Constants.ZERO;
          int total_idential = Constants.ZERO;
          this.visit_Any_UnVisitedNode();
          //Bhargavi: Group object based on Island_Number and
          // check the co-ordinates pattern
          int island_Number = Constants.ZERO;
          ListIterator iterator = this.temp.listIterator();
          Map<Integer, List<ExtractedInfo>> groupedValuebyIslandNumber = this.temp.stream().collect(Collectors.groupingBy(w -> w.island_number));
          while (iterator.hasNext()) {
              ExtractedInfo extractedInfo = (ExtractedInfo) iterator.next();
              island_Number = extractedInfo.island_number;

              if (!this.checkIfIdentical(groupedValuebyIslandNumber , island_Number)) {
                  total_count++;
              }
              System.out.println("Total Number of Distinct Islands are:" + total_count);
          }
          return total_count;
      }

      private boolean checkIfIdentical(Map<Integer, List<ExtractedInfo>> groupedValuebyIslandNumber, int island_Number){
              List<Integer> island_Numbers = (List<Integer>) groupedValuebyIslandNumber.keySet();
                    for(int cur_number : island_Numbers){
                        if(cur_number!= island_Number){
                            List<ExtractedInfo> otherParameters= groupedValuebyIslandNumber.get(island_Number);
                            List<ExtractedInfo> curParameters = groupedValuebyIslandNumber.get(cur_number);
                            for(ExtractedInfo otherParams: otherParameters){
                                for(ExtractedInfo curParams: curParameters){
                                    if((otherParams.row == curParams.row && otherParams.column == curParams.column) ||
                                            (otherParams.row > otherParams.column && curParams.row > curParams.column) ||
                                            (otherParams.row * otherParams.column == 0 && curParams.row * curParams.column == 0)){
                                        return Constants.TRUE_VALUE;
                                    }
                                }
                            }
                            if(this.checkIfIdentical(groupedValuebyIslandNumber, cur_number)){
                                return Constants.TRUE_VALUE;
                            }
                        }
                    }


              return Constants.FALSE_VALUE;
          }

      private void visit_Any_UnVisitedNode(){
          for(int i=Constants.ZERO;i<this.row_length;i++){
              for(int j=Constants.ZERO;j<this.column_length;j++){
                  if(!this.check_Visits[i][j]){
                      if(this.initial_array[i][j] ==Constants.ONE){
                          this.search_Inside_Matrix(initial_array,i,j);
                      }
                  }

              }
          }

      }
      public int count_Number_Of_Closed_Islands(){
        int total_count = Constants.ZERO;
        this.visit_Any_UnVisitedNode();
        ListIterator itr = this.temp.listIterator();
        int island_number = Constants.ZERO;
        while(itr.hasNext()){
            ExtractedInfo extractedInfo = (ExtractedInfo) itr.next();
            int row = extractedInfo.row;
            int temp = island_number;
            island_number = extractedInfo.island_number;
            int column = extractedInfo.column;
            if(row*column == Constants.ZERO || row == this.row_length-Constants.ONE || column == this.column_length-Constants.ONE){
                if(extractedInfo.value == Constants.ONE){
                    if(temp != island_number){
                        total_count++;
                    }
                }
            }
        }
          System.out.println("Total Number of Closed Islands: " + total_count);
          return  total_count;
      }
    }
