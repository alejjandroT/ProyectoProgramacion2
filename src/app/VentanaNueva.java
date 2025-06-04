package app;

import Repositorio.CiudadRepository;
import Repositorio.ConductorRepository;
import Repositorio.RutaRepository;
import Repositorio.VehiculoRepository;
import Repositorio.ViajeRepository;
import Servicios.ConductorServiceImpl;
import Servicios.VehiculoServiceImpl;
import java.sql.Connection;
import controladores.ConductorControlador;
import controladores.VehiculoControlador;
import controladores.ViajeControlador;
import interfaces.VehiculoService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import servicios.CiudadServiceImpl;
import servicios.RutaServiceImpl;
import servicios.ViajeServiceImpl;
import util.DatabaseConeccion;
import java.sql.SQLException;

/**
 *
 * @author luist
 */
public class VentanaNueva extends javax.swing.JFrame {

    private ConductorControlador conductorControlador;
    private VehiculoControlador vehiculoControlador;
    private ViajeControlador viajeControlador;
    private JMenuBar menuBar;
    private JMenu menuConductor;
    private JMenuItem menuItemGestionarConductores;
    private CiudadServiceImpl ciudadService;
    private RutaServiceImpl rutaService;
    private Connection connection;
    
    
    /**
     * Creates new form VentanaNueva
     */
    public VentanaNueva() {
      try {
            connection = DatabaseConeccion.getConnection(); 
            if (connection == null) {
                throw new SQLException("La conexión a la base de datos es null");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        
        }
        
        inicializarControladores();
        initComponents();
        configurarEventosMenu();
        menuBar = new JMenuBar();
        menuConductor = new JMenu("Conductor");
        menuItemGestionarConductores = new JMenuItem("Gestión de Conductores");
        escritorio.setLayout(null);

    }

    private void inicializarControladores() {
        
        if (connection == null) {
            throw new IllegalStateException("La conexión a la base de datos no está inicializada");
        }
        
        ConductorRepository conductorRepository = new ConductorRepository();
        VehiculoRepository vehiculoRepository = new VehiculoRepository(conductorRepository);
        CiudadRepository ciudadRepository = new CiudadRepository();
        RutaRepository rutaRepository = new RutaRepository(ciudadRepository);
        ViajeRepository viajeRepository = new ViajeRepository(conductorRepository, vehiculoRepository, ciudadRepository, (java.sql.Connection) connection);

        ConductorServiceImpl conductorService = new ConductorServiceImpl(conductorRepository);
        VehiculoServiceImpl vehiculoService = new VehiculoServiceImpl(vehiculoRepository, conductorService);
        ViajeServiceImpl viajeService = new ViajeServiceImpl(viajeRepository, conductorService, vehiculoService);
        CiudadServiceImpl ciudadService = new CiudadServiceImpl(ciudadRepository);
        RutaServiceImpl rutaService = new RutaServiceImpl(rutaRepository);

        conductorControlador = new ConductorControlador(conductorService, null);
        vehiculoControlador = new VehiculoControlador(vehiculoService, null, null);
        viajeControlador = new ViajeControlador(viajeService, ciudadService, null, null, null, null, null);
        this.ciudadService = ciudadService;
        this.rutaService = rutaService;
    }

    private void configurarEventosMenu() {
        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaConductor conductorFrame = new VentanaConductor(conductorControlador);
                escritorio.add(conductorFrame);//Es aca
                conductorFrame.setVisible(true);
                conductorFrame.toFront();
            }
        });

        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaVehiculo vehiculoFrame = new VentanaVehiculo(vehiculoControlador, conductorControlador);
                escritorio.add(vehiculoFrame);
                vehiculoFrame.setVisible(true);
                vehiculoFrame.toFront();
            }
        });

        jMenuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaViajes viajeFrame = new VentanaViajes(viajeControlador, conductorControlador, vehiculoControlador, ciudadService);
                escritorio.add(viajeFrame);
                viajeFrame.setVisible(true);
                viajeFrame.toFront();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(102, 102, 102));
        escritorio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cfb95fb0d36763d1793eb381502c00af.gif"))); // NOI18N
        escritorio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/giphy (1).gif"))); // NOI18N
        escritorio.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, 272));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Itzikgur-My-Seven-Travel-BMV.256.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 210, 270));

        escritorio.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 0, 200, 270));

        jMenuBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Acidrums4-Betelgeuse-Apps-preferences-desktop-theme.128_1.png"))); // NOI18N
        jMenu1.setText("Conductor");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem1.setText("Gestionar Conductor");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Informacion Del Conductor");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cemagraphics-Classic-Cars-Chevrolet-impala.128.png"))); // NOI18N
        jMenu2.setText("Vehiculo");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem2.setText("Gestionar Vehiculo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem5.setText("Informacion Del Vehiculo");
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Tribalmarkings-Colorflow-Travel-2.128.png"))); // NOI18N
        jMenu3.setText("Viaje");
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jMenuItem6.setText("Gestionar Viaje");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Informacion Del Viaje");
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1177, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
     if (ciudadService == null) {
        System.err.println("Error: ciudadService es null en VentanaNueva");
        return;
    }
    VentanaViajes ventanaViaje = new VentanaViajes(viajeControlador, conductorControlador, vehiculoControlador, ciudadService);
    escritorio.add(ventanaViaje);
    ventanaViaje.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaNueva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaNueva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaNueva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaNueva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaNueva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
