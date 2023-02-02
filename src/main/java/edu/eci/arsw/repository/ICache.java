package edu.eci.arsw.repository;

public interface ICache {

    static void saveQuery(String request, String response) {
    }

    static boolean hasQuery(String request) {
        return false;
    }

    static String getQuery(String request) {
        return request;
    }


}
