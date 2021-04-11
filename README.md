
# TodoList App with Room

Here is some brief explanation about Room

## Room
Room is a part of Jetpack Library that provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
In short, Room allows us to do some queries (like basic CRUD) easily. 
<br/>

## Room Components
Room has 3 main components:
<ol>
	<li>Database</li>
	<li>DAO (Data Access Object)</li>
	<li>Entity</li>
</ol>

### Database
Holds the **database** and serves as the **main access point** for the underlying connection to your app's persisted data. Simply, database class is a center of our database schema. To tell our app that this class is act as a Room Database, we should add annotation **@Database** above our class. And remember, our Database class should be an **abstract class**. Database class knows all the **entities, DAOs, database version,** and so on.


### DAO (Data Access Object)
Provide methods that your app can use to **query, update, insert, and delete data** in the database. DAO is basically an **interface**, annotated by **@Dao**, and has a **method to execute the query** by using **corresponding annotation**.

### Entity
Represents the table in your database. Entity is basically **data class** (in kotlin, or POJO in Java) that annotated by **@Entity** to tell our application that this class act as an entity. The **property / variable** represents the **column** in the correspond table.

## Room Schema
<img src="https://developer.android.com/images/training/data-storage/room_architecture.png" />

Source: https://developer.android.com/training/data-storage/room
