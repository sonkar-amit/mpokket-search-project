## Prerequisite

- Install latest version of redis and run locally.
- Install latest version of mysql database and run locally.
- Create DB schema with name "search_schema".
- Run the ddl.sql file in database from here https://github.com/sonkar-amit/mpokket-search-project/blob/main/src/main/resources/SQL-Script/ddl.sql
- Java version should be greater than 8

###Details
- Run the application as springBoot, by default server will run on port 8080.

	####API
	**Create Document API**
	- Resource path : /api/document
	- Http Method : POST
	- Request Body : {"title": "t1", "author":"a1", "content":"c1"}
	- Sample curl request : 
			curl --location 'localhost:8080/api/document' \
				--header 'Content-Type: application/json' \
				--data '{
					"title": "",
					"author": "Amit",
					"content" : "The cache data repository doesn'\''t provide a function to find the key with a specific string; fortunately, we can add that function using Dynamic queries in the repository."
				}' 

 **Search Document API**
	- Resource path : /api/document/search?content={{value}}
	- Http Method : GET
	- Sample curl request : 
			curl --location 'localhost:8080/api/document/search?content=cach'


