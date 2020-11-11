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
                if( setup[ y ].charAt( x ) == '*' ) {
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
                    setDead( x, y );
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
    if (cells[y][x] == CellValue.ALIVE) return true;
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

    String [] nextGen = new String[dim_y];
    for( int y = 0; y < dim_y; y++ ) {
      String t = "";
      for( int x = 0; x < dim_x; x++ ) {
        if( nextGArray[y][x] == 3 || nextGArray[y][x] == 2){ 
          t+="*";
          setAlive(x,y);
        }
        else if(nextGArray[y][x] < 2 || nextGArray[y][x] > 3) {
          t+= " ";
          setDead(x,y);
        }
      }
      
      nextGen[y] = t;
    }

    return new Life(nextGen);
  }


  
  public int countNeighboursOfCell(int x , int y){
    int counter = 0;
    
    if((x-1) >=0 && (y-1) >=0 && isAlive(x-1,y-1)) counter++;
    if((y-1) >=0 && isAlive(x,y-1)) counter++;
    if((x+1) < dim_x && (y-1) >=0 && isAlive(x+1,y-1)) counter++;
    if((x-1) >=0&& isAlive(x-1,y)) counter++;
    if((x+1) < dim_x && isAlive(x+1,y)) counter++;
    if((x-1) >=0 && (y+1) < dim_y && isAlive(x-1,y+1)) counter++;
    if( (y+1) <dim_y && isAlive(x,y+1)) counter++;
    if((x+1) <dim_x && (y+1) <dim_y && isAlive(x+1,y+1)) counter++;

    return counter;
  }
}
