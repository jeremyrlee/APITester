package com.example.webjson.test;

import com.example.webjson.StackQuery;
import com.example.webjson.StackQuery.SortBy;
import com.example.webjson.StackQuery.SortOrder;
import com.example.webjson.data.ResultData;
import com.example.webjson.parsers.IJsonParser;
import com.example.webjson.parsers.JacksonJsonParser;
import com.example.webjson.parsers.JsonpJsonParser;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApiTest {

    @Test
	public void testConnection() throws IOException {

		try {
			StackQuery query = new StackQuery();

			query.setSearchTerm("Hello World");
			query.setSortBy(SortBy.RELEVANCE);
			query.setSortOrder(SortOrder.ASCENDING);

			System.out.println(query.buildUrl());
			List<ResultData> result = query.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	@Test
	public void testUrl() throws MalformedURLException {
    	StackQuery query = new StackQuery();

		query.setSearchTerm("Hello World");
		query.setSortOrder(SortOrder.ASCENDING);
		query.setSortBy(SortBy.RELEVANCE);

		URL url = query.buildUrl();
		String goodUrl = "https://api.stackexchange.com/2.2/search?" +
				"site=stackoverflow&pagesize=5&order=asc&sortrelevance&tagged=java&intitle=Hello%20World";
		assertEquals(goodUrl, url.toString());
	}

	@Test
	public void jsonpParserTest() throws IOException {
		IJsonParser parser = new JsonpJsonParser();
		try (FileInputStream in = new FileInputStream("JSON Example.js")) {
			List<ResultData> results = parser.parseJson(in);
			checkTestParse(results);
		}
	}

	public void checkTestParse(List<ResultData> results) {
		// test against local Json Example.js file
		assertNotNull(results);
		assertEquals(2, results.size());

		ResultData result1 = results.get(0);
		assertEquals("java", result1.getTags()[0]);
		assertEquals("urlconnection", result1.getTags()[1]);
		assertEquals(1234, result1.getOwner().getReputation());
		// ...etc.
	}



	@Test
	public void jacksonParserTest() throws IOException {
		IJsonParser parser = new JacksonJsonParser();
		try (FileInputStream in = new FileInputStream("JSON Example.js")) {
			List<ResultData> results = parser.parseJson(in);
			checkTestParse(results);
		}
	}

}
