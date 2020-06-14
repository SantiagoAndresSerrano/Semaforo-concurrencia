/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;

/**
 *
 * @author santi
 */
public class SemaforoH extends Thread {

    JButton rojo, amarillo, verde;
    Semaphore s1;

    
    public SemaforoH(JButton rojo, JButton amarillo, JButton verde,Semaphore s1) {
        this.rojo = rojo;
        this.amarillo = amarillo;
        this.verde = verde;
        this.s1 = s1;
        this.cambiarABlanco(rojo);
        this.cambiarABlanco(verde);
        this.cambiarABlanco(amarillo);
    }

    public JButton getRojo() {
        return rojo;
    }

    public void setRojo(JButton rojo) {
        this.rojo = rojo;
    }

    public JButton getAmarillo() {
        return amarillo;
    }

    public void setAmarillo(JButton amarillo) {
        this.amarillo = amarillo;
    }

    public JButton getVerde() {
        return verde;
    }

    public void setVerde(JButton verde) {
        this.verde = verde;
    }

    @Override
    public void run() {

        try {
        while(true){
            
            sleep(800);
            s1.release();
            System.out.println("Peatones pueden pasar");
            cambiarARojo();
            sleep(3000);
            cambiarA_Amarillo();
            sleep(1000);
            s1.release();
            cambiarAVerde();
            System.out.println("Carros pueden pasar");
            sleep(2000);
            
        }
            

        } catch (InterruptedException ex) {
        }

    }
   
    private void cambiarARojo() {
        this.rojo.setBackground(Color.RED);
        this.cambiarABlanco(amarillo);
        this.cambiarABlanco(verde);
    }

    private void cambiarA_Amarillo() {
        this.amarillo.setBackground(Color.yellow);
        this.cambiarABlanco(rojo);
        this.cambiarABlanco(verde);
    }

    private void cambiarAVerde() {
        this.verde.setBackground(Color.green);
        this.cambiarABlanco(rojo);
        this.cambiarABlanco(amarillo);
    }

    private void cambiarABlanco(JButton b) {
        b.setBackground(Color.gray);

    }

}
