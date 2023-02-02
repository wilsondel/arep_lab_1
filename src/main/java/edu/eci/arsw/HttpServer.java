package edu.eci.arsw;

import edu.eci.arsw.repository.Cache;
import edu.eci.arsw.service.HttpConnection;

import java.net.*;
import java.io.*;
public class HttpServer {


public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {

            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;

            boolean flagForPath = true;
            String path = "/?name=test";
            while ((inputLine = in.readLine()) != null) {
                if (flagForPath) {
                    flagForPath = false;
                    path = inputLine.split(" ")[1];
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            URL basePathForm = new URL("http://www.localhost:35000" + path); //http://www.localhost:35000/?t=indiana
            String baseQueryForm = basePathForm.getQuery();

            boolean flag = false;
            if (basePathForm.getPath().contains("infomovie")) {
                flag = true;
            }

            String apiResponse = "";
            if (baseQueryForm != null) {
                if (Cache.hasQuery(baseQueryForm)) {
                    apiResponse = Cache.getQuery(baseQueryForm);
                } else {
                    apiResponse = HttpConnection.getAPIInfo(baseQueryForm);
                    Cache.saveQuery(baseQueryForm,apiResponse );
                }

            }

            outputLine = jsonSimple(apiResponse);
//            if (!flag) outputLine = htmlWithForms(apiResponse);
//            else outputLine = jsonSimple(apiResponse);

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    public static String jsonSimple(String jsonResponse) {
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html" +
                "\r\n"
                +
                jsonResponse;
    }


    public static String htmlWithForms(String apiResponse) {
        System.out.println("RESPOINSEEE :" + apiResponse);

        return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html" +
                "\r\n"
                +
                "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Movie´s information</title>\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding: 0;\">\n" +
                "\n" +
                "<main class=\"main\" style=\"background: rgb(179,153,212);background: linear-gradient(90deg, rgba(179,153,212,1) 0%, rgba(88,166,187,1) 47%, rgba(71,214,244,1) 100%);\">\n" +
                "\n" +
                "    <form action=\"localhost:35000\" onsubmit=\"return false\" class=\"form\" style=\"  display: flex;justify-content: center;align-items: center;background-color:rgba(255, 255, 255, 0.6);border-radius: 15px;width: 35%;height: 20%;margin-top: 10px; \">\n" +
                "        <div style=\"  \" >\n" +
                "            <p>Movie Name</p>\n" +
                "            <div>\n" +
                "            <label for=\"t\"></label>\n" +
                "                <input type=\"text\" name=\"t\" id=\"t\">\n" +
                "            </div>\n" +
                "            <input type=\"button\" value=\"Get information\" onclick=\"loadGetMsg()\">\n" +
                "        </div>\n" +
                "    </form>\n" +
                "    <div id=\"getrespmsg\" style=\" \" >\n" + apiResponse +
                "\n" +
                "    </div>\n" +
                "</main>" +
                "\n" +
                "<script>\n" +
                "    function loadGetMsg() {\n" +
                "        let nameVar = document.getElementById(\"t\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n"  +
                "        xhttp.onload = function() {\n" +
                "            document.getElementById(\"getrespmsg\").innerHTML= \n"  +
                "                this.responseText;\n" +
                "        }\n" +
                "        xhttp.open(\"GET\", \"/?t=\"+nameVar);\n" +
                "        xhttp.send();\n" +
                "    }\n" +
                "</script>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

    }


}