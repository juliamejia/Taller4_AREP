# Taller2_AREP
 Concretamente, exploraremos la arquitectura de  los servidores web y el protocolo http sobre el que están soportados.  
 Julia Mejia
 ## EJECUCION 
1. Clonar el repositorio usando este codigo desde el cmd, en la carpeta que gustes  
    `git clone https://github.com/juliamejia/AplicacionesDistribuidas-AREP.git`

2. Dentro de la carpeta llamada networking abrimos el cmd y ejecutamos el siguiente comando  
   `mvn clean package exec:java -D "exec.mainClass"="org.example.HttpServer"`
   lo cual nos mostrará que el proyecto se ejecutó correctamente  
   <img width="567" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/a2d6e0cd-2cc7-4998-8636-f09336c65408">
3. Desde cualquier browser, digitamos la direccion:  
 * http://localhost:35000/apps/index.html  
   <img width="391" alt="image" src="https://github.com/juliamejia/Taller2_AREP/assets/98657146/98a0a6a9-72c4-48c4-930f-0151f49a8ade">  
 * http://localhost:35000/apps/Estilo.css  
   <img width="358" alt="image" src="https://github.com/juliamejia/Taller2_AREP/assets/98657146/5d73e90f-408e-464d-aaaf-13ec1f62dead">  
 * http://localhost:35000/apps/script.js  
   <img width="456" alt="image" src="https://github.com/juliamejia/Taller2_AREP/assets/98657146/cf532ae0-07b2-4d4b-b9d4-ffbd4ab42ec1">  
 * http://localhost:35000/apps/imagenJulia.jpg  
   <img width="383" alt="image" src="https://github.com/juliamejia/Taller2_AREP/assets/98657146/0a995313-f910-4dcf-b1d6-859b9dca26bf">  

5. Para visualizar el javadoc ejecutamos el siguiente comando  
    `mvn javadoc:javadoc"` y vamos a la carpeta networking->target->site->apidocs donde encontraremos todos los documentos creados como se muestra en la imagen   
    <img width="618" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/2da5584d-b117-4b05-9b47-fa0986ebf50b">
   
   ## ARQUITECTURA
Se usa la aquitectura  arquitectura cliente-servidor que es un modelo de diseño en el que las aplicaciones se dividen en dos partes principales: el cliente y el servidor. Estas partes interactúan entre sí para proporcionar servicios, recursos o información a los usuarios o a otras aplicaciones. Es un enfoque comúnmente utilizado en sistemas distribuidos y en Internet para permitir la comunicación y el intercambio de datos entre dispositivos.
En este caso:   
* El "cliente" es cualquier navegador web u otra aplicación que pueda realizar solicitudes HTTP a través de una red.
Los clientes pueden acceder a diferentes rutas en el servidor web mediante URLs, como "/index.html" o "/script.js".
Los clientes también pueden acceder a recursos como imágenes ("/imagenJulia.jpg") o enviar solicitudes personalizadas ("/title?name=...") para obtener información.
* El "servidor" es la clase HttpServer en el código que está diseñada para manejar las solicitudes de los clientes y proporcionar respuestas apropiadas.
La clase HttpServer escucha en un puerto determinado (en este caso, el puerto 35000) y acepta conexiones entrantes de los clientes.
Cuando una conexión entrante es aceptada, el servidor crea flujos de entrada y salida para comunicarse con el cliente.  




 
