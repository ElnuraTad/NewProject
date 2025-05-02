# To-Do List RESTful API

## Requirements
Java 11 or higher

Maven

Running the Application
Clone the repository.

Run the command mvn clean install to install dependencies.

Run the command mvn spring-boot:run to start the application.

To connect to the database, open application.properties, set the database you want to use, and enter your username and password.

Using the API
Get a list of all tasks: GET /api/tasks

Get a task by ID: GET /api/tasks/{id}

Add a new task: POST /api/tasks

Update a task: PUT /api/tasks/{id}

Delete a task: DELETE /api/tasks/{id}
