import java.net.*;
import java.util.*;
import java.io.*;

class Server{
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        FileOutputStream fout = null;
        DataInputStream din  = null;
        DataOutputStream dout = null;
        ObjectOutputStream out  = null;
        try{
            server = new ServerSocket(8080);
            client = server.accept();
            System.out.println("Files");
            din = new DataInputStream(client.getInputStream());
            dout = new DataOutputStream(client.getOutputStream());
            out = new ObjectOutputStream(client.getOutputStream());
            File file = new File("D:\\CN\\Project\\data.pdf");
            fout = new FileOutputStream(file);
            byte[] buffer = new byte[4*1024];
            int count;
            while((count = din.read(buffer))>0){
                fout.write(buffer,0,count);
            }
            System.out.println("Files");
            String str = "File Recieved!";
            out.writeObject(str);
            System.out.println("Files2");
            client.close();
            server.close();
        }
        catch(Exception e){
            System.out.println("Exception!");
        }
    }
}