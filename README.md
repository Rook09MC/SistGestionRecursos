# Sistema de Control y Préstamo de Recursos Tecnológicos
Un sistema de gestión de recursos tecnológicos diseñado para automatizar y optimizar el control de préstamos, devoluciones y mantenimiento de equipos de electrónica, hardware, redes y telecomunicaciones. Minimiza errores y agiliza la administración de activos.

## 🚀 Acerca del Proyecto
Motivación/Problema: 
En entornos con una alta rotación y diversidad de equipos tecnológicos, la gestión manual de préstamos y el seguimiento de inventario presenta desafíos significativos, como el riesgo de pérdida de información, errores humanos y una baja eficiencia operativa. Este proyecto aborda estas problemáticas.
Solución: 
Nuestro 'Sistema de Control y Préstamo de Recursos Tecnológicos' es una solución robusta y amigable que automatiza completamente el proceso de gestión de equipos. Permite registrar activos, controlar préstamos y devoluciones, monitorear el mantenimiento y generar reportes detallados, asegurando una administración de recursos más eficiente y segura.
Beneficios/Valor:
El sistema optimiza la eficiencia operativa al reducir el tiempo de búsqueda y registro, minimiza los errores asociados a procesos manuales y moderniza la administración de activos tecnológicos, proveyendo una herramienta esencial y segura tanto para usuarios como para responsables de recursos.

## ✨ Características
- Gestión completa de usuarios (registro, roles: administrador, docente, estudiante).
- Control de inventario de equipos (electrónica, hardware, redes, telecomunicaciones).
- Funcionalidad de préstamo y devolución de equipos con registro de historial.
- Sistema de reservas de equipos.
- Módulo de registro y seguimiento de mantenimiento y reparación.
- Generación de reportes detallados (préstamos activos, historial, mantenimiento, usuarios, valoraciones).
- Interfaz de usuario intuitiva (basada en formularios Swing).
- Gestión de categorías y marcas de equipos.
- Sistema de valoración de equipos.
- Generación de códigos QR para identificación de equipos (mencionado por tus archivos qr_equipo_*.png).

## 🛠️ Tecnologías Usadas
- Lenguaje: Java (JDK 21+)
- Interfaz de Usuario (GUI): Java Swing (esto es clave para proyectos de escritorio en Java).
- Gestor de Dependencias: Apache Maven.
- Base de Datos: MySQL
- Conexión DB: JDBC (Java Database Connectivity)
- IDE: Apache NetBeans.
- Control de Versiones: Git & GitHub

## ⚙️ Instalación
- Clonar el Repositorio: git clone https://github.com/Rook09MC/SistGestionRecursos.git
- Configuración de la Base de Datos:
    - Tener MySQL Server instalado.
    - Crear una base de datos.
    - Importar el esquema de la base de datos: Si tienes un archivo .sql.
    - Configurar credenciales: Indicar dónde se deben actualizar las credenciales de conexión a la base de datos en el código Java (src/main/java/com/mycompany/proyecto_5sem/conexionMysql.java).
- Abrir en IDE (NetBeans): Pasos para abrir el proyecto Maven en NetBeans.
- Ejecutar: Cómo ejecutar la aplicación principal desde el IDE.

## 🚀 Uso
Una vez que la aplicación esté en funcionamiento, se presentará la interfaz de inicio de sesión. Puedes utilizar las siguientes credenciales de ejemplo para administradores: usuario: admin, contraseña: admin. Explora los diferentes módulos de gestión de equipos, préstamos, reservas, mantenimiento y generación de reportes.

## 📂 Estructura del Proyecto
- Pantalla de Login
- Panel de Administrador / Usuario Principal
- Gestión de Equipos (CRUD)
- Panel de Préstamos/Reservas
- Historial de Mantenimiento/Reportes
- Módulo 3D (QR) y chatbot.












