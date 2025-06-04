package Repositorio;

import interfaces.Repository;
import modelo.Ruta;
import modelo.Ciudad;
import util.DatabaseConeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RutaRepository implements Repository<Ruta> {
    private final CiudadRepository ciudadRepository;

    public RutaRepository(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public void save(Ruta ruta) {
        String sql = "INSERT INTO rutas (id_origen, id_destino, distancia, costo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ruta.getOrigen().getIdCiudad());
            stmt.setInt(2, ruta.getDestino().getIdCiudad());
            stmt.setDouble(3, ruta.getDistancia());
            stmt.setDouble(4, ruta.getCosto());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                ruta.setIdRuta(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la ruta: " + e.getMessage());
        }
    }

    public void update(int id, Ruta ruta) {
        String sql = "UPDATE rutas SET id_origen = ?, id_destino = ?, distancia = ?, costo = ? WHERE id_ruta = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ruta.getOrigen().getIdCiudad());
            stmt.setInt(2, ruta.getDestino().getIdCiudad());
            stmt.setDouble(3, ruta.getDistancia());
            stmt.setDouble(4, ruta.getCosto());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar la ruta: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM rutas WHERE id_ruta = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la ruta: " + e.getMessage());
        }
    }

    public Ruta findById(int id) {
        String sql = "SELECT * FROM rutas WHERE id_ruta = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Ciudad origen = ciudadRepository.findById(rs.getInt("id_origen"));
                Ciudad destino = ciudadRepository.findById(rs.getInt("id_destino"));
                Ruta ruta = new Ruta(origen, destino, rs.getDouble("distancia"), rs.getDouble("costo"));
                ruta.setIdRuta(id);
                return ruta;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ruta> findAll() {
        List<Ruta> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";
        try (Connection conn = DatabaseConeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ciudad origen = ciudadRepository.findById(rs.getInt("id_origen"));
                Ciudad destino = ciudadRepository.findById(rs.getInt("id_destino"));
                Ruta ruta = new Ruta(origen, destino, rs.getDouble("distancia"), rs.getDouble("costo"));
                ruta.setIdRuta(rs.getInt("id_ruta"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar las rutas: " + e.getMessage());
        }
        return rutas;
    }

    @Override
    public void update(String id, Ruta entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ruta findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}