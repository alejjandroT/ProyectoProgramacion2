package controladores;

import interfaces.VehiculoService;
import java.util.HashMap;
import modelo.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import modelo.Conductor;

public class VehiculoControlador {

   private VehiculoService vehiculoService;
    private JTable tabla;
    private JComboBox<String> cbConductores;
    private Map<String, String> nombreAIdentificacion;

    public VehiculoControlador(VehiculoService vehiculoService, JTable tabla, JComboBox<String> cbConductores) {
       this.vehiculoService = vehiculoService;
        this.tabla = tabla;
        this.cbConductores = cbConductores;
        this.nombreAIdentificacion = new HashMap<>();
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setComboBoxConductores(JComboBox<String> cbConductores) {
        this.cbConductores = cbConductores;
    }

   public void registrarVehiculo(String placa, int potencia, String nombreConductor) {
        String idConductor = nombreAIdentificacion.get(nombreConductor);
        if (idConductor == null) {
            throw new IllegalArgumentException("No se encontró la identificación para el conductor: " + nombreConductor);
        }
        vehiculoService.registrarVehiculo(placa, potencia, idConductor);
    }

    public void modificarVehiculo(String placa, int potencia, String nombreConductor) {
        String idConductor = nombreAIdentificacion.get(nombreConductor);
        if (idConductor == null) {
            throw new IllegalArgumentException("No se encontró la identificación para el conductor: " + nombreConductor);
        }
        vehiculoService.registrarVehiculo(placa, potencia, idConductor);
    }
    public void eliminarVehiculo(String placa) {
        vehiculoService.eliminarVehiculo(placa);
    }

    public void actualizarTabla() {
        if (tabla == null) return;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        List<Vehiculo> vehiculos = vehiculoService.listarVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            Conductor conductor = vehiculo.getConductor();
            String idConductor = (conductor != null) ? conductor.getIdentificacion() : "Sin conductor";
            model.addRow(new Object[]{
                vehiculo.getPlaca(),
                vehiculo.getPotencia(),
                idConductor, 
                vehiculo.getMotor().isActivo() 
            });
        }
    }

   public void actualizarComboBox() {
        if (cbConductores != null) {
            cbConductores.removeAllItems();
            nombreAIdentificacion.clear();
            List<Conductor> conductores = vehiculoService.getConductorService().listarConductores();
            for (Conductor conductor : conductores) {
                String nombreCompleto = conductor.getNombre() + " " + conductor.getApellido();
                cbConductores.addItem(nombreCompleto);
                nombreAIdentificacion.put(nombreCompleto, conductor.getIdentificacion());
            }
        }
    }

}
