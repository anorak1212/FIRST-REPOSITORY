/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hilorun;

/**
 *
 * @author leledezma    FORK ---> JOIN  MAIN CREA A T1 y A T1 ----> join
 */
class ThreadConRunnablePractica1 implements Runnable{
    String palabra;
    public ThreadConRunnablePractica1 (String _palabra){
        palabra = _palabra;
    }
    @Override
    public void run(){
        try {
            for(int i=0;i<10;i++) {
                Thread.sleep(1000); // Pausa la ejecución durante 1 segundo
                System.out.print(palabra);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        ThreadConRunnablePractica1 a = new ThreadConRunnablePractica1("hiloUno\n");
        ThreadConRunnablePractica1 b = new ThreadConRunnablePractica1("hiloDos\n");
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();
        System.out.print("Fin del programa principal\n");
    }
}
