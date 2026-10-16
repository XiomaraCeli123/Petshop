# 🐾 PetShop

Aplicación web de tienda de mascotas desarrollada con **Java y Spring Boot**. 
Permite administrar un catálogo de productos, registrar usuarios, gestionar citas y exportar el listado de productos a PDF.

## Funcionalidades

- Registro e inicio de sesión de usuarios con **Spring Security**
- **CRUD de productos**: crear, ver, editar y eliminar, con imagen por producto
- Gestión de **citas**
- **Exportación de productos a PDF**
- Interfaz disponible en **español, inglés y francés**

## Tecnologías

| Área | Tecnologías |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot, Spring Security, Spring Data JPA |
| Vistas | Thymeleaf, HTML5, CSS3 |
| Base de datos | MySQL |
| Gestión del proyecto | Maven |

## Requisitos previos

- Java 17 o superior
- MySQL en ejecución (por ejemplo, desde XAMPP)
- Git

## Cómo ejecutarlo

1. Clona el repositorio:

   ```bash
   git clone https://github.com/XiomaraCeli123/Petshop.git
   cd Petshop
   ```

2. Crea la base de datos en MySQL:

   ```sql
   CREATE DATABASE tiendaPetShop;
   ```

3. Revisa la conexión en `src/main/resources/application.properties`. Por defecto usa:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3307/tiendaPetShop
   spring.datasource.username=root
   spring.datasource.password=
   ```

   Si tu MySQL usa otro puerto (por ejemplo, 3306) o tiene contraseña, ajústalo aquí.

4. Ejecuta la aplicación:

   ```bash
   # En Windows
   .\mvnw.cmd spring-boot:run

   # En Linux o macOS
   ./mvnw spring-boot:run
   ```

5. Abre en tu navegador: [http://localhost:8080](http://localhost:8080)

Las tablas se crean automáticamente al iniciar la aplicación.

## Estructura del proyecto

```
src/main/java/com/dominio/PetShop
├── config         # Configuración de seguridad y web
├── controllers    # Controladores (productos, citas, cuentas, PDF)
├── models         # Entidades y DTOs
├── repositories   # Acceso a datos
├── services       # Lógica de negocio
src/main/resources
├── templates      # Vistas Thymeleaf
├── static         # CSS e imágenes
└── messages_*.properties   # Textos en varios idiomas
```

## Autora

**Damaris Xiomara Quispe Celi**
Desarrolladora Frontend Junior · [GitHub](https://github.com/XiomaraCeli123) · [LinkedIn](https://linkedin.com/in/damaris-xiomara-quispe-celi-229743292)
