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
En el gif simula cuando el microservicio de movie o serie este caido igual el sistema hace uso de la base de datos de catalog para no dejar de responder.
![Resilience](https://user-images.githubusercontent.com/52393397/230793801-8b58d27f-4d07-41e5-bf18-8898cae6db9a.gif)

### A continuacion relaciono el fragmento de codigo donde se pueden ver los metodos.

https://github.com/Elkinssm/parcial-backend-elkin-silva/blob/11de6303cb9558fa7c5c2a600203f6ebb25bbae8/catalog-service/src/main/java/com/dh/catalogservice/api/controller/CatalogController.java#L84-L157

## Metodos Offline 
Para esta parte de la consigna tengo en el controller de catalog metodos que se encargan de consultar la base de datos local de mongo que tiene catalogo
de manera offline valiendo me de MongoRepository, es capaz de mostrar todas las peliculas y series, y el otro muestra todas las peliculas y serie por genero

https://github.com/Elkinssm/parcial-backend-elkin-silva/blob/b4af3b4a571d1056684afbc5a9c603669f3ab119/catalog-service/src/main/java/com/dh/catalogservice/api/controller/CatalogController.java#L51-L72


Método: getCatalogOffline

Descripción: Este método devuelve todo el catálogo de películas y series que se encuentran en la base de datos local.

URL: /local/all

Método HTTP: GET

Respuesta exitosa:

```
{
    "genre": "local",
    "movies": [
        {
            "id": "6434c7878a4f684ec08ab721",
            "name": "Black Adam",
            "genre": "Accion",
            "urlStream": "testBd"
        },
        {
            "id": "6434c7898a4f684ec08ab722",
            "name": "Blade Runner 2049",
            "genre": "Sci-Fi",
            "urlStream": "testBd"
        }
    ],
    "series": [
        {
            "id": "6434c74f8a4f684ec08ab71f",
            "name": "Breaking Bad",
            "genre": "Drama",
            "seasons": [
                {
                    "seasonNumber": 1,
                    "chapters": [
                        {
                            "name": "Pilot",
                            "number": 1,
                            "urlStream": "https://www.youtube.com/watch?v=HhesaQXLuRY"
                        },
                        {
                            "name": "Cat's in the Bag...",
                            "number": 2,
                            "urlStream": "https://www.youtube.com/watch?v=EzjHVNOrtkg"
                        }
                    ]
                },
                {
                    "seasonNumber": 2,
                    "chapters": [
                        {
                            "name": "Seven Thirty-Seven",
                            "number": 1,
                            "urlStream": "https://www.youtube.com/watch?v=ZG_K5WGyN9o"
                        },
                        {
                            "name": "Grilled",
                            "number": 2,
                            "urlStream": "https://www.youtube.com/watch?v=vCgB0hA1NxA"
                        }
                    ]
                }
            ]
        },
        {
            "id": "6434c74f8a4f684ec08ab720",
            "name": "Stranger Things",
            "genre": "Sci-Fi",
            "seasons": [
                {
                    "seasonNumber": 1,
                    "chapters": [
                        {
                            "name": "Chapter One: The Vanishing of Will Byers",
                            "number": 1,
                            "urlStream": "https://www.netflix.com/watch/80057281"
                        },
                        {
                            "name": "Chapter Two: The Weirdo on Maple Street",
                            "number": 2,
                            "urlStream": "https://www.netflix.com/watch/80057282"
                        }
                    ]
                },
                {
                    "seasonNumber": 2,
                    "chapters": [
                        {
                            "name": "Chapter One: MADMAX",
                            "number": 1,
                            "urlStream": "https://www.netflix.com/watch/80133309"
                        },
                        {
                            "name": "Chapter Two: Trick or Treat, Freak",
                            "number": 2,
                            "urlStream": "https://www.netflix.com/watch/80133310"
                        }
                    ]
                }
            ]
        }
    ]
}
```

Método: getCatalogByGenreOffline

Descripción: Este método devuelve todas las películas y series que se encuentran en la base de datos local, filtradas por género.

URL: /local/all/{genre}

Método HTTP: GET

Parámetros:
| Parámetro | Tipo | Descripción |
| --------- | ---- | ----------- |
| genre     | string | El género por el que filtrar |



```
{
    "genre": "Sci-Fi",
    "movies": [
        {
            "id": "6434e1ac7c8ff65b7dbfdfde",
            "name": "Blade Runner 2049",
            "genre": "Sci-Fi",
            "urlStream": "testBd"
        }
    ],
    "series": [
        {
            "id": "6434e1a77c8ff65b7dbfdfdc",
            "name": "Stranger Things",
            "genre": "Sci-Fi",
            "seasons": [
                {
                    "seasonNumber": 1,
                    "chapters": [
                        {
                            "name": "Chapter One: The Vanishing of Will Byers",
                            "number": 1,
                            "urlStream": "https://www.netflix.com/watch/80057281"
                        },
                        {
                            "name": "Chapter Two: The Weirdo on Maple Street",
                            "number": 2,
                            "urlStream": "https://www.netflix.com/watch/80057282"
                        }
                    ]
                },
                {
                    "seasonNumber": 2,
                    "chapters": [
                        {
                            "name": "Chapter One: MADMAX",
                            "number": 1,
                            "urlStream": "https://www.netflix.com/watch/80133309"
                        },
                        {
                            "name": "Chapter Two: Trick or Treat, Freak",
                            "number": 2,
                            "urlStream": "https://www.netflix.com/watch/80133310"
                        }
                    ]
                }
            ]
        }
    ]
}
```
---

## Contacto

Si tienes alguna pregunta o comentario sobre este proyecto, no dudes en contactarme:

[![Correo electronico](https://ssl.gstatic.com/ui/v1/icons/mail/rfr/logo_gmail_lockup_dark_1x_r5.png)](mailto:elkinsilvamanrique@gmail.com)
[<img src="https://cdn-icons-png.flaticon.com/512/174/174857.png" width="50" height="50">](https://www.linkedin.com/in/elkinssm/)

