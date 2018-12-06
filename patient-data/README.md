# Patient Management System
Developed a Restful service for patient management system.
Used spring boot, maven, hibernate, spring security, oauth2, flyway.

Implemented Restful services:

List all patients details

`
GET localhost:8080/patients
`

Get a specific Patient Details:

`GET localhost:8080/patient/1`

Create Patient Details:

`POST localhost:8080/patient`
```
{"payload":
	{	"patientDetails": {
			"firstName": "Helen",
			"lastName": "Rojer",
			"bloodGroup": "O-",
			"gender": "Female"
		},
		"address": {
			"houseNo": "213",
			"streetNo": "323",
			"name": "banjarahills",
			"city": "Hyderabad",
			"state": "telangana",
			"country": "India",
			"zipcode": "500032"
		},
		"contact": {
			"phoneNumber": "3244232463532",
			"email": "Rojer@gmail.com"
		}
	}
	
}
```

