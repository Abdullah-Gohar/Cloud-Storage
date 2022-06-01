import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket s){
        this.socket = s;
    }

    public void run(){
        try{
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            File file = new File("D:\\CN\\Project\\data.pdf");
            FileOutputStream fout = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int count;
            while ((count = din.read(buffer)) > 0) {
                fout.write(buffer, 0, count);
            }
            System.out.println("Files");
            String str = "File Recieved!";
            out.writeObject(str);
            System.out.println("Files2");
            out.close();
            dout.close();
            fout.close();
            din.close();
            socket.close();
        
        }
        catch(IOException e){
            System.out.println(e.getStackTrace());
        }


    }


}
