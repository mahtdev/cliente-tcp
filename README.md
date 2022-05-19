# Objetivo

Aplicacion que permite abrir abrir un calan de comunicacion TCP/IP como cliente y puede mandar la informacion que contiene un archivo

# VM Options

La aplicacion usa las siguientes variables

| VM Option | Descripcion                                                                  | Tipo de dato | valor por defecto |
|:---------:|:-----------------------------------------------------------------------------|:------------:|:-----------------:|
|    ip     | Direccion IP a donde se realizara la conexion TCP                            |    String    |     localhost     |
|   port    | Puerto de conexion                                                           |   Integer    |       9090        |
|   file    | Archivo con los mensajes que se van a enviar una vez establecida la conexion |    String    |       NULL        |
| recursive | Indicador si el archivo se va a leer indefinidamente                         |   Boolean    |       false       |
|   time    | El tiempo en mseg que se va a esperar en mandar cada mensaje del archivo     |   Integer    |       1000        |

# Iniciar componente (ejemplos)

Realizar conexion a google.com

java -Dip=www.google.com -Dport=443 -jar cliente-1.1.0.0.jar

Realizar conexion a puerto 1000 en localhost

java -Dip=localhost -Dport=1000 -jar cliente-1.1.0.0.jar