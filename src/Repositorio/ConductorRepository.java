package Repositorio;

import interfaces.Repository;
import modelo.Conductor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConeccion;

public class ConductorRepository implements Repository<Conductor> {
    @Override
    public void save(Conductor conductor) {
        String sql = "INSERT INTO conductores (identificacion, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conductor.getIdentificacion());
            stmt.setString(2, conductor.getNombre());
            stmt.setString(3, conductor.getApellido());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, Conductor conductor) {
        String sql = "UPDATE conductores SET nombre = ?, apellido = ? WHERE identificacion = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conductor.getNombre());
            stmt.setString(2, conductor.getApellido());
            stmt.setString(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM conductores WHERE identificacion = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Conductor findById(String id) {
        String sql = "SELECT * FROM conductores WHERE identificacion = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Conductor(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("identificacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Conductor> findAll() {
        List<Conductor> conductores = new ArrayList<>();
        String sql = "SELECT * FROM conductores ORDER BY identificacion";
        try (Connection conn = DatabaseConeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                conductores.add(new Conductor(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("identificacion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conductores;
    }
}