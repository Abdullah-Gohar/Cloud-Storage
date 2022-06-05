import java.io.*;
import java.net.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        try{
            new File("D:\\CN\\Project\\client1").mkdir();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
