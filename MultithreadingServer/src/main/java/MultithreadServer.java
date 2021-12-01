
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author hasan
 */
public class MultithreadServer {

    public static void main(String[] args){
        ServerSocket server = null;
        
        try {
            server = new ServerSocket(8081);
            server.setReuseAddress(true);
            while(true){
                System.out.println("Server Started!");
                Socket client = server.accept();
                System.out.println("New Client Connected!");
                ClientHandler clientSocket = new ClientHandler(client);
                
                Thread thread = new Thread(clientSocket);
                thread.start();
                
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        finally{
            if(server != null){
                try{
                    server.close();
                }catch (IOException e){
                    System.err.println(e);
                }
            }
        }
        
    }
    
}

class ClientHandler implements Runnable{
    private final Socket clientSocket;
    
    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }
    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {            
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            boolean exitStatus = false;
            
            while(!exitStatus){
                try {
                    
                    if(in.readLine().equalsIgnoreCase("exit")){
                        Thread.sleep(6000);
                        out.println("Goodbye!");
                        exitStatus = false;
                    }
                        
                    else
                    {
                        Thread.sleep(6000);
                        out.println("Response Received!");
                        System.out.println("Respose is sent after 5 seconds!");
                    }    
                    
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }
                   
        } catch (IOException ex) {
            System.err.println(ex);
        }
        finally{
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}