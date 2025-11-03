/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import MODELO.Parcela;
/**
 *
 * @author Carla Terol
 */
public class Parcelas{
    private Parcela[] parcelas;
    public Parcelas(){
        parcelas = new Parcela[16];
    }
    public Parcelas(Parcela[] parcelas){
        this.parcelas = parcelas;
    }   
    public Parcela[] getParcelas(){
        return parcelas;
    }
    public void setParcela(int index, float m2, boolean luz, float precio){
        Parcela p = new Parcela(index+1, m2, luz, precio);
        parcelas[index] = p;
    }
    public Parcela getParcela(int index){
        return parcelas[index];
    }
    public boolean[] getLibres(){
        boolean[] libres = new boolean[parcelas.length];
        for(int i=0; i<parcelas.length; i++){
            libres[i] = parcelas[i].isLibre();
        }
        return libres;
    }

    public String[] getParcelaString(int index){
        String[] info = new String[4];
        Parcela p = parcelas[index];
        info[0] = Integer.toString(p.getId());
        info[1] = Float.toString(p.getM2());
        info[2] = Boolean.toString(p.hayLuz());
        info[3] = Float.toString(p.getPrecio());
        return info;
    }
    public float getM2 (int index){
       return parcelas[index].getM2();
    }
    public void setReservada(int index){
        parcelas[index].reservarParcela();
    }
}
