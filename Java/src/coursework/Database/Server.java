/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework.Database;

import Both.Parcel;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import coursework.Cheese;

public class Server {

    /**
     * this is created so instead of making new server it calls singleton this
     * doesn't allow new server to be created
     */
    private static Server singletonInstance = null;

    public static Server getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Server();
        }

        return singletonInstance;
    }

    /**
     * Singleton Design Pattern
     */
    private Server() {
    }

    ArrayList columnNames = new ArrayList();
    ArrayList<Cheese> data = new ArrayList<>();

    
    private void connectToClients() {
        System.out.println("Server: Server starting.");
        try (ServerSocket serverSocket = new ServerSocket(2000)) {
            int connectionCount = 0;
            
            while (true) {
                System.out.println("Server: Waiting for connecting client...");
                try (
                        Socket socket = serverSocket.accept();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                        
                    ) {
                    
                    objectOutputStream.writeObject(new Parcel(data, columnNames));
                    
//                    // spawn a thread to handle the clients requests, then go back to accepting the next connecting client
//                    ClientHandlerThread myHandler = new ClientHandlerThread(socket);
//                    Thread myThread = new Thread(myHandler, "Thread" + connectionCount);
//                    myThread.start();
//                    
                    connectionCount++;
                    System.out.println("Server: Connection " + connectionCount + " established.");
                    // Read and process names until an exception is thrown.
                    System.out.println("Server: Waiting for data from client...");
                    
                    System.out.println(objectOutputStream);
                    System.out.println(data);
                    System.out.println(columnNames);
                     
                } catch (SocketException | EOFException ex) {
                    System.out.println("Server: We have lost connection to client " + connectionCount + ".");
                }
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server: Closed down");
        }

        
    }

    public ArrayList<Cheese> connectToDatabase() {   // Connect to an MySQL Database, run query, get result set

        String url = "jdbc:derby://localhost:1527/MyDatabase";
        String userid = "hassan";
        String password = "12345";
        String sql = "SELECT * FROM Cheese";

        // Declare the JDBC objects.  
        try (Connection connection = DriverManager.getConnection(url, userid, password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++) {
                columnNames.add(md.getColumnName(i));
            }

            for (int count = 0; count < columnNames.size(); count++) {
                System.out.printf("%-25s", columnNames.get(count));
            }
            System.out.println();

            int check = 0;

            while (rs.next()) {
                Cheese cheese = new Cheese();
                for (int i = 1; i <= columns; i++) {
                    cheese.getColumns(i, rs.getObject(i).toString());

                }
                data.add(cheese);
            }
//            System.out.println(data.size());
            
            for (int count = 0; count < data.size(); count++) {
                System.out.printf("%-25s", data.get(count));

                }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
      
        return data;

    }

    public static void main(String args[]) {
        Server server = new Server();
        server.connectToClients();
        server.connectToDatabase();
    }
}


//                check++;
//            }
//            int total = 11;
//            int spaceFlag = 1;
//
//               for (int count = 0; count < data.size(); count++) {
//                System.out.printf("%-25s", data.get(count));
//
//                if (spaceFlag % 11 == 0) {
//                    System.out.println();
//                }
//                spaceFlag++;
//      
