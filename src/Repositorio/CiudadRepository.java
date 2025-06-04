package Repositorio;

import interfaces.Repository;
import modelo.Ciudad;
import util.DatabaseConeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiudadRepository implements Repository<Ciudad> {
    @Override
    public void save(Ciudad ciudad) {
        String sql = "INSERT INTO ciudad (nombre) VALUES (?)";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la ciudad: " + e.getMessage());
        }
    }

    public void update(int id, Ciudad ciudad) {
        String sql = "UPDATE ciudad SET nombre = ? WHERE id_ciudad = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ciudad.getNombre());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la ciudad: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ciudad WHERE id_ciudad = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la ciudad: " + e.getMessage());
        }
    }

    public Ciudad findById(int id) {
        String sql = "SELECT * FROM ciudad WHERE id_ciudad = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ciudad(rs.getInt("id_ciudad"), rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ciudad> findAll() {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudad";
        try (Connection conn = DatabaseConeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ciudades.add(new Ciudad(rs.getInt("id_ciudad"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar las ciudades: " + e.getMessage());
        }
        return ciudades;
    }

    @Override
    public void update(String id, Ciudad entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ciudad findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}