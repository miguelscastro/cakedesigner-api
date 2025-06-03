# 🍰 Cakedesigner API

This is the back-end API for the CakeDesigner Virtual Store, built with **Spring Boot** and integrated with a **React** front-end. It includes secure user authentication, role-based access control, product image storage, and integration with an external API to retrieve address information for delivery time estimation.

## Features (in progress)

- Implement CRUD operations to create, update, and delete different types of products  
- Configure CORS for specific URIs
- Store each product's image in an image bucket and persist its reference in a **PostgreSQL** database using **JPA**, alongside other relevant data
- Set up JWT-based authentication with role-based access (e.g., **admin** and **customer**)
- Integrate with a postal code API to calculate delivery fees and estimate delivery times

### Front-end

*(Currently under development, all authenticated paths won't be working).*
- https://cakedesigner.vercel.app/ 
