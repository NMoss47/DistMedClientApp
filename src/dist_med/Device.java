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

import java.util.ArrayList;

/**
 *
 * @author Nicholas Moss
 */

/**
 * This class represents the Distributed Medicine devices.
 * 
 * @author Nicholas Moss
 */
public class Device
{
    private String deviceSerialID;
    private String deviceName;
    private String status;
    private String patientID;
    
    // The current DeviceData
    private DeviceData currentData;
    // An array list of previous DeviceData
    private final ArrayList< DeviceData > deviceData;
    
    /**
     * Class constructor for the Device.
     */
    public Device( )
    {
        this.deviceSerialID = "";
        this.deviceName = "";
        this.status = "";
        this.currentData = null;
        this.patientID = null;
        this.deviceData = new ArrayList< >( 10 );
    }
    
    /**
     * Class constructor for the Device.
     * 
     * @param deviceSerialID The serial number of the device
     */
    public Device( String deviceSerialID )
    {
        this.deviceSerialID = deviceSerialID;
        this.deviceName = "";
        this.status = "";
        this.patientID = null;
        this.currentData = null;
        this.deviceData = new ArrayList< >( 10 );
    }
    
    /**
     * A function to get the current device ID.
     * 
     * @return The serial number of the device.
     */
    public String getDeviceSerialID( )
    {
        return deviceSerialID;
    }
    
    /**
     * A function to set the serial ID of the device.
     * 
     * @param deviceSerialID The serial ID of the device.
     */
    public void setDeviceSerialID( String deviceSerialID )
    {
        this.deviceSerialID = deviceSerialID;
    }
    
    /**
     * A function that retrieves the name of the device.
     * 
     * @return The name of the device.
     */
    public String getDeviceName( )
    {
        return deviceName;
    }
    
    /**
     * A function to set the name of the device.
     * 
     * @param deviceName The name of the device.
     */
    public void setDeviceName( String deviceName )
    {
        this.deviceName = deviceName;
    }
    
    /**
     * A function that gets the current status of the device.
     * 
     * @return The status of the device.
     */
    public String getStatus( )
    {
        return status;
    }
    
    /**
     * A function that sets the current status of the device.
     * 
     * @param status The current status of the device.
     */
    public void setStatus( String status )
    {
        this.status = status;
    }
    
    
    /**
     * A function that gets the current patient attached to the device.
     * 
     * @return The patientID attached to the device.
     */
    public String getPatient( )
    {
        return patientID;
    }
    
    /**
     * A function that sets a patientID to the device.
     * 
     * @param patientID The ID of the patient that the device is attached to.
     */
    public void setPatient( String patientID )
    {
        this.patientID = patientID;
    }
    
    /**
     * This function is used to add a new dataset to the Device.
     * 
     * @param newData A DeviceData object containing measurement data.
     */
    public void addDataSet( DeviceData newData )
    {
        this.deviceData.add( newData );
    }
    
    /**
     * A function that adds a new data set to the Device.
     * 
     * @param pulse A string containing the pulse measurement.
     * @param spo2 A string containing the SPO2 measurement.
     * @param time A string containing the time of the measurements.
     */
    public void addDataSet( String pulse, String spo2, String time )
    {
        this.deviceData.add( new DeviceData( pulse, spo2, time ) );
    }
    
    /**
     * A function to get the most recent dataset.
     * 
     * @return A DeviceData object of the most recent dataset.
     */
    public DeviceData getLatestDataAsDeviceData( )
    {
        // This gets the last element.
        //return this.deviceData.get( deviceData.size( ) -1 );
        return currentData;
    }
    
    /**
     * A function to get the most recent data.
     * 
     * @return A string representation of the most recent dataset.
     */
    public String getLatestDataAsString( )
    {
        //return this.deviceData.get( deviceData.size( ) -1 ).toString();
        return currentData.toString( );
    }
    
    /**
     * A function used to update the current DeviceData of the Device.
     * 
     * @param currentData The current DeviceData object.
     */
    public void updateDeviceData( DeviceData currentData )
    {
        deviceData.add( this.currentData );
        this.currentData = currentData;
    }
    
    /**
     * A function used to update the current DeviceData with strings.
     * 
     * @param pulse A string containing the pulse measurement.
     * @param spo2 A string containing the SPO2 measurement.
     * @param time A string containing the time of the measurements.
     */
    public void updateDeviceDataString( String pulse, String spo2, String time )
    {
        deviceData.add( this.currentData );
        this.currentData = new DeviceData( pulse, spo2, time );
    }
    
    /**
     * A function used to return the current size of the array list that holds
     * the DeviceData objects.
     * 
     * @return An int of the size of the data set.
     */
    public int getSizeOfDataSet( )
    {
        // Plus one since the current data is actually stored outside the array.
        return deviceData.size() + 1;
    }
    
    /**
     * A function that purges all DeviceData from the Device object.
     */
    public void destroyAllData( )
    {
        // I don't know if I want to keep this dude around.
        currentData = null;
        deviceData.clear( );
    }
    
} // End of the Device class
