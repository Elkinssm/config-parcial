# Parcial Especialización en Back end 1

# Elkin Silva Manrique

## Resumen

En este repositorio se puede encontrar el código a la consigna del parcial final de microservicios con spring boot, donde se
encuentra el
config server, gateway, eureka server, y los microservicios de movie y catalog los cuales usan feign client para
comunicarse entre si. Ademas se agrega un nuevo microservico de series el cual hara uso de las colas de RabbitMQ 
para lograr una comunicacion asincronica. El ecosistema de microservicios cuenta con el uso de zipkin
para poder realizar trazabilidad ademas se vale de un circuit braker para tener siempre disponibles desde catalog las
peliculas y las series.
---
Cada microservicio hace uso de una base de datos diferente

> catalog-service usa una base de datos no relacional Mongodb embebida 
> > movie-service usa una base de datos relacional de MySql
> > >serie-service usa una base de datos no relacional Mongodb embebida
---
## Uso

1. Inicia el contenedor Zipkin ejecutando el siguiente comando de Docker:

>docker run -d --name zipkin -p 9411:9411 openzipkin/zipkin:latest

2. Inicia el contenedor RabbitMQ ejecutando el siguiente comando de Docker:

>docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
 
### Nota: 
Para hacer pruebas de RabbitMQ, es posible que debas apagar el microservicio Catalog, ya que este último crea y consume colas RabbitMQ 
y puede interferir con las pruebas. Ademas es necesario crear unas series inicialmente.

3. Asegúrate de que el Config Server, Eureka Server y API Gateway estén funcionando 
correctamente en la consola o en el IDE, en ese orden.

4. Asegúrate de que los microservicios de Series y Movie estén 
funcionando correctamente en la consola o en el IDE.

5. Inicia el microservicio Catalog y asegúrate de que esté funcionando
correctamente en la consola o en el IDE.

6. Utiliza la colección de Postman proporcionada en este repositorio para realizar pruebas de 
los microservicios mediante los endpoints proporcionados.

Siguiendo este orden, garantizarás que todas las dependencias estén disponibles antes de que se inicien los servicios dependientes,
lo que garantizará un funcionamiento correcto y preciso en la gestión de las solicitudes y en el seguimiento de las mismas en Zipkin.
Además, al apagar el microservicio Catalog, podrás realizar pruebas específicas de RabbitMQ con mayor precisión.

[Colección de Postman](./Parcial-microservicios-elkin-silva-part-2.postman_collection.json)

[![Screenshot-2023-04-08-185132.jpg](https://i.postimg.cc/sXSjswdB/Screenshot-2023-04-08-185132.jpg)](https://postimg.cc/qhJVGc70)
---

## Trazabilidad con Zipkin
### Trazabilidad sin Catalogo encendido
![Trazabilidad1](https://user-images.githubusercontent.com/52393397/230747820-f974cb7c-6149-467f-8978-abc1bea1b13b.gif)
### Trazabilidad con Catalogo encendido
![Trazabilidad2](https://user-images.githubusercontent.com/52393397/230748373-f7540867-1b67-4ab3-b3be-9be0d1eb0e62.gif)

---

## Colas de mensajes con RabbitMQ
![RabbitMq](https://user-images.githubusercontent.com/52393397/230748183-fd64529e-2b64-46d4-8b01-9d317947a0b7.gif)

---

## Resiliencia - Resilence4J
En este caso elegi el microservicio de catalog, pues imagine un escenario donde los usuarios van a consultar las peliculas y series
disponibles atraves del catalogo por lo que estas deben estar siempre disponibles para lo que cubir dos metodos uno que llame las peliculas por genero 
y otro que haga lo mismo con series. Si en algun momento el servicio de series o peliculas este caido el sistema sea capaz de responderle
con los datos almacenados en al base de datos de catalog.
![Screenshot 2023-04-09 143034](https://user-images.githubusercontent.com/52393397/230792830-ea5b5208-c8da-43ca-b97b-68894cf21aad.jpeg)



---

## Contacto

Si tienes alguna pregunta o comentario sobre este proyecto, no dudes en contactarme:

[![Correo electronico](https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_dark_1x_r5.png)](mailto:elkinsilvamanrique@gmail.com)
[<img src="https://cdn-icons-png.flaticon.com/512/174/174857.png" width="50" height="50">](https://www.linkedin.com/in/elkinssm/)

