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

## Autenticación y Validación

Este proyecto implementa un sistema de autenticación y validación robusto utilizando Spring Security y JWT (JSON Web Tokens). A continuación, se detallan los aspectos clave de la implementación:

### Autenticación

-   **Inicio de Sesión:**
    -   El `AutenticacionController` gestiona el inicio de sesión, recibiendo un DTO (`DatosAutenticacion`) con las credenciales del usuario (login y contraseña).
    -   Este DTO se convierte en un `UsernamePasswordAuthenticationToken`, un formato que Spring Security puede procesar.
    -   El `AuthenticationManager` autentica al usuario.
    -   Si la autenticación es exitosa, se genera un token JWT utilizando el `TokenService`.
    -   El token JWT se devuelve al cliente en un DTO (`DatosTokenJWT`).

-   **Generación de Tokens JWT:**
    -   El `TokenService` es responsable de generar tokens JWT utilizando la biblioteca Auth0JWT.
    -   El token incluye información como el emisor, el asunto (login del usuario) y la fecha de expiración.
    -   La firma del token se realiza con un algoritmo HMAC256 y una clave secreta.

-   **Filtro de Seguridad:**
    -   El `SecurityFilter` es un filtro que se ejecuta antes de cada petición.
    -   Recupera el token JWT del encabezado de autorización (`Authorization`).
    -   Valida el token y extrae el "subject" (login del usuario).
    -   Utiliza el `UsuarioRepository` para obtener los detalles del usuario.
    -   Crea un `UsernamePasswordAuthenticationToken` para informar a Spring Security que el usuario ha sido autenticado.
    -   Establece la autenticación en el `SecurityContextHolder`.

### Seguridad

-   **Configuración de Seguridad:**
    -   La clase `SecurityConfigurations` define las políticas de seguridad del sistema.
    -   Deshabilita el CSRF (Cross-Site Request Forgery) ya que la API es stateless.
    -   Configura la gestión de sesiones como stateless.
    -   Define qué endpoints requieren autenticación y cuáles son permitidos (por ejemplo, `/login` es público).
    -   Añade el `SecurityFilter` antes del filtro de autenticación por nombre de usuario y contraseña de Spring Security.

-   **Hashing de Contraseñas:**
    -   Se utiliza Bcrypt para realizar el hashing de las contraseñas antes de almacenarlas en la base de datos.
    -   Bcrypt es un algoritmo de hashing de una sola vía, lo que significa que no se puede obtener la contraseña original a partir del hash.

### Validación

-   **Validación de Datos de Entrada:**
    -   Se utiliza la anotación `@Valid` para validar los datos de entrada en los controladores.
    -   Si los datos no son válidos, se lanza una `MethodArgumentNotValidException`.
    -   El `GestorDeErrores` intercepta esta excepción y devuelve una respuesta de error 400 (Bad Request) con los detalles de los errores de validación.

-   **Gestión de Errores:**
    -   El `RestControllerAdvice` (`GestorDeErrores`) gestiona las excepciones de forma centralizada.
    -   Para `EntityNotFoundException`, devuelve un error 404 (Not Found).
    -   Para `MethodArgumentNotValidException`, devuelve un error 400 (Bad Request) con los detalles de los errores de validación.

### Detalles del Usuario

-   **Implementación de `UserDetails`:**
    -   La clase `Usuario` implementa la interfaz `UserDetails` de Spring Security.
    -   Esto permite a Spring Security utilizar la clase `Usuario` para la autenticación y autorización.
    -   Se implementan los métodos `getAuthorities`, `getPassword` y `getUsername` para proporcionar a Spring Security la información necesaria.

-   **Repositorio de Usuarios:**
    -   El `UsuarioRepository` extiende `JpaRepository` y proporciona métodos para buscar usuarios en la base de datos.
    -   Se utiliza el método `findByLogin` para buscar un usuario por su nombre de usuario (login).