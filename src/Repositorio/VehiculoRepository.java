package Repositorio;
import interfaces.Repository;
import modelo.Vehiculo;
import modelo.Conductor;
import util.DatabaseConeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoRepository implements Repository<Vehiculo> {
    private final ConductorRepository conductorRepository;

    public VehiculoRepository(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    @Override
    public void save(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (placa, potencia, id_conductor, estado_motor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setInt(2, vehiculo.getPotencia());
            stmt.setString(3, vehiculo.getConductor().getIdentificacion());
            stmt.setBoolean(4, vehiculo.getMotor().isActivo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  @Override
public void update(String placa, Vehiculo vehiculo) {
    String sql = "UPDATE vehiculos SET potencia = ?, id_conductor = ?, estado_motor = ? WHERE placa = ?";
    try (Connection conn = DatabaseConeccion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, vehiculo.getPotencia());
        stmt.setString(2, vehiculo.getConductor() != null ? vehiculo.getConductor().getIdentificacion() : null);
        stmt.setBoolean(3, vehiculo.getMotor().isActivo());
        stmt.setString(4, placa);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
public void delete(String placa) {
    String sql = "DELETE FROM vehiculos WHERE placa = ?"; 
    try (Connection conn = DatabaseConeccion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, placa);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    @Override
    public Vehiculo findById(String placa) {
        String sql = "SELECT * FROM vehiculos WHERE placa = ?";
        try (Connection conn = DatabaseConeccion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Conductor conductor = conductorRepository.findById(rs.getString("id_conductor"));
                return new Vehiculo(
                    rs.getString("placa"),
                    rs.getInt("potencia"),
                    conductor
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehiculo> findAll() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos ORDER BY placa";
        try (Connection conn = DatabaseConeccion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Conductor conductor = conductorRepository.findById(rs.getString("id_conductor"));
                vehiculos.add(new Vehiculo(
                    rs.getString("placa"),
                    rs.getInt("potencia"),
                    conductor
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }
}