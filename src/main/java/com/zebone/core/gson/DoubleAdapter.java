package com.zebone.core.gson;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class DoubleAdapter extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter jsonWriter, Double aDouble) throws IOException {
        jsonWriter.value(aDouble);
    }

    @Override
    public Double read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        try {
            String result = jsonReader.nextString();
            if ("".equals(result)) {
                return null;
            }
            return Double.parseDouble(result);
        } catch (NumberFormatException e) {
            throw new JsonSyntaxException(e);
        }
    }
}
