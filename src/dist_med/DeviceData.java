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

/**
 *
 * @author Nicholas Moss
 */

/**
 * This class represents the data the Distributed Medicine devices get.
 * 
 * @author Nicholas Moss
 */
public class DeviceData
{
    private final String deviceID;
    private final String deviceName;
    private final String pulse;
    private final String spo2;
    private final String time;
    
    /**
     * Default constructor for DeviceData.
     */
    public DeviceData( )
    {
        this.deviceID = "";
        this.deviceName = "";
        this.pulse = "";
        this.spo2 = "";
        this.time = "";
    }
    
    /**
     * Constructor for DeviceData.
     * 
     * @param pulse A string representing the pulse measurement.
     * @param spo2 A string representing the SPO2 measurement.
     */
    public DeviceData( String pulse, String spo2 )
    {
        this.deviceID = "";
        this.deviceName = "";
        this.pulse = pulse;
        this.spo2 = spo2;
        this.time = "";
    }
    
    /**
     * Constructor for DeviceData.
     * 
     * @param deviceID A string containing the device's ID number.
     * @param pulse A string representing the pulse measurement.
     * @param spo2 A string representing the SPO2 measurement.
     * @param time A string representing the time the measurements were taken.
     */
    public DeviceData( String deviceID, String pulse, String spo2, String time )
    {
        this.deviceID = "";
        this.deviceName = "";
        this.pulse = pulse;
        this.spo2 = spo2;
        this.time = time;
    }
    
    /**
     * Constructor for DeviceData.
     * 
     * @param deviceID A string containing the device's ID number.
     * @param pulse A string representing the pulse measurement.
     * @param spo2 A string representing the SPO2 measurement.
     */
    public DeviceData( String deviceID, String pulse, String spo2 )
    {
        this.deviceID = deviceID;
        this.deviceName = "";
        this.pulse = pulse;
        this.spo2 = spo2;
        this.time = "";
    }
    
    /**
     * Constructor for DeviceData.
     * 
     * @param deviceID A string containing the device's ID number.
     * @param deviceName A string containing the name of the device.
     * @param pulse A string representing the pulse measurement.
     * @param spo2 A string representing the SPO2 measurement.
     * @param time A string representing the time the measurements were taken.
     */
    public DeviceData( String deviceID, String deviceName, String pulse,
            String spo2, String time )
    {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.pulse = pulse;
        this.spo2 = spo2;
        this.time = time;
    }
    
    /**
     * A function to get the pulse in this data set.
     * 
     * @return The pulse measured.
     */
    public String getPulseData( )
    {
        return pulse;
    }
    
    /**
     * A function to get the SPO2 value in this data set.
     * 
     * @return The SPO2 level measured.
     */
    public String getSPO2Data( )
    {
        return spo2;
    }
    
    /**
     * A function to get the time when the measurements were taken.
     * 
     * @return The time when this dataset originated.
     */
    public String getTime( )
    {
        return time;
    }
    
    /**
     * A function to get the ID number of the device.
     * 
     * @return The ID number of the device.
     */
    public String getDeviceID( )
    {
        return deviceID;
    }
    
    /**
     * A function to get the name of the device.
     * 
     * @return The name of the device.
     */
    public String getDeviceName( )
    {
        return deviceName;
    }
    
    /**
     * An overridden toString() function. Returns the internal data.
     * @return A string with the internal data of this measurement data set.
     */
    @Override
    public String toString( )
    {
        return "Pulse:" + pulse + " SPO2:" + spo2 + " Time:" + time;
    }
}
