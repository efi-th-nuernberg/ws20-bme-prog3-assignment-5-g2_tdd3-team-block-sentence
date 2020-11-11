import org.junit.Test;
import static org.junit.Assert.*;

public class LifeTest {
    
     @Test
     public void createNewCell() {
      
         // Arrange: drei lebende Zellen
         Life l = new Life(5, 5);
         l.setAlive(0, 0);
         l.setAlive(0, 1);
         l.setAlive(0, 2);

         // Act: Berechnung der Folgegeneration
         ILife nextGen = l.nextGeneration();        

         // Assert: Rasterpunkt mit drei Nachbarn sollte jetzt leben
         assertTrue("err", nextGen.isAlive(1, 1));
     }


    @Test
    public void destroyLonelyCell() {
      Life l = new Life(5, 5);
      l.setAlive(0, 1);

      ILife nextGen = l.nextGeneration();

      assertFalse("err1", nextGen.isAlive(0,1));

    }


    @Test
    public void keepAliveCell() {
      Life l = new Life(5,5);

      l.setAlive(1,1);
      l.setAlive(1,2);
      l.setAlive(1,3);

      ILife nextGen = l.nextGeneration();

      assertTrue("err2", nextGen.isAlive(1,2));
    }


    @Test
    public void destroyCrowdedCell() {
      Life l = new Life(5,5);

      l.setAlive(1,1);
      l.setAlive(1,2);
      l.setAlive(1,3);
      l.setAlive(0,2);

      ILife nextGen = l.nextGeneration();

      assertFalse("err3", nextGen.isAlive(1,2));
    }


}
