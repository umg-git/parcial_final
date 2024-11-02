package Interfaces;

import javax.swing.JTable;


public interface IOrdenVenta {
    String Lista();
    String Registrar(String nombre,float precio,int cantidad,String marca);
    String Actualizar(int id,String nombre, float nuevoPrecio, int nuevaCantidad, String nuevaMarca);
    String Eliminar(int id);
    
}
