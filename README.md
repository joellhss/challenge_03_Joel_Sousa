# CHALLENGE 03 - Week 12

The project consists of a REST API for a user authentication system while maintaining the log records. For this challenge, technologies and knowledge learned during the tracks were used, with a focus on spring security and rabbitmq.

## Microservices

1. **Ms. User**: The Order feature allows users to place orders for products from a catalog.
2. **Ms. Notification**: The Product feature allows users to create, read, update, and delete products.

### MS. USER

The ms. user is responsible for storing and managing user data and their addresses.
**Operations**:

| **Methods** | **URL**                | **Description**                                                                                       |
|:------------|:-----------------------|:------------------------------------------------------------------------------------------------------|
| POST        | /v1/login              | Login to the system (public): allows the user to authenticate their previously registered access.     |
| POST        | /v1/users              | Register user (public): creates a new user according to the payload available in this documentation.  |
| GET         | /v1/users/:id          | View registered data (private): authenticated users can view only their data by searching by ID.      |
| PUT         | /v1/users/:id          | Update user (private): updates user information, except for the password.                             |
| PUT         | /v1/users/:id/password | Update password (private): updates only the password.                                                 |

**System Rules**:

* The **firstName** and **lastName** fields must have at least 3 characters.
* The **email** field must be in a valid email format, and duplicate emails are not allowed. Example: test@email.com
* The **cpf** field must follow the pattern xxx.xxx.xxx-xx and cannot be duplicated.
* The **password** field must contain at least 6 characters.
* The **birthdate** field must be in the format yyyy-MM-dd.
* The **active** field will only accept boolean values.

**Payload Examples**

User registration.
```json
{
  "firstName": "Joel",
  "lastName": "Sousa",
  "cpf": "123.123.123-00",
  "birthdate": "1997-04-09",
  "email": "joel@email.com",
  "password": "123456789",
  "active": true
}
```

User login.
```json
{
  "email": "joel@email.com",
  "password": "123456789"
}
```

User update.
```json
{
  "firstName": "Joel",
  "lastName": "Henrique",
  "cpf": "123.123.123-00",
  "birthdate": "1997-04-09",
  "email": "joel@email.com",
  "active": true
}
```

Password update.
```json
{
  "password": "1234567"
}
```

**Note**:
* Use **Bearer** Token authentication to validate your access to private endpoints.
* The **birthdate** field will use the yyyy-MM-dd format for user creation and update, but the response will be in the Brazilian dd/MM/yyyy format.
* Common errors:
  * 400 - For invalid data and business rule violations.
  * 403 - For authorization and authentication failures.
  * 500 - Unknown errors or errors that should not occur.

### MS. NOTIFICATION

The ms. notification is responsible for receiving and storing user registration notifications, user registration changes, and login through received messages.

| **Methods** | **URL**           | **Description**                                                          |
|:------------|:------------------|:-------------------------------------------------------------------------|
| GET         | /v1/notification  | View logs (public): displays the system logs using a pagination system.  |

**Notes**

* Whenever an event of user creation, user editing, login, or password change occurs, a message will be sent to this microservice, which will perform persistence and display them as needed.
* Pagination by default will return the first 10 logs on the first page, i.e., page=0 and size=10;
* If you wish to pass your parameters, use the following filter after the URL: ?page=*{number}*&size=*{number}*

  **Payload**

```json
{
  "email": "joel@email.com",
  "event": "LOGIN",
  "date": "2023-11-13T23:22:10.087"
}
```

### Main Technologies Used
* Spring Security
* JWT Token
* RabbitMQ
* MySQL (ms-user)
* MongoDB (ms-notification)

Thank you to the SMs and instructors at Compass for all the tips and guidance in completing this project.
---

<p align="center">
Made with ❤️ by <a href="https://github.com/joellhss">Joel Sousa</a>.
</p>