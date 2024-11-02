
package Factories;
import Concrete.concreteOrdenVenta;
import Interfaces.IOrdenVenta;

public class ConcreteFactory extends AbstractFactory {
    @Override
    public IOrdenVenta venta() {
        return new concreteOrdenVenta(); 
    }
}
