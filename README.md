# Taller4 AREP
### Julia Marcela Mejia Perez
Construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web a partir de POJOS.
 ## EJECUCION 
1. Clonar el repositorio usando este codigo desde el cmd, en la carpeta que gustes  
    `git clone https://github.com/juliamejia/Taller4_AREP.git`

2. Dentro de la carpeta llamada Taller4_AREP abrimos el cmd y ejecutamos el siguiente comando  
   `mvn clean package exec:java -D "exec.mainClass"="org.example.Services.RestService"`
   lo cual nos mostrará que el proyecto se ejecutó correctamente  
   <img width="567" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/a2d6e0cd-2cc7-4998-8636-f09336c65408">
3. Desde cualquier browser, digitamos la direccion:  
 * http://localhost:35000/hello  
   <img width="348" alt="image" src="https://github.com/juliamejia/Taller4_AREP/assets/98657146/181a3498-878f-41ec-9d62-4502ce762cc9">  
 * http://localhost:35000/index  
   <img width="329" alt="image" src="https://github.com/juliamejia/Taller4_AREP/assets/98657146/ba44757a-ecdc-40be-a95c-4be0591169f4">  
 * http://localhost:35000/image  
   <img width="846" alt="image" src="https://github.com/juliamejia/Taller4_AREP/assets/98657146/3ed26bf5-b945-4553-8cae-4d3519289af4">  

4. Para visualizar el javadoc ejecutamos el siguiente comando  
    `mvn javadoc:javadoc"` y vamos a la carpeta Taller4_AREP->target->site->apidocs donde encontraremos todos los documentos creados como se muestra en la imagen   
    <img width="618" alt="image" src="https://github.com/juliamejia/AplicacionesDistribuidas-AREP/assets/98657146/2da5584d-b117-4b05-9b47-fa0986ebf50b">

    ## DESCRIPCION

   Se ha creado un Plain Old Java Object (POJO) que permite el llamado a varios controladores a través de anotaciones específicas. Cada controlador implementa su 
   propia lógica de funcionamiento, y estas anotaciones actúan como una especie de interfaz funcional que especifica cómo debe manejarse cada solicitud.  
   El uso de anotaciones permite asociar de manera sencilla y flexible las solicitudes HTTP entrantes con las funciones de control adecuadas en el POJO. Cada anotación    podría representar un tipo de solicitud o ruta específica en la aplicación web.

   #### Patrones de diseño

   1. Singleton: Se utiliza el patrón Singleton para garantizar que cada controlador sea una instancia única. Esto asegura que los controladores compartan un estado coherente y que no se creen instancias innecesarias, lo que puede ser eficiente en términos de uso de recursos.  
   2. Adaptador: Se hace uso del patrón Adaptador para adaptar las solicitudes HTTP entrantes al controlador adecuado. Esto implica traducir y enrutar las solicitudes a las funciones específicas del POJO, lo que permite una gestión más flexible y modular de las solicitudes web.  
    
   
   
   


   




   

    

