import java.util.*;

import javax.imageio.IIOException;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            File file = new File("D:\\CN\\Project\\dump\\data.txt");
            int choice;
            System.out.println("Enter 0 for Registeration and enter 1 to login");
            choice = sc.nextInt();
            if (choice == 0){
                register(out,in);
            }
            else if(choice == 1){
                // login(out,in);
            }
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
            System.out.println(file.getTotalSpace());
            System.out.println(file.length());
            Long len = file.length();
            fin = new FileInputStream(file);

            out.writeObject(len);

            byte[] buffer = new byte[Functions.buffer_size(len)];
            
            fin = new FileInputStream(file);


            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }

            System.out.println("Loop Exit");
            System.out.println((String) in.readObject());
            System.out.println("Got it!");
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!");
        }
    }


    public static void register(ObjectOutputStream out,ObjectInputStream in){
        Scanner sc = new Scanner(System.in);
        int count;
        int i = 0;
        int choice;
        try{
            out.writeObject(0);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        String userName;
        String pass;
        String purpose;
        String email;
        while(true){
            System.out.println("Enter user name");
            userName = sc.next();
            System.out.println("Enter password");
            pass = sc.next();
            System.out.println("Enter email");
            email = sc.next();
            System.out.println("Enter purpose");
            purpose = sc.next();
            String[] data = { userName, pass, email, purpose };
            // clientData Clientdata= new clientData(userName, pass, purpose);
            // //dout=new DataOutputStream(socket.getOutputStream());
            // out.writeObject(Clientdata);]
            try{
                out.writeObject(data);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Integer status = 1;
            try {
                status = (Integer) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce){
                ce.printStackTrace();
            }

            if(status == 0){
                System.out.println("Username already taken, please choose another one: ");
            }
            else{
                break;
            }
        }
        
    }




    public void login(ObjectOutputStream out, ObjectInputStream in){
        String userName;
        String pass;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter user name");
            userName = sc.next();
            System.out.println("Enter password");
            pass = sc.next();
            String[] data = { userName, pass };
            try {
                out.writeObject(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
