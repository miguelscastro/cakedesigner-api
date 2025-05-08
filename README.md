# 🍰 Cakedesigner API

This is the back-end API for the CakeDesigner Virtual Store, built with **Spring Boot** and integrated with a **React** front-end. It includes secure user authentication, role-based access control, product image storage, and integration with an external API to retrieve address information for delivery time estimation.

## Features (to-do)

- Implement CRUD operations to create, update, and delete different types of products  
- Persist each product's image in an image bucket and store its reference in a PostgreSQL database using **JPA** and **JPQL** alongside other relevant data.
- Configure JWT-based authentication with role-based access for users such as **admin** and **customer**
- Integrate with a Postal Code API to calculate delivery fees and estimate delivery time
