import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

// scene 1 - home , scene2 -menu , scene 3 - create booking , scene 4 - animation discount, scene 5 - cancel booking 
public class Spa extends Application {
	ImageView spaLogo,menuPhoto,fileView,menuView,bookingView;
	Menu spaMenu,bookingMenu,fileMenu;
	MenuBar menuBar;
	Image pic, pic2, filePic, menuPic, bookingPic;
	BorderPane pane,menuPane;
	Button home, submitBtn,deleteBtn, homeScene2, homescene4, homescene5;	
	GridPane bookingPane,cancelPane;
	StackPane drawPane;
	Stage window;
	Scene scene, scene2,scene3, scene4,scene5; 
	MenuItem homeMenuItem,exitMenuItem, spaMenuItem,spaDiscount, createBooking,cancelBooking;
	TextField firstNameInput,lastNameInput,emailInput,phoneInput,streetInput,aptInput,cityInput,zipInput,timeInput,firstNameCancel;
	DatePicker bookingDatePicker;
	ComboBox<String> treatmentComboBox;
	int key;

//	
  @Override 										// Override the start method in the Application class
  public void start(Stage primaryStage) {   
	window = primaryStage;
	initializeMenuBar(); 
	setPanes();
	
	   
    // scenes for switching 
    scene = new Scene(pane, 850, 500);
    scene2 = new Scene(menuPane, 850, 800);
    scene3 = new Scene(bookingPane,700,350);
    scene4 = new Scene(drawPane,850,500);
    scene5 = new Scene(cancelPane,850,500);
    window.setTitle("The Best Spa in Westchester County"); // Set the stage title
    window.setScene(scene); // Place the scene in the stage
    window.show(); // Display the stage
  }
  
   /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  

  public void initializeMenuBar() {
	  
	//images for the menu's 
	filePic = new Image(getClass().getResourceAsStream("file.jpg"));
	fileView = new ImageView(filePic);			// create the home page for the spa 
	fileView.setFitWidth(15);
	fileView.setFitHeight(15);
	
	menuPic = new Image(getClass().getResourceAsStream("spa2.png"));
	menuView = new ImageView(menuPic);			// create the home page for the spa 
	menuView.setFitWidth(15);
	menuView.setFitHeight(15);
	
	bookingPic = new Image(getClass().getResourceAsStream("pen2.png"));
	bookingView = new ImageView(bookingPic);			// create the home page for the spa 
	bookingView.setFitWidth(15);
	bookingView.setFitHeight(15);
		
	menuBar = new MenuBar();
	// First menu and menu items 
	fileMenu = new Menu("File");	
	
	homeMenuItem = new MenuItem("Home");				// need to add action listener to return to home 
	exitMenuItem = new MenuItem("Exit");				//  The add on for the menu item "File" 
	fileMenu.getItems().addAll(homeMenuItem, exitMenuItem );
	// set first menu action events 
	exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	homeMenuItem.setOnAction(e -> menuClicked(e));
	// Second Menu 
	spaMenu = new Menu("Spa Menu");							// Heading File Menus 
	spaMenuItem = new MenuItem("Menu");	
	spaDiscount = new MenuItem("Special Discount");
	spaMenu.getItems().addAll(spaMenuItem,spaDiscount);
	//set second menu action events
	spaMenuItem.setOnAction(e -> menuClicked(e));
	spaDiscount.setOnAction(e -> menuClicked(e));


	bookingMenu = new Menu("Bookings");							// Heading File Menus 
	createBooking = new MenuItem("Create a Booking");	
	cancelBooking = new MenuItem("Cancel a Booking");
	bookingMenu.getItems().addAll(createBooking,cancelBooking);
	// set third menu item action event
	createBooking.setOnAction(e -> menuClicked(e));
	cancelBooking.setOnAction(e -> menuClicked(e));


	menuBar.getMenus().addAll(fileMenu,spaMenu, bookingMenu);
	fileMenu.setGraphic(fileView);//setting the cameraIcon to cameraItem
	spaMenu.setGraphic(menuView);
	bookingMenu.setGraphic(bookingView);

  }
  public void menuClicked(ActionEvent e) {
		if (e.getSource()==spaMenuItem)
			window.setScene(scene2);
		else if(e.getSource()==createBooking)
			window.setScene(scene3);
		else if(e.getSource()==spaDiscount)
			window.setScene(scene4);
		else if(e.getSource()==cancelBooking)
			window.setScene(scene5);
		else 
			window.setScene(scene);
  }
  public void setPanes(){
	//pane for the home page 
	pane = new BorderPane();
	pic = new Image("file:///C:/Users/Trevi/Desktop/Java/Spa/images/logo.jpg");
    spaLogo = new ImageView();			// create the home page for the spa 
    spaLogo.setImage(pic);
    // place the spas home page and the menu bar on the pane 
    menuBar.prefWidthProperty().bind(window.widthProperty());
    menuBar.setStyle("-fx-background-color: FFFFFF;");
    spaLogo.fitWidthProperty().bind(window.widthProperty());
	spaLogo.fitHeightProperty().bind(window.heightProperty());
	pane.setTop(menuBar);
    pane.setCenter(spaLogo);
    
    //pane for the spa menu 
    menuPane = new BorderPane();
    pic2 = new Image("file:///C:/Users/Trevi/Desktop/Java/Spa/images/menu.jpg");
    menuPhoto = new ImageView();			// create the home page for the spa 
    menuPhoto.setImage(pic2);
    home = new Button("Home");
    home.setOnAction(new EventHandler<ActionEvent>() {
        @Override // Override the handle method
        public void handle(ActionEvent e) {
          window.setScene(scene); }
      });
   // place the spas home page and the menu bar on the pane 
   
    menuPhoto.fitWidthProperty().bind(window.widthProperty());
    menuPhoto.fitHeightProperty().bind(window.heightProperty());
	menuPane.setCenter(menuPhoto);
	menuPane.setTop(home);  
	
	//pane for creating a booking 
	 bookingPane = new GridPane();
	 bookingPane.setAlignment(Pos.CENTER);
	 bookingPane.setPadding(new Insets(11, 12, 13, 14));
	 bookingPane.setHgap(5);
	 bookingPane.setVgap(5);
	 bookingPane.setStyle("-fx-background-color: E39394;");
	 
	 // need to create names for the textfields to extract  the information for mysql
	 firstNameInput= new TextField();
	 lastNameInput= new TextField();
	 emailInput= new TextField();
	 phoneInput= new TextField();
	 streetInput= new TextField();
	 aptInput= new TextField();
	 cityInput= new TextField();
	 zipInput= new TextField();
	 timeInput = new TextField();
	 bookingDatePicker = new DatePicker();
	 treatmentComboBox = new ComboBox<String>();
	 treatmentComboBox.getItems().addAll(
         "30 Minute Massage",
         "60 Minute Massage",
         "90 Minute Massage",
         "Wax Brows",
         "Regular Facial",
         "Wax Back",
         "Wax Legs",
         "Wax Arms",
         "Wax Underarms",
         "Recharge Facial",
         "Diamond Peel",
         "Glycolic Treatment",
         "Cleansing Facial",
         "Exfoliation",
         "Regular Manicure",
         "Pedicure",
         "Gel Manicure"
     );
	   
	   // place nodes in the pane 
	 bookingPane.add(new Label("First Name:"), 0,0);
	 bookingPane.add(firstNameInput, 1,0);
	 bookingPane.add(new Label("Last Name:"), 0,1);
	 bookingPane.add(lastNameInput, 1,1);
	 bookingPane.add(new Label("Phone Number:"), 0,2);
	 bookingPane.add(phoneInput, 1,2);
	 bookingPane.add(new Label("Email Address:"), 0,3);
	 bookingPane.add(emailInput, 1,3);
	 bookingPane.add(new Label("Type of Treatment:"), 0,4);
	 bookingPane.add(treatmentComboBox, 1,4);
	 bookingPane.add(new Label("Street Address:"), 2,0);
	 bookingPane.add(streetInput, 3,0);
	 bookingPane.add(new Label("Apt,Suite,etc(optional):"), 2,1);
	 bookingPane.add(aptInput, 3,1);
	 bookingPane.add(new Label("City"), 2,2);
	 bookingPane.add(cityInput, 3,2);
	 bookingPane.add(new Label("Zipcode"), 2,3);
	 bookingPane.add(zipInput, 3,3);
	 bookingPane.add(new Label("Date"), 2,4);
	 bookingPane.add(bookingDatePicker, 3,4);
	 bookingPane.add(new Label("Time:"), 2,5);
	 bookingPane.add(timeInput, 3,5);
	 submitBtn = new Button("Submit");
	 submitBtn.setOnAction(e-> {
		 	connectToSQL();
	 });
	 bookingPane.add(submitBtn,3, 6);
	 homeScene2 = new Button("Home");
	 homeScene2.setOnAction(e-> {
		 window.setScene(scene); 
	 });
	 bookingPane.add(homeScene2,3, 6);
	 GridPane.setHalignment(submitBtn, HPos.RIGHT);
	 
	 // set the pane for the pow animation
	 
	// Create a scene and place a button in the scene
			drawPane = new StackPane();
			Polygon p = new Polygon();
			Polygon p2 = new Polygon();
		
			p.getPoints().addAll(new Double[] {100.0, 170.0, 170.0, 200.0, 160.0, 140.0, 220.0, 100.0, 160.0, 60.0, 170.0, 0.0, 100.0, 30.0,50.0, -20.0, 50.0, 220.0 });
			p.setFill(Color.PINK);
			p2.getPoints().addAll(new Double[] {120.0, 170.0, 50.0, 200.0, 60.0, 140.0, 0.0, 100.0, 60.0, 60.0, 50.0, 0.0, 120.0, 30.0, 170.0, -20.0, 170.0, 220.0 });
			p2.setFill(Color.PINK);
			p.getTransforms().add(new Rotate(180,90.0,100.0));
			p2.getTransforms().add(new Rotate(180,125.0,100.0));
					
			Label label = new Label("        Weekly Special:\n" 
					+ "$35 for 60 Minute Massage\n" + "        CODE: 30MM");
			label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
			drawPane.getChildren().add(p);
			drawPane.getChildren().add(p2);
			drawPane.getChildren().add(label);
			drawPane.setStyle("-fx-background-color: #FAFAD2;");
		    
		    //Apply a fade transition to circle
		    FadeTransition ft = new FadeTransition(Duration.millis(10000), p);
		    ft.setFromValue(0.0);
		    ft.setToValue(1.0);
		    ft.play();
		    
		  //Apply a fade transition to circle
		    FadeTransition ft2 = new FadeTransition(Duration.millis(10000), p2);
		    ft2.setFromValue(0.0);
		    ft2.setToValue(1.0);
		    ft2.play();
		    
		    //Apply a fade to the label 
		    FadeTransition ft3 = new FadeTransition(Duration.millis(10000), label);
			ft3.setFromValue(0.0);
			ft3.setToValue(1.0);
			ft3.play();
			
			
			// the cancel pane 
			cancelPane = new GridPane();
			cancelPane.setAlignment(Pos.CENTER);
			cancelPane.setPadding(new Insets(11, 12, 13, 14));
			cancelPane.setHgap(5);
			cancelPane.setVgap(5);
			cancelPane.setStyle("-fx-background-color: E39394;");
			
			cancelPane.add(new Label(" Please enter your name for the reservation you would like to cancel "), 0,0);
			cancelPane.add(new Label("First Name:"), 0,1);
			cancelPane.add(firstNameCancel, 1,1);
			deleteBtn = new Button("Delete");
			deleteBtn.setOnAction(e-> {
				 	connectToSQL();
			 });
			 cancelPane.add(deleteBtn,0, 2);
			 homescene5 = new Button("Home");
			 homescene5.setOnAction(e-> {
				 window.setScene(scene); 
			 });
			 cancelPane.add(homescene5,1, 2);
  }
  private void connectToSQL() {
	  //
	  String dbURL = "jdbc:mysql://localhost:3306/myspa";
      String username = "yourusername";
      String password = "yourpassword";
  try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
	     
	  String sql = "insert into customer (first_name, last_name, address_line1,address_line2,city,zipcode,phone_number,email)"
			  		+ " values (?, ?, ?, ?, ?, ?, ?, ? )";
	  
	  // String values for first table:customer 
	  String firstName = firstNameInput.getText();
      String lastName = lastNameInput.getText();
      String street = streetInput.getText();
      String apt = aptInput.getText();
      String city = cityInput.getText();
      String zip = zipInput.getText();
      String phone = phoneInput.getText();
      String email = emailInput.getText();
      //timeInput,treatmentComboBox;
      
      PreparedStatement statement= conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      statement.setString(1, firstName);
	  statement.setString(2, lastName);
	  statement.setString(3, street);
	  statement.setString(4, apt);
	  statement.setString(5, city);
	  statement.setString(6, zip);
	  statement.setString(7, phone);
	  statement.setString(8, email);
	  statement.executeUpdate();	    
      ResultSet keys = statement.getGeneratedKeys();

      // string values for second table: treatments
      if (keys.next()) {
		  key =keys.getInt(1);				  
	  } 
      	 
      String sql2 = "insert into bookings (Customer_idCustomer,Date_booking, time,Treatments_idTreatments)" 
    		  		+ " values (?,?,?,?)";

      String treatment = treatmentComboBox.getSelectionModel().toString();
      if (treatment.equals("30 Minute Massage"))
    	  treatment = "M3";
      else if (treatment.equals("60 Minute Massage"))
	  			treatment = "M6";
      else if (treatment.equals("90 Minute Massage"))
			treatment = "M9";
      else if (treatment.equals("Wax Brows"))
	  			treatment = "BR";
      else if (treatment.equals("Regular Facial"))
			treatment = "FA";
      else if (treatment.equals("Wax Legs"))
			treatment = "LE";
      else if (treatment.equals("Wax Arms"))
			treatment = "AR";
      else if (treatment.equals("Wax Underarms"))
			treatment = "UN";
      else if (treatment.equals("Diamond Peel"))
			treatment = "DP";
      else if (treatment.equals("Recharge Facial"))
			treatment = "RF";
      else if (treatment.equals("Glycolic Treatment"))
				treatment = "GT";
      else if (treatment.equals("Cleansing Facial"))
    	  	treatment = "CF";
      else if (treatment.equals("Exfoliation"))
			treatment = "EX";
      else if (treatment.equals("Regular Manicure"))
			treatment = "MA";
      else if (treatment.equals("Pedicure"))
			treatment = "PE";
      else 
    	  treatment = "GM";
      
      //String date = (TextField)bookingDatePicker.getEditor()).getText();
      String time = timeInput.getText();
         
      statement = conn.prepareStatement(sql2); 
      
	  statement.setInt(1,key);
	  statement.setString(2,((TextField)bookingDatePicker.getEditor()).getText());
	  statement.setString(3,time);
	  statement.setString(4,treatment);

	  		   
	  int rowsInserted = statement.executeUpdate();
	  if (rowsInserted > 0) {
	      System.out.println("A new user was inserted successfully!");
	  }
	  
	  
//	  String deletesql = "DELETE FROM bookings" + 
//	  		"WHERE Bookings.id IN (SELECT Customer.id FROM Customer WHERE Customer.firstName=?)"; 
//	  statement = conn.prepareStatement(sql2); 
//      
//	  statement.setInt(1,"the field they need to enter");
	} catch (SQLException ex) {
	    ex.printStackTrace();
	} 
  }
}
