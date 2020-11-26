package tankes;

//LUIS JESUS REYES VELAZQUEZ 201732135
import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;

public class ProductorMutex extends Thread{
    //DECLARACIÓN DE VARIABELS
    private Y recursoCompartidoMutex;  //objeto Y el cual nos dará la posición donde se pondrá la siguiente agua
    private DibujaTanke panelMutex;     //PANEL DONDE SE DIBUJARÁN LOS COMPONENTES
    private Lock mutex;             //OBJETO MUTEX
   
    
    ProductorMutex(DibujaTanke panelMutex, Y recursoCompartidoMutex){ //CONSTRUCTOR
        this.panelMutex=panelMutex;   //RECIBE EL PANEL QUE LE PASARON
        this.recursoCompartidoMutex=recursoCompartidoMutex;
        mutex = new ReentrantLock(); //INICIALIZACIÓN DE MUTEX
    }
    
    public void run(){
        while(true){
            if(recursoCompartidoMutex.getY()>50){
                if(mutex.tryLock()){
                    //SECCIÓN CRÍTICA (PRODUCE)
                    mutex.lock(); //EL MUTEX SE BLOQUEA
                        panelMutex.aguaM.getAgua().add(new Rectangle2D.Double(50,recursoCompartidoMutex.getY(), 100, 5));
                        recursoCompartidoMutex.setY(recursoCompartidoMutex.getY()-5);
                    mutex.unlock(); //EL MUTEX SE DESBLOQUEA 
                }
                panelMutex.repaint();
                try{
                    sleep(500+(int)Math.random()*200); //DUERME POR TIEMPO ALEATORIO(TERMINO DE PRODUCIR)
                }catch(Exception e){}
            }else if(recursoCompartidoMutex.getY()<=50){  //CONDICIÓN CUANDO YA ESTÁ LLENO EL TANQUE
                System.out.println("Llego al limite (Productor-Mutex)"); 
                try{
                    sleep(500+(int)Math.random()*200); //DUERME POR TIEMPO ALEATORIO(ESTÁ LLENO EL TANQUE)
                }catch(Exception e){}
            }
        }
    }
}
