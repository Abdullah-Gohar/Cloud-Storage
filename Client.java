import java.util.*;
import java.net.*;
import java.io.*;


public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        FileInputStream fin = null;
        DataOutputStream dout = null;
        try {
            socket = new Socket("localhost",8080);
            dout = new DataOutputStream(socket.getOutputStream());
            File file = new File("D:\\CN\\Project\\data1.txt");
            fin = new FileInputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }
            socket.close();
        } catch (Exception e) {

        }
    }
}
