package com.freddyheppell.dh.Message;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ConnectedMessage implements Message{

    @Override
    public String toJSON() {
        Map<String, String> data = new HashMap<>();
        data.put("event", "connecting");
        return new Gson().toJson(data);
    }
}
