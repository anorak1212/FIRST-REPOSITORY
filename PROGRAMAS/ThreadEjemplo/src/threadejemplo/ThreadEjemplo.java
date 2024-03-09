 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package threadejemplo;

/** Fork - join:   0 maestro ---> crear al hilo 1,2,  JOIN--eliminan
 *
 * @author leledezma compartidas, local o privada
 */
public class ThreadEjemplo extends Thread {
    //static int i = 2;
    public ThreadEjemplo(String str) {
        super (str);
    }

     @Override
 public void run() {
    for (int i = 0; i < 10 ; i++)
        System.out.println(i + " " + getName());
    
     System.out.println("Termina thread " + getName());
}

 public static void main (String [] args) {
 new ThreadEjemplo("Pepe").start();
 new ThreadEjemplo("luis").start();
 
 System.out.println("Termina thread main");
 } /* join */
} /* kill */
