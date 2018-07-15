package com.example.webjson.parsers;

import com.example.webjson.data.QueryResultBean;
import com.example.webjson.data.StackItems;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JacksonJsonParser implements IStackJsonParser {

    public static void main(String[] args) {
        IStackJsonParser parser = new JacksonJsonParser();
        try (FileInputStream in = new FileInputStream("JSON Example.js")) {
            List<QueryResultBean> results = parser.parseJson(in);
            for (QueryResultBean result : results) {
                System.out.println(result.getTitle());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<QueryResultBean> parseJson(InputStream in) throws IOException {
        StackItems items = JSON.std.beanFrom(StackItems.class, in);
        return items.getItems();
    }
}