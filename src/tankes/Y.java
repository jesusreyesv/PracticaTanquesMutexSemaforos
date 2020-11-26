package tankes;
//LUIS JESUS REYES VELAZQUEZ 201732135
public class Y {
    private int  y;
    Y(){
        y=250;
    }
    public synchronized int getY() {
        return y;
    }
    public synchronized void setY(int y) {
        this.y = y;
    }
}
