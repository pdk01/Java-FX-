package democrud;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    public boid SwitchtoMain

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_insert;

    @FXML
    private Button btn_update;

    @FXML
    private TableColumn<Train, String> col_CustomerId;

    @FXML
    private TableColumn<Train, String> col_customer_name;

    @FXML
    private TableColumn<Train, String> col_pnr_no;

    @FXML
    private TableColumn<Train, String> col_train_name;

    @FXML
    private TableColumn<Train, String> col_train_number;

    @FXML
    private TextField pnr;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_trainname;

    @FXML
    private TextField tf_trainnumber;

    @FXML
    private TableView<Train> tv_train;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Label lbdisplay;
    @FXML
    private Label labelll;
    
//    public void  displayy(){
//        lbdisplay.setText("Values UPDATED");
//    }
    // not needed
    
    @FXML
    public void switchtoScene3(ActionEvent event) throws IOException
    {
             Parent root = FXMLLoader.load(getClass().getResource("ThankYou.fxml"));
             stage=(Stage)((Node)event.getSource()).getScene().getWindow();
    
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
             
    }
    
    void handleButtonAction(ActionEvent event) {
        if(event.getSource()==btn_insert){
            
            insertButton();
        }
            else if(event.getSource()==btn_update){
                
                    labelll.setText("VALUES UPDATED");
                    updateButton();
                    
                    }
        
        
            else if(event.getSource()==btn_delete){
                
                labelll.setText("VALUES DELETED");
                
            
               deleteButton();
                
            }
        
        }

    
    public void initialize(URL url, ResourceBundle rb) {
        showTrain();
        
    }
    public Connection getConnection(){
        Connection con;
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/train","root","");
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=rootpassword"); 
         return con;    // 
        }
        catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
            return null;
        }
    }
    
    
    public ObservableList<Train> getTrainList(){
    	ObservableList<Train> trainList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM train ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Train train;
			while(rs.next()) {
				train = new Train(rs.getString("CustomerID"),rs.getString("CustomerName"),rs.getString("TrainName"),rs.getString("TrainNumber"),rs.getString("PNR_Number"));
				trainList.add(train);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return trainList;
    }
    
    public void showTrain() {
    	ObservableList<Train> list = getTrainList();
    	
    	col_CustomerId.setCellValueFactory(new PropertyValueFactory<Train,String>("CustomerID"));
    	col_customer_name.setCellValueFactory(new PropertyValueFactory<Train,String>("customerName"));
    	col_train_name.setCellValueFactory(new PropertyValueFactory<Train,String>("TrainName"));
    	col_train_number.setCellValueFactory(new PropertyValueFactory<Train,String>("TrainNumber"));
    	col_pnr_no.setCellValueFactory(new PropertyValueFactory<Train,String>("PNR_Number"));
    	
    	tv_train.setItems(list);
    }
    
    @FXML
   private void submitdetails(){
       String  CustomerID = tf_id.getText();
       String CustomerName = tf_name.getText();
       String TrainName =tf_trainname.getText();
       String TrainNumber = tf_trainnumber.getText().toString();
       String  PNR_Number = pnr.getText().toString();
       
       if(!(CustomerID.isEmpty())&&!(CustomerName.isEmpty())&&!(TrainName.isEmpty())&&!(TrainNumber.isEmpty())&&!(PNR_Number.isEmpty()) ){
             try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = getConnection();
            Statement st;
            
            st = connection.createStatement();
            
            String s1 = "insert into train(CustomerID,CustomerName,TrainName,TrainNumber,PNR_Number) values ('"+CustomerID+"','"+CustomerName+"','"+TrainName+"','"+TrainNumber+"','"+PNR_Number+");";
            System.out.println("sucess");
            st.executeUpdate(s1);

        } catch (Exception e) {
            System.out.println(e);
   }
       }
   }
   
   
   
//   private void insertRecord(){
//       String query ="INSERT INTO train VALUES("+tf_id.getText() + ",'" + tf_name.getText() + "','" + tf_trainname.getText()+ "',"
//               +tf_trainnumber.getText()+ "," +pnr.getText() + ")";
//       executeQuery(query);
//       showTrain();
//   }
   
     private void insertButton() {
    	String query = "insert into train values("+tf_id.getText()+",'"+tf_name.getText()+"','"+tf_trainname.getText()+"',"+tf_trainnumber.getText()+","+pnr.getText()+")";
    	executeQuery(query);
    	showTrain();
    }
    
    
    @FXML 
    private void updateButton() {
    String query = "UPDATE train SET Values='"+tf_id.getText()+"',Author='"+tf_name.getText()+"',Year="+tf_trainname.getText()+",Pages="+tf_trainnumber.getText()+" WHERE ID="+pnr.getText()+"";
    executeQuery(query);
	showTrain();
    }
    
    @FXML
    private void deleteButton() {
    	String query = "DELETE FROM train WHERE CustomerID="+tf_id.getText()+"";
    	executeQuery(query);
    	showTrain();
    }
    private void executeQuery(String query) {
        Connection con= getConnection();
        Statement st;
        try{
            st = con.createStatement();
            st.executeQuery(query);
            
        }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
       Train train= tv_train.getSelectionModel().getSelectedItem();
       
        System.out.println("CustomerID  " + train.getCustomerID());
        System.out.println("Customer Name " + train.getCustomerName());
        System.out.println("Train Name" + train.getTrainName());
        System.out.println("Train Number"+ train.getTrainNumber());
        System.out.println("PNR Number"+ train.getPNR_Number());
        
    
    
    
    
    
    
    
    
    }   
    
}

