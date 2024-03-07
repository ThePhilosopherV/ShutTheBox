import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShutTheBox extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Label with "Hello, World!" text
        
        Label RollDiceLabel = new Label("");

        Button RollDiceButton = new Button("Roll the Dice!");
        RollDiceButton.setId("RollDice");
        

        HBox DiceBox = new HBox();
        // Create a Button with "Click me!" text

        DiceBox.getChildren().addAll(RollDiceButton, RollDiceLabel );
        DiceBox.setSpacing(10);
        DiceBox.setAlignment(javafx.geometry.Pos.CENTER);

        Button tile1 = new Button("1");
        
        // Create a VBox layout and add the Label and Button to it
        VBox root = new VBox(20);
        HBox TilesBox = new HBox(20);
        Random random = new Random();
        RollDiceButton.setOnAction(new RollDiceButtonClickHandler(TilesBox,RollDiceButton,RollDiceLabel));

        for (int i = 1; i <= 9; i++) {
            Button button = new Button( Integer.toString(i));
            button.setDisable(true);
            button.setOnAction(new ButtonClickHandler()); // Add event handler to button
            TilesBox.getChildren().add(button); // Add button to HBox
        }
        
        root.getChildren().addAll(TilesBox,DiceBox);

        TilesBox.setAlignment(javafx.geometry.Pos.CENTER);
        root.setAlignment(javafx.geometry.Pos.CENTER);
        // Set action for the button
        

        // Generate a random number between 1 and 6

        // RoleDiceButton.setOnAction(new ButtonClickHandler());

        
        // RollDiceButton.setOnAction(event -> {
            
        //     int randomNumber = random.nextInt(6) + 1;
        //     RollDiceLabel.setText("Rolled Dice: "+randomNumber) ;

            
        //     System.out.println(randomNumber);
        // });

        // Create a Scene with the VBox as root
        Scene scene = new Scene(root, 600, 500);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Hello World Application");

        // Show the Stage
        primaryStage.show();

    }
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            
                Button clickedButton = (Button) event.getSource(); // Get the button that was clicked
                // String ButtonId = clickedButton.getId();
                // if (ButtonId.equals("RollDice")){
                //     boolean DiceRolled = true;
                    
                // }
                System.out.println("Button clicked: " + clickedButton.getText());
            
                // Alert alert = new Alert(AlertType.INFORMATION);
                // alert.setTitle("Warning");
                // alert.setHeaderText(null);
                // alert.setContentText("You have to roll the dice first!");

                // // Show the alert
                // alert.showAndWait();
            
            
            // Add your logic for handling button clicks here
        }
    }
    private class RollDiceButtonClickHandler implements EventHandler<ActionEvent> {
        private HBox TilesBox;
        private Button RollDiceButton;
        private Label RollDiceLabel;
        private Random random;

        public RollDiceButtonClickHandler(HBox TilesBox,Button RollDiceButton,Label RollDiceLabel) {
            this.RollDiceLabel = RollDiceLabel;
            this.TilesBox = TilesBox;
            this.RollDiceButton = RollDiceButton;
            this.random = new Random();
        }

        @Override
        public void handle(ActionEvent event) {
            int randomNumber = random.nextInt(6) + 1;
            
            // Enable all buttons in the HBox
            for (javafx.scene.Node node : TilesBox.getChildren()) {
                if (node instanceof Button) {
                    ((Button) node).setDisable(false);
                }
            }
            RollDiceButton.setDisable(true); // Disable the button after it's clicked
            int diceResult = random.nextInt(6) + 1;
            RollDiceLabel.setText("Dice Rolled: "+diceResult); // Change the text of the button
            
            // Generate a random number (simulating dice roll)
            
            System.out.println("Dice rolled: " + diceResult);
        }
    }
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
