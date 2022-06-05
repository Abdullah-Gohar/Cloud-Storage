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
            try{
                user = (String) in.readObject();
                pass = (String) in.readObject();
                fname = (String) in.readObject();
                len = (Long) in.readObject();

                
                new File(String.format("D:\\CN\\Project\\%s",user)).mkdir();
                File file = new File(String.format("D:\\CN\\Project\\%s\\%s", user,fname));

                
                FileOutputStream fout = new FileOutputStream(file);
                byte[] buffer = new byte[Math.round(len/10)];
                int count;
                int i =0;
                while(file.length()<=len){
                    count = din.read(buffer);
                    fout.write(buffer, 0, count);
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

    public void Register(ObjectInputStream in){
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
                    "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");

            PreparedStatement statement = connection.prepareStatement("select UserName from Client");

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                UN.add(result.getString("UserName").trim());
            }
            // System.out.println(UN);
            if (UN.contains(arr[0])) {
                System.out.println("UserName already exists");
            } else {
                int clientstat = 0;
                int TotalSpace = 0;
                int occSpace = 0;
                PreparedStatement statement1 = connection
                        .prepareStatement("Insert into Client values ('" + arr[0] + "','" + arr[1] + "','" + arr[2]
                                + " ','" + arr[3] + "','" + clientstat + "','" + TotalSpace + "', " + occSpace + ")");
                statement1.execute();
                System.out.println("User addded");
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
