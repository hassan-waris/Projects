/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework.Database;

import Both.Parcel;
import com.sun.javafx.fxml.ParseTraceElement;
import coursework.Cheese;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import coursework.Database.Server;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.SocketException;

/**
 *
 * @author Hassan
 */
public class ClientHandlerThread implements Runnable {

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;
    
    ArrayList<Cheese> data = Server.getSingletonInstance().data;
    ArrayList columnNames = Server.getSingletonInstance().columnNames;

    /**
     * Constructor to create handler thread this will establish input output
     * streams to communicate
     *
     * @param socket this is client socket instance
     */
    public ClientHandlerThread(Socket socket) throws IOException {
        
            this.socket = socket;
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        
    }

    @Override
    public void run() {
        try {
            Parcel writeParcel = new Parcel(data, columnNames);
            
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
                        
                        connectionCount++;
                        System.out.println("Server: Connection " + connectionCount + " established.");
                        // Read and process names until an exception is thrown.
                        System.out.println("Server: Waiting for data from client...");
                        
                        objectOutputStream.writeObject(new Parcel(data, columnNames));
                        
                    } catch (SocketException | EOFException ex) {
                        System.out.println("Server: We have lost connection to client " + connectionCount + ".");
                    }
                    
                    
                }
                
            } catch (IOException ex) {                
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Server: Closed down");
            }
            
            // 1. receive message from client as to what client wants server to do?
            // example  client sends sever message " get me all cheeses in db "
            objectOutputStream.writeObject(writeParcel);

            // Read and process names until an exception is thrown.
            System.out.println("Server: Waiting for data from client...");
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientHandlerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

}

/*
try (ServerSocket serverSocket = new ServerSocket(2000)) {
            int connectionCount = 0;
            
            while (true) {
                System.out.println("Server: Waiting for connecting client...");
                try (
                        Socket socket = serverSocket.accept();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    ) {
                    
                    connectionCount++;
                    System.out.println("Server: Connection " + connectionCount + " established.");
                    // Read and process names until an exception is thrown.
                    System.out.println("Server: Waiting for data from client...");
                    
                    objectOutputStream.writeObject(new Parcel(data, columnNames));
                     
                } catch (SocketException | EOFException ex) {
                    System.out.println("Server: We have lost connection to client " + connectionCount + ".");
                }
                
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Server: Closed down");
        }
*/