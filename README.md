stockphone
==========

1. Introducción

   Este manual le permitirá aprender a utilizar todas las funcionalidades que nos ofrece StockPhone, así como instalar, desinstalar, conocer los requerimientos mínimos para poder utilizar la aplicación y hacer un uso efectivo de esta herramienta.

1.1. ¿Qué es StockPhone?

   StockPhone es un aplicativo móvil desarrollado con la tecnología Android, la versión del mismo es 2.1, con lo cual podrá utilizara este aplicativo en dispositivos que soporten Android, con una versión igual o superior a 2.1.

1.2. ¿Qué se puede hacer con StockPhone?

   StockPhone es un aplicativo móvil para realizar el control de stock de la organización. El control de stock que realiza este aplicativo consiste en seleccionar los artículos que desea reponer y agregarlos con sus respectivas cantidades a una lista de pedido, una vez finalizada la carga de artículos a la lista de pedidos, lo que resta es confirmar la lista de pedido, para así generar el archivo CSV (separado por coma), este archivo contendrá los artículos a reponer con sus respectivas cantidades. Aclaración: el archivo CSV con los artículos a reponer, debe ser transferido al sistema que cuenta la organización.

1.3. ¿Qué funcionalidades ofrece?

•	Podrá realizar la búsqueda de artículos a través de un filtro, así como ordenarlo por medio del criterio que desee.

•	Podrá ver la información de los artículos.

•	Podrá generar una lista de pedido, la misma estará conformada con aquellos artículos que desee reponer.

•	Podrá agregar, eliminar artículos que se encuentren en la lista de pedido, así como editar la cantidad a reponer de cada artículo.

•	Podrá generar un archivo CSV (separado por coma) con los artículos a reponer.

1.4. Beneficios de utilizar StockPhone

•	Reducción de tiempo de carga de datos, debido a que el archivo CSV generado por el aplicativo móvil con los artículos a reponer, será leído por el sistema para luego generar el pedido, reduciéndose así el tiempo que demandaba cargar los datos de la planilla al sistema de manera manual.

•	Carga y persistencia de datos consistente, ya que al realizar la carga de artículos con el aplicativo móvil no habrá redundancia de datos, así como reducción de errores en la 

carga, es decir si comparamos con la carga manual en la planilla, la misma está sujeta a errores como ser, anotar mal el código de artículo o descripción.

•	Agilización del pedido al proveedor, está ventaja se puede apreciar al hacer una comparación con el control de stock manual. En el control de stock  manual generalmente no se suele realizar la carga de la planilla con los artículos a reponer al sistema de manera inmediata, debido al tiempo que esto demanda, con lo cual al postergar dicha carga, se corre el riesgo de no cargar la planilla por descuido o extravió de la misma a causa de traspapelarse. En cambio al realizar el control de stock con el aplicativo móvil una vez finalizado el mismo, solamente resta hacer que el sistema lea el archivo CSV generado por el aplicativo móvil con los artículos a reponer, para así realizar el pedido a los correspondientes proveedores.

1.5. Requerimientos mínimos para poder utilizar StockPhone

•	Tener un Smartphone o Tablet que utilice la tecnología Android.

•	La versión de Android que utiliza es 2.1, por lo tanto podrá ejecutar la aplicación si la versión de Android que posee su dispositivo es igual o superior a 2.1.

•	Inicialmente StockPhone no posee base de datos, para poder hacer uso de sus funcionalidades debe generar la base de datos, para ello debe crear un archivo CSV (separado por coma) con los datos de los artículos de la Organización, a partir de la Base de Datos que posea el Sistema que utiliza la organización. Este requerimiento se explica con detalle en el ítem 1.6.

•	Debe contar con una memoria externa SD, en la misma se almacenará la base de datos de la aplicación.

1.6. ¿Cómo generar la Base de Datos que utiliza StockPhone?

   Para poder utilizar StockPhone debe crear un archivo CSV con los datos de los artículos de la Organización, a partir de la Base de Datos que posea el Sistema que utiliza la Organización 

   Los datos necesarios son: el código de artículo, descripción, código de artículo del proveedor y nombre del proveedor. Aclaración: los datos requeridos tiene relación con los campos: NUMERO, DESCRIPCION, PROVEEDOR Y NRO DEL PROVEEDOR que pertenecen a la tabla de artículo de la base de datos del Sistema que utiliza la organización. El formato del mismo se puede visualizar en la Fig. 1.

   Una vez generado el archivo CSV con los datos de los artículos de la organización, debe cargar el mismo al dispositivo. Los detalles de cómo cargar el archivo CSV al dispositivo se explica en el punto 2.
   Aclaración: el archivo CSV creado a partir de la base de datos del sistema que utiliza la organización, debe llamarse stockphone.csv. Por favor no olvide este detalle, ya que si lo nombra de otra forma el aplicativo no reconocerá la base de datos.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/formato_arch_csv.png)

Fig. 1: Formato del Archivo CSV, creado a partir de la base de datos del sistema que utiliza la organización.

2. Instalación

   Para poder instalar StockPhone en su dispositivo móvil deberá seguir los siguientes pasos, estos pasos son necesarios debido a que StockPhone no es una aplicación que provenga del Market de Android.

   Los pasos son: 

1.	Habilitar la opción de poder instalar aplicaciones que no provenga del Market de Android, para ello debemos ir a Ajustes -> Aplicaciones -> Orígenes desconocidos y activar la opción de orígenes desconocidos.

2.	Una vez habilitada la opción de orígenes desconocidos podemos propiamente instalar StockPhone para ello tenemos que cargar el apk de StockPhone en la tarjeta de memoria SD, otra opción es pasar el apk de la PC al dispositivo móvil por medio del cable USB, teniendo otras opciones como por ej. enviar el apk por medio de Bluetooth. Aclaración en caso de elegir una opción distinta a la tarjeta de memoria SD, podrá almacenar el apk en cualquier directorio, generalmente se suele almacenar los apk en el directorio de descargas. Luego de haber hecho esto lo que resta es buscar el apk en nuestro dispositivo móvil e instalarlo.

3.	Al finalizar la instalación debemos ejecutar la aplicación. La primera vez que ejecutemos la aplicación veremos una ventana de advertencia (Fig. 2), la cual nos dirá que debemos cargar la base de datos a la aplicación. Aclaración debemos tener montada la tarjeta de memoria externa SD al dispositivo, ya que al ejecutar la primera vez la aplicación crea el directorio donde se deberá almacenar la base de datos, dicho directorio es creado en la tarjeta de memoria externa SD.

4.	Este paso consiste en carga la base de datos que utiliza StockPhone en la tarjeta de memoria externa SD, para realizar este paso tenemos varias opciones:

a)	Cargar la base de datos directamente en la tarjeta de memoria externa SD, con lo cual debemos insertar la tarjeta memoria SD a la PC (Aclaración esto es posible solo si su PC puede leer memorias SD), luego cargar la base de datos de StockPhone en el siguiente directorio, sdcard -> stockphone -> database (Tanto sdcard, stockphone y database son directorios). En el directorio database debemos almacenar la base de datos. Una vez finalizado esto insertamos la tarjeta de SD en 

el dispositivo móvil. Aclaración los directorios sdcard, stockphone y database son directorios que se encuentran en la tarjeta de memoria externa SD del dispositivo móvil.

b)	Otra opción es cargar la base de datos a la memoria SD del dispositivo por medio del cable USB directamente, para lo cual debemos conectar el cable USB del dispositivo móvil a la PC, habilitar el modo Almacenamiento USB, para luego transferir el archivo de la Base de Datos a la tarjeta de memoria externa SD del dispositivo. 

El directorio donde se debe almacenar el archivo de la Base de Datos puede variar en los distintos dispositivos que utilicen Android, pero en general deberá navegar por los directorios: sdcard -> stockphone -> database, para poder almacenar el archivo de la base de datos en el directorio database. Aclaración los directorios sdcard, stockphone y database son directorios que se encuentran en la tarjeta de memoria externa SD del dispositivo móvil.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_inicial.png)

Fig. 2: Ventana Inicial. Advertencia que visualizará 
en la primer ejecución de StockPhone.

   Una vez finalizado estos pasos, ya está en condiciones de poder ejecutar StockPhone para así poder realizar el control de stock de la organización.

3. Desinstalación

   Para desinstalar StockPhone es sumamente sencillo, lo que debe hacer es dirigirse a los ajustes del dispositivo móvil y seguir esta secuencia de pasos: entrar en aplicaciones, una vez dentro pulsar en administrar aplicaciones, haciendo esto nos saldrá una lista de aplicaciones. De dicha lista debe seleccionar StockPhone y dentro de la información de la aplicación tiene que pulsar desinstalar para así finalizar con la desinstalación de StockPhone.

4. Realizar el control de stock

   Para poder explicar con la mayor claridad posible el control de stock efectuado con StockPhone se explicara las secciones que posee el mismo con la ayuda de imágenes para enriquecer así la forma de transmitir las instrucciones para hacer un buen uso de StockPhone. 

Las secciones son:

4.1	Sección Login: en esta sección deberá ingresar su usuario y clave, los cuales son los mismos que la cuenta que posee en el sistema de la organización (Fig. 3).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_login.png)

Fig. 3: Ventana de Login.

4.2	Sección Menú Principal: en esta sección visualizara un menú con las siguientes opciones (Fig. 4): 

•	Listar artículos (sección c)): este ítem del menú al presionarlo visualizara la lista de artículos, para poder realizar la búsqueda de los mismo y poder generar la lista de pedido, para luego generar el archivo CSV con los artículos a reponer.

•	Acerca de StockPhone: si selecciona esta opción obtendrá información acerca de la aplicación, la versión de Android que cuenta, para que sirve, etc.

•	Salir: esta opción nos hará salir del sistema, pero previamente nos advertirá con una ventana emergente si estamos seguros de salir del sistema.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_menu_principal.png)

Fig. 4: Ventana Menú Principal.
    
4.3	Sección Lista de Artículos: en esta sección veremos la lista de artículos que cuenta la organización, los datos de cada artículo son: código de artículo, descripción y código de artículo del proveedor. 

¿Qué se puede hacer?
   
    Aquí podrá buscar el artículo que desea reponer seleccionarlo y agregarlo a la lista de pedido.

Explicación de los componentes de esta sección:

   En la parte superior de la ventana tenemos dos etiquetas, una de ellas A-Z: nos dice el tipo de ordenamiento al filtrar la lista y la otra etiqueta es Filtro: nos indica el tipo de filtro. 

   La caja de texto nos sirve para introducir la palabra clave para buscar algún artículo dependiendo el tipo de filtrado.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_listar_articulo.png)

Acciones que se pueden realizar

•	Seleccionar artículo, para ello debe mantener presionado sobre el artículo deseado, con lo cual se abrirá una ventana que tendrá las opciones de Agregar a la Lista de Pedido y Ver Información (Fig. 6).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_seleccion_articulo.png)

Fig. 6: Ventana emergente al seleccionar algún artículo de lista.

•	Presionar el botón menú: visualizara las siguientes opciones: tipo de ordenamiento, tipo de filtrado, ver lista de pedido y confirmar lista de pedido. En la Fig.7 se explica cada opción, de las cuales Ver Lista de Pedido es la sección d) y Confirmar Lista de Pedido la sección e).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/menu_ventana_lista_pedido.png)

Fig. 7: Menú de la ventana de lista de artículos.

4.4	Sección Lista de Pedido: en esta sección visualizara la lista de pedido conformada con los artículos que fue agregando a la misma. Así como la sección de Lista de Artículos podrá buscar el artículo a través de una palabra clave que dependiendo del tipo de ordenamiento y filtrado obtendrá un determinado resultado.

¿Qué se puede hacer?
   
 Aquí podrá buscar el artículo que desea reponer seleccionarlo y agregarlo a la lista de pedido al igual que la sección de lista de artículos. También podrá editar la cantidad de artículos o su eliminación.
 
 Explicación de los componentes de esta sección:

   En la parte superior de la ventana tenemos dos etiquetas, una de ellas A-Z: nos dice el tipo de ordenamiento al filtrar la lista y la otra etiqueta es Filtro: nos indica el tipo de filtro. 

   La caja de texto nos sirve para introducir la palabra clave para buscar algún artículo dependiendo el tipo de filtrado.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_lista_pedido.png)

Fig. 8: Ventana Lista de Pedido.

Acciones que se pueden realizar

•	Seleccionar artículo, para ello debe mantener presionado sobre el artículo deseado, con lo cual se abrirá una ventana que tendrá las opciones de Editar Cantidad, Ver Información y Eliminar Art. de Lista (Fig. 6).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_list_articulo_menu.png)

Fig. 9: Ventana emergente al seleccionar 
algún artículo de Lista de Pedido.


•	Presionar el botón menú: visualizara las siguientes opciones: Tipo de Ordenamiento, Tipo de Filtrado, Volver Lista Pedido y Confirmar Lista de Pedido. En la Fig.10 se explica cada opción, de las cuales y Confirmar Lista de Pedido la sección e).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/menu_ventana_lista_pedido.png)

Fig. 10: Menú de la ventana Lista de Pedido.

4.5	Sección Confirmar Lista de Pedido: en esta sección visualizará el resumen del pedido, es decir la lista de pedido con sus artículos, donde verá el cód. artículo, cód. art. Proveedor, descripción y cantidad de cada artículo, así como también datos pertinentes al usuario que genero la lista de pedido, fecha y hora.

¿Qué se puede hacer?
   
    Aquí podrá verificar si la lista de pedido está cargada correctamente antes de generar el archivo CSV para así poder realizar el pedido a los correspondientes proveedores.

Explicación de los componentes de esta sección:

   En la parte superior de la ventana tenemos el nombre del usuario, así como la fecha y hora de generación del archivo CSV, Debajo de este encabezado tenemos la lista de pedido con los artículos a reponer, con sus respectivos datos (cód. artículo, cód. art. Proveedor, descripción y cantidad de cada artículo).

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/ventana_confirmar_pedido.png)

Fig. 11: Ventana Confirmar Pedido.

Acciones que se pueden realizar

•	Presionar el botón menú: visualizara las siguientes opciones: Volver Lista de Artículos, Volver Lista de Pedido, Generar Archivo CSV y Cancelar Pedido. 

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/menu_ventana_confirmar_pedido.png)

Fig. 12: Menú de la Ventana Confirmar Pedido. 

   Si presiona confirmar el pedido se abrirá una nueva ventana solicitando la confirmación del mismo, en caso afirmativo se generara el archivo CSV, el mismo se creara en el directorio de la aplicación. Aclaración: el directorio de la aplicación se encuentra en la memoria externa sd por ej.: sd -> stockphone puede variar el nombre del primer directorio dad las versiones de Android, pero siempre sigue este esquema de directorios.

5. Extraer el archivo CSV generado

   Para poder extraer el archivo CSV generado con el pedido tenemos que conectar el dispositivo móvil a la PC por medio del cable USB y buscar el archivo CSV generado con el pedido en el siguiente directorio: SD -> stockphone, SD como stockphone son directorios creados en la memoria externa sd, el archivo CSV se encuentra en el directorio stockphone. El nombre del archivo será “pedido” seguido de un guión bajo la fecha, luego nuevamente un guión bajo y la hora.

![alt tag](https://dl.dropboxusercontent.com/u/94597454/stockphone/formato_arch_csv.png)

Fig. 13: Formato del archivo CSV del pedido generado con StockPhone.
