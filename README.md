# Taller 1

Aplicación para consultar la información de películas de cine.  La aplicación recibe una frase de búsqueda del título, por ejemplo “Guardians of the galaxy”  y muestra los datos de la película correspondiente.


### Instalando
Descarga o clona el repositorio y ejecuta
```
mvn package
```

luego puedes ejecutar desde tu IDE o en la terminal

```
 java -cp .\target\classes\ edu.eci.arsw.HttpServer
```
Posteriormente ingresando a localhost:35000 se puede ver:

![img_1.png](img/img_1.png)


## Corriendo tests

Para correr las pruebas puedes des tu IDE o usando el comando

```
mvn test
```

![img_2.png](img/img_2.png)



Para las pruebas se hizo uso del nombramiento given_When_Then

```
givenAvalueWhenSaveInCacheThenReturnIt()
```

## Diseño

Teniendo en cuenta la arquitectura propuesta: 

![img.png](img/img.png)

El diseño implementado en la aplicación es:

![img_3.png](img/img_3.png)

Donde se tiene también la clase ClientJavaTesting para realizar los llamados al servidor HttpServer.

Para ejemplificar la búsqueda del nombre de una película se tiene:

![img_4.png](img/img_4.png)


### Ejemplo extensibilidad
Se puede extender las funcionalidades del caché para así poder aumentar el rendimiento de la aplicación. Por ejemplo si se quiere adicionar una funcionalidad de limpiar el caché o de guardar en el caché el título, con respuesta y hora para luego poder actualizar con nueva información si la información ya es obsoleta.


![img_5.png](img/img_5.png)


### Ejemplo para que una función que sea implementada por otro proveedor
Por ejemplo el método getAPIInfo de HttpConnection, podría ser accedido teniendo un punto de acceso en HttpServer, exponiéndolo así como un servicio para los clientes que hagan peticiones a este. 


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Versioning

*  Se hizo uso de git para el manejo de versionamiento.

## Authors

* **Wilson Alirio Delgado Hernández** 

