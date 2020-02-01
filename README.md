"# usuarios-demo" 

Proyecto basado en Spring Boot y hibernate para persistencia. Utilizando arquitectura hexagonal y patron de diseño builder ademas de jpa constraints para validacion. Tambien se incorporo spring data con hibernate como proveedor de persistencia.

Operaciones Expuestas.

1 - Creacion de usuario y obtencion de token jwt
Url : http://localhost:8080/security/v1.0/users

Para invocar el servicio debe realizar una peticion a la url indicada con este payload

{
	"email":"email@google.com",
	"password":"asfdA1w1",
	"name":"nombre",
	"phones":[
		{
			"number": 1,
			"cityCode": 1,
			"countryCode":1
		}
		]
}

Restricciones de request

- Se debe ingresar un correo valido.
- Nombre no puede ser nulo.
- La clave debe seguir el siguiente formato. (Una
Mayuscula, letras minúsculas, y dos números)

Ejemplo response ok:

{
    "code": 0,
    "type": "Success_Response",
    "message": [
        "User Created"
    ],
    "id": "1",
    "created": "2020-02-01T21:23:54.211+0000",
    "modified": "2020-02-01T21:23:54.211+0000",
    "lastLogin": "2020-02-01T21:23:54.211+0000",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTgwNTkyMjM0LCJzdWIiOiJzdWJqZWN0IiwiaXNzIjoiaXNzdWVyVGVzdCIsImV4cCI6MTU4MDU5MjIzN30.-FIyMVA06ygXt929apbynj1O7KJAu3CCsTDRZtY5iJI",
    "active": true
}

Ejemplo response error:

{
    "code": 1,
    "type": "System_Error",
    "message": [
        "Email already registered"
    ],
    "id": null,
    "created": null,
    "modified": null,
    "lastLogin": null,
    "token": null,
    "active": false
}
