package com.beantastic.oauth;

import com.beantastic.utils.PropertiesLoader;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

public class GitHubOAuth {

    public static String getAccessToken() throws IOException, InterruptedException {

        String clientIdKey = "clientId";
        Properties clientIdProperty = PropertiesLoader.loadProperties();
        String clientId = clientIdProperty.getProperty(clientIdKey);

        String clientSecretKey = "clientSecret";
        Properties clientSecretProperty = PropertiesLoader.loadProperties();
        String clientSecret = clientSecretProperty.getProperty(clientSecretKey);
        
        String authorizationEndpoint = "https://github.com/login/oauth/authorize";
        String tokenEndpoint = "https://github.com/login/oauth/access_token";

        String redirectUri = "http://localhost:8080";

        String authorizationUrl;

        authorizationUrl = String.format("%s?client_id=%s&redirect_uri=%s", authorizationEndpoint,
                URLEncoder.encode(clientId, "UTF-8"), URLEncoder.encode(redirectUri, "UTF-8"));

        System.out.println("Please open the following URL in your browser and authorize the application:");
        System.out.println(authorizationUrl);

        SimpleHttpServer server = new SimpleHttpServer(8080);
        server.start();

        System.out.println("Waiting for authorization...");
        String authorizationCode = server.waitForAuthorization();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenEndpoint))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("client_id=%s&client_secret=%s&code=%s", clientId, clientSecret,
                                URLEncoder.encode(authorizationCode, "UTF-8"))))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        String accessToken = "";
        if (statusCode == 200) {
            String responseBody = response.body();
            accessToken = responseBody.split("&")[0].split("=")[1];
        } else {
            System.out.println("Failed to retrieve access token. Status code: " + statusCode);
        }

        server.stopServer();

        return accessToken;
    }
}

class SimpleHttpServer extends Thread {
    private final int port;
    private boolean running = true;
    private String authorizationCode;

    public SimpleHttpServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            java.net.ServerSocket serverSocket = new java.net.ServerSocket(port);
            while (running) {
                java.net.Socket socket = serverSocket.accept();
                Scanner scanner = new Scanner(socket.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains("GET /?code=")) {
                        authorizationCode = line.split("GET /\\?code=")[1].split(" ")[0];
                    }
                    if (line.isEmpty()) {
                        break;
                    }
                }
                String response = "HTTP/1.1 200 OK\r\n\r\n<html><body><h1>Authorization successful! You can close this window now.</h1></body></html>";
                socket.getOutputStream().write(response.getBytes("UTF-8"));
                socket.close();
                running = false;
                scanner.close();
            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String waitForAuthorization() {
        while (authorizationCode == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return authorizationCode;
    }

    public void stopServer() {
        running = false;
    }
}
