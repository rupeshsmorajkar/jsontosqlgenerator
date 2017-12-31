package com.mycompany.jsontosqlgenerator;

import java.lang.reflect.Field;

public class QueryBuilder<T> {
	private T obj;

	private QueryType type;

	public QueryBuilder() {
	}

	public QueryBuilder(T param) {
		this.obj = param;
	}

	public QueryBuilder(QueryType type) {
		this.type = type;
	}

	public QueryBuilder(T param, QueryType type) {
		this.obj = param;
		this.type = type;
	}

	protected String generateQuery() {
		StringBuilder builder = new StringBuilder();

		if(QueryType.INSERT == this.type) {
			generateInsertQuery(builder);
		}

		return builder.toString();
	}

	protected void generateInsertQuery(StringBuilder builder) {
		builder.append("INSERT INTO ").append(obj.getClass().getSimpleName()).append(" ( "); 
		getColumnNamesFromType(builder);
		builder.append(" ) VALUES ( ");
		getColumnValuesFromType(builder);
		builder.append(" );");
	}

	protected void getColumnNamesFromType(StringBuilder builder) {
		Field[] flds = obj.getClass().getDeclaredFields();

		int counter = 0;
		for(Field field: flds) {
			if(counter != 0) {
				builder.append(", ");
			}

			builder.append(field.getName());
			counter++;
		}
	}

	protected void getColumnValuesFromType(StringBuilder builder) {
		Field[] flds = obj.getClass().getDeclaredFields();

		try {
			int counter = 0;
			Object value;
			for(Field field: flds) {
				field.setAccessible(true);
				value = field.get(obj);

				if(value != null) {
					if(counter != 0) {
						builder.append(", ");
					}

					setValueInQuery(builder, value);
					counter++;
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	protected void setValueInQuery(StringBuilder builder, Object value) {
		String val = value.toString();
		if(value instanceof String) {
			val = "\"" + val + "\"";
		}

		builder.append(val);
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}
}
