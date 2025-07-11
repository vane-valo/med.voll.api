# API REST con Spring Boot 3

## Introducción

Este proyecto es una API REST desarrollada con Spring Boot 3. El objetivo principal es implementar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) básicas para la gestión de datos.

## Tecnologías Utilizadas

*   **Spring Boot 3:** Framework para el desarrollo rápido de aplicaciones Java.
*   **Spring Data JPA:** Simplifica el acceso a la base de datos mediante JPA.
*   **Lombok:** Reduce el código boilerplate con anotaciones.
*   **MySQL:** Base de datos relacional utilizada para el almacenamiento de datos.
*   **Flyway:** Herramienta para la gestión de migraciones de la base de datos.
*   **Maven:** Gestor de dependencias y construcción del proyecto.

## Configuración del Entorno

1.  **Requisitos:**
    *   JDK (Java Development Kit)
    *   Maven
    *   IntelliJ IDEA (u otro IDE de tu preferencia)
2.  **Configuración de `application.properties`:**

    Asegúrate de configurar las siguientes propiedades en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.hibernate.ddl-auto=none
    ```

    *   `spring.datasource.url`: URL de la base de datos MySQL.
    *   `spring.datasource.username`: Nombre de usuario de la base de datos.
    *   `spring.datasource.password`: Contraseña de la base de datos.
    *   `spring.datasource.driver-class-name`: Driver JDBC para MySQL.
    *   `spring.jpa.properties.hibernate.dialect`: Dialecto de Hibernate para MySQL 8.
    *   `spring.jpa.hibernate.ddl-auto`: Configurado como `none` para que Flyway gestione las migraciones.

## Estructura del Proyecto

*   **`pom.xml`:** Archivo de configuración de Maven que define las dependencias y la configuración del proyecto.
*   **Entidades JPA:** Clases que representan las tablas en la base de datos.
*   **DTOs (Data Transfer Objects):** Clases utilizadas para transferir datos entre el front-end y el back-end.
*   **Controladores:** Clases que manejan las solicitudes HTTP y definen los endpoints de la API.
*   **Repositorios:** Interfaces que facilitan el acceso y la manipulación de los datos en la base de datos.
*   **Migraciones de Flyway:** Scripts SQL para la gestión de la evolución del esquema de la base de datos.

## Operaciones CRUD Implementadas

La API REST implementa las siguientes operaciones CRUD:

*   **Crear (Create):** Permite crear nuevos registros en la base de datos.
*   **Leer (Read):** Permite recuperar registros de la base de datos.
*   **Actualizar (Update):** Permite modificar registros existentes en la base de datos.
*   **Eliminar (Delete):**
    *   **Eliminación Lógica:** Marca un registro como inactivo sin eliminarlo físicamente.
    *   **Eliminación Física:** Elimina el registro de la base de datos.

## Uso de Insomnia

Se recomienda utilizar [Insomnia](https://insomnia.rest/) para probar los endpoints de la API REST. Puedes importar un archivo de configuración de Insomnia (si lo tienes

