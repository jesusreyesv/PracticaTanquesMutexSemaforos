package tankes;
//LUIS JESUS REYES VELAZQUEZ 201732135
import java.awt.geom.*;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;
public class ProductorSemaforo extends Thread{
    //DECLARACIÓN DE VARIABLES
    private Y recursoCompartidoSem; //SE DECLARA EL RECURSO COMPARTIDO
    private DibujaTanke panelSem;   
    private Semaphore sem;  //DECLARACIÓN DEL SEMÁFORO
    public ProductorSemaforo(DibujaTanke panelSem, Y recursoCompartidoSem){
        this.panelSem=panelSem; //SE RECIBE EL PANEL DONDE SE DIBUJARÁ TODO
        this.recursoCompartidoSem=recursoCompartidoSem; //SE RECIBE EL RC
        sem = new Semaphore(1); //INICIALIZACIÓN DEL SEMAFORO
        
    }
    public void run(){
        while(true){
            if(recursoCompartidoSem.getY()>50){ 
                try{
                    sem.acquire(); //EL SEMAFORO ES ADQUIRIDO
                    //SECCIÓN CRÍTICA
                        panelSem.aguaS.getAgua().add(new Rectangle2D.Double(200,recursoCompartidoSem.getY(), 100, 5));
                        recursoCompartidoSem.setY(recursoCompartidoSem.getY()-5);
                    sem.release(); //EL SEMAFORO ES LIBERADO
                } catch (InterruptedException exc) { 
                    System.out.println(exc);  
                }
                panelSem.repaint(); //SE ACTUALIZA LO QUE SE VE EN EL PANEL
                try{ 
                    sleep(600+(int)Math.random()*200); //SE DUERME ALEATORIO (ENTRE 600 Y 800) CUANDO PRODUCIÓ
                }catch(Exception e){}
            }else if(recursoCompartidoSem.getY()<=50){ //CONDICIÓN QUE SE CUMPLE CUANDO LLEGA AL LÍMITE
                System.out.println("Llego al limite (Productor-Semaforo)");  
                try{
                    sleep(600+(int)Math.random()*200);  //SE DUERME ALEATORIO (ENTRE 600 Y 800) TANQUE LLENO
                }catch(Exception e){}
            }
        }
    }
}
