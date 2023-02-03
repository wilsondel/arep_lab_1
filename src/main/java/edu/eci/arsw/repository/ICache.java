package edu.eci.arsw.repository;

public interface ICache {

    /**
     * Save a request with its response
     * @param request
     * @param response
     */
    static void saveQuery(String request, String response) {
    }

    /**
     * Validate if it has a request
     * @param request
     * @return
     */
    static boolean hasQuery(String request) {
        return false;
    }

    /**
     * @param request
     * @return a response of the request
     */
    static String getQuery(String request) {
        return request;
    }


}
