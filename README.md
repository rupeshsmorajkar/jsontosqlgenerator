About

Generate SQL statements from JSON file.

Example:
{
 "productList": [
 	{
 		"id": "-KrqgOLs07ZkbapP4EGi",
 		"category": "vegetables",
 		"imageUrl": "http://www.xyz.com/mypic.jpg",
 		"price": 2.5,
 		"title": "Spinach"
 	}
 ]
}

will be converted to ->

INSERT INTO Products ( id, category, imageUrl, price, title ) 
  VALUES ( "-KrqgOLs07ZkbapP4EGi", "vegetables", "http://www.xyz.com/mypic.jpg", 2.5, "Spinach" );</code>


How To use?
Program can be executed directly from IDE or by generated JAR file.

JVM Argument - Absolute path of input JSON file.
java -jar jsontosqlgenerator-0.0.1-SNAPSHOT.jar <INPUT_JSON_FILE_PATH>

Important Points:
1. Component generates queries for one table at a time..
2. Current component generates only INSERT statement.
3. Component supports conversion of parent JSON record only. If your JSON structure contains child records then code changes are required 
4. Component requires VO/models to generate SQL. 

Licensing:
This project is licensed under the MIT license.
