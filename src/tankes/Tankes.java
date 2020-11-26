package tankes;
//LUIS JESUS REYES VELAZQUEZ 201732135
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;

public class Tankes extends JFrame{
    private DibujaTanke panel;
    
    private Y recursoCompartidoMutex;
    private Y recursoCompartidoSem;
    
    private ProductorMutex productorMutex;
    private ConsumidorMutex consumidorMutex;
    private ProductorSemaforo productorSem;
    private ConsumidorSemaforo consumidorSem;
    
    private Agua aguaMutex;
    private Agua aguaSem;
    private Lock mutex;
    private Semaphore sem;
    
    
    public Tankes(){
        setSize(400,400);
        setLocation(550,250);
        mutex = new ReentrantLock();
        sem = new Semaphore(1);
        recursoCompartidoMutex=new Y();
        recursoCompartidoSem=new Y();
        aguaMutex=new Agua();
        aguaSem = new Agua();
        panel = new DibujaTanke(aguaMutex,aguaSem);
        productorMutex = new ProductorMutex(panel,recursoCompartidoMutex);
        consumidorMutex = new ConsumidorMutex(panel,recursoCompartidoMutex);
        productorSem = new ProductorSemaforo(panel,recursoCompartidoSem);
        consumidorSem = new ConsumidorSemaforo(panel,recursoCompartidoSem);
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(panel);
        productorMutex.start();
        consumidorMutex.start();
        productorSem.start();
        consumidorSem.start();
    }
    
    public static void main(String[] args) {
        Tankes fr = new Tankes();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
