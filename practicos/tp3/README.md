# Práctico 3 - Enviador de imágenes

El objetivo del práctico es poder familiarizarse con 
el manejo de imágenes y el envío de una imagen en 
forma de texto usando la red.

La fecha de entrega de este práctico es el 3 de mayo 
de 2024.

## Concepto cliente - servidor
El concepto de cliente-servidor es un modelo de 
comunicación entre dos programas en el que uno de
ellos, el cliente, solicita un servicio y el otro,
el servidor, lo provee.

En este caso el servidor recibe una imagen y el 
cliente envía la imagen.

NO DEBE HACER DOS PROGRAMAS, SOLAMENTE UNO.

## Funcionamiento
Cuando muestre su programa tendrá solamente un código
(es uno solo) pero lo ejecutará dos veces. Al ejecutar
una primera vez aparece una ventana vacía. Cuando ejecuta
la segunda vez, aparece una ventana vacía (porque justamente 
es el mismo código que se ha vuelto a ejecutar)

Luego, la sesión será de la siguiente manera
* En una de las ventanas cargará una imagen.
* En la otra indicará que es el servidor y espera una imagen.
* En la ventana que cargó la imagen, 
enviará la imagen al servidor por pedazos.
* En la ventana que espera la imagen, recibirá los pedazos
y se irán mostrando a medida que llegan.

## Requerimientos
El requerimiento es muy sencillo, se trata de hacer un
programa con un JFrame que se muestra al principio
con un panel vacío.

El programa tiene un menú que tiene 4 opciones
1. Cargar una imagen desde una carpeta.
2. Enviar la imagen al servidor.
3. Ser el servidor y esperar la imagen.
4. Salir del programa.

La opción 1 abre un cuadro de diálogo para seleccionar
una imagen y la muestra en el panel.

La opción 2 abre un cuadro de diálogo para ingresar
la dirección IP del servidor y el puerto. Luego envía
la imagen al servidor (ver la siguiente sección).

La opción 3 abre un cuadro de diálogo para ingresar
el puerto en el que espera la imagen. Luego espera
la imagen y la va mostrando a medida que llega.

La opción 4 cierra el programa.

## Envío de la imagen
El envío de la imagen se hace por pedazos de 30x30 pixeles.
Cada pedazo se envía en un thread aparte. Esto significa
que el servidor debe poder atender varias solicitudes al 
mismo tiempo.

Para poder enviar un pedazo el thread que envía debe seguir 
el siguiente protocolo.

En el primer mensaje se envía:
* el tamaño total de la imagen como dos números ancho y alto 
separados por un espacio
* el tamaño del pedazo a enviar como dos números ancho y alto
separados por un espacio
* el punto inicial superior izquierdo del pedazo para saber
dónde colocarlo en la imagen

Luego el servidor lee esta información, y si es el primero
de los paquetes que llega entonces crea la imagen del 
tamaño que se le pide. Para confirmar que se leyó 
correctamente el servidor responderá ok.

En el segundo mensaje el cliente envía los colores de los
pixeles del pedazo. Cada color se envía como un string
de 6 caracteres hexadecimal. Así que normalmente enviará
900 números separados por espacio. 
El servidor responde con ok.

````plaintext
>>> 600 400 30 30 180 120
<<< OK
>>> a4b391 d790ab ... (x 900)
<<< OK
````

## Consejos de implementación
Debe existir un thread en el servidor que atienda cada
cliente que envía una imagen. Este thread debe ser capaz
de cambiar los pixeles de la imagen que se está creando
y de llamar a la notificación del observador panel para
que se muestre el pedazo de la imagen ni bien llegue.

En el cliente, para tener un mejor funcionamiento al enviar
la imagen, formar TODOS los pedazos en objetos tipo thread
y luego, en un for, solamente ir llamando a start para que
comiencen.
