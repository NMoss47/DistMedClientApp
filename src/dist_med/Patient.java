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
 * This class is used to represent the information of a patient.
 * 
 * @author Nicholas Moss
 */
public class Patient
{
    private String firstname;
    private String lastname;
    private String patientID;
    private int age;
    private String birthdate;
    private String address;
    private String status;
    
    private String symptoms;
    private String diagnosis;
    private String prognosis;
    private String notes;
    
    /**
     * Class constructor for the Patient.
     */
    public Patient( )
    {
        this.firstname = "";
        this.lastname = "";
        this.patientID = "";
        this.age = 0;
        this.birthdate = "";
        this.address = "";
        this.status = "";
        
        this.symptoms = "";
        this.diagnosis = "";
        this.prognosis = "";
        this.notes = "";
    }
    
    /**
     * Class constructor for the Patient.
     * @param patientID The patientID of the new Patient.
     */
    public Patient( String patientID )
    {
        this.firstname = "";
        this.lastname = "";
        this.patientID = "";
        this.age = 0;
        this.birthdate = "";
        this.address = "";
        this.status = "";
        
        this.symptoms = "";
        this.diagnosis = "";
        this.prognosis = "";
        this.notes = "";
    }
    
    /**
     * Class constructor for the Patient.
     * 
     * @param firstname Primary name of the patient.
     * @param lastname Surname of the patient.
     */
    public Patient( String firstname, String lastname )
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patientID = "";
        this.age = 0;
        this.birthdate = "";
        this.address = "";
        this.status = "";
        
        this.symptoms = "";
        this.diagnosis = "";
        this.prognosis = "";
        this.notes = "";
    }
    
    /**
     * Class constructor for the Patient.
     * 
     * @param firstname Primary name of the patient.
     * @param lastname Surname of the patient.
     * @param age The age of the patient in years.
     */
    public Patient( String firstname, String lastname, int age )
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patientID = "";
        this.age = age;
        this.birthdate = "";
        this.address = "";
        this.status = "";
        
        this.symptoms = "";
        this.diagnosis = "";
        this.prognosis = "";
        this.notes = "";
    }
    
    /**
     * Class constructor of the patient.
     * 
     * @param firstname Primary name of the patient.
     * @param lastname Surname of the patient.
     * @param age Age of the patient in years.
     * @param birthdate Birth date of the patient.
     * @param address Address of the patient.
     */
    public Patient( String firstname, String lastname, int age,
            String birthdate, String address )
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthdate = birthdate;
        this.address = address;
        this.status = "";
        
        this.symptoms = "";
        this.diagnosis = "";
        this.prognosis = "";
        this.notes = "";
    }
    
    /**
     * Function that returns the name of the patient.
     * 
     * @return Returns a name in the format of "Last, First".
     */
    public String getName( )
    {
        return lastname + ", " + firstname;
    }
    
    /**
     * Function that returns the first name of the patient.
     * 
     * @return Returns the first name of the patient.
     */
    public String getFirstName( )
    {
        return firstname;
    }
    
    /**
     * Functions that sets the first name of the patient.
     * 
     * @param firstname The first name of the patient.
     */
    public void setFirstName( String firstname )
    {
        this.firstname = firstname;
    }
    
    /**
     * Function that returns the last name of the patient.
     * 
     * @return Returns the last name of the patient.
     */
    public String getLastName( )
    {
        return lastname;
    }
    
    /**
     * Function that sets the last name of the patient.
     * 
     * @param lastname The last name of the patient.
     */
    public void setLastName( String lastname )
    {
        this.lastname = lastname;
    }
    
    /**
     * Function that returns the ID of the patient.
     * 
     * @return Returns a string representing the patient ID.
     */
    public String getPatientID( )
    {
        return patientID;
    }
    
    /**
     * Function that returns the age of the patient.
     * 
     * @return Returns the age of the patient in years.
     */
    public int getAge( )
    {
        return age;
    }
    
    /**
     * Function that returns the date of birth of the patient.
     * 
     * @return Returns a string representing the date of birth of the patient.
     */
    public String getBirthdate( )
    {
        return birthdate;
    }
    
    /**
     * Function that sets the date of birth for the patient.
     * 
     * @param birthdate Date of birth for the patient. Format as DD/MM/YYYY.
     */
    // TODO: ISO date standards
    private void setBirthdate( String birthdate )
    {
        this.birthdate = birthdate;
    }
    
    /**
     * Function that sets the address of the patient.
     * 
     * @param address The address of the patient.
     */
    public void setAddress( String address )
    {
        this.address = address;
    }
    
    /**
     * Function that sets the health status of the patient.
     * 
     * @param status The health status of the patient given as a string.
     */
    public void setStatus( String status )
    {
        this.status = status;
    }
    
    /**
     * Function that returns the current health status of the patient.
     * 
     * @return Returns the current health status of the patient as a string.
     */
    public String getStatus( )
    {
        return status;
    }
    
    /**
     * Function that returns the current address of the patient.
     * 
     * @return Returns the address of the patient.
     */
    public String getAddress( )
    {
        return address;
    }
    
    /**
     * Function that sets the symptoms data of the patient.
     * 
     * @param symptoms The symptoms of the patient.
     */
    public void setSymptoms( String symptoms )
    {
        this.symptoms = symptoms;
    }
    
    /**
     * Function that returns the symptoms of the patient.
     * 
     * @return A string representing the symptoms of the patient.
     */
    public String getSymptoms( )
    {
        return symptoms;
    }
    
    /**
     * Function that sets the diagnosis of the patient.\
     * 
     * @param diagnosis The diagnosis of the patient.
     */
    public void setDiagnosis( String diagnosis )
    {
        this.diagnosis = diagnosis;
    }
    
    /**
     * Function that returns the current diagnosis of the patient.
     * 
     * @return The current diagnosis of the patient.
     */
    public String getDiagnosis( )
    {
        return diagnosis;
    }
    
    /**
     * Function that sets the prognosis of the patient.
     * 
     * @param prognosis The prognosis of the patient.
     */
    public void setPrognosis( String prognosis )
    {
        this.prognosis = prognosis;
    }
    
    /**
     * Function that returns the prognosis of the patient.
     * 
     * @return The current prognosis of the patient.
     */
    public String getPrognosis( )
    {
        return prognosis;
    }
    
    /**
     * Function that sets the current notes on a patient.
     * 
     * @param notes The notes of the patient.
     */
    public void setNotes( String notes )
    {
        this.notes = notes;
    }
    
    /**
     * Function that returns the current notes on a patient.
     * 
     * @return The current notes on a patient.
     */
    public String getNotes( )
    {
        return notes;
    }
    
    /**
     * Function that sets the age of the patient.
     * @param age The age of the patient.
     */
    public void setAge( int age )
    {
        this.age = age;
    }
}
