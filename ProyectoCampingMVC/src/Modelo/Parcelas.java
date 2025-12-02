package Modelo;

/**
 *
 * @author Sergio Gimenez Gomez
 */
/**
 *
 * @author Carla Terol
 */
public class Parcelas {
    private Parcela[] parcelas;
    
    public Parcelas(){
        parcelas = new Parcela[16];
        // Inicializar parcelas por defecto
        for(int i = 0; i < parcelas.length; i++) {
            parcelas[i] = new Parcela(i+1, 2 + i*5, i%2==0, 15 + i*2);
        }
    }
    
    public Parcelas(Parcela[] parcelas){
        this.parcelas = parcelas;
    }   
    
    public Parcela[] getParcelas(){
        return parcelas;
    }
    
    public void setParcela(int index, float m2, boolean luz, float precio){
        if (index >= 0 && index < parcelas.length) {
            Parcela p = new Parcela(index+1, m2, luz, precio);
            parcelas[index] = p;
        }
    }
    
    public Parcela getParcela(int index){
        if (index >= 0 && index < parcelas.length) {
            return parcelas[index];
        }
        return null;
    }
    
    public boolean[] getLibres(){
        boolean[] libres = new boolean[parcelas.length];
        for(int i = 0; i < parcelas.length; i++){
            libres[i] = parcelas[i].isLibre();
        }
        return libres;
    }

    public String[] getParcelaString(int index){
        if (index < 0 || index >= parcelas.length) {
            return new String[0];
        }
        
        String[] info = new String[4];
        Parcela p = parcelas[index];
        info[0] = Integer.toString(p.getId());
        info[1] = Float.toString(p.getM2());
        info[2] = Boolean.toString(p.hayLuz());
        info[3] = Float.toString(p.getPrecio());
        return info;
    }
    
    public float getM2(int index){
        if (index >= 0 && index < parcelas.length) {
            return parcelas[index].getM2();
        }
        return 0;
    }
    
    public void setReservada(int index){
        if (index >= 0 && index < parcelas.length) {
            parcelas[index].reservarParcela();
        }
    }
    
    public void setOcupada(int index) {
        if (index >= 0 && index < parcelas.length) {
            parcelas[index].ocuparParcela();
        }
    }
    
    public void liberarParcela(int index) {
        if (index >= 0 && index < parcelas.length) {
            parcelas[index].liberarParcela();
        }
    }
    
    public int getNumParcelas() {
        return parcelas.length;
    }
}