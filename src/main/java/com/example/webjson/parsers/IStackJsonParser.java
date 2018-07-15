package com.example.webjson.parsers;

import com.example.webjson.data.QueryResultBean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IStackJsonParser {
    List<QueryResultBean> parseJson(InputStream in) throws IOException;
}
