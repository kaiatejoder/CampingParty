package MODELO;
import MODELO.Acompanyante;
import java.util.ArrayList; 
/**
 * Tabla para mostrar los acompañantes en la vista de reserva del cliente
 * @author Carla Terol
 */

public class tablaClientesModel extends javax.swing.table.AbstractTableModel {
    private final String[] nombresColumnas = {"Nombre", "Edad"};
    private final Object[][] datos;
    private int fila;

    public tablaClientesModel(ArrayList<Acompanyante> acompanyantes) {
        fila = acompanyantes.size();
        datos = new Object[acompanyantes.size()][2];
        for (int i = 0; i < acompanyantes.size(); i++) {
            datos[i][0] = acompanyantes.get(i).getNombre();
            datos[i][1] = acompanyantes.get(i).getEdad();
        }
    }

    @Override
    public int getRowCount() {
        return datos.length;
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datos[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumnas[column];
    }
    /**
     * anyade un acompañante
     * @param a 
     */
    public void anyade(Acompanyante a){
        datos[fila][0] = a.getNombre();
        datos[fila][1] = a.getEdad();
        fila++;
    }
}
