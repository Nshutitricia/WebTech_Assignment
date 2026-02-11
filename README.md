# Spring Boot RESTful API Assignment

This repository contains the solutions for the Module 1-3 Practical Questions. It consists of 5 distinct RESTful API projects and one bonus project, covering basic CRUD operations, filtering, searching, pagination, and response wrapping.

## How to Run

1. **Prerequisites:** Java JDK 17+ and Maven.
2. **Clone the repository:**

    ```bash
    git clone <https://github.com/Nshutitricia/WebTech_Assignment.git>
    ```

3. **Run a specific question:**
    Navigate to the specific project folder (e.g., `question1-library-api`) and run:

    ```bash
    mvn spring-boot:run
    ```

    *Alternatively, if using the shared project structure, navigate to the root and run the main application file.*

---

## Question 1: Library Book Management API

**Base URL:** `/api/books`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/books` | Get a list of all books |
| `GET` | `/api/books/{id}` | Get a specific book by ID |
| `GET` | `/api/books/search?title={title}` | Search books by title |
| `POST` | `/api/books` | Add a new book |
| `DELETE` | `/api/books/{id}` | Delete a book by ID |

### Testing Evidence

**Get All Books:**
![Get All Books](Screenshots/getAllBooks.png)

**Get One Book:**
![Get One Book](Screenshots/getOneBook.png)

**Search Books:**
![Search Books](Screenshots/searchBooks.png)

**Delete Book:**
![Delete Book](Screenshots/deleteBook.png)

---

## Question 2: Student Registration API

**Base URL:** `/api/students`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/students` | Get all students |
| `GET` | `/api/students/{studentId}` | Get student by ID |
| `GET` | `/api/students/major/{major}` | Get students by major |
| `GET` | `/api/students/filter?gpa={min}` | Filter students by GPA |
| `POST` | `/api/students` | Register a new student |
| `PUT` | `/api/students/{studentId}` | Update student information |

### 📸 Testing Evidence

**Get All Students:**
![Get All Students](Screenshots/getAllStudents.png)

**Create Student:**
![Create Student](Screenshots/createStudent.png)

**Get One Student:**
![Get One Student](Screenshots/getOneStudent.png)

**Filter by Major:**
![Filter By Major](Screenshots/filterByMajor.png)

**Filter by GPA:**
![Filter By GPA](Screenshots/filterByGpa.png)

**Update Student:**
![Update Student](Screenshots/updateStudent.png)

---

## Question 3: Restaurant Menu API

**Base URL:** `/api/menu`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/menu` | Get all menu items |
| `GET` | `/api/menu/{id}` | Get specific menu item |
| `GET` | `/api/menu/category/{category}` | Get items by category |
| `GET` | `/api/menu/available` | Get only available items |
| `GET` | `/api/menu/search?name={name}` | Search items by name |
| `POST` | `/api/menu` | Add new menu item |
| `PUT` | `/api/menu/{id}/availability` | Toggle item availability |
| `DELETE` | `/api/menu/{id}` | Remove menu item |

### Testing Evidence

**Get All Menu Items:**
![Get All Menu](Screenshots/getAllMenu.png)

**Add Menu Item:**
![Add Item](Screenshots/addItem.png)

**Get One Item:**
![Get One Item](Screenshots/getOneItem.png)

**Get by Category:**
![Get By Category](Screenshots/getByCategory.png)

**Get Available Items:**
![Get Available](Screenshots/getAvailableItem.png)

**Search by Name:**
![Search By Name](Screenshots/searchByName.png)

**Toggle Availability:**
![Toggle Availability](Screenshots/togglAvailablity.png)

**Delete Item:**
![Delete Item](Screenshots/deleteItem.png)

---

## Question 4: E-Commerce Product API

**Base URL:** `/api/products`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get product details |
| `GET` | `/api/products/category/{cat}` | Get products by category |
| `GET` | `/api/products/brand/{brand}` | Get products by brand |
| `GET` | `/api/products/search?keyword={key}`| Search products by keyword |
| `GET` | `/api/products/price-range` | Filter by price range (min & max) |
| `GET` | `/api/products/in-stock` | Get in-stock items |
| `POST` | `/api/products` | Add new product |
| `PUT` | `/api/products/{id}` | Update product details |
| `PATCH`| `/api/products/{id}/stock` | Update stock quantity |
| `DELETE`| `/api/products/{id}` | Delete product |

### Testing Evidence

**Get All Products:**
![Get All Products](Screenshots/getAllProducts.png)

**Add Product:**
![Add Product](Screenshots/addProduct.png)

**Get One Product:**
![Get One Product](Screenshots/getOneProduct.png)

**Filter by Category:**
![Filter By Category](Screenshots/filterByCategory.png)

**Filter by Brand:**
![Filter By Brand](Screenshots/filterByBrand.png)

**Search by Keyword:**
![Search By Keyword](Screenshots/searchByKeyword.png)

**Filter by Price Range:**
![Filter By Price Range](Screenshots/filterByPriceRange.png)

**Get In Stock:**
![Get In Stock](Screenshots/getInStock.png)

**Update Stock (Patch):**
![Update Stock](Screenshots/updateStock.png)

**Delete Product:**
![Delete Product](Screenshots/deleteProduct.png)

---

## Question 5: Task Management API

**Base URL:** `/api/tasks`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/tasks` | Get all tasks |
| `GET` | `/api/tasks/{id}` | Get task by ID |
| `GET` | `/api/tasks/status` | Get tasks by completion status |
| `GET` | `/api/tasks/priority/{level}` | Get tasks by priority |
| `POST` | `/api/tasks` | Create new task |
| `PUT` | `/api/tasks/{id}` | Update task |
| `PATCH`| `/api/tasks/{id}/complete` | Mark task as completed |
| `DELETE`| `/api/tasks/{id}` | Delete task |

### Testing Evidence

**Get All Tasks:**
![Get All Tasks](Screenshots/getAllTasks.png)

**Create Task:**
![Create Tasks](Screenshots/createTasks.png)

**Get One Task:**
![Get One Task](Screenshots/getOneTask.png)

**Get Completed Tasks:**
![Get Completed Tasks](Screenshots/getCompletedTasks.png)

**Get High Priority Tasks:**
![Get High Priority](Screenshots/getHighPriorityTasks.png)

**Mark as Complete:**
![Mark As Complete](Screenshots/markAsComplete.png)

**Delete Task:**
![Delete Task](Screenshots/deleteTask.png)

---

## Bonus Question: User Profile API

**Base URL:** `/api/users`
*Note: These endpoints utilize a custom `ApiResponse` wrapper for consistent JSON output.*

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/users` | Get all users |
| `GET` | `/api/users/{id}` | Get user by ID |
| `GET` | `/api/users/search/country` | Search by country |
| `GET` | `/api/users/search/age` | Search by age range |
| `POST` | `/api/users` | Create user |
| `POST` | `/api/users/{id}/deactivate` | Deactivate user |
| `POST` | `/api/users/{id}/activate` | Activate user |

### Testing Evidence

**Get All Users:**
![Get All Users](Screenshots/getAllUsers.png)

**Create User:**
![Create User](Screenshots/createUser.png)

**Get One User:**
![Get One User](Screenshots/getOneUser.png)

**Search by Country:**
![Search By Country](Screenshots/searchByCountry.png)

**Search by Age:**
![Search By Age](Screenshots/searchByAge.png)

**Deactivate User:**
![Deactivate User](Screenshots/deactivateUser.png)
