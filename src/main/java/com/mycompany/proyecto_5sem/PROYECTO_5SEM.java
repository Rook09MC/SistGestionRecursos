/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto_5sem;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marvin Siles
 */
public class PROYECTO_5SEM {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PROYECTO_5SEM.class);
    

    public static void main(String[] args) {
        logger.info("Inicio de la aplicación.");

        try {
            Inicio objetoFormulario = new Inicio();
            objetoFormulario.setVisible(true);
            logger.info("Ventana principal mostrada correctamente.");
        } catch (Exception e) {
            logger.error( "Error al iniciar la aplicación: ", e);
        }
    }
}
