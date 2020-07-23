package com.zebone.core.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    public static Gson gson = new GsonBuilder()
            .registerTypeAdapter(int.class, new IntTypeAdapter())
            .registerTypeAdapter(Integer.class, new IntTypeAdapter())
            .registerTypeAdapter(Double.class,new DoubleAdapter())
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
}
