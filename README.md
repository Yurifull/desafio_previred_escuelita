# DESAFIO ESCUELITA PREVIRED
Este repositorio corresponde al desafío requerido por parte de PREVIRED, en el cual se crearon servicios REST para una empresa llamada PREVIRED tuviera registro de la información de sus clientes en empresas y sus trabajadores.

## Dependecias
- spring-boot-devtools
- spring-boot-starter-data-jpa
- spring-boot-starter-web
- spring-boot-starter-test
- postgresql
- lombok

## Programas utilizados
Para ejecutar el programa correctamente se deben instalar los siguientes programas:
- IDE Visual Studio Code
- Extensiones Visual Studio Code:
	- Extension Pack for Java
	- Spring Initializr Java Support
	- Spring Boot Tools
	- Spring Boot Dashboard
- PostgreSQL
- Postman

## Instalación y ejecución
### Crear y poblar la base de datos
Click derecho en la seccion Tablas de una base de datos en PostgreSQL y seleccionar la opción Query Tool y pegar el codigo SQL de los script XXXXX

### Acceso a la base de datos
En el archivo ``application.properties`` se debe ingresar la contraseña, que en este caso es ``password``

### Configuración Spring Boot
Dentro del archivo ``pom.xml`` se debe cambiar la version de Spring Boot a la version ``2.7.5`` para un correcto funcionamiento del programa. En caso de que tenga esa version se puede omitir el paso.

### Como ejecutar
Presionar el icono de la extension de Spring Boot en la barra lateral y posteriormente en la sección de ``APPS`` donde dice desafio_escuelita, debe presionarse el boton que dice ``Run`` y se ejecutara el programa.

### Comprobar funcionamiento
Dentro de ``Postman`` se puede obtener, guardar, eliminar y actualizar los datos de la base de datos. Para esto es necesario agregar en la barra de busqueda ``localhost:8080`` y posteriormente las URLs creadas en el controlador.

Para la tabla de empresa: ``/company``
Para la tabla de empleados: ``/employee``

Guardar datos: ``/save``
Actualizar datos: ``/update``
Eliminar datos: ``/delete/{id}``
Obtener datos: ``/findAll``



