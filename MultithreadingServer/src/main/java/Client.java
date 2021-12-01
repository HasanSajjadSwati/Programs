
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hasan
 */
public class Client {
      
    public static void main(String[] args) throws IOException, InterruptedException {
        
        Socket socket = new Socket("127.0.0.1",8081);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Send Request To Client:");
        Scanner scanner = new Scanner(System.in);
        String inputToServer = null;
        
        while(!"exit".equalsIgnoreCase(inputToServer)){
            inputToServer = scanner.nextLine();
            waitingMessage message = new waitingMessage();
            out.println(inputToServer);
            Thread thread = new Thread(message);
            thread.start();
            
            if(inputToServer.equalsIgnoreCase("exit"))
                System.out.println(in.readLine());
            
            else
                System.out.println(in.readLine() + " Waited = " + message.getCounter() + "Seconds!");
            message.resetCounter();
            thread.stop();
        }
        scanner.close();
    }
    
    
}

class waitingMessage implements Runnable {
    private static int counter = 0;
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                counter++;
                System.out.println("I am waiting for response from server!");
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
}