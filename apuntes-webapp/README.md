# Getting shit done

## Front

 * desde la carpeta root del proyecto
   * mvn clean
   * mvn eclipse:eclipse (o simil)
 * desde la carpeta frontend
   * npm install
   * bower install
   * grunt eslint:all _(esto va a chequear syntax)_
   * grunt build --force _(importante que esto no tire ningun error. Si tira arreglarlo antes de seguir)_
 * desde la carpeta root del proyecto
   * mvn package clean
 * desde la carpeta frontend
   * grunt serve _(esto va a levantar en localhost:9000 la pagina)_

# Endpoints de la API REST

## Clients

**Iniciar sesion**
----

* **URL**

  /api/v1/clients/login

* **Method:**

  `POST`

* **Data Params**

   `username=[string]`

   `password=[string]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"token":"..."}`

* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{"message":"Unauthorized","status":401}`


**Perfil**
----

* **URL**

  /api/v1/clients/me

* **Method:**

  `GET`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:**

        {
            "clientId": 5,
            "email": "changeme@example.com",
            "name": "admin",
            "recoveryQuestion": "jajjajaja",
            "role": "ROLE_ADMIN",
            "universityId": 1
        }

* **Error Response:**

  * **Code:** 403 FORBIDDEN <br />
    **Content:**

        {
            "message": "Unauthorized",
            "status": 403
        }

**Documentos Subidos**
----

* **URL**

  /api/v1/clients/me/documents

* **Method:**

  `GET`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:**

        {
            "documentList": [
                 {
                    "courseid": 1,
                    "dateUploaded": "2016-12-13T19:40:55.662-03:00",
                    "description": "descripcoiiooono",
                    "documentId": 15,
                    "documentName": "nombre del archivo.pdf",
                    "subject": "asuntoooooo",
                    "userid": 5
                }
            ],
        }

 **Calificaciones realizadas**
 ----

 * **URL**

   /api/v1/clients/me/reviews

 * **Method:**

   `GET`

 * **Success Response:**

   * **Code:** 200 <br />
     **Content:**

            {
                "reviewList": [
                    {
                        "fileid": 15,
                        "ranking": 3,
                        "review": "asdadads",
                        "reviewid": 1,
                        "userid": 5
                    }
                ],
            }

 **Cambiar contrasena**
 ----

 * **URL**

   /api/v1/clients/me/change_password

 * **Method:**

   `POST`

 * **Body**

        {
            "password": "newPassword"
        }

 * **Success Response:**

   * **Code:** 200 <br />
     **Content:**

            {
                "clientId": 5,
                "email": "changeme@example.com",
                "name": "admin",
                "recoveryQuestion": "jajjajaja",
                "role": "ROLE_ADMIN",
                "universityId": 1
            }

 **Restaurar contrasena**
 ----

 * **URL**

   /api/v1/clients/reset_password

 * **Method:**

   `POST`

 * **Body**

        {
            "name": "username",
            "secretAnswer": "Barbie",
            "password": "123456"
        }

 * **Success Response:**

   * **Code:** 200 <br />
     **Content:**

         {
            "clientId": 5,
            "email": "changeme@example.com",
            "name": "admin",
            "recoveryQuestion": "jajjajaja",
            "role": "ROLE_ADMIN",
            "universityId": 1
         }

 **Registrarse**
 ----

 * **URL**

   /api/v1/clients/register

 * **Method:**

   `POST`

 * **Body**

        {
            "name": "username",
            "email": "asd@itba.edu.ar",
            "password": "123456"
        }

 * **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"token":"..."}`
