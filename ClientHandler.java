import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class ClientHandler implements Runnable{
    private Socket socket;
    ArrayList UN = new ArrayList<>();
    //clientData Clientdata;
    String[] arr;
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
            String pass = "";
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
                // login(out,in);
            }


            try{
                user = (String) in.readObject();
                pass = (String) in.readObject();
                fname = (String) in.readObject();
                len = (Long) in.readObject();

                
                File file = new File(String.format("D:\\CN\\Project\\%s\\%s", user,fname));

                
                FileOutputStream fout = new FileOutputStream(file);
                byte[] buffer = new byte[Functions.buffer_size(len)];
                int count;
                int i =0;
                while(file.length()<len){
                    count = din.read(buffer);
                    fout.write(buffer, 0, count);
                    System.out.println(file.length());
                    System.out.println(i);
                    i++;
                }
                System.out.println("Files");
                String str = "File Recieved!";
                out.writeObject(str);
                System.out.println("Files2");
                out.close();
                dout.close();
                //fout.close();
                din.close();
                socket.close();
        
            }
            catch(IOException e){
                e.printStackTrace();
                // System.out.println(e.getStackTrace());
            }
            catch(ClassNotFoundException ce){
                ce.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
}

    public void register(ObjectOutputStream out, ObjectInputStream in){
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");

            PreparedStatement statement = connection.prepareStatement("select UserName from Client");

            ResultSet result = statement.executeQuery();
            while(true){
                try {
                    // Clientdata = (clientData) in.readObject();
                    arr = (String[]) in.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Connection connection = DriverManager.getConnection(
                // "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=Resort_DB;userName=admin;password=123;trustServerCertificate=true");

                while (result.next()) {

                    UN.add(result.getString("UserName").trim());
                }
                // System.out.println(UN);
                if (UN.contains(arr[0])) {
                    try{
                        out.writeObject(0);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                } else {
                    int clientstat = 0;
                    int TotalSpace = 0;
                    int occSpace = 0;
                    PreparedStatement statement1 = connection
                            .prepareStatement("Insert into Client values ('" + arr[0] + "','" + arr[1] + "','" + arr[2]
                                    + " ','" + arr[3] + "','" + clientstat + "','" + TotalSpace + "', " + occSpace + ")");
                    statement1.execute();
                    new File(String.format("D:\\CN\\Project\\%s", arr[0])).mkdir();
                    System.out.println("User addded");
                    try {
                        out.writeObject(1);
                        continue;
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void login(ObjectInputStream in, ObjectOutputStream out){
        try {

            try {
                // Clientdata = (clientData) in.readObject();
                arr = (String[]) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Connection connection = DriverManager.getConnection(
            // "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=Resort_DB;userName=admin;password=123;trustServerCertificate=true");
            Connection connection = DriverManager.getConnection(
             // "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");
             "jdbc:sqlserver://5CB18B9:1433;databaseName=CloudStorage;userName=mishaltariq;password=123;trustServerCertificate=true");


            PreparedStatement statement = connection.prepareStatement("select UserName from Client");

            ResultSet result = statement.executeQuery();
            String password;

            while (result.next()) {

                UN.add(result.getString("UserName").trim());
            }
            // System.out.println(UN);
            if (UN.contains(arr[0])) {
                System.out.println("ID exists");
                //String userName= UN.indexOf(arr[0])
                PreparedStatement statement1 = connection.prepareStatement("select Pass from Client where UserName="+arr[0]);
                ResultSet result1 = statement.executeQuery();
                while (result.next()) {

                    if(result1.getString("UserName").equals(arr[0]))
                    {
                        password=result.getString("Pass");
                        if(arr[1]==password)
                        {
                            System.out.println("Login Successful!");
                        }
                        else
                        {
                            System.out.println("incorrect password!");
                        }
                    }
                    
                }
            } 
            else {
                System.out.println("ID not found!");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
