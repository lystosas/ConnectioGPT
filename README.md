# POS-SaaS - Sistema de Punto de Venta en la Nube

Este proyecto es un sistema POS SaaS (Software as a Service) desarrollado en Spring Boot 3.2.5, con soporte para múltiples negocios y funcionalidades como autenticación, seguridad, manejo de inventario, ventas y reportes.

## 🚀 Tecnologías

- Java 17
- Spring Boot 3.2.5
- Spring Security
- Spring Data JPA
- PostgreSQL
- Lombok
- Swagger UI (springdoc-openapi)
- Maven

---

## 📦 Dependencias principales

```xml
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
```

---

## ⚙️ Configuración de Build

### 🔧 `maven-compiler-plugin`

Es importante incluir la versión de Lombok en `annotationProcessorPaths` para evitar errores de compilación:

```xml
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
```

### 🔧 `spring-boot-maven-plugin`

Excluye Lombok para evitar que se empaquete en el JAR final:

```xml
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
```

---

## 📄 Acceso a Swagger UI

Una vez que la aplicación esté corriendo, accede a la documentación Swagger en:

```
http://localhost:8080/swagger-ui/index.html
```

El endpoint por defecto de la documentación de la API es:

```
/v3/api-docs
```

---

## 🧪 Ejecución

Para compilar el proyecto:

```bash
mvn clean install
```

Para ejecutar la aplicación:

```bash
mvn spring-boot:run
```

---

## 🧰 Notas adicionales

- Si llegas a ver errores como `NoSuchMethodError` relacionados con `ControllerAdviceBean`, asegúrate de usar versiones compatibles entre Spring Boot y springdoc-openapi.
- Lombok debe estar correctamente configurado como `provided` y también como `annotationProcessor` en Maven.

---

## 🧑‍💻 Autor

**Business Group D&J SAS**  
_Soluciones tecnológicas a tu alcance_
