Configuración:<br>
Java 17<br>

1-Clonar repositorio<br>

2-Crear servidor postgreSQL, seguidamente crear una base de datos vacía <br>
![Crear bd](./assets/images/create.png)<br>
3-Usar QueryTool para insertar el script que generará la base de datos<br>
![Seleccionar querytool](./assets/images/querytool.png)<br>
4-Insertar el código de script.sql (Ubicado en la carpeta Script), click en ejecutar<br>
![Insertar script](./assets/images/script.png)<br>
5-Editar el archivo "application.properties" dentro de la ruta src/main/resources/<br>
  Ajustar los parámetros según las propiedades de la base de datos recién creada<br>
![Propiedades bd](./assets/images/properties.png)<br>
6-Ejecutar el proyecto<br>
![Swagger](./assets/images/run.png)<br>
7-Ingresar a la url: http://localhost:8080/swagger-ui/index.html#/ (Cambiar el puerto en caso de que sea necesario, por defecto 8080)<br>
![Swagger](./assets/images/swagger.png)<br>
<br>
<br>
<br>
NOTAS:<br>
1-Los siguientes campos son asignados automáticamente:<br>
	- Fecha de registro (Empresa) <br>
	- Id (Empresa)<br>
	- Id (Empleado)<br>
	 Estos pueden ser dejados tal como estan.<br>
![Swagger2](./assets/images/swagger2.png)<br>

2-Al actualizar un registro, es necesario que el ID enviado en la solicitud PUT coincida con el ID del objeto JSON<br>

En el ejemplo se actualiza mediante la solicitud PUT (1) al empleado con el ID = 1, asignamos una empresa al empleado (flecha azul) <br>
![Swagger4](./assets/images/swagger4.png)<br>
Solo con escribir el ID de la empresa, el resto de campos se actualizan con los datos correspondientes. <br>
![Swagger6](./assets/images/swagger6.png)<br>

