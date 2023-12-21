package servers;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author harish.kumar-mbp
 * @created 23/04/23
 */
public class SampleTCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Server only receive a request once. It wait for a client to make connection.
     * accept call is blocking system call. It reads multiple requests for a client.
     * Will sequentially read the client requests
     * @param port
     */
    public void startOpenAlways(int port) {
        try{
            serverSocket = new ServerSocket(port);

            while(true){
                System.out.println("waiting for client");
                clientSocket = serverSocket.accept();
                //out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();
                System.out.println("processing request");
                Thread.sleep(10000);
                String greeting = in.readLine();
                System.out.println(greeting);
//                while (!greeting.isEmpty()) {
//                    System.out.println(greeting);
//                    greeting = in.readLine();
//                }
                String http = "GET / HTTP/1.0\r\n";
                String host = "Host: localhost\r\n";
                String outGreet = "hello client\r\n";
                String outGreet2 = "unrecognised greeting\r\n";
                if ("hello server".equals(greeting)) {
                    System.out.println("Sending response 1");
                    out.write(http.getBytes("UTF-8"));
                    out.write(host.getBytes(StandardCharsets.UTF_8));
                    out.write(outGreet.getBytes(StandardCharsets.UTF_8));
                }
                else {
                    System.out.println("Sending response 2");
                    out.write(outGreet2.getBytes("UTF-8"));
                }
                clientSocket.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void startOpenAlwaysMultipleClients(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("Waiting for a client to connect");
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void startForSingleClientRequest(int port) {
        try{
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
            System.out.println(greeting);
            if ("hello server".equals(greeting)) {
                //out.println("hello client");
            }
            else {
                System.out.println("unrecognised greeting");
            }
        }catch (Exception e){
            out.println(e.getMessage());
        }
    }

    public void stop() {
        try{
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        }catch(Exception e) {
            out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        SampleTCPServer server=new SampleTCPServer();
        server.startOpenAlways(8080);
    }

    private static class ServerThread extends Thread {
        Socket socket;
        private OutputStream out;
        private BufferedReader in;
        public ServerThread(Socket socket){
            this.socket = socket;
        }
        public void run(){
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //out = new PrintWriter(socket.getOutputStream(), true);
                out = socket.getOutputStream();
                String greeting = in.readLine();
                System.out.println("processing request");
                //out.write(greeting.getBytes(StandardCharsets.UTF_8));
                System.out.println("Sending response 0");
                while (!greeting.isEmpty()) {
                    System.out.println(greeting);
                    greeting = in.readLine();
                }
                String http = "GET / HTTP/1.0\r\n";
                String host = "Host: localhost\r\n";
                String outGreet = "hello client\r\n";
                String outGreet2 = "unrecognised greeting\r\n";
                if ("hello server".equals(greeting)) {
                    System.out.println("Sending response 1");
                    out.write(http.getBytes("UTF-8"));
                    out.write(host.getBytes(StandardCharsets.UTF_8));
                    out.write(outGreet.getBytes(StandardCharsets.UTF_8));
                }
                else {
                    System.out.println("Sending response 2");
                    out.write(outGreet2.getBytes("UTF-8"));
                }
                Thread.sleep(10000);
                socket.close();
                System.out.println("closed connection");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
