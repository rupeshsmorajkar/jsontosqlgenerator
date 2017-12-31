package com.mycompany.jsontosqlgenerator;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.mycompany.jsontosqlgenerator.model.Products;

/**
 * Hello world!
 *
 */
public class App {
	private static String path = JSONToSQLGenerator.class.getClassLoader().getResource("").getPath();

	public static void main(String[] args) {
		Gson gson = new Gson();
		try (FileReader fileReader = new FileReader(path + "/oshop-68414-export_single_table.json")) {
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
