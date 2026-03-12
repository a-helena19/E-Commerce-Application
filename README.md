# E-Commerce-Application
## Exercise 1

- We use an H2 database to store the data.
- The APIs are documented using Swagger UI (http://localhost:8088/swagger-ui/index.html).
- The order history of a user can be accessed via GET /api/orders/user/{userId}.
- DELETE operations (e.g., for products or users) set the status to INACTIVE, meaning the data is not physically removed from the database.