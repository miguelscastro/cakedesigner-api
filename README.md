# 🍰 Cakedesigner API

Back-end API for the CakeDesigner virtual store, built with **Spring Boot** and integrated with a **React** front-end. The API provides secure user authentication, role-based access control, product image storage, and integration with an external API to retrieve address data for delivery.

## Features

- Full CRUD for product types and products
- CORS configuration (currently allowing `localhost:3000`)
- Image storage in a local directory, with the full image path stored in a **PostgreSQL** database via **JPA**
- JWT authentication with role-based access control (**ADMIN**, **USER**)
- External ZIP code (CEP) API integration to retrieve delivery address data

## 🧁 Product Routes

| Method | Endpoint               | Description                              | Auth Required |
| ------ | ---------------------- | ---------------------------------------- | ------------- |
| GET    | `/manage/product`      | Get all products                         | ❌ Public     |
| POST   | `/manage/product`      | Create new product (multipart/form-data) | ✅ ADMIN      |
| PUT    | `/manage/product`      | Update product (multipart/form-data)     | ✅ ADMIN      |
| DELETE | `/manage/product/{id}` | Delete a product by ID                   | ✅ ADMIN      |

## 🧩 Product Type Routes

| Method | Endpoint                    | Description                 | Auth Required |
| ------ | --------------------------- | --------------------------- | ------------- |
| GET    | `/manage/product-types`     | Get all product types       | ✅ ADMIN      |
| POST   | `/manage/product-type`      | Create a new product type   | ✅ ADMIN      |
| PUT    | `/manage/product-type`      | Update a product type       | ✅ ADMIN      |
| DELETE | `/manage/product-type/{id}` | Delete a product type by ID | ✅ ADMIN      |

## 📦 Order Routes

| Method | Endpoint       | Description                       | Auth Required |
| ------ | -------------- | --------------------------------- | ------------- |
| POST   | `/orders/user` | Create new order                  | ✅ USER       |
| GET    | `/orders/user` | Get all orders for logged-in user | ✅ USER       |
| GET    | `/orders`      | Get all orders (admin only)       | ✅ ADMIN      |

## 👤 User Routes

| Method | Endpoint                       | Description                     | Auth Required |
| ------ | ------------------------------ | ------------------------------- | ------------- |
| GET    | `/user`                        | Get logged-in user's profile    | ✅ USER/ADMIN |
| PUT    | `/user/profile`                | Update logged-in user's profile | ✅ USER/ADMIN |
| DELETE | `/user/profile/delete-account` | Delete logged-in user's account | ✅ USER/ADMIN |

## 🔐 Auth Routes

| Method | Endpoint        | Description                                  | Auth Required |
| ------ | --------------- | -------------------------------------------- | ------------- |
| POST   | `/auth/sign-up` | Register new user (admin needs JWT if ADMIN) | ❌ / ✅       |
| POST   | `/auth/sign-in` | Authenticate user and return JWT             | ❌ Public     |

---

## 🔒 Security Notes

- The following paths are **public**:

  - `GET /manage/product`
  - All `/auth/**` routes
  - Static files under `/uploads/images/**`

- All other routes are **protected** and require JWT authentication.

- Product routes for `POST`, `PUT`, and `DELETE` require a valid **admin token**.

- Uploaded product images are saved in a local `uploads/images/` directory, and their complete URL path is stored in the database:
