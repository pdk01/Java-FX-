/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package democrud;

/**
 *
 * @author Pranav Kaushik
 */
public class Train{
    private String CustomerID;
    private String CustomerName;
    private String TrainName;
    private String TrainNumber;
    private String PNR_Number;

    public Train(String CustomerID, String CustomerName, String TrainName, String TrainNumber, String  PNR_Number) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.TrainName = TrainName;
        this.TrainNumber = TrainNumber;
        this.PNR_Number = PNR_Number;
    }

    /**
     * @return the CustomerID
     */
    public String getCustomerID() {
        return CustomerID;
    }

    /**
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @return the TrainName
     */
    public String getTrainName() {
        return TrainName;
    }

    /**
     * @return the TrainNumber
     */
    public String getTrainNumber() {
        return TrainNumber;
    }

    /**
     * @return the PNR_Number
     */
    public String getPNR_Number() {
        return PNR_Number;
    }

    
    
}
