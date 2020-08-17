
# BudgetManager

This is a simple web application to track daily incomes and expenses, where user can sign up, sign in, add, remove or edit the income or expense details.


### For Live Demo, [Click here](https://budget-manager-879.netlify.app).


## Screenshot

1. Homepage
![Screenshot 1](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/blob/master/screenshot/homepage.jpg "")

2. Sign in
![Screenshot 2](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/blob/master/screenshot/sign-in.jpg "")

3. Dashboard
![Screenshot 3](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/blob/master/screenshot/dashboard.jpg "")


## Features

- sign up, sign in
- email verification, forgot password
- responsive web design
- pagination
- secured REST end points
- jwt token based authentication
- CRUD operations


## Built With

- React.js 16
- Bootstrap 4
- Spring Boot 2.3.0
- MySQL


## Prerequisites

* Maven
* node package manager(npm)
* MySQL Workbench ([click here for download](https://dev.mysql.com/downloads/workbench/))

## Open and Run Project

**For Database:**
1. open MySQL Workbench.
2. create a new database named as "db_budget_manager".

**For Spring Boot application:**
1. open application.properties and replace all "TODO" with your configuration.
2. open command prompt in [spring-boot](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/tree/master/spring-boot) directory.
3. run command "mvn install".
4. run command "mvn spring-boot:run".

**For React.js application:**
1. open command prompt in [react-js](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/tree/master/react-js) directory.
2. run command "npm install".
3. run command "npm start".


## RESTful API ##

> **Secure Route**: /api/items/**

**1. API Description for User related action**
base = /api

METHOD | PATH | DESCRIPTION
------------|-----|------------
POST | /register | new user registration
POST | /login | user login
GET | /email-verification | verifying email address
POST | /request-password-reset | sending password reset link to registered email
POST | /password-reset | reset the password

**2. API Description for Item(Income or Expense)**
base = /api/items

METHOD | PATH | DESCRIPTION
------------|-----|------------
GET | / | get all items
POST | /{category} | create new item
PUT | /{category}/{id} | update item
DELETE | /{category}/{id} | delete item

{category} = income OR expense



## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- This project is licensed under the **[MIT license](http://opensource.org/licenses/mit-license.php)**.


## Support

Please feel free to submit [issues](https://github.com/Ravi879/BudgetManager-React-Spring-Boot/issues) with any bugs or other unforeseen issues you experience.


## Author

- Ravi Gadhiya
[https://github.com/Ravi879](https://github.com/Ravi879)
[https://www.linkedin.com/in/gadhiyaravi](https://www.linkedin.com/in/gadhiyaravi)
[gadhiyara@gmail.com](mail:gadhiyara@gmail.com)

### Thanks

I'm always happy to hear your feedback!
Enjoy 🤘
