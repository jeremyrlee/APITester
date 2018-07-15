package com.example.webjson;

import com.example.webjson.data.ResultData;
import com.example.webjson.parsers.IJsonParser;
import com.example.webjson.parsers.JacksonJsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class StackQuery {
	private static final String BASE_URL = "https://api.stackexchange.com/2.2/search?";
	private static final String PARAM_SITE = "site=";
	private static final String PARAM_PAGE_SIZE = "&pagesize=";
	private static final String PARAM_ORDER = "&order=";
	private static final String PARAM_SORT = "&sort";
	private static final String PARAM_TAG = "&tagged=";
	private static final String PARAM_FILTER = "&filter=";
	private static final String PARAM_SEARCH_TERM = "&intitle=";
	
	private String site = "stackoverflow";
	private int pageSize = 5;
	private SortOrder sortOrder = SortOrder.ASCENDING;
	private SortBy sortBy = SortBy.RELEVANCE;
	private String tag = "java";
	private String searchTerm = "";
	
	public enum SortOrder {
		ASCENDING   ("asc"), 
		DESCENDING  ("desc");
		
		private final String key;
		SortOrder(String key) { this.key = key; }
		
		public String toString() { return key; }
	}

	public enum SortBy {
		RELEVANCE	("relevance"), 
		VOTES		("votes"), 
		CREATION	("creation"), 
		ACTIVITY	("activity");
		
		private final String key;
		SortBy(String key) { this.key = key; }
		
		public String toString() { return key; }
	}

	public URL buildUrl() throws MalformedURLException {
		StringBuilder urlString = new StringBuilder();
		urlString.append(BASE_URL);
		urlString.append(PARAM_SITE).append(this.site);
		urlString.append(PARAM_PAGE_SIZE).append(this.pageSize);
		urlString.append(PARAM_ORDER).append(this.sortOrder);
		urlString.append(PARAM_SORT).append(this.sortBy);
		urlString.append(PARAM_TAG).append(this.tag);
		urlString.append(PARAM_SEARCH_TERM).append(this.searchTerm);
		
		URL url = new URL(urlString.toString());

		try {
			URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
			url = uri.toURL();
		} catch (URISyntaxException e) {
			throw new MalformedURLException(e.getMessage());
		}

		return url;
	}

	public List<ResultData> execute() throws IOException {
	    //create a URL
        URL url = buildUrl();

        // connect to a server
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
		if ("gzip".equals(connection.getContentEncoding())) {
			in = new GZIPInputStream(in);
		}

		List<ResultData> result;
		IJsonParser parser = new JacksonJsonParser();
		result = parser.parseJson(in);
		in.close();

        // get information back
        // return data
	    return result;
    }

	public void setSite(String site) {
		this.site = site;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setSortBy(SortBy sortBy) {
		this.sortBy = sortBy;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
