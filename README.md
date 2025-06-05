# Sistema de Control y Préstamo de Recursos Tecnológicos ✨
![Estado del Proyecto](https://img.shields.io/badge/Estado-Completado-brightgreen)
![Lenguaje Principal](https://img.shields.io/badge/Lenguaje-Java-orange.svg)
[![Licencia: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Un sistema de gestión de recursos tecnológicos diseñado para automatizar y optimizar el control de préstamos, devoluciones y mantenimiento de equipos de electrónica, hardware, redes y telecomunicaciones. Minimiza errores y agiliza la administración de activos.

## 📚 Tabla de Contenidos
- [Acerca del Proyecto](#acerca-del-proyecto)
- [Características](#características)
- [Tecnologías Usadas](#tecnologías-usadas)
- [Instalación](#instalación)
- [Uso](#uso)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Contacto](#contacto)

## 🚀 Acerca del Proyecto
### Problema
En entornos con una alta rotación y diversidad de equipos tecnológicos, la gestión manual de préstamos y el seguimiento de inventario presenta desafíos significativos, como el riesgo de pérdida de información, errores humanos y una baja eficiencia operativa. Este proyecto aborda estas problemáticas.
### Solución
Nuestro 'Sistema de Control y Préstamo de Recursos Tecnológicos' es una solución robusta y amigable que automatiza completamente el proceso de gestión de equipos. Permite registrar activos, controlar préstamos y devoluciones, monitorear el mantenimiento y generar reportes detallados, asegurando una administración de recursos más eficiente y segura.
### Beneficios y Valor
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
- Generación de códigos QR para la identificación de equipos.

## 🛠️ Tecnologías Usadas
- Lenguaje: Java (JDK 21+)
- Interfaz de Usuario (GUI): Java Swing (esto es clave para proyectos de escritorio en Java).
- Gestor de Dependencias: Apache Maven.
- Base de Datos: MySQL
- Conexión DB: JDBC (Java Database Connectivity)
- IDE: Apache NetBeans.
- Control de Versiones: Git & GitHub

## ⚙️ Instalación
1.  **Clonar el Repositorio:**
    ```bash
    git clone [https://github.com/Rook09MC/SistGestionRecursos.git](https://github.com/Rook09MC/SistGestionRecursos.git)
    cd SistGestionRecursos # Asegúrate de que el nombre de la carpeta coincida
    ```
2.  **Configuración de la Base de Datos:**
    * Asegúrate de tener un servidor MySQL instalado y en ejecución (ej. XAMPP, WAMP, o instalación nativa).
    * Crea una base de datos con el nombre `tu_nombre_de_bd` (ej. `gestion_recursos_db`).
    * **Importa el esquema de la base de datos:** Si tienes un archivo `.sql` con la estructura de la DB (normalmente en una carpeta como `database/schema.sql` o similar), indica cómo ejecutarlo:
        ```bash
        # Ejemplo para ejecutar desde la consola MySQL:
        # mysql -u tu_usuario -p tu_nombre_de_bd < database/schema.sql
        ```
        (Si no tienes un archivo `.sql` para compartir, solo menciona que la BD debe ser creada y las tablas configuradas).
    * **Actualizar credenciales:** Edita el archivo `src/main/java/com/mycompany/proyecto_5sem/conexionMysql.java` para configurar el usuario, contraseña y nombre de la base de datos de tu instalación MySQL.

3.  **Abrir en IDE (Apache NetBeans):**
    * Abre Apache NetBeans.
    * Selecciona `File` > `Open Project...` y navega a la carpeta donde clonaste el repositorio (`SistGestionRecursos`).
    * NetBeans debería detectar el proyecto Maven y resolver automáticamente las dependencias.

4.  **Ejecutar el Proyecto:**
    * Una vez que el proyecto esté cargado en NetBeans, localiza el archivo principal `src/main/java/com/mycompany/proyecto_5sem/PROYECTO_5SEM.java`.
    * Haz clic derecho sobre este archivo y selecciona `Run File`.

## 🚀 Uso
Una vez que la aplicación esté en funcionamiento, se presentará la interfaz de inicio de sesión. Puedes utilizar las siguientes credenciales de ejemplo para administradores: usuario: admin, contraseña: admin. Explora los diferentes módulos de gestión de equipos, préstamos, reservas, mantenimiento y generación de reportes.

## 📂 Estructura del Proyecto
├── src/
│   ├── main/
│   │   ├── java/                         # Código fuente Java (.java, .form)
│   │   │   └── com/mycompany/proyecto_5sem/  # Paquete principal con todas las clases y formularios
│   │   └── resources/                    # Archivos de recursos (imágenes, logs, configuraciones)
├── assets/                               # Imágenes y otros recursos para el README
├── logs/                                 # Archivos de log generados por la aplicación
├── pom.xml                               # Archivo de configuración de Apache Maven
├── database/                             # (Opcional) Carpeta para el script SQL de la base de datos
└── README.md                             # Este documento


- Pantalla de Login
- Panel de Administrador / Usuario Principal
- Gestión de Equipos (CRUD)
- Panel de Préstamos/Reservas
- Historial de Mantenimiento/Reportes
- Módulo 3D (QR) y chatbot.

## ✉️ Contacto
Para cualquier consulta o colaboración, no dudes en contactarnos:

* **Estudiantes:**
    * Rolando Junior Mendoza Conde
    * Marvin Siles Mejía
    * Ángel Fernando Espinoza Condori
    * Brayan Yhojan Cardenas Condarco
* **Docente:** Ing. Edson David Veneros Vásquez
* **Email General:** Drackhe00@gmail.com
* **Enlace del Proyecto:** [https://github.com/Rook09MC/SistGestionRecursos](https://github.com/Rook09MC/SistGestionRecursos)










