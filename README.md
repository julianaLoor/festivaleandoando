# FestivaleandoAndo

FestivaleandoAndo es una aplicación web dedicada exclusivamente a la **venta de entradas para festivales musicales**. Su objetivo es ofrecer una experiencia personalizada basada en la ubicación del usuario, filtrando eventos cercanos por estilos musicales, fechas y localización.

---

## Descripción

Después del auge de la música en vivo tras la pandemia, esta plataforma busca resolver una necesidad real: **unificar la oferta de entradas de festivales en una sola web**, sin redirecciones y con enfoque en la experiencia de usuario.

Los proveedores ceden el 30% de sus entradas para ser vendidas en esta plataforma, y el sistema gestiona el stock, pagos simulados (modo trial), carrito de compras y localización mediante código postal.

---

## Características principales

- Registro y autenticación de usuarios (cliente, proveedor, administrador)
- Filtros por localización, categoría musical, artistas y fechas
- Carrito de compras con límite de entradas por usuario
- Simulación de compra con sistema de pago trial
- Gestión de stock en tiempo real
- Panel de administrador y panel de proveedor
- Localización con Leaflet.js y código postal
- Responsive design con HTML, CSS y JS

---

## Tecnologías utilizadas

| Categoría         | Herramientas / Lenguajes                        |
|------------------|--------------------------------------------------|
| **Frontend**     | HTML5, CSS3, JavaScript, jQuery, Leaflet.js      |
| **Backend**      | Java con Spring Boot *(planificado)*             |
| **Base de datos**| MySQL, modelo E-R y relacional optimizado        |
| **Servidor**     | Apache (XAMPP en local)                          |
| **Diseño**       | Figma                                            |

---

## Estructura del proyecto

```
festivaleandoando/
├── backend/                # Proyecto Spring Boot (Java + Maven) 
│   └── festivaleando-backend/
│       ├── src/
│       ├── pom.xml
│       └── FestivaleandoandoApplication.java
│
├── frontend/               # Sitio web (HTML, CSS, JS)
│   ├── index.html
│   ├── css/
│   ├── js/
│   └── assets/
│
├── base_datos/             # Script SQL con la estructura y datos de la BD
│   └── festivaleandoando.sql
│
├── docs/                   # Documentación técnica y de diseño
│   ├── Anteproyecto a entregar.pdf
│   ├── Requisitos funcionales y no funcionales.pdf
│   ├── Diagramas UML - Casos de uso.pdf
│   ├── Modelos ER y relacionales (JPG/Draw.io)
│   └── Programación_Proyecto.pdf
│
├── README.md               # Instrucciones generales del proyecto
└── LICENSE                 # Licencia del proyecto (MIT)
```

---

## ⚙️ Cómo ejecutar el proyecto en local (con XAMPP)

1. Clona o copia el proyecto dentro de `htdocs`:
   ```bash
   C:/xampp/htdocs/festivaleandoando
   ```

2. Abre XAMPP y asegúrate de que **Apache** y **MySQL** estén activos.

3. Crea la base de datos desde phpMyAdmin e importa el archivo:
   ```
   sql/festivaleandoando.sql
   ```

4. Abre tu navegador y accede a:
   ```
   http://localhost/festivaleandoando
   ```

---

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.  
© 2025 Juliana Lisseth Loor Montoya – Todos los derechos reservados.

---

## Autora

**Juliana Lisseth Loor Montoya**  
jllm74@educa.madrid.org 
Proyecto del módulo *Desarrollo de Aplicaciones Web*
