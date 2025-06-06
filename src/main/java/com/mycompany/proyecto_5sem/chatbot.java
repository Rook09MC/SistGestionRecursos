/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto_5sem;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Marvin Siles
 */
public class chatbot extends javax.swing.JFrame {

    /**
     * Creates new form chatbot
     */
    conexionMysql conecta = new conexionMysql();
    Connection cn = conecta.estableceConexion();
    private boolean reservaActiva = false;
    private int idUsuario;
    private StringBuilder chatHTML = new StringBuilder("<html><body>");
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(chatbot.class);
    private Map<Integer, Integer> mapaCategorias = new HashMap<>();
    private String estadoChatbot = "NORMAL";  // Estados: NORMAL, ESPERANDO_CATEGORIA
    private boolean esperandoCategoria = false;

    public chatbot(int idUsuario) {
        this.idUsuario = idUsuario;
        initComponents();
        setLocationRelativeTo(null);

        chatArea.setContentType("text/html"); // Permite interpretar HTML
        chatArea.setEditable(false); // Para que el usuario no escriba en el área
        chatArea.setText("<html><body></body></html>"); // Inicializamos con HTML vacío

        logger.info("Chatbot iniciado para el usuario con ID: " + idUsuario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        chatArea.setBackground(new java.awt.Color(164, 181, 196));
        chatArea.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        chatArea.setForeground(new java.awt.Color(255, 255, 255));
        chatArea.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(chatArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String pregunta = inputField.getText().trim();
        if (!pregunta.isEmpty()) {
            logger.info("Pregunta recibida: {}", pregunta);

            String nuevoMensaje = "<p><b>Tú:</b> " + pregunta + "</p>";
            String respuesta;
            String preguntaMinuscula = pregunta.toLowerCase();

            if (preguntaMinuscula.contains("reservar un equipo") || preguntaMinuscula.contains("cómo reservar")) {
                reservaActiva = true;

                respuesta = "Para realizar una reserva de un equipo, sigue los siguientes pasos:<br><br>"
                        + "1. Ve al panel <b>EQUIPOS DISPONIBLES</b>.<br>"
                        + "2. Selecciona un equipo de la <b>LISTA DE EQUIPOS DISPONIBLES</b> y pon la <b>fecha de devolución</b> y la <b>cantidad</b> del equipo.<br>"
                        + "3. Añade a tu lista con el botón <b>\"Añadir a mi lista\"</b>.<br>"
                        + "4. Para terminar, confirma la reserva con el botón <b>\"CONFIRMAR\"</b>.<br><br>"
                        + "Si tienes alguna duda en los pasos que te di, selecciona el número del paso y te ayudo con la información.";

                logger.info("Modo reserva activado.");

            } else if (esperandoCategoria) {
                try {
                    int opcionUsuario = Integer.parseInt(pregunta.trim());
                    if (mapaCategorias.containsKey(opcionUsuario)) {
                        int idCategoriaSeleccionada = mapaCategorias.get(opcionUsuario);
                        respuesta = obtenerEquiposPorCategoria(idCategoriaSeleccionada);
                        // Mantener esperandoCategoria = true para seguir seleccionando categorías
                    } else {
                        // Número no válido: salgo del modo esperandoCategoria
                        esperandoCategoria = false;
                        respuesta = "Opción inválida. Salgo del modo selección de categorías. ¿En qué más te puedo ayudar?";
                    }
                } catch (NumberFormatException e) {
                    esperandoCategoria = false;
                    // Procesar la pregunta normalmente, como fuera del modo selección
                    respuesta = enviarPregunta(pregunta);
                }

            } else if (pregunta.equals("1") || pregunta.equals("2") || pregunta.equals("3") || pregunta.equals("4")) {
                if (reservaActiva) {
                    switch (pregunta) {
                        case "1":
                            respuesta = "Aquí te explico 1: Debes ir al panel 'EQUIPOS DISPONIBLES', donde verás todos los equipos que puedes reservar.";
                            break;
                        case "2":
                            respuesta = "Aquí te explico 2: En la lista de equipos disponibles, haz clic sobre el equipo que deseas. Luego, selecciona la fecha de devolución y escribe la cantidad que necesitas.";
                            break;
                        case "3":
                            respuesta = "Aquí te explico 3: Una vez que hayas configurado el equipo, haz clic en el botón 'Añadir a mi lista'. Esto agregará el equipo a tu lista de reserva.";
                            break;
                        case "4":
                            respuesta = "Aquí te explico 4: Después de añadir los equipos a tu lista, confirma la reserva haciendo clic en el botón 'CONFIRMAR'.";
                            break;
                        default:
                            respuesta = "Paso no reconocido.";
                            logger.warn("Paso no reconocido: {}", pregunta);
                    }
                } else {
                    respuesta = "Para poder explicarte un paso, primero pregúntame cómo reservar un equipo.";
                    logger.warn("Intento de acceder a un paso sin activar el modo de reserva.");
                }

            } else if (preguntaMinuscula.contains("equipos disponibles") || preguntaMinuscula.contains("qué equipos hay") || preguntaMinuscula.contains("mostrar categorías")) {
                // Mostrar categorías disponibles y pedir que elija una
                mapaCategorias.clear();
                StringBuilder sb = new StringBuilder("Elige una categoría para ver equipos disponibles:<br>");

                int opcion = 1;  // <-- definir fuera del while
                try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_5sem", "root", "sqlo69")) {
                    String sql = "SELECT id_categoria, nombre_categoria FROM categorias";
                    PreparedStatement ps = cn.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        int idCategoria = rs.getInt("id_categoria");
                        String nombre = rs.getString("nombre_categoria");
                        sb.append(opcion).append(". ").append(nombre).append("<br>");
                        mapaCategorias.put(opcion, idCategoria);
                        opcion++;
                    }
                    rs.close();
                    ps.close();
                } catch (Exception e) {
                    sb.append("Error al obtener categorías: ").append(e.getMessage());
                    logger.error("Error al obtener categorías: ", e);
                }

                esperandoCategoria = true;  // activo modo espera número
                respuesta = sb.toString();

            } else if (preguntaMinuscula.contains("mis equipos prestados") || preguntaMinuscula.contains("qué equipos tengo prestados")) {
                respuesta = obtenerHistorialPrestamos(idUsuario);
                reservaActiva = false;
                logger.info("Consulta sobre equipos prestados del usuario con ID {}", idUsuario);

            } else if (preguntaMinuscula.contains("sancion")
                    || preguntaMinuscula.contains("bloqueo")
                    || preguntaMinuscula.contains("multa")
                    || preguntaMinuscula.contains("penalización")) {

                respuesta = consultarSanciones(idUsuario);
                reservaActiva = false;
            } else if (preguntaMinuscula.matches("cancelar reserva \\d+")) {
                int idPrestamo = Integer.parseInt(preguntaMinuscula.replaceAll("\\D+", ""));
                boolean cancelada = cancelarReserva(idPrestamo);

                if (cancelada) {
                    respuesta = "✅ Reserva cancelada con éxito.";
                } else {
                    respuesta = "⚠️ No se pudo cancelar la reserva. Verifica que el ID sea correcto y que esté en estado 'reservado'.";
                }
                reservaActiva = false;
            } else if (preguntaMinuscula.contains("mis reservas activas")
                    || preguntaMinuscula.contains("qué reservé")
                    || preguntaMinuscula.contains("qué tengo reservado")
                    || preguntaMinuscula.contains("mostrar reservas")) {
                respuesta = obtenerReservasActivas(idUsuario);
                reservaActiva = false;
                logger.info("Consulta sobre reservas activas (estado reservado) del usuario con ID {}", idUsuario);
            } else {
                reservaActiva = false;
                respuesta = enviarPregunta(pregunta); // respuesta del modelo IA
                logger.info("Pregunta enviada al modelo IA.");
            }

            respuesta = procesarRespuestaConMarkdown(respuesta);
            logger.info("Respuesta generada: {}", respuesta.replaceAll("<[^>]*>", "").trim()); // quitamos HTML para log

            nuevoMensaje += "<p><b>Bot:</b> " + respuesta + "</p>";
            chatHTML.append(nuevoMensaje);

            String htmlCompleto = "<html><body style='width:100%; font-family: sans-serif;'>"
                    + chatHTML.toString()
                    + "</body></html>";

            chatArea.setContentType("text/html");
            chatArea.setText(htmlCompleto);
            chatArea.setCaretPosition(chatArea.getDocument().getLength());

            inputField.setText("");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatbot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane chatArea;
    private javax.swing.JTextField inputField;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private static final String API_KEY = "AIzaSyB8Uejk3-_n0catSqDIjVwVWhOyEC9pD2E";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    public static String enviarPregunta(String pregunta) {
        logger.info("Enviando pregunta a Gemini: {}", pregunta);

        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Construir el JSON
            JSONObject textPart = new JSONObject();
            textPart.put("text", "Responde siempre en español. " + pregunta);

            JSONObject content = new JSONObject();
            content.put("parts", new JSONArray().put(textPart));

            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", new JSONArray().put(content));

            // Enviar JSON
            try (OutputStream os = con.getOutputStream()) {
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
                logger.debug("JSON enviado a Gemini: {}", requestBody.toString());
            }

            // Leer respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseBuilder.append(line);
            }
            in.close();

            logger.debug("Respuesta cruda recibida de Gemini: {}", responseBuilder.toString());

            // Procesar JSON de respuesta
            JSONObject jsonResponse = new JSONObject(responseBuilder.toString());
            JSONArray candidates = jsonResponse.getJSONArray("candidates");
            JSONObject first = candidates.getJSONObject(0);
            JSONObject contentResponse = first.getJSONObject("content");
            JSONArray parts = contentResponse.getJSONArray("parts");
            JSONObject firstPart = parts.getJSONObject(0);

            String markdown = firstPart.getString("text").trim();
            logger.info("Texto recibido desde Gemini: {}", markdown.replaceAll("\\n", " "));

            String respuestaHtml = markdownToHtml(markdown);
            if (markdown.contains("|") && markdown.contains("---")) {
                respuestaHtml = procesarRespuestaConMarkdown(markdown);
            }

            return respuestaHtml;

        } catch (Exception e) {
            logger.error("Error al contactar con Gemini: ", e);
            return "Error al contactar con Gemini: " + e.getMessage();
        }
    }

    private static String markdownToHtml(String markdown) {
        logger.debug("Convirtiendo markdown a HTML...");
        String html = markdown;

        html = html.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>");
        html = html.replaceAll("`([^`]+)`", "<code>$1</code>");
        html = html.replaceAll("(?s)```(.*?)```", "<pre>$1</pre>");
        html = html.replaceAll("(?m)^- (.*?)$", "<li>$1</li>");
        if (html.contains("<li>")) {
            html = html.replaceAll("(<li>.*?</li>)", "<ul>$1</ul>");
            html = html.replaceAll("</ul><ul>", "");
        }
        html = html.replaceAll("(?m)^\\d+\\. (.*?)$", "<li>$1</li>");
        if (html.contains("<li>")) {
            html = html.replaceAll("(<li>.*?</li>)", "<ol>$1</ol>");
            html = html.replaceAll("</ol><ol>", "");
        }
        html = html.replaceAll("\n", "<br>");

        logger.debug("HTML generado desde markdown: {}", html.replaceAll("\\n", " "));
        return html;
    }

    public static String convertirMarkdownATablaHTML(String markdown) {
        logger.debug("Convirtiendo tabla Markdown a HTML...");
        String[] lines = markdown.split("\n");
        StringBuilder html = new StringBuilder("<table border='1' cellpadding='5' style='border-collapse: collapse;'>");

        boolean headerParsed = false;
        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }
            // Línea separadora de tabla: ---|---|--- la ignoramos
            if (line.trim().matches("^\\|?\\s*-+\\s*(\\|\\s*-+\\s*)+\\|?$")) {
                continue;
            }

            String[] cells = line.split("\\|");
            html.append("<tr>");
            for (String cell : cells) {
                cell = cell.trim();
                if (!cell.isEmpty()) {
                    if (!headerParsed) {
                        html.append("<th>").append(cell).append("</th>");
                    } else {
                        html.append("<td>").append(cell).append("</td>");
                    }
                }
            }
            html.append("</tr>");
            if (!headerParsed) {
                headerParsed = true;
            }
        }

        html.append("</table><br>");
        logger.debug("HTML generado para tabla:\n{}", html.toString());
        return html.toString();
    }

    public static String procesarRespuestaConMarkdown(String respuesta) {
        logger.info("Procesando respuesta con posible Markdown...");
        String[] lines = respuesta.split("\n");
        StringBuilder html = new StringBuilder();
        StringBuilder tablaMarkdown = new StringBuilder();
        boolean dentroDeTabla = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            // Detectamos si la línea parece parte de una tabla Markdown:
            boolean esLineaTabla = line.contains("|") && !line.matches("^\\s*-+\\s*$");

            if (esLineaTabla) {
                tablaMarkdown.append(line).append("\n");
                dentroDeTabla = true;
            } else {
                if (dentroDeTabla) {
                    logger.debug("Tabla Markdown detectada, convirtiendo...");
                    html.append(convertirMarkdownATablaHTML(tablaMarkdown.toString()));
                    tablaMarkdown.setLength(0);
                    dentroDeTabla = false;
                }
                if (!line.trim().isEmpty()) {
                    logger.debug("Procesando línea fuera de tabla: {}", line.trim());
                    String textoEstilizado = markdownToHtml(line.trim());
                    html.append("<p>").append(textoEstilizado).append("</p>");
                }
            }
        }

        // Si al final quedó tabla pendiente, convertirla también
        if (dentroDeTabla) {
            logger.debug("Final de respuesta, convirtiendo tabla pendiente...");
            html.append(convertirMarkdownATablaHTML(tablaMarkdown.toString()));
        }

        logger.info("Conversión de Markdown a HTML completada.");
        return html.toString();
    }

    private String obtenerHistorialPrestamos(int idUsuario) {
        StringBuilder respuesta = new StringBuilder();
        logger.info("Consultando historial de préstamos para el usuario con ID: {}", idUsuario);

        try {
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_5sem", "root", "sqlo69");
            String sql = "SELECT e.nombre, p.fecha_prestamo, p.fecha_devolucion_esperada, ep.nombre_estado "
                    + "FROM prestamos p "
                    + "JOIN equipos e ON p.id_equipo = e.id_equipo "
                    + "JOIN estado_prestamo ep ON p.id_estado = ep.id_estado "
                    + "WHERE p.id_usuario = ? "
                    + "ORDER BY p.fecha_prestamo DESC "
                    + "LIMIT 1000";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                respuesta.append("<div style='padding:10px;border:1px solid #ccc;'>No tienes historial de préstamos.</div>");
                logger.info("El usuario con ID {} no tiene historial de préstamos.", idUsuario);
            } else {
                respuesta.append("<div style='overflow-x:auto;'>");
                respuesta.append("<table border='1' cellpadding='8' style='border-collapse: collapse; width: 100%;'>");
                respuesta.append("<thead><tr style='background-color: #f2f2f2;'>")
                        .append("<th>Equipo</th>")
                        .append("<th>Fecha de préstamo</th>")
                        .append("<th>Fecha devolución esperada</th>")
                        .append("<th>Estado</th>")
                        .append("</tr></thead><tbody>");

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String fechaPrestamo = rs.getString("fecha_prestamo");
                    String fechaDevolucion = rs.getString("fecha_devolucion_esperada");
                    String estado = rs.getString("nombre_estado");

                    respuesta.append("<tr>")
                            .append("<td>").append(nombre).append("</td>")
                            .append("<td>").append(fechaPrestamo).append("</td>")
                            .append("<td>").append(fechaDevolucion).append("</td>")
                            .append("<td>").append(estado).append("</td>")
                            .append("</tr>");
                    logger.debug("Historial: Equipo: {}, Prestado: {}, Devolver: {}, Estado: {}", nombre, fechaPrestamo, fechaDevolucion, estado);
                }

                respuesta.append("</tbody></table></div>");
                logger.info("Se recuperó historial de préstamos para usuario con ID: {}", idUsuario);
            }

            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
            respuesta.append("<div style='color:red;'>Error al consultar el historial de préstamos.</div>");
            logger.error("Error al consultar historial de préstamos para usuario con ID {}: {}", idUsuario, e.getMessage(), e);
        }

        return respuesta.toString();
    }

    private String obtenerEquiposPorCategoria(int idCategoria) {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipos disponibles en la categoría:<br>");
        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_5sem", "root", "sqlo69")) {
            String sql = "SELECT nombre, stock FROM equipos WHERE id_categoria = ? AND stock > 0";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            ResultSet rs = ps.executeQuery();

            boolean hayEquipos = false;
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("stock");
                sb.append("- ").append(nombre).append(" (Stock: ").append(stock).append(")<br>");
                hayEquipos = true;
            }

            if (!hayEquipos) {
                sb.append("No hay equipos disponibles en esta categoría.");
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            sb.append("Error al obtener equipos: ").append(e.getMessage());
            logger.error("Error al obtener equipos por categoría: ", e);
        }
        return sb.toString();
    }

    private String obtenerReservasActivas(int idUsuario) {
        StringBuilder respuesta = new StringBuilder();
        logger.info("Consultando reservas activas (estado: reservado) para el usuario con ID: {}", idUsuario);

        try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_5sem", "root", "sqlo69")) {
            String sql = "SELECT p.id_prestamo, u.nombre AS nombre_usuario, u.apellido AS apellido_usuario, "
                    + "e.nombre AS nombre_equipo, p.fecha_prestamo, p.fecha_devolucion_esperada, "
                    + "ep.nombre_estado AS estado_prestamo "
                    + "FROM prestamos p "
                    + "JOIN usuarios u ON p.id_usuario = u.id_usuario "
                    + "JOIN equipos e ON p.id_equipo = e.id_equipo "
                    + "JOIN estado_prestamo ep ON p.id_estado = ep.id_estado "
                    + "WHERE ep.nombre_estado = 'reservado' "
                    + "AND u.id_usuario = ? "
                    + "ORDER BY p.id_prestamo ASC";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                respuesta.append("<div style='padding:10px;border:1px solid #ccc;'>Actualmente no tienes reservas activas.</div>");
                logger.info("El usuario con ID {} no tiene reservas con estado 'reservado'.", idUsuario);
            } else {
                respuesta.append("<div style='overflow-x:auto;'>");
                respuesta.append("<table border='1' cellpadding='8' style='border-collapse: collapse; width: 100%;'>");
                respuesta.append("<thead><tr style='background-color: #f2f2f2;'>")
                        .append("<th>ID Préstamo</th>")
                        .append("<th>Equipo</th>")
                        .append("<th>Fecha de Préstamo</th>")
                        .append("<th>Fecha de Devolución</th>")
                        .append("<th>Estado</th>")
                        .append("</tr></thead><tbody>");

                while (rs.next()) {
                    int idPrestamo = rs.getInt("id_prestamo");
                    String equipo = rs.getString("nombre_equipo");
                    String fechaPrestamo = rs.getString("fecha_prestamo");
                    String fechaDevolucion = rs.getString("fecha_devolucion_esperada");
                    String estado = rs.getString("estado_prestamo");

                    respuesta.append("<tr>")
                            .append("<td>").append(idPrestamo).append("</td>")
                            .append("<td>").append(equipo).append("</td>")
                            .append("<td>").append(fechaPrestamo).append("</td>")
                            .append("<td>").append(fechaDevolucion).append("</td>")
                            .append("<td>").append(estado).append("</td>")
                            .append("</tr>");
                }

                respuesta.append("</tbody></table></div>");

                // 🟢 Mensaje adicional debajo de la tabla
                respuesta.append("<div style='margin-top:10px; padding:10px; background-color:#e9f5ff; border:1px solid #b3d8ff;'>")
                        .append("ℹ️ Si deseas cancelar una reserva, por favor indica el <strong>ID del préstamo</strong> que aparece en la tabla.")
                        .append("</div>");

                logger.info("Reservas activas encontradas para el usuario con ID: {}", idUsuario);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            respuesta.append("<div style='color:red;'>Error al consultar tus reservas activas.</div>");
            logger.error("Error al consultar reservas activas para el usuario con ID {}: {}", idUsuario, e.getMessage(), e);
        }

        return respuesta.toString();
    }

    private boolean cancelarReserva(int idPrestamo) {
        String url = "jdbc:mysql://localhost:3306/proyecto_5sem";
        String user = "root";
        String pass = "sqlo69";

        try (Connection cn = DriverManager.getConnection(url, user, pass)) {

            // 1. Verificar si la reserva está en estado 'reservado' y obtener datos
            String select = "SELECT p.id_equipo, p.cantidad FROM prestamos p "
                    + "JOIN estado_prestamo ep ON p.id_estado = ep.id_estado "
                    + "WHERE p.id_prestamo = ? AND ep.nombre_estado = 'reservado'";
            PreparedStatement psSelect = cn.prepareStatement(select);
            psSelect.setInt(1, idPrestamo);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                int idEquipo = rs.getInt("id_equipo");
                int cantidad = rs.getInt("cantidad");

                // 2. Sumar cantidad al stock
                String updateStock = "UPDATE equipos SET stock = stock + ? WHERE id_equipo = ?";
                PreparedStatement psStock = cn.prepareStatement(updateStock);
                psStock.setInt(1, cantidad);
                psStock.setInt(2, idEquipo);
                psStock.executeUpdate();

                // 3. Cambiar estado del préstamo a 'cancelado'
                // (Debes tener una fila con nombre_estado = 'cancelado' en la tabla estado_prestamo)
                String updateEstado = "UPDATE prestamos SET id_estado = (SELECT id_estado FROM estado_prestamo WHERE nombre_estado = 'cancelado') WHERE id_prestamo = ?";
                PreparedStatement psEstado = cn.prepareStatement(updateEstado);
                psEstado.setInt(1, idPrestamo);
                psEstado.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

private String consultarSanciones(int idUsuario) {
    StringBuilder resultado = new StringBuilder();
    try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_5sem", "root", "sqlo69")) {
        String sql = "SELECT sancionado_hasta FROM usuarios WHERE id_usuario = ?";
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            java.sql.Date sancionadoHasta = rs.getDate("sancionado_hasta");
            java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());

            if (sancionadoHasta != null && sancionadoHasta.after(hoy)) {
                resultado.append("⚠️ Estás sancionado hasta el día ")
                         .append(sancionadoHasta.toString())
                         .append(". Durante este tiempo no podrás realizar reservas ni préstamos.");
            } else {
                resultado.append("No tienes sanciones activas. ¡Puedes seguir usando el sistema con normalidad!");
            }
        } else {
            resultado.append("Usuario no encontrado.");
        }
        rs.close();
        ps.close();
    } catch (Exception e) {
        resultado = new StringBuilder("Error al consultar sanciones: " + e.getMessage());
    }
    return resultado.toString();
}


}
