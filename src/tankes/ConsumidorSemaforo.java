package tankes;

//LUIS JESUS REYES VELAZQUEZ 201732135

import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;
public class ConsumidorSemaforo extends Thread{
    //DECLARACIÓN DE VARIABLES
    private Y recursoCompartidoSem;  //RECURSO COMPARTIDO 
    private DibujaTanke panelSemaforo; //PANEL DONDE SE DIBUJARA TODO
    private Semaphore sem;  //SEMAFORO
    
    
     ConsumidorSemaforo(DibujaTanke panelSemaforo, Y recursoCompartidoSem){
        this.panelSemaforo=panelSemaforo;  //SE RECIBE EL PANEL
        this.recursoCompartidoSem=recursoCompartidoSem; //SE RECIBE EL RECURSO COMPARTIDO
        
        sem = new Semaphore(1);
    }
     public void run(){
        while(true){
            if (recursoCompartidoSem.getY()<250){
                try{
                    sem.acquire(); //EL SEMAFORO ES ADQUIRIDO 
                        //SECCIÓN CRÍTICA
                        //QUITA EL AGUA QUE ESTÁ MÁS ARRIBA DEL TANQUE
                        panelSemaforo.aguaS.getAgua().remove(panelSemaforo.aguaS.getAgua().size()-1);
                        recursoCompartidoSem.setY(recursoCompartidoSem.getY()+5);
                     sem.release();// EL SEMAFORO ES LIBERADO 
                } catch (InterruptedException exc) { 
                    System.out.println(exc);    //EXCEPTION EN CASO DE QUE ALGO SALGA MAL
                }
                
                
                panelSemaforo.repaint(); //ACTUALIZA EL AGUA QUITADA
                try{    
                    sleep(1000);
                }catch(InterruptedException e){}
            }
        }
     }
}
