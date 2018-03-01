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

import com.google.gson.Gson;
import static dist_med.GUI.AddToQueue;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicholas Moss
 */

/**
 * This class is used to create a thread to handle each client device. 
 * It is responsible for receiving data from the client and either passing it 
 * or processing it.
 * 
 * @author Nicholas Moss
 */
public class ClientThread extends Thread
{
    private final Socket clientSocket;
    private final Device device;
    private String data;
    private DeviceData deviceData;
    private InputStreamReader inputStream;
    private BufferedReader bufferedReader;

    
    /**
     * Class constructor for the ClientThread.
     * @param clientSocket The client socket representing the device.
     */
    public ClientThread( Socket clientSocket )
    {
        System.out.println( "client thread created!" );
        this.clientSocket = clientSocket;
        this.device = null; // We need to parse the first packet to get this.
        //this.run( );

    }

    /*
     * The main running function. This thread will be used to communicate with
     * the device and parse data
     */
    @Override
    public void run( )
    {
        
        System.out.println( "client thread now running.");
        try
        {
            Gson gson = new Gson( );
            inputStream = new InputStreamReader(
                    clientSocket.getInputStream( ) );
            bufferedReader = new BufferedReader( inputStream );
            
            while( (data = bufferedReader.readLine( )) != null )
            {
                // Parse the incoming JSON data into a DeviceData object
                deviceData = gson.fromJson( data , DeviceData.class );
                // Add the DeviceData object to the queue.
                AddToQueue( deviceData );
            }
            
            sleep( 50 );
        }
        catch( IOException ex )
        {
            //ex.printStackTrace( );
            Logger.getLogger(ClientThread.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(ClientThread.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        finally
        {
            try
            {
                bufferedReader.close( );
                inputStream.close( );
                clientSocket.close( );
            }
            catch( IOException ex )
            {
                //ex.printStackTrace( );
                Logger.getLogger(ClientThread.class.getName()).log(
                    Level.SEVERE, null, ex);
            }
        }
    } 
    
} // End of ClientThread class
