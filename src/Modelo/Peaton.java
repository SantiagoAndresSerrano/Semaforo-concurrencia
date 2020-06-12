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
public class Peaton extends Thread {

    String nombre;
    JLabel persona;
    int limite;
    final int POSICION_INICIAL;
    Semaphore sp;

    public Peaton(String nombre, JLabel persona, int limite, Semaphore sp) {
        this.nombre = nombre;
        this.persona = persona;
        this.limite = limite;
        this.sp = sp;
        POSICION_INICIAL = persona.getY();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public JLabel getPersona() {
        return persona;
    }

    public void setPersona(JLabel persona) {
        this.persona = persona;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    @Override
    public void run() {

        try {
            sp.acquire();
        } catch (InterruptedException ex) {

        }
        while (paseLaCalle() == false) {
            try {

                movimiento();

                sleep(18);
                if (paseLaCalle() == true) {
                    dejarAlInicio();
                    System.out.println("Espero a que se vuelva Rojo");
                    sp.acquire();
                }

            } catch (InterruptedException ex) {
            }
        }
        stop();
    }

    private boolean paseLaCalle() {
        return persona.getY() + 40 <= limite;
    }

    public void dejarAlInicio() {
        this.persona.setLocation(persona.getX(), this.POSICION_INICIAL);

    }

    private void movimiento() {

        this.persona.setLocation(persona.getX(), persona.getY() - ((int) (Math.random() * 4)));

    }

}
