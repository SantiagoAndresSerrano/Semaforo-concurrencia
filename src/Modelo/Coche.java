/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author santi
 */
public class Coche extends Thread {

    JLabel coche;
    int limite;
    final int POSICION_INICIAL;
    Semaphore sc;
    
    public Coche(JLabel coche, int limite, Semaphore sc) {
        this.coche = coche;
        this.limite = limite;
        this.sc = sc;
        POSICION_INICIAL = coche.getX();
    }

    @Override
    public void run() {
        try {
            sc.acquire();

            while (noLLegaLimite() == false) {

                movimiento();
                sleep(10);

                if (noLLegaLimite() == true) {
                    dejarAlInicio();
                    System.out.println("Espero a que se vuelva verde");
                    sc.acquire();

                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private boolean noLLegaLimite() {
        return coche.getX() + 130 >= limite;
    }
    
    public void dejarAlInicio(){
        coche.setLocation(POSICION_INICIAL, coche.getY());
    }
    private void movimiento() {
        coche.setLocation(coche.getX() + ((int) (Math.random() * 5)), coche.getY());
    }

}
