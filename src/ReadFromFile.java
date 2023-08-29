import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFromFile {
    private String file_Name;
    ReadFromFile(String fileName){
        this.file_Name = fileName;
    }

    private int[] convert_Row_To_Values(String line){
        String[] newline = new String[line.length()];
        int[] convertedArray;
        for(int i=Constants.ZERO;i<line.length();i++){
            newline[i] = String.valueOf(line.charAt(i));
        }
        convertedArray = Arrays.stream(newline)
                .mapToInt(Integer::parseInt).toArray();
    return convertedArray;
    }
    public List<int[][]> read_values(){
        List<int[][]> final_Input_Matrix = new ArrayList<>();
        BufferedReader br = null;
        Path rootDir = Paths.get(".").normalize().toAbsolutePath();
        System.out.println(rootDir);
        File file = new File(rootDir.toString() + "\\"+this.file_Name);
        Reader input = null;
        if (file.exists()) {
            try {
                input = new FileReader(file);
                br = new BufferedReader(input);
                String line = "";
                int count =Constants.ZERO;
                int[][] input_Single_Matrix = new int[Constants.ZERO][];
                while ((line = br.readLine()) != null) {
                    if(line!= Constants.NEW_LINE){
                        System.out.println(this.convert_Row_To_Values(line));
                        input_Single_Matrix[count] = this.convert_Row_To_Values(line);
                        count++;
                    }else{
                        final_Input_Matrix.add(input_Single_Matrix);
                      count = Constants.ZERO;
                    }
                }
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return final_Input_Matrix;
    }
    }

