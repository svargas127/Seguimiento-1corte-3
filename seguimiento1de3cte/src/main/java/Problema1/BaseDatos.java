package Problema1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDatos {
    private Connection connection;

    public BaseDatos() {
        conectar();
    }

    private void conectar() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:supermercado.db");
            crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS Ventas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "clientesAtendidos INTEGER, "
                + "totalVentas REAL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardarRegistro(int clientes, double ventas) {
        String sql = "INSERT INTO Ventas(clientesAtendidos, totalVentas) VALUES(?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clientes);
            stmt.setDouble(2, ventas);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrar() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
