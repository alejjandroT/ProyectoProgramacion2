package controladores;

import interfaces.ConductorService;
import modelo.Conductor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ConductorControlador {

    private ConductorService conductorService;
    private JTable tabla;
    
    

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public ConductorControlador(ConductorService conductorService, JTable tabla) {
        this.conductorService = conductorService;
        this.tabla = tabla;
      
    }

    public void registrarConductor(String nombre, String apellido, String identificacion) {
        Conductor conductor = new Conductor(nombre, apellido, identificacion);
        conductorService.registrarConductor(conductor);
        actualizarTabla();
    }

    public void modificarConductor(String id, String nombre, String apellido) {
        Conductor conductor = new Conductor(nombre, apellido, id);
        conductorService.modificarConductor(conductor);
        actualizarTabla();
    }
    
    public void eliminarConductor(String identificacion) {
        conductorService.eliminarConductor(identificacion);
        actualizarTabla();
    }

    public void actualizarTabla() {
        if (tabla == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        List<Conductor> conductores = conductorService.listarConductores();
        for (Conductor conductor : conductores) {
            model.addRow(new Object[]{
                conductor.getNombre(),
                conductor.getApellido(),
                conductor.getIdentificacion()
            });
        }
    }

}
