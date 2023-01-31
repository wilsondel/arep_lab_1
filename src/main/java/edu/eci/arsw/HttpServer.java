package edu.eci.arsw;

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

            URL basePathForm = new URL("http://www.localhost:35000" + path);
            String baseQueryForm = basePathForm.getQuery();
//            System.out.println(baseQueryForm.getQuery());

            String apiResponse = "";
            if (baseQueryForm != null) {
//                System.out.println("IT DOES THE QUERY");
                if (Cache.hasQuery(baseQueryForm)) {
                    apiResponse = Cache.getQuery(baseQueryForm);
                } else {
                    apiResponse = HttpConnection.getAPIInfo(baseQueryForm);
                    Cache.saveQuery(baseQueryForm,apiResponse );
                }

            }


            outputLine = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html" +
                    "\r\n"
                    +
                    htmlWithForms(apiResponse);



            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }

        serverSocket.close();
    }

    public static String jsonSimple() {
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "{\"Title\":\"Indiana Jones and the Raiders of the Lost Ark\",\"Year\":\"1981\",\"Rated\":\"PG\",\"Released\":\"12 Jun 1981\",\"Runtime\":\"115 min\",\"Genre\":\"Action, Adventure\",\"Director\":\"Steven Spielberg\",\"Writer\":\"Lawrence Kasdan, George Lucas, Philip Kaufman\",\"Actors\":\"Harrison Ford, Karen Allen, Paul Freeman\",\"Plot\":\"Archaeology professor Indiana Jones ventures to seize a biblical artefact known as the Ark of the Covenant. While doing so, he puts up a fight against Renee and a troop of Nazis.\",\"Language\":\"English, German, Hebrew, Spanish, Arabic, Nepali\",\"Country\":\"United States\",\"Awards\":\"Won 4 Oscars. 38 wins & 24 nominations total\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNTU2ODkyY2MtMjU1NC00NjE1LWEzYjgtMWQ3MzRhMTE0NDc0XkEyXkFqcGdeQXVyMjM4MzQ4OTQ@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"8.4/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"96%\"},{\"Source\":\"Metacritic\",\"Value\":\"85/100\"}],\"Metascore\":\"85\",\"imdbRating\":\"8.4\",\"imdbVotes\":\"966,972\",\"imdbID\":\"tt0082971\",\"Type\":\"movie\",\"DVD\":\"13 May 2008\",\"BoxOffice\":\"$248,159,971\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
    }


    public static String htmlWithForms(String apiResponse) {

        return  "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Movie´s information</title>\n" +
                "    <style>\n" +
                "\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<form action=\"localhost:35000\" onsubmit=\"return false\" >\n" +
                "    <p>Movie´s Name</p>\n" +
                "    <label for=\"t\"></label>\n" +
                "    <input type=\"text\" name=\"t\" id=\"t\">\n" +
                "    <input type=\"button\" value=\"Get information\" onclick=\"loadGetMsg()\">\n" +
                "</form>\n" +
                "<div id=\"getrespmsg\">\n" + apiResponse +
                "\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "    function loadGetMsg() {\n" +
                "        let nameVar = document.getElementById(\"t\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n" +
                "        xhttp.onload = function() {\n" +
                "            document.getElementById(\"getrespmsg\").innerHTML =\n" +
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