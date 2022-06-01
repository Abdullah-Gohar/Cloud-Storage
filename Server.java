import java.net.*;
import java.util.*;
import java.io.*;

class Server{
    public static void main(String[] args) {
        ServerSocket server = null;
        FileOutputStream fout = null;
        DataInputStream din  = null;
        DataOutputStream dout = null;
        ObjectOutputStream out  = null;
        try{
            server = new ServerSocket(8080);
        }
        catch(IOException e){
            e.printStackTrace();
        } 
        
        
        while(true){
            Socket client = null;
            try{
            
                client = server.accept();
                ClientHandler handler = new ClientHandler(client);
                Thread t = new Thread(handler);
                t.start();
                
            }
            catch(Exception e){
                System.out.println("Exception!");

            }
        }
    }
}
