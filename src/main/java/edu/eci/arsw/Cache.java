package edu.eci.arsw;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    private static Map<String, String> cache= new HashMap<String, String>();

    public static void saveQuery(String request,String response) {
        cache.put(request,response);
    }

    public static boolean hasQuery(String request) {
        return  cache.containsKey(request);
    }

    public static String getQuery(String request) {
        return  cache.get(request);
    }





}
