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
        try {
            socket = new Socket("localhost",8083);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            in = new ObjectInputStream(socket.getInputStream());
            File file = new File("D:\\CN\\Project\\data1.pdf");
            Scanner scn = new Scanner(System.in);

            fin = new FileInputStream(file);

            scn.nextLine();
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
