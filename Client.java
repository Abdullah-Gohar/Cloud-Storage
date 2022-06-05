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
        Scanner sc= new Scanner(System.in);
        try {
            socket = new Socket("localhost",8085);

            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            int choice;
            System.out.println("Enter 0 for Registeration and enter 1 to login");
            choice= sc.nextInt();
            if(choice==0) {
                String userName;
                String pass;
                String purpose;
                String email;
                System.out.println("Enter user name");
                userName= sc.next();
                System.out.println("Enter password");
                pass= sc.next();
                System.out.println("Enter email");
                email= sc.next();
                System.out.println("Enter purpose");
                purpose= sc.next();
                String[] data= {userName, pass,email,purpose};
                // clientData Clientdata= new clientData(userName, pass, purpose);
                // //dout=new DataOutputStream(socket.getOutputStream());
                // out.writeObject(Clientdata);
                out.writeObject(data);
            }
            /*File file = new File("D:\\CN\\Project\\data1.pdf");
            // System.out.println("Enter Username: ");
            // Scanner scn = new Scanner(System.in);
            // System.out.println("Enter Password: ");
            
            fin = new FileInputStream(file);

            //scn.nextLine();
            byte[] buffer = new byte[4 * 1024];
            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }
            socket.shutdownOutput();
            System.out.println("Loop Exit");
            System.out.println((String) in.readObject());
            System.out.println("Got it!");*/
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!");
        }
    }
}
