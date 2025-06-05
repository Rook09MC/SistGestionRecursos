/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_5sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marvin Siles
 */

public class conexionMysql {
    Connection conecta = null;
    String usuario ="root";
    String contraseña ="sqlo69";
    String bd = "Proyecto_5sem";
    String ip = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(conexionMysql.class);
    
   public Connection estableceConexion() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conecta = DriverManager.getConnection(cadena, usuario, contraseña);
        logger.info("Conexión a la base de datos establecida correctamente.");
        // JOptionPane.showMessageDialog(null, "Se conectó a la base de datos");
    } catch (Exception e) {
        logger.error("No se pudo conectar a la base de datos, error: " + e.toString());
        // JOptionPane.showMessageDialog(null, "No se conectó a la base de datos, error:" + e.toString());
    }
    return conecta;
}
}