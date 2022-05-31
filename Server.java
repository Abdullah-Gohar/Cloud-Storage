import java.net.*;
import java.util.*;
import java.io.*;

class Server{
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        FileOutputStream fout = null;
        DataInputStream din  = null;
        try{
            server = new ServerSocket(8080);
            client = server.accept();
            din = new DataInputStream(client.getInputStream());
            File file = new File("D:\\CN\\Project\\data.txt");
            fout = new FileOutputStream(file);
            byte[] buffer = new byte[4*1024];
            int count;
            while((count = din.read(buffer))>0){
                fout.write(buffer,0,count);
            }
            client.close();
            server.close();
        }
        catch(Exception e){

        }
    }
}