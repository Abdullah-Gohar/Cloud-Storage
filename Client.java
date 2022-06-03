import java.util.*;
import java.net.*;
import java.io.*;


public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        FileInputStream fin = null;
        DataOutputStream dout = null;
        DataInputStream din = null;
        ObjectInputStream in;
        ObjectOutputStream out;
        try {
            socket = new Socket("localhost",8085);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            File file = new File("D:\\CN\\Project\\dump\\data1.txt");
            System.out.println("Enter Username: ");
            Scanner scn = new Scanner(System.in);
            String name = scn.nextLine();
            System.out.println("Enter Password: ");
            String pass = scn.nextLine();
            try{
                out.writeObject(name);
                out.writeObject(pass);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            String fname = file.getName();
            try {
                out.writeObject(fname);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fin = new FileInputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }
            socket.shutdownOutput();
            System.out.println("Loop Exit");
            System.out.println((String) in.readObject());
            System.out.println("Got it!");
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!");
        }
    }
}
