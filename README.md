# Taller3_AREP
### Julia Marcela Mejia Perez
Construir un  servidor web para soportar una funcionalidad similar a la de Spark. Permitir por lo menos el registro de servicios get y post usando funciones lambda  
 ## EJECUCION 
1. Clonar el repositorio usando este codigo desde el cmd, en la carpeta que gustes  
    `git clone https://github.com/juliamejia/AplicacionesDistribuidas-AREP.git`

2. Dentro de la carpeta llamada Taller3_AREP abrimos el cmd y ejecutamos el siguiente comando  
   `mvn clean package exec:java -D "exec.mainClass"="org.example.HttpServer"`
   lo cual nos mostrará que el proyecto se ejecutó correctamente  
   <img width="567" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/a2d6e0cd-2cc7-4998-8636-f09336c65408">
3. Desde cualquier browser, digitamos la direccion:  
 * http://localhost:35000/index.html  
   <img width="688" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/29d73e4e-96e5-4fe6-ac2a-085e3c94eb1a">  
 * http://localhost:35000/Estilo.css  
   <img width="715" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/2ab1e954-1d1a-4132-88b5-bda424bf6351">  
 * http://localhost:35000/script.js  
   <img width="711" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/113e67ca-a2ee-4b04-adf7-5975f2bb167a">  
 * http://localhost:35000/imagenJulia.jpg  
   <img width="818" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/269c2001-b438-4fa4-9b2b-c6f4714f4ac5">  
  
4. Para visualizar el javadoc ejecutamos el siguiente comando  
    `mvn javadoc:javadoc"` y vamos a la carpeta networking->target->site->apidocs donde encontraremos todos los documentos creados como se muestra en la imagen   
    <img width="618" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/2da5584d-b117-4b05-9b47-fa0986ebf50b">
   ## GET Y POST
   Se ha desarrollado una interfaz práctica que permite crear funciones lambda en tiempo real al ejecutar el servidor. Estas funciones habilitan las operaciones GET y POST sin requerir servicios adicionales, y además son capaces de identificar el tipo de archivo para mostrarlo con el content-type adecuado.  
* Para probar el funcionamiento del metodo GET abrimos la consola y ejecutamos el siguiente comando con cualquiera de los recursos que nos ofrece el proyecto, sea el html, jpg css, js, en este caso usaremos el archivo css para el ejemplo  
   `curl http://localhost:35000/Estilo.css`  
   vemos que en consola se muestra el contenido de este archivo  
   <img width="581" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/02716577-4a31-4e44-a09a-a7b0a5344e88">  
   <img width="459" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/25f0eae9-dd27-49ee-bf6c-0fe082553463">  

* Para probar el funcionamiento del metodo POST abrimos la consola y ejecutamos el siguiente comando en este caso con dos parametros String ya que es lo que se esta recibiendo en el metodo spark.post  
   `curl -X POST -d "value=Julia&key=Mejia" http://localhost:35000/Estilo.css`  
    vemos que la aplicacion lo ejecuta  
   <img width="452" alt="image" src="https://github.com/juliamejia/Taller3_AREP/assets/98657146/c1d70b44-7fe6-4f9e-91e9-daa1cb2f1612">
## ARQUITECTURA  
En este caso se usa una arquitectura de servidor web de tipo "microframework" donde se opta por un marco más pequeño y liviano que proporciona solo las funcionalidades esenciales necesarias para construir aplicaciones web de manera más ágil y específica.  
Estas son algunas de sus caracteristicas con respecto al codigo: 
1. Minimalismo: El código del proyecto utiliza Spark, que es un microframework Java, el cual se caracteriza por su minimalismo y simplicidad. En el código, se ve cómo se define el enrutamiento de solicitudes y se manejan las respuestas HTTP de manera directa y sin una sobrecarga de abstracción innecesaria.
2. Enrutamiento Simple: En el código, puedes observar cómo se define el enrutamiento de solicitudes utilizando el método get() de Spark. Este método asocia una ruta de URL con un controlador de ruta específico, lo que simplifica el enrutamiento de las solicitudes entrantes.
3. Flexibilidad y Elección: En el código se puede ver cómo se integran diferentes tipos de respuestas, como respuestas JSON, HTML y de archivos, según las necesidades de las rutas específicas. Esto demuestra la flexibilidad de Spark para adaptarse a diferentes tipos de respuestas.
4. Rápido Aprendizaje y Desarrollo: Spark es conocido por ser fácil de aprender y utilizar. El código es claro y conciso, lo que facilita el desarrollo rápido de rutas y controladores de solicitud.
5. Bajo Acoplamiento: En el código, los componentes están diseñados con bajo acoplamiento. Cada ruta y controlador de solicitud puede funcionar de manera independiente, lo que facilita la modificación y la expansión sin afectar otras partes del sistema.  


   




   

    

