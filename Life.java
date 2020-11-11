public class Life implements ILife {

  private final int dim_x;              
  private final int dim_y;
    
  public enum CellValue {DEAD, ALIVE}
  private final CellValue[][] cells;

  public static void main(String[] args) {
    Life l = new Life(new String[] { "     ", "     ", " *** ", "     ", "     " });
    l = (Life) l.nextGeneration();
  }

public Life( int dim_y, int dim_x ) {
        this.dim_x = dim_x;
        this.dim_y = dim_y;
        cells = new CellValue[ dim_y ][ dim_x ];
        nukeAll();
    }

    public Life( String[] setup ) {
        this( setup[ 0 ].length(), setup.length );
        for( int y = 0; y < dim_y; y++ ) {
            for( int x = 0; x < dim_x; x++ ) {
                if( setup[ y ].charAt( x ) != '*' ) {
                    setAlive( y, x );
                }
            }
        }
    }

  @Override
  public void nukeAll() {
    // setDead(all)
     for( int y = 0; y < dim_y; y++ ) {
            for( int x = 0; x < dim_x; x++ ) {
                    setDead( y, x );
            }
        }
  }

  @Override
  public void setAlive(int x, int y) {
    cells[y][x] = CellValue.ALIVE;
  }

  @Override
  public void setDead(int x, int y) {
    cells[y][x] = CellValue.DEAD;
  }

  @Override
  public boolean isAlive(int x, int y) {
    if(cells[y][x] == CellValue.ALIVE) return true;
    return false;
  }

  @Override
  public ILife nextGeneration() {
    // update Life by applying rules
    int[][] nextGArray= new int[ dim_y ][ dim_x ];

    for( int y = 0; y < dim_y; y++ ) {
            for( int x = 0; x < dim_x; x++ ) {
              int nbrOfNeighbours = countNeighboursOfCell(x,y);
              nextGArray[y][x] = nbrOfNeighbours;
            }
    }

     for( int y = 0; y < dim_y; y++ ) {
            for( int x = 0; x < dim_x; x++ ) {
             //Neue Zelle
              if( nextGArray[y][x] == 3){
                  if(!isAlive(x,y)){
                    setAlive(x, y);
                  }

              // Regeln für lebende Zellen
              }else{
                if(nextGArray[y][x] < 2 || nextGArray[y][x] > 3) {
                  setDead(x,y);
                  }
              } 
            }
        }
    
    return null;
  }



  
  public int countNeighboursOfCell(int x , int y){
    int counter = 0;

    int x_begin = x - 1;
    int y_begin = y - 1;
    int x_end = x + 1;
    int y_end = y + 1;

    if(x_begin < 0) x_begin = 0;
    if(y_begin < 0) y_begin = 0;
    if(x_end > dim_x) x_end = dim_x;
    if(y_end > dim_y) y_end = dim_y;

    
    for(int y_cell = y_begin; y_cell <= y_end; y++ ) {
            for( int x_cell = x_begin; x_cell <= x_end; x++ ) {
            if(cells[x_cell][y_cell] != cells[x][y]){
             if(cells[x_cell][y_cell] == CellValue.ALIVE) counter++;
               } 
            }
      }


//       int column = x; 
//       int row = y;
//       int row_end;
//       int column_end;

//       // Warum -3? dim_y ist doch eigentlich auch das row_end
//       if(y>=dim_y-3){
//         row_end=dim_y;
//       }else{
//         row_end=y+3;
//       }

//       if(x>=dim_x-3){
//         column_end=dim_x;
//       }else{
//         column_end=x+3;
//       }
      
// System.out.println("row "+row+ " - " +row_end+" column "+column+" - "+ column_end);

//      for( ; row < row_end; row++ ) {
//        for( ; column < column_end; column++ ) {

//           //  if(column==x && row==y){
//           //    continue;
//           //  }
           
//           //  if(isAlive(column, row)){  
//                 System.out.println(row + "  " + column + "  "+counter++);      
//           }counter++;}

    return counter;
  }
}
