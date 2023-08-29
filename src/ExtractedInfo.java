public class ExtractedInfo {
    public int row;
    public int column;
    public boolean visited;
    public int island_number;
    public int value;
    ExtractedInfo(int row,int column,boolean visited,int island_number,int value){
        this.row = row;
        this.column = column;
        this.visited = visited;
        this.island_number = island_number;
        this.value = value;
    }
}
