package com.example.webjson;

import com.example.webjson.StackQuery.SortBy;
import com.example.webjson.StackQuery.SortOrder;

import java.io.IOException;

public class ApiTest {

	public void test1() {

		try {
			StackQuery query = new StackQuery();

			query.setSearchTerm("Hello World");
			query.setSortBy(SortBy.RELEVANCE);
			query.setSortOrder(SortOrder.ASCENDING);

			Object result = query.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
