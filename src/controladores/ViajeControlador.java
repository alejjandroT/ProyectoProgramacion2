package controladores;

import modelo.Ciudad;
import modelo.Viaje;
import servicios.CiudadServiceImpl;
import interfaces.ViajeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;
import modelo.Conductor;

public class ViajeControlador {

    private ViajeService viajeService;
    private CiudadServiceImpl ciudadService;
    private JTable tabla;
    private JComboBox<String> cbConductores;
    private JComboBox<String> cbVehiculos;
    private JComboBox<String> cbOrigen;
    private JComboBox<String> cbDestino;

    public ViajeControlador(ViajeService viajeService, CiudadServiceImpl ciudadService, JTable tabla,
            JComboBox<String> cbConductores, JComboBox<String> cbVehiculos,
            JComboBox<String> cbOrigen, JComboBox<String> cbDestino) {
        this.viajeService = viajeService;
        this.ciudadService = ciudadService;
        this.tabla = tabla;
        this.cbConductores = cbConductores;
        this.cbVehiculos = cbVehiculos;
        this.cbOrigen = cbOrigen;
        this.cbDestino = cbDestino;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void setComboBoxes(JComboBox<String> cbConductores, JComboBox<String> cbVehiculos,
            JComboBox<String> cbOrigen, JComboBox<String> cbDestino) {
        this.cbConductores = cbConductores;
        this.cbVehiculos = cbVehiculos;
        this.cbOrigen = cbOrigen;
        this.cbDestino = cbDestino;
    }

    public void registrarViaje(String nombreConductor, String placaVehiculo, Ciudad origen, Ciudad destino) {
        String idConductor = null;
        List<Conductor> conductores = viajeService.getConductorService().listarConductores();
        for (Conductor conductor : conductores) {
            String nombreCompleto = conductor.getNombre() + " " + conductor.getApellido();
            if (nombreCompleto.equals(nombreConductor)) {
                idConductor = conductor.getIdentificacion();
                break;
            }
        }
        if (idConductor == null) {
            throw new IllegalArgumentException("Conductor no encontrado: " + nombreConductor);
        }
        viajeService.registrarViaje(idConductor, placaVehiculo, origen, destino);
        actualizarTabla();  
    }

    public void modificarViaje(String id, String nombreConductor, String placaVehiculo, Ciudad origen, Ciudad destino) {
        String idConductor = null;
        List<Conductor> conductores = viajeService.getConductorService().listarConductores();
        for (Conductor conductor : conductores) {
            String nombreCompleto = conductor.getNombre() + " " + conductor.getApellido();
            if (nombreCompleto.equals(nombreConductor)) {
                idConductor = conductor.getIdentificacion();
                break;
            }
        }
        if (idConductor == null) {
            throw new IllegalArgumentException("Conductor no encontrado: " + nombreConductor);
        }
        Viaje viaje = new Viaje(
                viajeService.getConductorService().buscarConductor(idConductor),
                viajeService.getVehiculoService().buscarVehiculo(placaVehiculo),
                origen,
                destino
        );
        viajeService.modificarViaje(id, viaje);
        actualizarTabla();
    }

    public void eliminarViaje(String id) {
        viajeService.eliminarViaje(id);
        actualizarTabla();
    }

    public void actualizarTabla() {
        if (tabla == null) return;
    DefaultTableModel model = (DefaultTableModel) tabla.getModel();
    model.setRowCount(0);
    List<Viaje> viajes = viajeService.listarViajes();
    for (Viaje viaje : viajes) {
        String nombreOrigen = (viaje.getOrigen() != null) ? viaje.getOrigen().getNombre() : "Desconocido";
        String nombreDestino = (viaje.getDestino() != null) ? viaje.getDestino().getNombre() : "Desconocido";
        
        model.addRow(new Object[]{
            viaje.getIdConductor() + viaje.getPlacaVehiculo(),
            viaje.getIdConductor(),
            viaje.getPlacaVehiculo(),
            nombreOrigen, 
            nombreDestino,
            viaje.getEstado()
        });
    }
    }

    public void actualizarComboBoxes() {
        if (cbConductores != null) {
            cbConductores.removeAllItems();
            List<Conductor> conductores = viajeService.getConductorService().listarConductores();
            for (Conductor conductor : conductores) {
                cbConductores.addItem(conductor.getNombre() + " " + conductor.getApellido());
            }
        }
        if (cbVehiculos != null) {
            cbVehiculos.removeAllItems();
            List<String> placas = viajeService.getVehiculoService().listarPlacas();
            for (String placa : placas) {
                cbVehiculos.addItem(placa);
            }
        }
        if (cbOrigen != null) {
            cbOrigen.removeAllItems();
            List<Ciudad> ciudades = ciudadService.listarCiudades();
            for (Ciudad ciudad : ciudades) {
                cbOrigen.addItem(ciudad.getNombre());
            }
        }
        if (cbDestino != null) {
            cbDestino.removeAllItems();
            List<Ciudad> ciudades = ciudadService.listarCiudades();
            for (Ciudad ciudad : ciudades) {
                cbDestino.addItem(ciudad.getNombre());
            }
        }
    }

    public void arrancarViaje(String id) {
        viajeService.arrancarViaje(id);
        actualizarTabla();
    }

    public void detenerViaje(String id) {
        viajeService.detenerViaje(id);
        actualizarTabla();
    }

    public void buscarViajesPorConductor(String conductorBuscado) {
        if (tabla == null) return;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        if (conductorBuscado.isEmpty()) {
            actualizarTabla();
            return;
        }
        List<Conductor> conductores = viajeService.getConductorService().listarConductores();
        String idConductor = null;
        for (Conductor conductor : conductores) {
            String nombreCompleto = conductor.getNombre() + " " + conductor.getApellido();
            if (nombreCompleto.toLowerCase().contains(conductorBuscado.toLowerCase())) {
                idConductor = conductor.getIdentificacion();
                break;
            }
        }
      
        final String finalIdConductor = idConductor;
        if (finalIdConductor != null) {
            List<Viaje> viajesFiltrados = viajeService.listarViajes().stream()
                    .filter(v -> v.getIdConductor().equals(finalIdConductor))
                    .collect(Collectors.toList());
            for (Viaje viaje : viajesFiltrados) {
                model.addRow(new Object[]{
                    viaje.getIdConductor() + viaje.getPlacaVehiculo(),
                    viaje.getIdConductor(),
                    viaje.getPlacaVehiculo(),
                    viaje.getOrigen().getNombre(),
                    viaje.getDestino().getNombre(),
                    viaje.getEstado()
                });
            }
        }
    }

    public ViajeService getViajeService() {
        return viajeService;
    }

    public CiudadServiceImpl getCiudadService() {
        return ciudadService;
    }

    private Ciudad buscarCiudadPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }
        List<Ciudad> ciudades = ciudadService.listarCiudades();
        for (Ciudad ciudad : ciudades) {
            if (ciudad.getNombre().equals(nombre)) {
                return ciudad;
            }
        }
        return null;
    }

    private Ciudad getCiudadSeleccionada(JComboBox<String> comboBox) {
        String nombreCiudad = (String) comboBox.getSelectedItem();
        return buscarCiudadPorNombre(nombreCiudad);
    }

}
