package com.mycompany.jsontosqlgenerator;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mycompany.jsontosqlgenerator.model.Products;

/**
 * Generate SQL statements from JSON file.
 * 
 * JVM Argument - Absolute path of input JSON file.
 * 
 * Program can be executed directly from IDE or by generated JAR file.
 * <pre> java -jar jsontosqlgenerator-0.0.1-SNAPSHOT-jar-with-dependencies.jar <INPUT_JSON_FILE_PATH></pre>
 * 
 * <br/> 
 * <b>Example:</b>
 * <pre>
 * {
 *	"productList": [
 *		{
 *			"id": "-KrqgOLs07ZkbapP4EGi",
 *			"category": "vegetables",
 *			"imageUrl": "http://www.publicdomainpictures.net/pictures/170000/velka/spinach-leaves-1461774375kTU.jpg",
 *			"price": 2.5,
 *			"title": "Spinach"
 *		}
 *	]
 * }
 * </pre>
 * 
 * <b>will be converted to -></b>
 *  
 * <pre>
 * INSERT INTO Products ( id, category, imageUrl, price, title ) 
 *    VALUES ( "-KrqgOLs07ZkbapP4EGi", "vegetables", "http://www.publicdomainpictures.net/pictures/170000/velka/spinach-leaves-1461774375kTU.jpg", 2.5, "Spinach" );</code>
 * </pre>
 * 
 * <b>Important Points - </b>
 * <br/>
 * 1. Component generates queries for one table at a time..
 * <br/>
 * 2. Current component generates only INSERT statement.
 * <br/>
 * 3. Component supports conversion of parent JSON record only. If your JSON structure contains child records then code changes are required 
 * <br/>
 * 4. Component requires VO/models to generate SQL. 
 * <br/>
 * 
 * @author RM
 * @version 0.1
 * 
 */

public class JSONToSQLGenerator {
	public static void main(String[] args) {
		if(args == null || args.length < 1) {
			System.out.println("Missing JVM argument -> input JSON file path.");
			return;
		}
		
		String path = args[0];
		Gson gson = new Gson();

		try (FileReader fileReader = new FileReader(path)) {
			
			ProductList products = gson.fromJson(fileReader, ProductList.class);
			if(products == null || products.getProductList() == null || products.getProductList().isEmpty())
				return;

			QueryBuilder<Products> queryBuilder = new QueryBuilder<>(QueryType.INSERT);
			for(Products p : products.getProductList()) {
				queryBuilder.setObj(p);

				System.out.println(queryBuilder.generateQuery());
			}
		} catch (JsonSyntaxException | JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
}
