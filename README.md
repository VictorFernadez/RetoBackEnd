## RetoBackEnd 💻
Desarrollo de una API para la consulta de Clientes

## Estructura del Proyecto:

![image](https://user-images.githubusercontent.com/26204625/219102769-7fc8f1cb-e090-4ced-8676-3b39088ac8f3.png)

## 🚩 Módulos:
> ⚙️**service-client:** Aplicación que expone métodos para los servicios REST para el cliente.

> ⚙️**time-log-aop:** Starter que mide el tiempo de ejecución por petición de la Api service-client.

> ⚙️**consume-clients:** Aplicación web que hace un consumo básico del servicio expuesto por la Api service-client.


### 🔍 Otros:

El service-client se encuentra disponible en DockerHub, así mismo ejecutando el siguiente comando en la ruta de la Api se podrá levantar:

Construir la imagen:
```bash
docker build -t servicio-client:latest . --Construye la imagen de la aplicación service-client.
```
![image](https://user-images.githubusercontent.com/26204625/219104900-7ef93950-131f-47ff-af0a-74b4ef19dd8e.png)


```bash
docker run --name service-client -p 8002:8002 service-client
```

o en su defecto
```bash
docker compose up --Para la ejecución del docker-compose.yml y levantamiento del Api.
```

> 📝 **También se encuentra en DockerHub para descarga y uso:**

![image](https://user-images.githubusercontent.com/26204625/219105899-7e704516-014b-4d81-ae24-cd4ba5335dff.png)

![image](https://user-images.githubusercontent.com/26204625/219105942-46c79ff5-a746-4ceb-b6f3-151d665325e1.png)

> ⚙️ **service-client:**
*Este módulo contiene la lógica para las consultas de un modelo Cliente:

![image](https://user-images.githubusercontent.com/26204625/219107760-6ce67a0e-daa3-4e03-9736-99289ac16521.png)


![image](https://user-images.githubusercontent.com/26204625/219107884-8d469dfd-3d8d-4765-ba9f-e1b86a97de65.png)

**Los servicios disponibes son los siguientes:**

![image](https://user-images.githubusercontent.com/26204625/219108032-7272e2bf-73cf-4a12-a8fe-d980fedd1416.png)

*Para lo cual se han definido 2 tipos de Roles, un ADMIN y CLIENT, para efectos del caso propuesto el rol de Client podrá realizar métodos transaccionales.

**Regitro de un Cliente:**
* POST: localhost:8002/v1/client/
* Credenciales : client - client

![image](https://user-images.githubusercontent.com/26204625/219108764-40a303af-f3f1-46d9-b37b-afcb1441e860.png)

![image](https://user-images.githubusercontent.com/26204625/219108950-dde56a99-a25f-4c10-b6b6-42d0b035a23e.png)

**Consulta de Clientes:**
* GET: localhost:8002/v1/client/

![image](https://user-images.githubusercontent.com/26204625/219109397-65f34403-a6e6-4928-bb0a-f255b66df946.png)

Consulta de Clientes por Código Único encriptado:
GET: localhost:8002/v1/client/M39mv0pNdTAZx6vCLNBmCg== (Por Ejemplo)

![image](https://user-images.githubusercontent.com/26204625/219109644-f9424b55-bdaf-4ab0-bcda-33b379374f54.png)

**Para el caso del rol de Usuario se tiene una consulta extra, esta es para que pueda ver el detalle de sus datos:**
*GET: localhost:8002/v1/client/detail/75823031 (Por Ejemplo)

![image](https://user-images.githubusercontent.com/26204625/219109908-637ff8ff-5332-429d-9a83-672cc2c51694.png)

**Como se mencionó líneas arriba, solo el usuario podrá tener esta consulta:**
*Credenciales : admin - password

![image](https://user-images.githubusercontent.com/26204625/219110272-578fe039-9463-44db-ad59-1d1d3cb5c9e5.png)

**Testing de las transacciones de la aplicación:**

![image](https://user-images.githubusercontent.com/26204625/219110778-c1f19286-cd13-4d42-a795-c3fab7497822.png)


> ⚙️ **time-log-aop:**
*Esta aplición mediante AOP se encarga de medir el tiempo de ejecución de cada request, es un starter que para efectos del requerimiento se prueba en la aplicación 
service-client como dependencia:

![image](https://user-images.githubusercontent.com/26204625/219111453-9ab92b9f-3721-4408-90e6-e186066f0056.png)

*Al momento de hacer algún request en la Api service-client se puede revisar en el log de la aplicación lo mencionado:

![image](https://user-images.githubusercontent.com/26204625/219112019-e813c8ef-94b1-4deb-a587-69afaf231b9e.png)

*Así mismo también se evidencia la configuración de los logs.


> ⚙️ **consume-clients:**
**Aplicación Web que consume el servicio de la Api service-client:**
*http://localhost:8080/clients

![image](https://user-images.githubusercontent.com/26204625/219112560-83782c50-812e-4560-a58f-e787cfc59898.png)


**Ejecución mediante el contenedor de Docker:**
> 📝 *Nos dirigimos a la ubicación de la Api*

![image](https://user-images.githubusercontent.com/26204625/219112923-3ba85145-5d07-4031-b696-1a70377481da.png)

![image](https://user-images.githubusercontent.com/26204625/219113031-3a68d0ad-5f3a-4248-91ce-9898d6b30d6b.png)

> 📝 *Ejecutamos el contenedor:*

![image](https://user-images.githubusercontent.com/26204625/219113243-ad467c58-bedc-4b5b-9097-e2d4f7731084.png)

![image](https://user-images.githubusercontent.com/26204625/219113311-5afd6f0e-ec85-4df7-9dfb-4d0c9b8fc91f.png)


**Realizamos las consultas mencionadas:**

> ⚙️ **PostMan:**
![image](https://user-images.githubusercontent.com/26204625/219113624-fb843139-d08b-454a-af76-6b2980fde246.png)


> ⚙️ **Desde la Web:**

![image](https://user-images.githubusercontent.com/26204625/219113657-48df4525-9b7d-4955-b848-da51f46963c9.png)



## 👌Eso sería todo, saludos!## 




























