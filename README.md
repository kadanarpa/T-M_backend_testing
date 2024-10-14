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

# SQL Queries
## Crear Tablas
```SQL
CREATE TABLE roles (
	rol_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    rol_name VARCHAR(50) NOT NULL UNIQUE,
    rol_status BOOLEAN DEFAULT TRUE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE users (
    usr_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    usr_email VARCHAR(100) NOT NULL UNIQUE,
    usr_password VARCHAR(200) NOT NULL,
    usr_rol_id BIGINT,
    CONSTRAINT `fk_rol_id` FOREIGN KEY (usr_rol_id) REFERENCES roles(rol_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE owners (
	own_id BIGINT PRIMARY KEY NOT NULL,
    own_first_name VARCHAR(100) NOT NULL,
    own_last_name VARCHAR(100),
    own_address VARCHAR(50) NOT NULL,
    own_phone VARCHAR(10) NOT NULL,
    CONSTRAINT `fk_own_id` FOREIGN KEY (own_id) REFERENCES users(usr_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE veterinarians (
	vet_id BIGINT PRIMARY KEY NOT NULL,
    vet_first_name VARCHAR(100) NOT NULL,
    vet_last_name VARCHAR(100),
    vet_address VARCHAR(50) NOT NULL,
    vet_phone VARCHAR(10) NOT NULL,
    vet_specialty VARCHAR(100),
    vet_veterinary VARCHAR(100),
    CONSTRAINT `fk_vet_id` FOREIGN KEY (vet_id) REFERENCES users(usr_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pet_species (
    pes_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    pes_name VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pet_breeds (
    peb_pes_id BIGINT NOT NULL,
    peb_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    peb_name VARCHAR(100) NOT NULL,
    CONSTRAINT `fk_pes_id` FOREIGN KEY (peb_pes_id) REFERENCES pet_species(pes_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pets (
    pet_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    pet_own_id BIGINT NOT NULL,
    pet_name VARCHAR(100) NOT NULL,
    pet_specie_id BIGINT NOT NULL,
    pet_breed_id BIGINT NOT NULL,
    pet_age INT,
    pet_weight DECIMAL(5,2),
    CONSTRAINT `fk_pet_own_id` FOREIGN KEY (pet_own_id) REFERENCES owners(own_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    CONSTRAINT `fk_pet_specie_id` FOREIGN KEY (pet_specie_id) REFERENCES pet_species(pes_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
    CONSTRAINT `fk_pet_breed_id` FOREIGN KEY (pet_breed_id) REFERENCES pet_breeds(peb_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE appointment_status (
	aps_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    aps_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE appointments (
    app_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    own_id BIGINT,
    pet_id BIGINT,
    vet_id BIGINT,
    app_date DATE NOT NULL,
    app_reason VARCHAR(250),
    app_description VARCHAR(250),
    app_status_id BIGINT,
    CONSTRAINT `fk_app_own_id` FOREIGN KEY (own_id) REFERENCES owners(own_id)
    ON DELETE SET NULL,
    CONSTRAINT `fk_app_pet_id` FOREIGN KEY (pet_id) REFERENCES pets(pet_id)
    ON DELETE SET NULL,
    CONSTRAINT `fk_app_vet_id` FOREIGN KEY (vet_id) REFERENCES veterinarians(vet_id)
    ON DELETE SET NULL,
    CONSTRAINT `fk_aps_id` FOREIGN KEY (app_status_id) REFERENCES appointment_status(aps_id)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE product_types (
    prt_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    prt_name VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE products (
    pro_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    pro_name VARCHAR(100) NOT NULL UNIQUE,
    pro_description TEXT NOT NULL,
    pro_price DECIMAL(10,2) NOT NULL,
    pro_stock INT NOT NULL,
    pro_prt_id BIGINT NOT NULL,
    pro_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_prt_id` FOREIGN KEY (pro_prt_id) REFERENCES product_types(prt_id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE orders (
    ord_id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    usr_id BIGINT,
    ord_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ord_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT `fk_ord_usr_id` FOREIGN KEY (usr_id) REFERENCES users(usr_id)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE order_details (
    orde_id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ord_id BIGINT,
    pro_id BIGINT,
    orde_quantity INT NOT NULL,
    orde_unit_price DECIMAL(10,2) NOT NULL,
    CONSTRAINT `fk_ord_id` FOREIGN KEY (ord_id) REFERENCES orders(ord_id)
    ON DELETE SET NULL,
    CONSTRAINT `fk_pro_id` FOREIGN KEY (pro_id) REFERENCES products(pro_id)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
```
## Insertar Roles
```SQL
INSERT INTO `roles`(`rol_name`, `rol_status`) VALUES ('ROLE_ADMIN',1),('ROLE_USER',1), ('ROLE_VET',1);
```
