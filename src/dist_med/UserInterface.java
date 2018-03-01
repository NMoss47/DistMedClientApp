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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;

/**
 *
 * @author Nicholas Moss
 */

/**
 * This class is more or less the entire application. It contains the
 * server and client threads as well as the GUI. It probably isn't the best way
 * to build something but its something I know will work now that the deadlines
 * are coming quick.
 * 
 * @author Nicholas Moss
 */
public class UserInterface extends Thread
{

    // The server part of the application
    private DistMedServer distMedServer;
    
    // Main container
    private JFrame mainFrame;
    private JPanel mainPanel;
    private WindowListener mainWindowListener;
    
    // Extended JPanel classes
    private PatientInfoPanel patientInfoPanel;
    private DevicePanel devicePanel;
    private OverviewPanel overviewPanel;
    
    private static Queue< DeviceData > dataQueue;
    private ArrayList< Device > connectedDevices;
    
    /**
     * Default constructor for the UserInterface class. It does quite a
     * lot. TODO: finish this comment
     */
    public UserInterface( )
    {
        dataQueue = new ConcurrentLinkedQueue< >( );
        connectedDevices = new ArrayList< >( 10 );
        
        distMedServer = new DistMedServer( 9999 );
        Thread serverThread = new Thread(distMedServer);
        serverThread.start( );
        
        
        System.out.println( "Starting the GUI..." );
        
        // Create the data queue for the incoming data from each client socket
        //queueThread = new QueueThread( );
        // Create the main containers for the application
        mainFrame = new JFrame( "Distributed Medicine Client Application" );
        mainPanel = new JPanel( );
        // TODO: fill out the unimplemented methods
        mainWindowListener = new WindowListener( ) {
            @Override
            public void windowOpened( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosing( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowClosed( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowIconified( WindowEvent e )
            {
                ///throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeiconified( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowActivated( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void windowDeactivated( WindowEvent e )
            {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        patientInfoPanel = new PatientInfoPanel( );
        devicePanel = new DevicePanel( );
        overviewPanel = new OverviewPanel( );
        
        
        // Settings and such
        mainFrame.setSize( 1600, 1200 );
        mainFrame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        
 
        mainPanel.setVisible( true );
        mainPanel.setLayout( new GridLayout(1, 2, 5, 5 ) );
        mainPanel.add( overviewPanel);
        //mainPanel.add( new JPanel() );
        mainPanel.add( patientInfoPanel );
        overviewPanel.setVisible( true );
        
        mainFrame.add( mainPanel );
        mainFrame.addWindowListener( mainWindowListener );
        
        
        // Finalization and running
        mainFrame.setVisible( true );
        
        this.start( );
    } // End of the constructor UserInterface constructor
    
    /***************************************************************************
    *   UserInterface functions
    ***************************************************************************/
    
    @Override
    public void run()
    {
        while( true )
        {
            if( dataQueue.peek() != null )
            {
                
                this.updateInterface( dataQueue.poll( ) );
                
                
                
                // Read the data from the queue
                // Send the information to 
                //String data = dataQueue.poll( );
                
                //Gson gson = new Gson( );
                // Lol.
                //this.connectedDevices.get(1).addDataSet(gson.fromJson(data, DeviceData.class));
                
                // Okay, so I will pass in the data to update
                // But I don't know how Gson works and I think its just class
                // casting or something. If that is the case there is data
                // that is needed that is NOT from DeviceData, like deviceID or
                // device name.
                //updateInterface( "devideID" , gson.fromJson(data, DeviceData.class) );
                //this.mainFrame.repaint();

                // for debugging purposes.
                //System.out.println( dataQueue.size() );
            }
            try
            {
                // Delay for a bit so the other threads can do their stuff.
                sleep(100);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } // End of run()
    
    /**
     * This function is used to dynamically update all the device
     * information in the GUI.
     * 
     * @param data The data to be rendered.
     */
    public void updateInterface( DeviceData data )
    {
        System.out.println("updateInterface() called!");
        // Something like this....
        this.overviewPanel.OverviewPanelUpdate( data );
        this.devicePanel.DevicePanelUpdate( data );
        
    }
    
    /**
     * This function is used to add some raw data to the queue.
     * 
     * @param data Raw JSON data.
     */
    public static void AddToQueue( DeviceData data )
    {
        dataQueue.add( data );
    }
    
            
    public void setPatientInfoPanel( )
    {
        this.mainPanel.remove(1);
        this.mainPanel.add(new PatientInfoPanel( new Patient( "Nicholas", "Moss", 28)));
    }
    
    
/*******************************************************************************
*   Inner classes
*******************************************************************************/
    
    // TODO: Figure out if I want to switch this to a JScrollPanel or whatever
    /**
     * This panel is used to create the devices overview screen. It has 
     * a collection of many different devices and their current bio information.
     */
    private class OverviewPanel extends JPanel
    {
        private ArrayList<DeviceDetailPanel> deviceDetailPanelList;
        private JScrollPane deviceDetailScrollPane;
        private JPanel viewPanel;
        private JPanel mainPanel;

        /**
         * Default constructor for OverviewPanel
         */
        public OverviewPanel( )
        {
            deviceDetailPanelList = new ArrayList< >( 10 );
            mainPanel = new JPanel( );
            mainPanel.setLayout( new BorderLayout( ) );
            viewPanel = new JPanel( );
            viewPanel.setLayout( new GridLayout( 30, 2, 5, 5 ) );
            
            mainPanel.add( new JPanel( ), BorderLayout.WEST );
            mainPanel.add( viewPanel, BorderLayout.CENTER );
            mainPanel.add( new JPanel( ), BorderLayout.EAST );
            mainPanel.setVisible( true );
            
            deviceDetailScrollPane = new JScrollPane( viewPanel );
            deviceDetailScrollPane.setLayout( new ScrollPaneLayout( ) );
            deviceDetailScrollPane.setVisible( true );
            
            // A test write, holy crap, but at least it works!
            ((JPanel) deviceDetailScrollPane.getViewport().getView()).add(
                    new DeviceDetailPanel( 
                      new DeviceData( "123", "44", "77")), BorderLayout.CENTER);
            
            this.setLayout(new BorderLayout( ) );
            this.add( deviceDetailScrollPane, BorderLayout.CENTER );
        } // End of OverviewPanel Contructor
        
        /**
         * A function to update the specific data of a specific device in the 
         * OverviewPanel.
         * 
         * @param deviceID The ID of the device in question.
         * @param data Object containing patient measurements.
         */
        public void OverviewPanelUpdate( DeviceData data )
        {
            boolean editedData = false;
            // Just loop through the list for the element that contains the
            // serial number of the device.
            for (DeviceDetailPanel ddplElement : deviceDetailPanelList)
            {
                if( ddplElement.getDeviceID( ).equalsIgnoreCase( 
                        data.getDeviceID() ) )
                {
                    ddplElement.DeviceDetailPanelUpdate( data );
                    editedData = true;
                }
            }
            
            if(!editedData)
            {
                DeviceDetailPanel newPanel = new DeviceDetailPanel ( data );
                deviceDetailPanelList.add( newPanel );
                ((JPanel)this.deviceDetailScrollPane.getViewport().getView()).add( newPanel );
            }
        }
        
        public void destroyDeviceDetail( String ID )
        {
            for(DeviceDetailPanel dDPL : deviceDetailPanelList)
            {
                if( dDPL.getDeviceID().equalsIgnoreCase( ID ) );
                    deviceDetailPanelList.remove( dDPL );
            }
            
            this.updateUI( );
        }
        
        public void refreshOverviewPanel( )
        {
            for( DeviceDetailPanel dDPL : deviceDetailPanelList )
            {
                this.remove( dDPL );
            }
            
            this.updateUI( );
            
            for( DeviceDetailPanel dDPL : deviceDetailPanelList )
            {
                this.add( dDPL );
            }
            
            this.updateUI( );
        }

        //----------------------------------------------------------------------
        //  End OverviewPanel, begin DeviceDetailPanel
        //----------------------------------------------------------------------
        
        /**
         * This class is an extended JPanel made to handle the details each 
         * device in the OverviewPanel class.
         * 
         * TODO: Finish constructors
         */
        private class DeviceDetailPanel extends JPanel
        {
            private String deviceID;
            
            private JPanel labelPanel;
            
            private JLabel deviceNameLabel;
            private JLabel patientIDLabel;
            private JLabel pulseLabel;
            private JLabel spo2Label;
            
            private JPanel buttonPanel;
            private JButton patientButton;
            private JButton resetButton;
            private JButton endButton;
            private ActionListener actionListener;
            
            /**
             * A constructor for the DeviceDetailPanel.
             * 
             * @param data A deviceData object containing the data to be shown.
             */
            public DeviceDetailPanel( DeviceData data )
            {
                this.deviceID = data.getDeviceID( );
                
                this.labelPanel = new JPanel( new GridLayout(4, 2, 5, 5) );
                
                this.deviceNameLabel = new JLabel( data.getDeviceName( ) );
                this.patientIDLabel = new JLabel( "N/A");
                this.pulseLabel = new JLabel( data.getPulseData( ) );
                this.spo2Label = new JLabel( data.getSPO2Data( ) );
                
                this.labelPanel.add( new JLabel( "Device: " ) );
                this.labelPanel.add( deviceNameLabel );
                this.labelPanel.add( new JLabel( "Patient: " ) );
                this.labelPanel.add( patientIDLabel );
                this.labelPanel.add( new JLabel( "Pulse" ) );
                this.labelPanel.add( pulseLabel );
                this.labelPanel.add( new JLabel( "SPO2: " ) );
                this.labelPanel.add( spo2Label );
                
                this.buttonPanel = new JPanel( new GridLayout(3, 4, 5, 5) );
                this.patientButton = new JButton( "Patient");
                this.resetButton = new JButton( "Reset" );
                this.endButton = new JButton( "End" );
                
                this.setLayout(new GridLayout( 3, 1, 5, 5) ); // todo: fix
                
                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );

                

                this.buttonPanel.add(resetButton);

                this.buttonPanel.add(endButton);

                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add(patientButton);

                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );
                this.buttonPanel.add( new JPanel() );


                
                this.add( labelPanel );
                //this.add( new JPanel( ) );
                this.add( buttonPanel );
                
                // One lambda expression to do actions.
                actionListener = ( ActionEvent e ) ->
                {
                    if( e.getSource( ) == this.endButton )
                    {
                        // TODO When removing this overview component I need to
                        // somehow remove the device from the List in
                        // UserInterface and get the overview panel to remove 
                        // the reference to this DeviceDetailPanel
                        this.removeAll( );
                        this.updateUI( );
                    }
                    else if( e.getSource( ) == this.patientButton )
                    {
                        System.out.println( "patient button test");
                        //this.getParent().getParent().setBackground(Color.red);
                        //UserInterface.setPatientPanel2( );
                        //System.out.println( this.getParent().getParent().getComponentCount());
                        //this.getParent().getParent().getParent().remove(0);
                        //this.updateUI();
                    }
                    else if( e.getSource( ) == this.resetButton )
                    {
                        System.out.println( "reset button test" );
                        this.pulseLabel.setText( "N/A" );
                        this.spo2Label.setText( "N/A" );
                        this.patientIDLabel.setText( "N/A" );
                        this.setBackground( Color.WHITE );
                        this.updateUI( );
                    }
                };
                
                this.endButton.addActionListener( actionListener );
                this.patientButton.addActionListener( actionListener );
                this.resetButton.addActionListener( actionListener );
            } // End of constructor
            
            /**
             * A constructor for the DeviceDetailPanel.
             * 
             * @param deviceID Device unique identifier.
             * @param deviceName The name of the device.
             */
            public DeviceDetailPanel( String deviceID, String deviceName )
            {
                this.deviceID = deviceID;
                this.deviceNameLabel = new JLabel( deviceName );
                this.patientIDLabel = new JLabel( "No Patient" );
                this.pulseLabel = new JLabel( "N/A" );
                this.spo2Label = new JLabel( "N/A" );
                this.endButton = new JButton( "End" );
                
                this.setLayout(new GridLayout( 5, 5, 5, 5 ) ); //This is not correct, please work on this
                
                // These may need work as well
                this.add( this.deviceNameLabel );
                this.add( this.patientIDLabel );
                this.add( this.pulseLabel );
                this.add( this.spo2Label );
                this.add( this.endButton );
                
                // One lambda expression to do actions.
                actionListener = ( ActionEvent e ) ->
                {
                    if( e.getSource( ) == this.endButton )
                    {
                        this.removeAll( );
                        this.updateUI( );
                    }
                };
                
                this.endButton.addActionListener( actionListener );
            } // End of Constructor.
            
            /**
             * A function that is used to update the pulse displayed.
             * 
             * @param pulse The current pulse.
             */
            public void setPulse( String pulse )
            {
                this.pulseLabel.setText( pulse );
            }
            
            /**
             * A function that is used to update the SPO2 displayed.
             * 
             * @param spo2 The current SPO2 value.
             */
            public void setSpo2( String spo2 )
            {
                this.spo2Label.setText( spo2 );
            }
            
            /**
             * A function that retrieves the deviceID.
             * 
             * @return The deviceID.
             */
            public String getDeviceID( )
            {
                return deviceID;
            }
            
            public void setPatientID( String patientID ) 
            {
                this.patientIDLabel.setText( patientID );
            }
            
            /**
             * A function that updates the displayed data.
             * 
             * @param data A DeviceData object that contains the data.
             */
            public void DeviceDetailPanelUpdate( DeviceData data )
            {
                this.deviceNameLabel.setText( data.getDeviceName() );
                this.pulseLabel.setText( data.getPulseData( ) );
                this.spo2Label.setText( data.getSPO2Data( ) );
                
                // Color code the current pulse!
                
                this.labelPanel.setBackground( getColorStatusCode( data ));
                
                this.updateUI( );
            }
            
            /**
             * This function is used to get a background color depending on the
             * current pulse value.
             * 
             * @param data A DeviceData object containing the pulse.
             * @return The color the background should be.
             */
            private Color getColorStatusCode( DeviceData data )
            {
                double pulse = Double.parseDouble( data.getPulseData( ) );
                
                if( isBetween( pulse, 45, 100 ) )
                    return Color.WHITE;
                if( isBetween( pulse, 20, 44 ) )
                    return Color.RED;
                if( isBetween( pulse, 0, 19 ) )
                    return Color.BLACK;
                
                return Color.WHITE;
            }
            
            /**
             * This function is used to see if an int is in a range of values.
             * 
             * @param input The number to check.
             * @param lower The lower bound of the range.
             * @param upper The upper bound of the range.
             * @return True if in the range.
             */
            private boolean isBetween( double input,
                    double lower, double upper )
            {
                return ((input >= lower) && (input <= upper));
            }
            
        } // End of DeviceDetailPanel class
        
    } // End of OverviewPanel class
    
//------------------------------------------------------------------------------    
    
    /**
     * This panel is used to create the PatientInfo screen. It is used
     *  to add, modify, and create patients and patient information.
     */
    private class PatientInfoPanel extends JPanel
    {
        // Labels for the text fields.
        private JLabel patientFirstNameLabel;
        private JLabel patientLastNameLabel;
        private JLabel patientIDLabel;
        private JLabel patientAgeLabel;
        private JLabel patientAddressLabel;
        private JLabel patientStatusLabel;
        
        // Text fields for the patient information.
        private JTextArea patientTextFirstName;
        private JTextArea patientTextLastName;
        private JTextArea patientTextID;
        private JTextArea patientTextAge;
        private JTextArea patientTextAddress;
        private JTextArea patientTextStatus;
        
        // Tiny panels to combine both the text fields and labels.
        private JPanel patientFirstNamePanel;
        private JPanel patientLastNamePanel;
        private JPanel patientIDPanel;
        private JPanel patientAgePanel;
        private JPanel patientAddressPanel;
        private JPanel patientStatusPanel;
        
        // Panel to combine the name panels.
        private JPanel patientNamePanel;
        
        /**
         * Default and empty constructor for PatientInfoPanel.
         */
        public PatientInfoPanel( )
        {
            // Create the labels
            patientFirstNameLabel = new JLabel( "First Name" );
            patientLastNameLabel = new JLabel( "Last Name" );
            patientIDLabel = new JLabel( "PatientID" );
            patientAgeLabel = new JLabel( "Age" );
            patientAddressLabel = new JLabel( "Address" );
            patientStatusLabel = new JLabel( "Status" );
            
            // Create the text areas
            patientTextFirstName = new JTextArea( );
            patientTextLastName = new JTextArea( );
            patientTextID = new JTextArea( );
            patientTextAge = new JTextArea( );
            patientTextAddress = new JTextArea( );
            patientTextStatus = new JTextArea( );
            
            // Create the panels to hold the labels and text areas
            patientFirstNamePanel = new JPanel( );
            patientLastNamePanel = new JPanel( );
            patientIDPanel = new JPanel( );
            patientAgePanel = new JPanel( );
            patientAddressPanel = new JPanel( );
            patientStatusPanel = new JPanel( );
            patientNamePanel = new JPanel( );
            
            // Add componenets to the panels
            patientFirstNamePanel.add( patientFirstNameLabel );
            patientFirstNamePanel.add( patientTextFirstName );
            patientLastNamePanel.add( patientLastNameLabel );
            patientLastNamePanel.add( patientTextLastName );
            patientNamePanel.add( patientFirstNamePanel );
            patientNamePanel.add( patientLastNamePanel );
            
            patientIDPanel.add( patientIDLabel );
            patientIDPanel.add( patientTextID );
            patientAgePanel.add( patientAgeLabel );
            patientAgePanel.add( patientTextAge );
            patientAddressPanel.add( patientAddressLabel );
            patientAddressPanel.add( patientTextAddress );
            patientStatusPanel.add( patientStatusLabel );
            patientStatusPanel.add( patientTextStatus );
            
            // Add the combined components to the self
            this.setLayout( new GridLayout( ) ); // Redo this one to make this mesh well
            this.add( patientNamePanel );
            this.add( patientIDPanel );
            this.add( patientAddressPanel );
            this.add( patientStatusPanel );
            this.setVisible( true );
            
        } // End of the constructor
        
        /**
         * Constructor for PatientInfoPanel using patient information.
         * @param patient Patient object with which to get data
         */
        public PatientInfoPanel( Patient patient )
        {
            patientFirstNameLabel = new JLabel( "First Name" );
            patientLastNameLabel = new JLabel( "Last Name" );
            patientIDLabel = new JLabel( "PatientID" );
            patientAgeLabel = new JLabel( "Age" );
            patientAddressLabel = new JLabel( "Address" );
            patientStatusLabel = new JLabel( "Status" );
            
            // Create the text areas
            patientTextFirstName = new JTextArea( patient.getFirstName( ) );
            patientTextLastName = new JTextArea( patient.getLastName( ) );
            patientTextID = new JTextArea( patient.getPatientID( ) );
            // Get the age, cast as Integer type, convert to String
            patientTextAge = new JTextArea(
                    ((Integer)patient.getAge( )).toString( ) );
            patientTextAddress = new JTextArea( patient.getAddress( ) );
            patientTextStatus = new JTextArea( patient.getStatus( ) );
            
            // Create the panels to hold the labels and text areas
            patientFirstNamePanel = new JPanel( );
            patientLastNamePanel = new JPanel( );
            patientIDPanel = new JPanel( );
            patientAgePanel = new JPanel( );
            patientAddressPanel = new JPanel( );
            patientStatusPanel = new JPanel( );
            patientNamePanel = new JPanel( );
            
            // Add componenets to the panels
            patientFirstNamePanel.add( patientFirstNameLabel );
            patientFirstNamePanel.add( patientTextFirstName );
            patientLastNamePanel.add( patientLastNameLabel );
            patientLastNamePanel.add( patientTextLastName );
            patientNamePanel.add( patientFirstNamePanel );
            patientNamePanel.add( patientLastNamePanel );
            
            patientIDPanel.add( patientIDLabel );
            patientIDPanel.add( patientTextID );
            patientAgePanel.add( patientAgeLabel );
            patientAgePanel.add( patientTextAge );
            patientAddressPanel.add( patientAddressLabel );
            patientAddressPanel.add( patientTextAddress );
            patientStatusPanel.add( patientStatusLabel );
            patientStatusPanel.add( patientTextStatus );
            
            // Add the combined components to the self
            this.setLayout( new GridLayout( ) ); // Redo this one to make this mesh well
            this.add( patientNamePanel );
            this.add( patientIDPanel );
            this.add( patientAddressPanel );
            this.add( patientStatusPanel );
            this.setVisible( true );
            
        } // End of the constructor
        
    } // End of the PatientInfoPanel class
   
//------------------------------------------------------------------------------
    
    /**
     * This panel is used to create the DevicePanel screen. It is used 
     * to give specific information on devices.
     */
    private class DevicePanel extends JPanel
    {
        
        /**
         * Default constructor for DevicePanel.
         */
        public DevicePanel( )
        {
            
        } // End of the constructor
        
        public void DevicePanelUpdate( DeviceData data )
        {
            
        }
        
    } // End of DevicePanel class
    
} // End of UserInterface class and EOF
