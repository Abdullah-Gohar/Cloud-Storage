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
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            //PrintWriter w = new PrintWriter(socket.getOutputStream(), true);
            String name = "dump.txt";
            // try{
            //     name = (String) in.readObject();
            // }
            // catch(Exception e){
            //     e.printStackTrace();
            // }
            String userName = "";

            try{
                // Clientdata =  (clientData) in.readObject();
                arr = (String[]) in.readObject();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            try {

                // Connection connection = DriverManager.getConnection(
                //         "jdbc:sqlserver://DESKTOP-TA4RQON:1433;databaseName=Resort_DB;userName=admin;password=123;trustServerCertificate=true");
                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://Hareem:1433;databaseName=CloudStorage;userName=hareem123;password=12345;trustServerCertificate=true");
                
                PreparedStatement statement = connection.prepareStatement("select UserName from Client");
            
                ResultSet result = statement.executeQuery();
                
                while (result.next()) {
                    
                    UN.add(result.getString("UserName").trim());
                }
                //System.out.println(UN);
                if(UN.contains(arr[0])) {
                    System.out.println("UserName already exists");
                }
                else {
                    int clientstat=0;
                    int TotalSpace=0;
                    int occSpace=0;
                    PreparedStatement statement1 = connection.prepareStatement("Insert into Client values ('"+arr[0]+"','"+arr[1]+"','"+arr[2]+" ','"+arr[3]+"','"+clientstat+"','"+TotalSpace+"', "+occSpace+")");
                    System.out.println("User addded");
                }   
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            /*File file = new File("F:\\CloudStorage\\mssql-jdbc-10.2.1.jre8");


            FileOutputStream fout = new FileOutputStream(file);
            byte[] buffer = new byte[1024 * 1024];
            int count;
            int i =0;
            while ((count = din.read(buffer)) > 0) {
                fout.write(buffer, 0, count);
                i++;
                System.out.println(i);
            }
            System.out.println("Files");
            String str = "File Recieved!";
            out.writeObject(str);
            System.out.println("Files2");*/
            out.close();
            dout.close();
            //fout.close();
            din.close();
            socket.close();
        
        }
        catch(IOException e){
            System.out.println(e.getStackTrace());
        }


    }


}
