# T&M Backend
Este proyecto es una aplicación de gestión de usuarios y roles desarrollada utilizando **Spring Boot**. El objetivo principal es proporcionar una estructura básica para manejar las operaciones CRUD sobre entidades como **Usuario** y **Rol**, con vistas en **Thymeleaf** y persistencia en una base de datos MySQL.

## Tecnologías utilizadas

Este proyecto ha sido desarrollado con las siguientes tecnologías y dependencias principales:

- **Spring Boot**: Framework principal para el desarrollo de aplicaciones en Java.
- **Spring Web**: Para el manejo de las rutas HTTP y la creación de controladores.
- **Spring Data**: Para facilitar la interacción con la base de datos y la gestión de entidades.
- **Spring Validation**: Para la validación de formularios y datos introducidos por el usuario.
- **Thymeleaf**: Motor de plantillas para la generación de vistas en HTML.
- **Lombok**: Librería para reducir la escritura de código repetitivo como getters, setters, y constructores.
- **Hibernate Core**: Para la gestión de la persistencia de datos utilizando el patrón ORM (Object-Relational Mapping) y futuras funcionalidades con mensajes HQL para realizar Queries dentro de los servicios.
- **MySQL Connector**: Driver JDBC para conectar la aplicación con una base de datos MySQL.

## Características

Este proyecto incluye las siguientes funcionalidades:

1. **Controladores**:
    
    - Se han creado controladores para manejar las operaciones CRUD de usuarios y roles.
    - Los controladores manejan las solicitudes y respuestas HTTP, gestionando las vistas Thymeleaf para mostrar formularios y tablas.
2. **Entidades**:
    
    - Se han creado las entidades `Usuario` y `Rol` utilizando anotaciones de **JPA** (Java Persistence API).
    - Estas entidades representan las tablas en la base de datos y permiten mapear sus relaciones a través de Hibernate.
3. **Conexión con Base de Datos**:
    
    - La configuración de la base de datos se realiza a través del archivo `application.properties`, en el que se define la conexión con una base de datos MySQL utilizando el conector JDBC.
4. **Formulario de Inserción y Actualización**:
    
    - Se han creado formularios Thymeleaf para insertar y actualizar registros de usuarios.
    - Los formularios utilizan validaciones a través de **Spring Validation** para garantizar la correcta introducción de datos.
5. **Estilos y Vistas**:
    
    - Se ha implementado un **header** básico y un conjunto de estilos CSS para proporcionar una estructura visual sencilla a la aplicación.
6. **DAO (Data Access Object)** y **Servicios**:
    
    - Se han creado repositorios DAO para las entidades `Usuario` y `Rol`.
    - En lugar de utilizar `JpaRepository`, se ha optado por el uso de **`CrudRepository`** para la implementación de los métodos CRUD (Create, Read, Update, Delete).
    - Los métodos de servicio no están implementados utilizando `ResponseEntity<>` como suele ser común en proyectos REST, sino que se devuelven **listas** (`List<>`) y **opciones** (`Optional<>`) según la necesidad de la consulta.
7. **CRUD Completo**:
    
    - Las operaciones CRUD están completamente implementadas para la entidad `Usuario`. Esto incluye la creación, lectura, actualización y eliminación de usuarios en la base de datos.
8. **Trabajo en Progreso**:
    
    - Actualmente se está trabajando en la internacionalización (i18n) utilizando el archivo `message.properties` para habilitar la traducción de textos.
    - También se está desarrollando la seguridad de la aplicación, que incluirá autenticación y autorización de usuarios.
