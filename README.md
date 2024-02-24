

# This is a Customer Detail project.
I developed a web application by using Spring Boot + Angular.

## Tech Stack

+ Spring Boot
+ MySQl Database
+ Bootstrap4 
+ Angular Material
+ Tailwind css

## Backend Design
#### Entities
Entities are inspired by the real world entities that can use the applications
1. User having attributes:
- UserID (Primary Key), FullName, Email, Password

2. Customer having attributes:
- CustomerID (Primary Key), FirstName, LastName, Address, City, state, Street, Image, Email, Phone No, createAt

### Relationships Between Entities 

User has an one-to-many relationship with customer. This means that one user can have many customer, but each customer belongs to one user. The relationship is indicated by the line connecting the "User" entity to the "Customers" entity, and the crow's foot notation on the "customer deatil" side of the line.


## Port

```bash
 http://localhost:4200
```

## Screenshot

#### Register
![App Screenshot](https://github.com/vish-muskan19/customers/blob/main/Customer_Details/SS/signup.png?raw=true)

#### Login
![App Screenshot](https://github.com/vish-muskan19/customers/blob/main/Customer_Details/SS/login.png?raw=true)

#### Home
![App Screenshot](https://github.com/vish-muskan19/customers/blob/main/Customer_Details/SS/Home.png?raw=true)

#### Add
![App Screenshot](https://github.com/vish-muskan19/customers/blob/main/Customer_Details/SS/create.png?raw=true)

#### Update
![App Screenshot](https://github.com/vish-muskan19/customers/blob/main/Customer_Details/SS/update.png?raw=true)

