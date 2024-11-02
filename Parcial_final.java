

package com.mycompany.parcial_final;

import Factories.AbstractFactory;
import Factories.ConcreteFactory;

public class Parcial_final {

    public static void main(String[] args) {
        
        AbstractFactory fabrica = new ConcreteFactory();
                AbstractFactory fabrica1 = new ConcreteFactory();
        
        fabrica.venta().Lista();
        fabrica.venta().Registrar(String nombre,float precio,int cantidad,String marca);
        fabrica.venta().Actualizar(int id,String nombre, float nuevoPrecio, int nuevaCantidad, String nuevaMarca);
        fabrica.venta().Eliminar(int id);
        
        System.out.println("Hello World!");
    }
}
