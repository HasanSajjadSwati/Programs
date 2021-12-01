
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hasan
 */
public class Server {
    
    public static void main(String[] args) throws IOException {
       
       ServerSocket server = new ServerSocket(8081);
       server.setReuseAddress(true);
       System.out.println("Server Started!");
       Socket client = server.accept();
       System.out.println("New Client Connected!");
       PrintWriter out = new PrintWriter(client.getOutputStream(), true);
       
       while(true){
           out.println("I am Alive");
       }
    }
}
