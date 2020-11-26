package tankes;
//LUIS JESUS REYES VELAZQUEZ 201732135
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
public class AguaS {
    private ArrayList<Rectangle2D> aguaS; 
    AguaS(){
        aguaS=new ArrayList<Rectangle2D>();
    }    
    public ArrayList<Rectangle2D> getAguaS() {
        return aguaS;
    }
    public void setAguaS(ArrayList<Rectangle2D> aguaS) {
        this.aguaS = aguaS;
    }   
}
