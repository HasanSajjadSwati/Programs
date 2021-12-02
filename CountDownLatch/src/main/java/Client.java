
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class Client {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1",8081);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Send Request To Server:");
        Scanner scanner = new Scanner(System.in);
        String inputToServer = null;
        String outputFromServer = null;
            
   

        while(!"exit".equalsIgnoreCase(inputToServer)){
            inputToServer = scanner.nextLine();
            out.println(inputToServer);
            
            CountDownLatch latch = new CountDownLatch(1);
            waitingMessage message = new waitingMessage(latch);
            Thread thread = new Thread(message);
            thread.start();
            outputFromServer = in.readLine();
            
            
            if(outputFromServer.equalsIgnoreCase("response received!")){
                System.out.println("Response Received! Waited = " +message.getCounter() + " Seconds!" );
                message.setExit(true);
                message.resetCounter();
            }
            
            if(outputFromServer.equalsIgnoreCase("Goodbye!"))
            {
                System.out.println("Goodbye!");
                message.setExit(true);
            }
        }
        scanner.close();
    }
    
    
}

class waitingMessage implements Runnable {
    private static int counter = 0;
    private final CountDownLatch latch;
    private boolean exitStatus = false;
    
    public waitingMessage(CountDownLatch latch){
        this.latch = latch;
    }
    
    @Override
    public void run() {
        
        while(!exitStatus){
            try {
            
            System.out.println("I am waiting for response from server!");
            counter++;
            latch.await(1,TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.getStackTrace();
        }
        }
    }
    
    public int getCounter(){
        return counter;
    }
    public void resetCounter(){
        counter = 0;
    }
    public void setExit(Boolean exitStatus){
        this.exitStatus = exitStatus;
    }
}