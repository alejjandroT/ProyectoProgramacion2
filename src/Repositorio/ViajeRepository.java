package Repositorio;

import interfaces.Repository;
import modelo.Viaje;
import modelo.Conductor;
import modelo.Vehiculo;
import modelo.Ciudad;
import util.DatabaseConeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ViajeRepository implements Repository<Viaje> {
    private final ConductorRepository conductorRepository;
    private final VehiculoRepository vehiculoRepository;
    private final CiudadRepository ciudadRepository;
    private Connection connection;  

    public ViajeRepository(ConductorRepository conductorRepository, VehiculoRepository vehiculoRepository, CiudadRepository ciudadRepository, Connection connection) {
        this.conductorRepository = conductorRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.ciudadRepository = ciudadRepository;
        this.connection = connection;
    }

    @Override
    public void save(Viaje viaje) {
        if (connection == null) {
            throw new IllegalStateException("La conexión a la base de datos es null");
        }
        String sql = "INSERT INTO viajes (id_conductor, placa_vehiculo, id_origen, id_destino, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, viaje.getIdConductor());
            stmt.setString(2, viaje.getPlacaVehiculo());
            stmt.setInt(3, viaje.getOrigen().getIdCiudad());
            stmt.setInt(4, viaje.getDestino().getIdCiudad());
            stmt.setString(5, viaje.getEstado());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    viaje.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el viaje: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(String id, Viaje viaje) {
        String sql = "UPDATE viajes SET id_conductor = ?, placa_vehiculo = ?, id_origen = ?, id_destino = ?, estado = ? WHERE id_conductor = ? AND placa_vehiculo = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, viaje.getIdConductor());
            stmt.setString(2, viaje.getPlacaVehiculo());
            stmt.setInt(3, viaje.getOrigen().getIdCiudad());
            stmt.setInt(4, viaje.getDestino().getIdCiudad());
            stmt.setString(5, viaje.getEstado());
            stmt.setString(6, viaje.getIdConductor());
            stmt.setString(7, viaje.getPlacaVehiculo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el viaje: " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        String[] parts = id.split("(?<=\\G.{20})"); // Divide id_conductor y placa_vehiculo
        if (parts.length == 2) {
            String idConductor = parts[0];
            String placaVehiculo = parts[1];
            String sql = "DELETE FROM viajes WHERE id_conductor = ? AND placa_vehiculo = ?";
            try (Connection conn = DatabaseConeccion.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, idConductor);
                stmt.setString(2, placaVehiculo);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al eliminar el viaje: " + e.getMessage());
            }
        }
    }

    @Override
    public Viaje findById(String id) {
        String[] parts = id.split("(?<=\\G.{20})");
        if (parts.length == 2) {
            String idConductor = parts[0];
            String placaVehiculo = parts[1];
            String sql = "SELECT * FROM viajes WHERE id_conductor = ? AND placa_vehiculo = ?";
            try (Connection conn = DatabaseConeccion.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, idConductor);
                stmt.setString(2, placaVehiculo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Conductor conductor = conductorRepository.findById(rs.getString("id_conductor"));
                    Vehiculo vehiculo = vehiculoRepository.findById(rs.getString("placa_vehiculo"));
                    Ciudad origen = ciudadRepository.findById(rs.getInt("id_origen"));
                    Ciudad destino = ciudadRepository.findById(rs.getInt("id_destino"));
                    Viaje viaje = new Viaje(conductor, vehiculo, origen, destino);
                    viaje.setEstado(rs.getString("estado"));
                    return viaje;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Viaje> findAll() {
        List<Viaje> viajes = new ArrayList<>();
        String sql = "SELECT * FROM viajes";
        try (Connection conn = DatabaseConeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Conductor conductor = conductorRepository.findById(rs.getString("id_conductor"));
                Vehiculo vehiculo = vehiculoRepository.findById(rs.getString("placa_vehiculo"));
                Ciudad origen = ciudadRepository.findById(rs.getInt("id_origen"));
                Ciudad destino = ciudadRepository.findById(rs.getInt("id_destino"));
                Viaje viaje = new Viaje(conductor, vehiculo, origen, destino);
                viaje.setEstado(rs.getString("estado"));
                viajes.add(viaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar los viajes: " + e.getMessage());
        }
        return viajes;
    }

    public List<Viaje> findByConductor(String idConductor) {
        List<Viaje> viajes = new ArrayList<>();
        String sql = "SELECT * FROM viajes WHERE id_conductor = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idConductor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Conductor conductor = conductorRepository.findById(rs.getString("id_conductor"));
                Vehiculo vehiculo = vehiculoRepository.findById(rs.getString("placa_vehiculo"));
                Ciudad origen = ciudadRepository.findById(rs.getInt("id_origen"));
                Ciudad destino = ciudadRepository.findById(rs.getInt("id_destino"));
                Viaje viaje = new Viaje(conductor, vehiculo, origen, destino);
                viaje.setEstado(rs.getString("estado"));
                viajes.add(viaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viajes;
    }
}