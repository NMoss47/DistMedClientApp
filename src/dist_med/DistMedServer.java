/*
 * Copyright 2017 Nicholas Moss
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to    
 * deal in the Software without restriction, including without limitation the  
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the Software is   
 * furnishedto do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in   
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR   
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,     
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER       
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN   
 * THE SOFTWARE.
 */

/*
 * Senior Project - Team A
 * Distributed Medical Devices
 * Nick Moss, Aaron Neff, Matthew Smith
 */

package dist_med;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Nicholas Moss
 */

/**
 * This class is used to create a main server thread. This class spins
 * client threads to handle each new client socket.
 * 
 * @author Nicholas Moss
 */
public class DistMedServer extends Thread
{
    private final int port;
    private final int backlog;
    private static int connectedDevices;
    
    /**
    * Class constructor for the server.
    * It has a default backlog of 5.
    * 
    * @param port The port number the server uses.
    */
    public DistMedServer( int port )
    {
        this.port = port;
        backlog = 5;
        connectedDevices = 0;
    }
    
    /**
     * Class constructor for the server.
     * 
     * @param port The port number the server uses.
     * @param backlog Specified amount of connections to keep on backlog.
     */
    public DistMedServer( int port, int backlog )
    {
        this.port = port;
        this.backlog = backlog;
        connectedDevices = 0;
    }

    /*
     * The runnable method of the server. This spins threads for each connected
     * client. 
     */
    @Override
    public final void run( )
    {
        System.out.println( "Starting the server." );
        System.out.println( "" );
        
        while( true )
        {
            try
            {
                // Recheck this one since *nix systems have many addresses
//                ServerSocket serverSocket = new ServerSocket(
//                    port, backlog, InetAddress.getLocalHost( ) );
                ServerSocket serverSocket = new ServerSocket(
                    port, backlog );
                System.out.println( "Server started.");
                System.out.println( "On: " + serverSocket.getInetAddress( ) + 
                        ", port: " + serverSocket.getLocalPort( ) );
                
                while( true )
                {
                    Socket client = serverSocket.accept( );
                    System.out.println("Client connected! " + 
                            client.getInetAddress( ) );
                    connectedDevices++;
                    ClientThread clientThread = new ClientThread( client );
                    clientThread.start( );
                    System.out.println("client thread created");
                    
                }
            }
            catch( IOException exception )
            {
                System.out.println( "Failed to start server!" );
                exception.printStackTrace( );
            }
            
            System.out.println( "Server thread ending..." );
        }
    } // End of run()
    
    /**
     * This function returns the total amount of serviced devices. It 
     * does not return the amount of currently connected devices.
     * 
     * @return Total devices served.
     */
    public int getDeviceCount( )
    {
        return connectedDevices;
    }
    
    /**
     * This function returns the current port number of the server.
     * 
     * @return The port number of the server.
     */
    public int getPort( )
    {
        return port;
    }
    
    /**
     * This function returns the current size of the backlog for the 
     * server.
     * 
     * @return Current backlog size.
     */
    public int getBacklog( )
    {
        return backlog;
    }
    
} // End of DistMedServer class
