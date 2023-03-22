
EDIT: Link for documentation:
https://documenter.getpostman.com/view/20391003/2s93K1r14a


Application runs on port 8082!

URL for database:
http://localhost:8082/h2-console
JDBC URL:
jdbc:h2:mem:insurance_db


The application has no method for CustomerController.
Therefore, the customer is created using data.sql.

URLs and JSON formats for endpoint calls:

Create Quotation:
http://localhost:8082/api/v2/quotation

{
    "beginningOfInsurance": "2023-03-12",
    "insuredAmount": 10000.00,
    "dateOfSigningMortgage": "2023-03-01",
    "customer": {
    "customerId": 1
    }
}
--------------------------------------------------------------------------
Create Subscription:
http://localhost:8082/api/v2/subscription

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

--------------------------------------------------------------------------
Get single Subscription:
http://localhost:8082/api/v2/subscription/1
--------------------------------------------------------------------------
Update Customer: update attributes
http://localhost:8082/api/v2/customer/1
{
    "firstName": "Karl",
    "lastName": "Ufon",
    "middleName": "Von",
    "email": "KharlVonUfon@programproutajovanisvedku.cz",
    "phoneNumber": 52234343,
    "birthDate": "1991-06-16"
}
--------------------------------------------------------------------------
Update Customer: remove attributes
http://localhost:8082/api/v1/customer/1
{
    "removeAll": true
}