# Control de Stock

Control de Stock es un sistema de gestión de inventario desarrollado en Java, utilizando el framework de Spring Boot. El objetivo de este proyecto es proporcionar una herramienta para ayudar a las empresas a realizar un seguimiento de sus inventarios y optimizar su gestión de stock.

## Características

El sistema incluye las siguientes características:

* Registro y seguimiento de productos: los usuarios pueden agregar nuevos productos y realizar un seguimiento de su cantidad en stock, precio y otra información relevante.

* Registro y seguimiento de proveedores: los usuarios pueden agregar información de proveedores y llevar un seguimiento de los pedidos pendientes.

* Pedidos y recepción de mercancía: los usuarios pueden generar y enviar órdenes de compra a los proveedores y llevar un seguimiento de las entregas.

* Generación de informes: el sistema permite generar informes de inventario, listas de productos, pedidos pendientes, entre otros.

## Requisitos del sistema

* Java 8 o superior
* Maven 3.2 o superior
* MySQL 5.7 o superior

## Instalación

Para instalar y ejecutar el sistema, sigue los siguientes pasos:

1. Clona el repositorio del proyecto desde GitHub:
```
git clone https://github.com/marcelogonve/control-de-stock.git

```
2. Abre el proyecto en tu IDE de Java favorito (se recomienda utilizar Eclipse o IntelliJ IDEA).

3. Crea una base de datos MySQL y actualiza las credenciales de la base de datos en el archivo application.properties.

4. Ejecuta la aplicación utilizando el comando mvn spring-boot:run.

5. Puedes abrir tu navegador y acceder a la dirección http://localhost:8080 o, en su defecto, ejecutar el fichero ControlDeStockMain.java, para que se te abra una ventana en tu SO.

## Uso

Una vez que la aplicación esté en funcionamiento, los usuarios pueden navegar a través de la interfaz de usuario y utilizar las diferentes funciones para gestionar el inventario y los pedidos de la empresa.

## Créditos

Este proyecto fue creado por Marcelo González y corresponde al curso ONE de Oracle Next Education.

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.
