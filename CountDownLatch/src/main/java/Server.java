
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
       ServerSocket server = null;
       CountDownLatch startSignal = new CountDownLatch(5);
        try {
            server = new ServerSocket(8081);
            server.setReuseAddress(true);
            while(true){
                System.out.println("Server Started!");
                Socket client = server.accept();
                System.out.println("New Client Connected!");
                
                new Thread(new ClientHandler(client,startSignal)).start();
                
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
    private final CountDownLatch startSignal;
    
    public ClientHandler(Socket socket, CountDownLatch startSignal){
        this.clientSocket = socket;
        this.startSignal = startSignal;
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
                        startSignal.await(5, TimeUnit.SECONDS);
                        out.println("Goodbye!");
                        exitStatus = false;
                    }
                        
                    else
                    {
                        startSignal.await(5, TimeUnit.SECONDS);
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