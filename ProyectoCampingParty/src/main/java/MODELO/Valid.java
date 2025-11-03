/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 * Clase hecha para validar los diferentes tipos de datos
 * @author Carla terol
 */
public class Valid {
    public static boolean dni (String s){
        boolean res1,res2,res;
        if ( s.length() == 9){
            res1 = s.matches("\\d{8}[A-Z]");
            res2 = s.matches("\\d{8}[a-z]");
            res = res1||res2;
        }
        else
               res = false;
        
        return res;
        }
    
    public static boolean correo(String s){
        boolean res;
        if(s.contains("@")){
            String[] p;
            p = s.split("@");
            if(p[1].contains("."))
                 res = true;
            else 
                res =false;
    }
        else
            res =false;
        
    return res;
    }
    public static boolean password(char[] c){
        String s = c.toString();
        if(s.contains("[A-Z]+") && s.contains("[a-z]+") && s.contains("\\d")
                && s.contains("[.-,=!_*/?¿]+") )
            return true;
        else
            return false;
    }

}
    

