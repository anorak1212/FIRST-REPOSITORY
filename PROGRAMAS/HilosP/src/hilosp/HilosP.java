   /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hilosp;

/**
 *
 * @author leledezma
 */
import java.util.Random;

public class HilosP {
 
static Random random= new Random();
static int azar,azar1, suma;
public static void main(String args[]) {
Hilo3 hilo3 = new Hilo3();
Hilo1 hilo1 = new Hilo1();
Hilo2 hilo2 = new Hilo2();
hilo3.start();
hilo1.start();
hilo2.start();
}

public static class Hilo3 extends Thread {

@Override
public void run() {
while (azar!=2&&azar1!=2) {
azar=random.nextInt(10)+1;
System.out.println("Hilo 3: "+azar);
/*yield();*/
}
}
}

public static class Hilo1 extends Thread {

@Override
public void run() {
while (azar1!=2&&azar!=2) {
azar1=random.nextInt(10)+1;
System.out.println("Hilo 1: "+azar1);
/*yield();*/
}
}
}

public static class Hilo2 extends Thread {

@Override
public void run() {
suma = azar1+azar;
System.out.println("Hilo 2 Suma: " + azar + " + " + azar1 + " = " + suma);
/*yield();*/

}
}

}


