# My Blog (Spring MVC App)
This is a simple educational blog web application built with **Java 21** and **Spring Framework** (no Spring Boot).  
The app supports full CRUD for blog posts, image uploads, likes, tags, comments, and pagination.  
Manually configured and packaged as a WAR file for deployment to a servlet container (Tomcat 10.1+).

## Technologies:
- Java 21
- Spring Framework 6 (WebMVC, Spring Test)  
- Thymeleaf 3  
- Maven  
- Apache Tomcat 10.1+  
- H2 Database (for tests)  
- JUnit 5 + Mockito

## Project Structure 
- MVC layered architecture (`Controller`, `Service`, `DAO`)
- Manual configuration (`web.xml`, `DispatcherServlet`, etc.)
- WAR packaging for servlet container deployment
- Image upload support (`MultipartFile`)
- Full CRUD for blog posts, with tags, likes, comments, and pagination

## Features 
- Homepage and post list  
- Full CRUD: create, edit, delete blog posts  
- Add/edit/delete comments  
- Like/dislike system  
- Tag support  
- Pagination and search  
- Image upload  

## Build WAR
- Use Maven to compile the project and generate a WAR file:
``bash mvn clean package``
- The WAR file will be located at:
``target/spring-web-myblog.war``

## Deploy to Tomcat
- Install **Apache Tomcat 10.1 or higher**  
-  Copy the WAR file to Tomcat's deployment directory:
``bash cp target/spring-web-myblog.war /path/to/tomcat/webapps/``
- Start Tomcat:
``bash cd /path/to/tomcat/bin./startup.sh``
- Open the app in your browser:
``http://localhost:8080/spring-web-myblog/``
- To change the context path, rename the WAR file or configure `conf/server.xml`.

## Running Tests
This project includes unit and integration tests for:
- Controllers (Spring MVC)
- Service layer logic
- DAO/model layer
- Run all tests using:
``bash mvn test``

## Usage
- Navigate to `/posts` to see the blog post list
- Click “Add Post” to create a new blog entry
- Edit or delete posts and comments via the UI
- Like/dislike posts
- Use the search bar and pagination controls
