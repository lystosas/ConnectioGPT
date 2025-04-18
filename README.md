POS-SaaS - Sistema de Punto de Venta en la Nube
Actualizado: 2025-04-18
Versión actual: 1.0.0

Este proyecto es un sistema POS SaaS (Software as a Service) desarrollado en Spring Boot 3.2.5. Ofrece soporte para múltiples negocios y funcionalidades avanzadas como autenticación segura con JWT, manejo de empresas y usuarios, protección de endpoints y documentación con Swagger.

🚀 Tecnologías
Java 17

Spring Boot 3.2.5

Spring Security + JWT

Spring Data JPA

PostgreSQL

Lombok

Swagger UI (springdoc-openapi)

Maven

📦 Dependencias principales
xml
Copiar
Editar

<!-- Swagger UI compatible con Spring Boot 3.2+ -->
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.2.0</version>
</dependency>

<!-- Lombok -->
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <version>1.18.30</version>
  <scope>provided</scope>
</dependency>
⚙️ Configuración de Build
🔧 maven-compiler-plugin
xml
Copiar
Editar
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.11.0</version>
  <configuration>
    <annotationProcessorPaths>
      <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
      </path>
    </annotationProcessorPaths>
  </configuration>
</plugin>
🔧 spring-boot-maven-plugin
xml
Copiar
Editar
<plugin>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-maven-plugin</artifactId>
  <configuration>
    <excludes>
      <exclude>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
      </exclude>
    </excludes>
  </configuration>
</plugin>
🧠 Funcionalidades clave
🔐 Seguridad y autenticación
Autenticación con correo y clave

Generación y validación de JWT

Protección de endpoints con @SecurityRequirement y @PreAuthorize

Swagger soporta login con Bearer token

🏢 Módulo Empresa
Crear, listar, actualizar, eliminar empresas

Validaciones personalizadas (nombre, NIT, dirección, teléfono)

Respuesta con CustomApiResponse<T>

Impide eliminación si tiene usuarios asociados

Todo se guarda en mayúsculas

👤 Módulo Usuario
Crear, listar, actualizar, eliminar usuarios

Cambio de empresa con endpoint PATCH /api/usuarios/{id}/empresa/{empresaId}

Evita actualizar la clave si no se incluye

Valida existencia de empresa antes de guardar

DTOs profesionales para entrada/salida

🔐 Swagger UI + JWT
Hacer login en /auth/login

Copiar token JWT

Usar botón "Authorize" en Swagger:
Pegar el token como Bearer TU_TOKEN

🔧 Variables de entorno
env
Copiar
Editar
JWT_SECRET=clave-super-segura-de-32+caracteres
📄 Documentación
Swagger UI:

bash
Copiar
Editar
http://localhost:8080/swagger-ui/index.html
Documentación JSON:

bash
Copiar
Editar
/v3/api-docs
🧪 Ejecución
bash
Copiar
Editar
mvn clean install
mvn spring-boot:run
🧰 Notas adicionales
Asegúrate de usar una clave JWT de al menos 32 caracteres.

Todos los campos de texto se almacenan en mayúsculas por defecto.

Las respuestas siguen un formato profesional y consistente con estructura { status, message, timestamp, data }.

🧑‍💻 Autor
Business Group D&J SAS
Soluciones tecnológicas a tu alcance
