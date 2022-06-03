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
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String fname = "dump.txt";
            String user = "";
            String pass = null;
            try{
                user = (String) in.readObject();
                pass = (String) in.readObject();
                fname = (String) in.readObject();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            System.out.println(user);
            new File(String.format("D:\\CN\\Project\\%s",user)).mkdir();
            File file = new File(String.format("D:\\CN\\Project\\%s\\%s", user,fname));


            FileOutputStream fout = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 1024];
            int count;
            int i =0;
            while ((count = din.read(buffer)) > 0) {
                fout.write(buffer, 0, count);
                i++;
                System.out.println(i);
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
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }


    }


}
