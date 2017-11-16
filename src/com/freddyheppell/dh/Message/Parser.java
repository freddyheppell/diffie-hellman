package com.freddyheppell.dh.Message;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Parser {
    public static Map<String, String> parse(String data) {
        Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
        return gson.fromJson(data, stringStringMap);
    }
}
