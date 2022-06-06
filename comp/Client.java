package comp;
import java.util.*;

import javax.imageio.IIOException;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;


public class Client {
    private static String user = "";
    Socket socket = null;
    FileInputStream fin = null;

    ObjectInputStream in;    DataOutputStream dout = null;
    DataInputStream din = null;
    ObjectOutputStream out;

    public Client(){
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("192.168.143.52", 8085);

            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream()); 

            // File file = new File("D:\\CN\\Project\\dump\\data.txt");
            // int choice;
            // System.out.println("Enter 0 for Registeration and enter 1 to login");
            // choice = sc.nextInt();
            // if (choice == 0) {
            //     register(out, in);
            // } else if (choice == 1) {
            //     login(out, in);

            //     System.out.println("Enter 0 to Upload, enter 1 to Download or enter 2 to delete: ");
            //     choice = sc.nextInt();
            //     sc.nextLine();

                // try {
                //     out.writeObject(choice);
                // } catch (IOException e) {
                //     e.printStackTrace();
                // }
            //     // upload(out,in,dout);
            //     if (choice == 0) {
            //         upload(out, in, dout);
            //     } else if (choice == 1) {
            //         download(out, in, din);
            //     } else if (choice == 2) {
            //         delete(out, in);
            //     }
            // }
            // socket.close();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Exception!");
        }
    }

    public static void main(String[] args) {
        
        
    }

    public void delete(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file name to delete: ");
        String fname = sc.nextLine();
        try{
            out.writeObject(fname);
            Integer status = (Integer) in.readObject();
            if(status == 0){
                System.out.println("Failed to delete the file");
            }else if(status == 1){
                System.out.println("Deleted the file!");
            }
        }catch(IOException e){ 
            e.printStackTrace();
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

    public void upload(){
        try {
            out.writeObject(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // File file = new File("D:\\CN\\Project\\dump\\data.pdf");
        // FileInputStream fin = null;
        // String fname = file.getName();
        // try {
        //     out.writeObject(fname);
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        // // System.out.println(file.getTotalSpace());
        // // System.out.println(file.length());
        // Long len = file.length();
        // try{
        //     fin = new FileInputStream(file);
        //     out.writeObject(len);
            
        //     byte[] buffer = new byte[Functions.buffer_size(len)];

        //     int count;
        //     while ((count = fin.read(buffer)) > 0) {
        //         dout.write(buffer, 0, count);
        //     }

        // }catch(IOException e){
        //     e.printStackTrace();
        // }
        
        // try{
        //     Integer i = (Integer) in.readObject();
        //     if(i==0){
        //         System.out.println("No more space available! Failed to upload file!s");
        //     }else{
        //         System.out.println("Uploaded!");
        //     }
        //     fin.close();
        // }catch(IOException e){
        //     e.printStackTrace();
        // }catch(ClassNotFoundException ce){
        //     ce.printStackTrace();
        // }
    }


    public int try_upload(String path){
        File file = new File(path);
        FileInputStream fin = null;
        String fname = file.getName();
        Integer i =0;
        try {
            out.writeObject(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(file.getTotalSpace());
        // System.out.println(file.length());
        Long len = file.length();
        try {
            fin = new FileInputStream(file);
            out.writeObject(len);

            i = 0;
            try {
                i = (Integer) in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ce) {
                ce.printStackTrace();
            }

            byte[] buffer = new byte[Functions.buffer_size(len)];

            int count;            if(i==1){
                while ((count = fin.read(buffer)) > 0) {
                    dout.write(buffer, 0, count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return i;
    }

    public void download() {
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

            fout.close();
            
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

    public void register(){
        try{
            out.writeObject(0);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
    }


    public int try_register(String userName, String pass, String email, String purpose){
  
        String[] data = { userName, pass, email, purpose };
        // clientData Clientdata= new clientData(userName, pass, purpose);
        // //dout=new DataOutputStream(socket.getOutputStream());
        // out.writeObject(Clientdata);]
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

        return status;
    }



    public void login(){
        String userName;
        String pass;
        Scanner sc = new Scanner(System.in);
        try {
            out.writeObject(1 );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // while(true){
        //     System.out.println("Enter user name");
        //     userName = sc.next();
        //     System.out.println("Enter password");
        //     pass = sc.next();
        //     String[] data = { userName, pass };
        //     try {
        //         out.writeObject(data);
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }

        //     Integer status = 1;
        //     try {
        //         status = (Integer) in.readObject();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     } catch (ClassNotFoundException ce) {
        //         ce.printStackTrace();
        //     }
        //     System.out.println(status);
        //     if (status == 0) {
        //         System.out.println("Invalid data, please try again!");
        //     } else {
        //         user = data[0];
        //         break;
        //     }

        // }
    }

    public int try_login(String user,String pass){
        String[] data = { user, pass };
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

        return status;
    }
}
