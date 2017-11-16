package com.freddyheppell.dh.Message;

import com.google.gson.Gson;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ExchangeValueMessage implements Message {

    private BigInteger value;

    public ExchangeValueMessage(BigInteger value) {
        this.value = value;
    }

    public String toJSON() {
        Map<String, String> data = new HashMap<>();
        data.put("exchange_value", value.toString());
        return new Gson().toJson(data);
    }
}
