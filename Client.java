import java.util.*;

import javax.imageio.IIOException;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;


public class Client {
    private static String user = "";
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
                login(out,in);
            

                System.out.println("Enter 0 to Upload and enter 1 to Download");
                choice = sc.nextInt();
                sc.nextLine();

                try{
                    out.writeObject(choice);
                }catch(IOException e){
                    e.printStackTrace();
                }
                // upload(out,in,dout);
                if (choice == 0) {
                    upload(out,in,dout);
                } else if (choice == 1) {
                    download(out,in,din);
                }
            }
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!");
        }
    }


    public static void upload(ObjectOutputStream out,ObjectInputStream in, DataOutputStream dout){
        File file = new File("D:\\CN\\Project\\dump\\data.pdf");
        FileInputStream fin = null;
        String fname = file.getName();
        try {
            out.writeObject(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(file.getTotalSpace());
        // System.out.println(file.length());
        Long len = file.length();
        try{
            fin = new FileInputStream(file);
            out.writeObject(len);
            
            byte[] buffer = new byte[Functions.buffer_size(len)];

            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            System.out.println("Loop Exit");
            System.out.println((String) in.readObject());
            System.out.println("Got it!");
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

    public static void download(ObjectOutputStream out, ObjectInputStream in, DataInputStream din) {
        try {
            String fname = (String) in.readObject();
            Long len = (Long) in.readObject();
            File file = new File(String.format("D:\\CN\\Project\\%s", fname));

            FileOutputStream fout = new FileOutputStream(file);
            byte[] buffer = new byte[Functions.buffer_size(len)];
            int count;
            int i = 0;
            while (file.length() < len) {
                count = din.read(buffer);
                fout.write(buffer, 0, count);

                i++;
            }
            System.out.println("Files");
            String str = "File Recieved!";
            out.writeObject(str);
            System.out.println("Files2");

        } catch (IOException e) {
            e.printStackTrace();
            // System.out.println(e.getStackTrace());
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
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




    public static void login(ObjectOutputStream out, ObjectInputStream in){
        String userName;
        String pass;
        Scanner sc = new Scanner(System.in);
        try {
            out.writeObject(1 );
        } catch (IOException e) {
            e.printStackTrace();
        }

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

            Integer status = 1;
            try {
                status = (Integer) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce) {
                ce.printStackTrace();
            }
            System.out.println(status);
            if (status == 0) {
                System.out.println("Invalid data, please try again!");
            } else {
                user = data[0];
                break;
            }

        }
    }
}
