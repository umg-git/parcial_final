package Concrete;

import Interfaces.IOrdenVenta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class concreteOrdenVenta implements IOrdenVenta {
    
    // Parámetros de la base de datos
    String url = "jdbc:mysql://localhost:1000/dbventas";
    String usuario = "admin";
    String clave = "root";
    
    @Override
    public String Lista() {
                String mensaje = "";
        String cadena = "select a.idOrdenVenta,a.cliente,a.Apellido,a.FechaOrden,b.Nombre,c.Producto,a.total from orden_venta a\n" +
                        "inner join estado_orden_venta b on a.Estado=b.idEstadoOrdenVenta\n" +
                        "inner join producto c on a.idProducto=c.idProducto\n" +
                        "order by a.idOrdenVenta";

        //DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        try (Connection conn = DriverManager.getConnection(url, usuario, clave);
                Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(cadena)) {

            int columnCount = rs.getMetaData().getColumnCount();

            //model.setRowCount(0);
            
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                //model.addRow(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje += "Error" + e;
        }
        return mensaje;
    }

    @Override
    public String Registrar(String nombre,float precio,int cantidad,String marca) {
                        String mensaje = "";
        // Consulta SQL de inserción
        String cadena = "insert into venta(nombre,precio,cantidad,marca)" +
                        "values(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement pstmt = conn.prepareStatement(cadena)) {

            // Establecer los valores de los parámetros
            pstmt.setString(1, nombre);  
            pstmt.setFloat(2, precio); 
            pstmt.setInt(3, cantidad); 
            pstmt.setString(4, marca);

            // Ejecutar el insert
            int rowsAffected = pstmt.executeUpdate();

            // Verificar el resultado
            if (rowsAffected > 0) {
                mensaje += "Operación realizada con exito";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje += "Error" + e;
        }
        return mensaje;
    }
    
    @Override
    public String Actualizar(int id,String nombre, float nuevoPrecio, int nuevaCantidad, String nuevaMarca) {
    String mensaje = "";
    // Consulta SQL de actualización
    String cadena = "update venta set precio = ?, cantidad = ?, marca = ? where idVenta = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, clave);
         PreparedStatement pstmt = conn.prepareStatement(cadena)) {

        // Establecer los valores de los parámetros
        pstmt.setFloat(1, nuevoPrecio); 
        pstmt.setInt(2, nuevaCantidad); 
        pstmt.setString(3, nuevaMarca);
        pstmt.setString(4, nombre);  
        pstmt.setInt(5, id);

        // Ejecutar el update
        int rowsAffected = pstmt.executeUpdate();

        // Verificar el resultado
        if (rowsAffected > 0) {
            mensaje += "Operación de actualización realizada con éxito";
        } else {
            mensaje += "No se encontró el registro para actualizar";
        }

    } catch (SQLException e) {
        e.printStackTrace();
        mensaje += "Error: " + e.getMessage();
    }
    return mensaje;
}

    
    
    @Override
    public String Eliminar(int id) {
    String mensaje = "";
    // Consulta SQL de eliminación
    String cadena = "delete from venta where idVenta = ?";

    try (Connection conn = DriverManager.getConnection(url, usuario, clave);
         PreparedStatement pstmt = conn.prepareStatement(cadena)) {

        // Establecer el valor del parámetro
        pstmt.setInt(1, id);  // Condición para encontrar el registro

        // Ejecutar el delete
        int rowsAffected = pstmt.executeUpdate();

        // Verificar el resultado
        if (rowsAffected > 0) {
            mensaje += "Registro eliminado con éxito";
        } else {
            mensaje += "No se encontró el registro para eliminar";
        }

    } catch (SQLException e) {
        e.printStackTrace();
        mensaje += "Error: " + e.getMessage();
    }
    return mensaje;
}

    
}
