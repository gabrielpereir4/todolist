Simple To-Do List CLI Application built in Java.
App uses MVC (Model-View-Controller) Architecture and SQLite database. 

Task is the only model class present in this project.
DatabaseManager is a static class responsible for initializing the database (if not already initialized)

TaskDAO (Data Access Object), responsible for Database Communication based on Task model;
and TaskController, Controller responsible for communication between Model and View, both apply the Singleton concept.

ViewManager is responsible for managing different CLI menus and options, and sending requests to Controller layer.