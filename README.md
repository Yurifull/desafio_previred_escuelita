# API de gestion de empresas y trabajadores

Esta API RESTful proporciona un conjunto de servicios para gestionar trabajadores y empresas. Permite realizar operaciones de registro, obtención, actualización y eliminación de trabajadores y empresas, proporcionando una interfaz sencilla y segura para interactuar con los datos.

## Características Principales

- Registro de Trabajadores: Permite crear nuevos trabajadores y asociarlos a empresas existentes. Se realizan validaciones de datos y verificaciones para evitar duplicados y mantener la integridad de la información.

- Gestión de Empresas: Proporciona funcionalidades para crear, obtener, actualizar y eliminar empresas. Es posible realizar operaciones de búsqueda por ID y obtener la lista completa de empresas registradas.

- Consulta de Trabajadores: Permite obtener la lista completa de trabajadores registrados y buscar trabajadores individuales por ID.

- Actualización y Eliminación: Ofrece la posibilidad de actualizar y eliminar tanto trabajadores como empresas mediante sus respectivos ID.

## Tecnologías Utilizadas

La aplicación ha sido desarrollada utilizando las siguientes tecnologías:

- Spring Boot: Framework de desarrollo de aplicaciones Java para la creación de servicios RESTful.
- Spring Data JPA: Biblioteca de persistencia que facilita el acceso a bases de datos relacionales.
- Swagger: Herramienta para la documentación y prueba de APIs RESTful.
- Validaciones y Manejo de Errores: Se implementan validaciones de datos y se gestionan los posibles errores que puedan ocurrir durante las operaciones.

## Instalación

Sigue estos pasos para instalar y ejecutar la aplicación en tu entorno local:

1. Clona este repositorio en tu máquina local o descárgalo como archivo ZIP.

2. Asegúrate de tener instalado Java Development Kit (JDK) en tu sistema. Puedes verificarlo ejecutando el siguiente comando en tu terminal:

```java -version```

Si no tienes instalado Java, puedes descargarlo e instalarlo desde el sitio web oficial de Oracle.

3. Abre tu entorno de desarrollo integrado (IDE) preferido. Recomendamos utilizar Spring Tool Suite (STS) para una integración sin problemas.

4. Importa el proyecto en tu IDE como un proyecto de Maven existente.

5. Espera a que las dependencias del proyecto se descarguen e instalen automáticamente. Esto puede tomar algún tiempo dependiendo de tu conexión a Internet.

6. Una vez que todas las dependencias se hayan descargado correctamente, busca la clase principal del proyecto llamada `Application` y ejecútala como una aplicación Java.

7. La aplicación se ejecutará en un servidor integrado de Spring Boot y se levantará en la siguiente URL por defecto: `http://localhost:8080`. Puedes acceder a ella utilizando tu navegador web.

8. ¡Y eso es todo! Ahora deberías tener la aplicación API RESTful ejecutándose en tu entorno local. Puedes probar los diferentes endpoints utilizando herramientas como Postman o cURL.

Recuerda que puedes consultar la documentación de la API generada automáticamente con Swagger accediendo a la siguiente URL: `http://localhost:8080/swagger-ui/index.html`. Allí encontrarás información detallada sobre cada uno de los endpoints disponibles y cómo interactuar con ellos.

## Uso
Una vez que la aplicación esté en funcionamiento, podrás interactuar con los endpoints proporcionados mediante las peticiones HTTP correspondientes. Consulta la documentación de la API para obtener información detallada sobre cada uno de los endpoints y los datos que se esperan.

## License

[MIT](https://choosealicense.com/licenses/mit/)