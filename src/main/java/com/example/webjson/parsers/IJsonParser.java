package com.example.webjson.parsers;

import com.example.webjson.data.ResultData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IJsonParser {
    List<ResultData> parseJson(InputStream in) throws IOException;
}
