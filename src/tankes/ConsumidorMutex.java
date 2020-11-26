package tankes;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;
public class ConsumidorMutex extends Thread{
    //DECLARACIÓN DE VARIABLES
    private Y recursoCompartidoMutex;  //RECURSO COMPARTIDO (LO OCUPAREMOS EN LA SC
    private DibujaTanke panelMutex;   
    private Lock mutex;                 //DECLARACIÓN DEL MUTEX
    public ConsumidorMutex(DibujaTanke panelMutex, Y recursoCompartidoMutex){
        this.panelMutex=panelMutex;  //RECIBE EL PANEL DONDE SERÁN PINTADOS LOS COMPONENTES
        this.recursoCompartidoMutex=recursoCompartidoMutex;     //RECIBE EL RECURSO COMPARTIDO
        mutex = new ReentrantLock();        //INICIALIZACIÓN DEL MUTEX
    }
    public void run(){
        while(true){
            if (recursoCompartidoMutex.getY()<250){
                if(mutex.tryLock()){
                    //SECCIÓN CRÍTICA 
                    mutex.lock(); //EL MUTEX SE CIERRA 
                        panelMutex.aguaM.getAgua().remove(panelMutex.aguaM.getAgua().size()-1); //REMUEVE EL AGUA
                        recursoCompartidoMutex.setY(recursoCompartidoMutex.getY()+5);
                       
                    mutex.unlock();//EL MUTEX SE DESBLOQUEA
                }
                panelMutex.repaint(); //SE HACE ACTUALIZACIÓN DONDE SE VERÁ REFLEJADA EL AGUA QUITADA
                try{    
                    sleep(1000);
                }catch(InterruptedException e){}
            }
        }
    }
}
