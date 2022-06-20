/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package democrud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;// 
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Pranav Kaushik
 */
public class LoginSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public Label label;
    @FXML
    private Button submit;
  Connection con;
   ResultSet rs;
 PreparedStatement pst;
    @FXML
    private Button button2;
    @FXML
    public void switchtoScene1(ActionEvent event) throws IOException
    {
             Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();// 
    
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
    }
    
    public void displayyy(){
        label.setText("Success");
    }
    @FXML
    public void switchtoScene2(ActionEvent event) throws IOException
    {
             Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
      
             
    }      
 public void handlesubmit(ActionEvent event) throws ClassNotFoundException {
 
 String uname=username.getText(); // text field 
 String pass=password.getText(); // text field
 try{
 Class.forName("com.mysql.login.jdbc.Driver"); 
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/train?autoReconnect=true&useSSL=false","root","");
 pst=con.prepareStatement("select * from login where username=? and password=?");
 
 pst.setString(1, uname);
 pst.setString(2, pass);
 
 rs= pst.executeQuery();
 
 if(rs.next())
 {
 displayyy();

             
    } 
 }
 
     catch(Exception e){
         System.out.println("e");
     }     
    
    
    
 }
 }

