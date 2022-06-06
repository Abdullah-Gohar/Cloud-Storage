import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.sql.*;

public class ClientHandler implements Runnable{
    
    private Socket socket;
    public static boolean status = true; 
    ArrayList UN = new ArrayList<>();
    //clientData Clientdata;
    String[] arr;
    private String user;
    public ClientHandler(Socket s){
        this.socket = s;
    }

    


    public void run(){
        try{
           
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            FileInputStream fin;
            String fname = "dump.txt";

            user = "";
            String pass = "";
            String filename = "";
            Long len = 0L;
            Integer choice = 0;

            try{
                choice = (Integer) in.readObject();
            }catch(IOException e){
                e.printStackTrace();
            }
            catch(ClassNotFoundException ce){
                ce.printStackTrace();
            }

            if (choice == 0) {
                register(out, in);
            } else if (choice == 1) {
                
                login(out,in);
                try {
                    choice = (Integer) in.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ce) {
                    ce.printStackTrace();
                }
                
                if (choice == 1) {
                    upload(out, in, dout);
                } else if (choice ==0) {
                    download(out, in, din);
                }
                // download(out, in, din);
                // upload(out,in,dout);

            }



            out.close();
            dout.close();
            // fout.close();
            din.close();
            socket.close();


            
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
    }

    public void upload(ObjectOutputStream out, ObjectInputStream in, DataOutputStream dout) {
        File file = new File("D:\\CN\\Project\\dump\\data.txt");
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
        try {
            fin = new FileInputStream(file);
            out.writeObject(len);

            byte[] buffer = new byte[Functions.buffer_size(len)];

            int count;
            while ((count = fin.read(buffer)) > 0) {
                dout.write(buffer, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Loop Exit");
            System.out.println((String) in.readObject());
            System.out.println("Got it!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }


    public void download(ObjectOutputStream out,ObjectInputStream in,DataInputStream din){
        int count;
        int i = 0;
        boolean status = true;
        Long len = 0L;
        File file;
        byte[] buffer;
        FileOutputStream fout = null;
        try{
            String fname = (String) in.readObject();
            len = (Long) in.readObject();
            file = new File(String.format("D:\\CN\\Project\\%s\\%s", user, fname));

            fout = new FileOutputStream(file);
            buffer = new byte[Functions.buffer_size(len)];
            
 
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=CloudStorage;userName=admin;password=123;trustServerCertificate=true");

            PreparedStatement statement = connection.prepareStatement("Select SpaceOcc from Client where UserName  = '"+user+"'");

            ResultSet result = statement.executeQuery();
            result.next();
            long total =Long.parseLong(result.getString("SpaceOcc"));
            if(total+len<2*1024*1024*1024){
                status = false;
                out.writeObject(-1);
            }
            connection.close();

            if (status){
                while (file.length() < len) {
                    count = din.read(buffer);
                    fout.write(buffer, 0, count);

                    i++;
                }
                try {
                    
                    statement = connection.prepareStatement("Update Client Set SpaceOcc = SpaceOcc+"
                            + String.format("%d", len) + " where UserName = '" + user + "'");

                    result = statement.executeQuery();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            

                System.out.println("Files");
                String str = "File Recieved!";
                out.writeObject(1);
                System.out.println("Files2");
                fout.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
                // System.out.println(e.getStackTrace());
            }
             catch (SQLException e) {
                e.printStackTrace();
             }
            catch(ClassNotFoundException ce){
            ce.printStackTrace();
            }
    } 

     






    public void register(ObjectOutputStream out, ObjectInputStream in){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://DESKTOP-IO2BR35:1433;databaseName=CloudStorage;userName=admin1;password=123;trustServerCertificate=true");

            PreparedStatement statement = connection.prepareStatement("select UserName from Client");

            ResultSet result = statement.executeQuery();
            while(true){
                System.out.println("kuch bhi dal do");
                try {
                    // Clientdata = (clientData) in.readObject();
                    arr = (String[]) in.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Connection connection = DriverManager.getConnection(
                // "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=Resort_DB;userName=admin;password=123;trustServerCertificate=true");

                while (result.next()) {
                    System.out.println(result.getString("UserName"));
                    UN.add(result.getString("UserName").trim());
                }
             if(UN.size()>=100){
                        System.out.println("Access Denied !!!! NO space !!!DAFA HO");
                        try {
                            out.writeObject(-1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                }
            else {
                // System.out.println(UN);
                if (UN.contains(arr[0])) {
                    try{
                        out.writeObject(0);
                        continue;
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } else {
                    int clientstat = 0;
                    int TotalSpace = 0;
                    // int occSpace = 0;
                    PreparedStatement statement1 = connection
                            .prepareStatement("Insert into Client values ('" + arr[0] + "','" + arr[1] + "','" + arr[2]
                                    + " ','" + arr[3] + "','" + clientstat + "','" + TotalSpace + "', DEFAULT)");
                    statement1.execute();
                    new File(String.format("D:\\CN\\Project\\%s", arr[0])).mkdir();
                    System.out.println("User addded");
                    try {
                        out.writeObject(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break; 


            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    public void login( ObjectOutputStream out, ObjectInputStream in){
        while(true){
            try {
                try {
                    // Clientdata = (clientData) in.readObject();
                    arr = (String[]) in.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Connection connection = DriverManager.getConnection(
               "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=CloudStorage;userName=admin;password=123;trustServerCertificate=true"); // "jdbc:sqlserver://DESKTOP-IO2BR35:1433;databaseName=CloudStorage;userName=admin1;password=123;trustServerCertificate=true");
                // Connection connection = DriverManager.getConnection(
                // "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");
                // "jdbc:sqlserver://5CB18B9:1433;databaseName=CloudStorage;userName=mishaltariq;password=123;trustServerCertificate=true");


                PreparedStatement statement = connection.prepareStatement("select UserName from Client");

                ResultSet result = statement.executeQuery();
                String password;

                while (result.next()) {

                    UN.add(result.getString("UserName").trim());
                }
                boolean status = true;
                // System.out.println(UN);
                if (UN.contains(arr[0])) {
                    System.out.println("ID exists");
                    //String userName= UN.indexOf(arr[0])
                    PreparedStatement statement1 = connection.prepareStatement("select Pass from Client where UserName='"+arr[0]+"'");
                    ResultSet result1 = statement1.executeQuery();
                    while (result1.next()) {

                        password=result1.getString("Pass");
                        if(arr[1].equals(password))
                        {
                            System.out.println("Login Successful!");
                        }
                        else
                        {
                            System.out.println("incorrect password!");
                            status = false;
                        }
                        
                    }
                } 
                else {
                    System.out.println("ID not found!");
                    status = false;
                }
                if(!status){
                    try {
                        out.writeObject(0);
                        continue;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }    
                else{
                    try {
                        out.writeObject(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }   
                System.out.println("Great Success!");       
                connection.close();
                user = arr[0];
                break;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
