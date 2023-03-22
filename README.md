# InsuranceApp
***
This application was created for Java developer job interview.  
App allows access to the insurance database.  
It's published on [GitHub](https://github.com/mARTinKOPULETY/InsuranceApp "Link of the Insurance App on the GitHub") . 
 
## Endpoints:
* editing/deleting customer attributes
* creating a quotation
* creating and listing a subscription  
  
## H2 database:
|NAME|URL|
|---|---|
|console|http://localhost:8082/h2-console|
|JDBC|jdbc:h2:mem:insurance_db|  
---
### PUT Customer - UPDATE/DELETE some attributes, or remove all attributes at once

```
http://localhost:8082/api/v2/customer/1
```    



Endpoint for modifying an existing Customer. It has two features. The first is update or delete individual attributes of Customer. The second is to delete all attributes at once, if the attribute "boolean removeAll" is "true". It accepts a Long customerId and a CustomerDTO object containing the modifications, retrieves the existing Customer, updates it with the values in CustomerDTO, logs the update message using customerLogging, and saves it back to the customerRepository. Raises an IllegalArgumentException if there is no Customer object with the given customerId.

> A successful updating will result in HTTP 200 OK.   

> A request for non-existing Customer will return HTTP 400 "Customer not found for id: 42"  

### Request body raw (json)  
#### Request A - UPDATE/DELETE some attributes
```
{
    "customerId": 1,
    "firstName": "Karl",
    "lastName": "Ufon",
    "middleName": "Von",
    "email": "KarlVonUfon@programnautajovanisvedku.cz",
    "phoneNumber": "52234343",
    "birthDate": "2023-03-12",
    "removeAll": false
}    
```
### Example Response  
#### HTTP 200 OK
```
{
    "customerId": 1,
    "firstName": "Karl",
    "lastName": "Ufon",
    "middleName": "Von",
    "email": "KarlVonUfon@programnautajovanisvedku.cz",
    "phoneNumber": "52234343",
    "birthDate": "2023-03-12",
    "removeAll": false
}
```  
### If the  "customerId" is changed in  URL
```http://localhost:8082/api/v2/customer/42``` 
#### HTTP 404 "Customer not found for id: 42"
```
{
    "timestamp": "",
    "status": 404,
    "error": "Customer not found for id: 42",
    "path": "/api/v2/customer/42"
} 
```    
### Request body raw (json)

#### Request B - DELETE all attributes at once
```
{
    "firstName": "Karl",
    "lastName": "Ufon" ,
    "middleName": "Von",
    "email": "KarlVonUfon@programnautajovanisvedku.cz",
    "phoneNumber": 52234343,
    "birthDate": "2023-03-12",
    "removeAll": true 
}
```

### Example Response
#### HTTP 200 OK
```
{
    "customerId": 1,
    "firstName": null,
    "lastName": null,
    "middleName": null,
    "email": null,
    "phoneNumber": null,
    "birthDate": null,
    "removeAll": false
}
```

***
### POST Quotation - CREATE

```
http://localhost:8082/api/v2/quotation
```  
Endpoint for creating a new Quotation object. It accepts a QuotationDTO as input, performs some validation on the input data, maps the QuotationDTO object to a Quotation, logs the creation message using quotationLogging, and then saves the Quotation object to the quotationRepository.
> A successful creating will result in HTTP 201 CREATED.

> A request for non-existing Customer will return HTTP 400 "Customer does not exist"  


### Request body raw (json)
```
{ 
    "beginningOfInsurance": "2023-03-12",
    "insuredAmount": 100000000000.00,
    "dateOfSigningMortgage": "2023-03-01",
    "customer": {
    "customerId": 1
    }
}
```
### Example Response
#### HTTP 201 CREATED
```
{
    "quotationId": 1,
    "beginningOfInsurance": "2023-03-12",
    "insuredAmount": 100000000000,
    "dateOfSigningMortgage": "2023-03-01",
    "customer": {
        "customerId": 1,
        "firstName": null,
        "lastName": null,
        "middleName": null,
        "email": null,
        "phoneNumber": null,
        "birthDate": null,
        "removeAll": false
    }
}
```
### If the "customerId" is changed to 42 in the Request body
#### HTTP 400 "Customer does not exist"
```
{
    "timestamp": "",
    "status": 400,
    "error": "Customer does not exist",
    "path": "/api/v2/quotation"
}
```
***

### POST Subscription - CREATE

```
http://localhost:8082/api/v2/subscription
```  
Endpoint for creating a new subscription. It accepts a SubscriptionDTO as input, performs some validation on the input data, maps the SubscriptionDTO to a Subscription, logs the creation message using subscriptionLogging, and then saves the Subscription object to the repository.
> A successful creating will result in an HTTP 201 CREATED.

> A request for non-existing Customer will return HTTP 400 "Customer does not exist"  

> A request for non-existing Quotation will return HTTP 400 "Quotation does not exist"


### Request body raw (json)
```
{
    "quotation": {
    "quotationId":1 ,
    "customer": {
    "customerId": 1
    }
    },
    "startDate": "2023-03-15",
    "validUntil": "2024-03-15"
}

```
### Example Response
#### HTTP 201 CREATED
```
{
    "subscriptionId": 1,
    "quotation": {
        "quotationId": 1,
        "beginningOfInsurance": null,
        "insuredAmount": null,
        "dateOfSigningMortgage": null,
        "customer": {
            "customerId": 1,
            "firstName": null,
            "lastName": null,
            "middleName": null,
            "email": null,
            "phoneNumber": null,
            "birthDate": null,
            "removeAll": false
        }
    },
    "startDate": "2023-03-15",
    "validUntil": "2024-03-15"
}
```
### If the "customerId" is changed to 42 in the Request body
#### HTTP 400 "Customer does not exist"
```
{
    "timestamp": "",
    "status": 400,
    "error": "Customer does not exist",
    "path": "/api/v2/subscription"
}
```  
### If the "quotationId" is changed to 42 in the Request body
#### HTTP 400 "Quotation does not exist"
```

    "timestamp": "",
    "status": 400,
    "error": "Quotation does not exist",
    "path": "/api/v2/subscription"
}
```
***
### GET Subscription - GET by ID

```
http://localhost:8082/api/v2/subscription/1
```  
Endpoint for retrieving a single Subscription object by its id. It accepts a Long value as id of the Subscription, queries the corresponding objects from the repositories using the id, logs a message of the retrieved objects using subscriptionLogging, and returns the Subscription object. Throws a ResponseStatusException if the Subscription or any associated objects are not found.
> A successful getting of the Subscription will result in HTTP 200 OK.

> A request for non-existing Subscription will return HTTP 404 "Subscription with id 42 does not exist"

### Example Response
#### HTTP 201 CREATED
```
{
    "subscriptionId": 1,
    "quotation": {
        "quotationId": 1,
        "beginningOfInsurance": "2023-03-12",
        "insuredAmount": 100000000000.00,
        "dateOfSigningMortgage": "2023-03-01",
        "customer": {
            "customerId": 1,
            "firstName": null,
            "lastName": null,
            "middleName": null,
            "email": null,
            "phoneNumber": null,
            "birthDate": null,
            "removeAll": false
        }
    },
    "startDate": "2023-03-15",
    "validUntil": "2024-03-15"
}
```
### If the "subscriptionId" is changed in  URL
```http://localhost:8082/api/v2/subscription/42```
#### HTTP 404 "Quotation does not exist"
```
{
    "timestamp": "",
    "status": 404,
    "error": "Subscription with id 42 does not exist",
    "path": "/api/v2/subscription/42"
}
```
***



