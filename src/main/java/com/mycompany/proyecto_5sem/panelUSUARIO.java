/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto_5sem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marvin Siles
 */
public class panelUSUARIO extends javax.swing.JFrame {

    /**
     * Creates new form panelUSUARIO
     */
    private int idUsuario;
    private int idRol;
    conexionMysql conecta = new conexionMysql();
    Connection cn = conecta.estableceConexion();
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(panelUSUARIO.class);

    public panelUSUARIO(int idUsuario) {
        this.idUsuario = idUsuario;
        initComponents();
        mostrarNombreUsuario();
        mostrarFecha();
        setLocationRelativeTo(null);
        logger.info("Inicializando panelUSUARIO");

        Principal_usu prc = new Principal_usu(idUsuario);
        cargarPanel(prc);
        logger.info("Panel Principal_usu cargado en panelUSUARIO");

        ImageIcon icon = new ImageIcon(getClass().getResource("/logousbb.png"));
        Image img = icon.getImage();
        Image imgEscalada = img.getScaledInstance(255, 86, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(imgEscalada));
        lblImagen.setPreferredSize(new Dimension(255, 86));
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);

        logger.info("Imagen logousbb.png cargada y configurada");
        
    }

    public void setIdUsuario(int idUsuario, int idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        logger.info("Seteado idUsuario: " + idUsuario + ", idRol: " + idRol);
        habilitarPanelesPermitidos(idRol);
    }

// Método para habilitar los paneles según el rol del usuario
    private void habilitarPanelesPermitidos(int idRol) {
        logger.info("Cargando accesos de panel para idRol: " + idRol);

        // Ocultar todos los paneles por defecto
        equiposDisponibles.setVisible(false);
        misPrestamosPanel.setVisible(false);
        valoracionesPanel.setVisible(false);
        perfilPanel.setVisible(false);
        ReportesPanel.setVisible(false);
        MantenimientoPanel.setVisible(false);

        try {
            String sql = "SELECT nombre_panel FROM accesos_panel WHERE id_rol = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombrePanel = rs.getString("nombre_panel");
                logger.info("Permiso para panel: " + nombrePanel);

                switch (nombrePanel) {
                    case "Equipos Disponibles":
                        equiposDisponibles.setVisible(true);
                        break;
                    case "Mis Préstamos":
                        misPrestamosPanel.setVisible(true);
                        break;
                    case "Valoraciones":
                        valoracionesPanel.setVisible(true);
                        break;
                    case "Perfil / Cerrar Sesión":
                        perfilPanel.setVisible(true);
                        break;
                    case "Reportes":
                        ReportesPanel.setVisible(true);
                        break;
                    case "Mantenimiento":
                        MantenimientoPanel.setVisible(true);
                        break;
                }
            }
        } catch (SQLException e) {
            logger.error("Error al cargar accesos del rol: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error al cargar accesos del rol: " + e.getMessage());
        }
    }

    public void mostrarFecha() {
        // Obtener la fecha actual
        LocalDate fechaHoy = LocalDate.now();

        // Obtener el día de la semana (ej. "jueves")
        DayOfWeek diaSemana = fechaHoy.getDayOfWeek();
        String diaNombre = diaSemana.getDisplayName(java.time.format.TextStyle.FULL, new Locale("es", "ES"));

        // Formatear la fecha (ej. "29 de mayo de 2025")
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaFormateada = fechaHoy.format(formato);

        // Combinar y mostrar en el JLabel
        String texto = diaNombre + ", " + fechaFormateada;
        lblFecha.setText(texto);
    }
    private void mostrarNombreUsuario() {
        String nombre = obtenerNombreUsuario(idUsuario);
        if (nombre != null && !nombre.isEmpty()) {
            NomUsuario.setText("Bienvenido!! " + nombre);
            logger.info("Nombre de usuario cargado: {0}", nombre);
        } else {
            NomUsuario.setText("Bienvenido!! Usuario");
            logger.error("No se pudo obtener el nombre del usuario con ID: {0}", idUsuario);
        }
    }

    private void cargarPanel(JPanel panel) {
        logger.info("Cargando panel: " + panel.getClass().getSimpleName());
        panel.setSize(950, 450);
        panel.setLocation(0, 0);
        Contenido.removeAll();
        Contenido.add(panel, BorderLayout.CENTER);
        Contenido.revalidate();
        Contenido.repaint();
        logger.info("Panel cargado correctamente: " + panel.getClass().getSimpleName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        equiposDisponibles = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblImagen = new javax.swing.JLabel();
        misPrestamosPanel = new javax.swing.JButton();
        valoracionesPanel = new javax.swing.JButton();
        perfilPanel = new javax.swing.JButton();
        ReportesPanel = new javax.swing.JButton();
        MantenimientoPanel = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        NomUsuario = new javax.swing.JLabel();
        Contenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(205, 213, 219));

        jPanel2.setBackground(new java.awt.Color(7, 23, 57));

        equiposDisponibles.setBackground(new java.awt.Color(166, 136, 104));
        equiposDisponibles.setForeground(new java.awt.Color(255, 255, 255));
        equiposDisponibles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        equiposDisponibles.setText("EQUIPOS DISPONIBLES");
        equiposDisponibles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equiposDisponiblesActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(166, 136, 104));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        jButton3.setText("PRINCIPAL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        misPrestamosPanel.setBackground(new java.awt.Color(166, 136, 104));
        misPrestamosPanel.setForeground(new java.awt.Color(255, 255, 255));
        misPrestamosPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        misPrestamosPanel.setText("MIS PRESTAMOS");
        misPrestamosPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misPrestamosPanelActionPerformed(evt);
            }
        });

        valoracionesPanel.setBackground(new java.awt.Color(166, 136, 104));
        valoracionesPanel.setForeground(new java.awt.Color(255, 255, 255));
        valoracionesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        valoracionesPanel.setText("VALORACIONES");
        valoracionesPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valoracionesPanelActionPerformed(evt);
            }
        });

        perfilPanel.setBackground(new java.awt.Color(166, 136, 104));
        perfilPanel.setForeground(new java.awt.Color(255, 255, 255));
        perfilPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        perfilPanel.setText("CERRAR SECION");
        perfilPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilPanelActionPerformed(evt);
            }
        });

        ReportesPanel.setBackground(new java.awt.Color(166, 136, 104));
        ReportesPanel.setForeground(new java.awt.Color(255, 255, 255));
        ReportesPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        ReportesPanel.setText("REPORTES");
        ReportesPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesPanelActionPerformed(evt);
            }
        });

        MantenimientoPanel.setBackground(new java.awt.Color(166, 136, 104));
        MantenimientoPanel.setForeground(new java.awt.Color(255, 255, 255));
        MantenimientoPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/casa (1).png"))); // NOI18N
        MantenimientoPanel.setText("MANTENIMIENTO");
        MantenimientoPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MantenimientoPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblImagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(equiposDisponibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(misPrestamosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valoracionesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ReportesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MantenimientoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(perfilPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equiposDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(misPrestamosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valoracionesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReportesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MantenimientoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(perfilPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(7, 23, 57));

        lblFecha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(255, 255, 255));
        lblFecha.setText("jLabel1");

        NomUsuario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        NomUsuario.setForeground(new java.awt.Color(255, 255, 255));
        NomUsuario.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(NomUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(NomUsuario))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout ContenidoLayout = new javax.swing.GroupLayout(Contenido);
        Contenido.setLayout(ContenidoLayout);
        ContenidoLayout.setHorizontalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        ContenidoLayout.setVerticalGroup(
            ContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(Contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(Contenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void equiposDisponiblesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equiposDisponiblesActionPerformed
        logger.info("Botón Equipos Disponibles presionado, cargando panel EquiposDisponibles con idUsuario: " + idUsuario);
        EquiposDisponibles ed = new EquiposDisponibles(idUsuario); // ← Aquí sí pasás el ID
        cargarPanel(ed);

    }//GEN-LAST:event_equiposDisponiblesActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        logger.info("Botón Principal presionado, cargando panel Principal_usu");
        Principal_usu prc = new Principal_usu(idUsuario);
        cargarPanel(prc);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void misPrestamosPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misPrestamosPanelActionPerformed
        logger.info("Botón Mis Préstamos presionado, cargando panel MisPrestamoss con idUsuario: " + idUsuario);
        MisPrestamoss ps = new MisPrestamoss(idUsuario);  // Asegúrate de que idUsuario esté disponible
        cargarPanel(ps);

    }//GEN-LAST:event_misPrestamosPanelActionPerformed

    private void valoracionesPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valoracionesPanelActionPerformed
        logger.info("Botón Valoraciones presionado, cargando panel Valoraciones con idUsuario: " + idUsuario);
        Valoraciones vl = new Valoraciones(idUsuario);
        cargarPanel(vl);

    }//GEN-LAST:event_valoracionesPanelActionPerformed

    private void perfilPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilPanelActionPerformed
        // Cerrar o ocultar ventana actual
        this.dispose();  // O this.setVisible(false);

        // Mostrar ventana de login
        Inicio login = new Inicio();
        login.setVisible(true);
    }//GEN-LAST:event_perfilPanelActionPerformed

    private void ReportesPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesPanelActionPerformed
        logger.info("Botón Reportes presionado, cargando panel Reportes");
        ReportesGen re = new ReportesGen();
        cargarPanel(re);
    }//GEN-LAST:event_ReportesPanelActionPerformed

    private void MantenimientoPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MantenimientoPanelActionPerformed
        logger.info("Botón Mantenimiento presionado, cargando panel Mantenimiento");
        Mantenimiento mt = new Mantenimiento();
        cargarPanel(mt);
    }//GEN-LAST:event_MantenimientoPanelActionPerformed

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
            java.util.logging.Logger.getLogger(panelUSUARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panelUSUARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panelUSUARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panelUSUARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenido;
    private javax.swing.JButton MantenimientoPanel;
    private javax.swing.JLabel NomUsuario;
    private javax.swing.JButton ReportesPanel;
    private javax.swing.JButton equiposDisponibles;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JButton misPrestamosPanel;
    private javax.swing.JButton perfilPanel;
    private javax.swing.JButton valoracionesPanel;
    // End of variables declaration//GEN-END:variables
public class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Pintar fondo por defecto
            imagen = new ImageIcon(getClass().getResource("/LOGO-USB.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false); // Hace el panel transparente
        }
    }
    private String obtenerNombreUsuario(int idUsuario) {
        logger.info("Obteniendo nombre de usuario para id_usuario: {0}", idUsuario);
        String nombre = "";
        conexionMysql conecta = new conexionMysql();
        Connection cn = conecta.estableceConexion();

        String sql = "SELECT nombre FROM usuarios WHERE id_usuario = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombre = rs.getString("nombre");
                logger.info("Nombre de usuario obtenido: {0}", nombre);
            } else {
                logger.warn("No se encontró usuario con id: {0}", idUsuario);
            }

        } catch (SQLException e) {
            logger.error("Error al obtener el nombre del usuario con id: " + idUsuario, e);
            JOptionPane.showMessageDialog(null, "Error al obtener el nombre del usuario: " + e.getMessage());
        }

        return nombre;
    }
}
