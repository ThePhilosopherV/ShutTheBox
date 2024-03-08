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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShutTheBox extends Application {
    private int counter = 0;
    private int sum = 0;
    private boolean FirstMove = false;
    // private int[] TilesPicked;
    private int[] TilesPicked = new int[10];
    // TilesPicked = new int[10];

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
        
        RollDiceButtonClickHandler rollDiceButtonClickHandler = new RollDiceButtonClickHandler(TilesBox,RollDiceButton,RollDiceLabel);
        RollDiceButton.setOnAction(rollDiceButtonClickHandler);

        for (int i = 1; i <= 9; i++) {
            Button button = new Button( Integer.toString(i));
            button.setDisable(true);
            button.setOnAction(new ButtonClickHandler(rollDiceButtonClickHandler)); // Add event handler to button
            TilesBox.getChildren().add(button); // Add button to HBox
        }
        
        root.getChildren().addAll(TilesBox,DiceBox);

        TilesBox.setAlignment(javafx.geometry.Pos.CENTER);
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(root, 600, 500);

        // Set the Scene to the Stage
        primaryStage.setScene(scene);

        // Set the title of the Stage
        primaryStage.setTitle("Shut The Box!");

        // Show the Stage
        primaryStage.show();

    }
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        public static List<List<Integer>> findCombinations(int target) {
            List<List<Integer>> result = new ArrayList<>();
            findCombinationsHelper(target, 1, new ArrayList<>(), result);
            return result;
        }
    
        private static void findCombinationsHelper(int target, int start, List<Integer> current, List<List<Integer>> result) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
                return;
            }
            for (int i = start; i <= 10 && i <= target; i++) {
                current.add(i);
                findCombinationsHelper(target - i, i + 1, current, result);
                current.remove(current.size() - 1);
            }
        }

        public static List<List<Integer>> findCombinationsWithNumber(int target, int number) {
            List<List<Integer>> combinations = findCombinations(target);
            List<List<Integer>> result = new ArrayList<>();
            for (List<Integer> combination : combinations) {
                if (combination.contains(number)) {
                    result.add(combination);
                }
            }
            return result;
        }
        private RollDiceButtonClickHandler rollDiceButtonClickHandler;

        public ButtonClickHandler(RollDiceButtonClickHandler rollDiceButtonClickHandler) {
            this.rollDiceButtonClickHandler = rollDiceButtonClickHandler;
        }
        // private int counter;
        // private int sum;

        @Override
        public void handle(ActionEvent event) {
            
                Button clickedButton = (Button) event.getSource(); // Get the button that was clicked

                System.out.println("Button clicked: " + clickedButton.getText());
                
                int diceResult = rollDiceButtonClickHandler.getDiceResult();
                // int target = diceResult;
                int PickedTile = Integer.parseInt(clickedButton.getText());

                
                
                // TilesPicked = new int[10];
                // int sum = 0;
                // int counter = 0;
                boolean found = false;
                for (int i = 0; i < TilesPicked.length; i++) {
                    if (TilesPicked[i] == PickedTile) {
                        found = true;
                        break;
                    }
                }
                
                if (found) {
                    // System.out.println("Item found in the array");
                } else {
                    // System.out.println("Item not found in the array");
                    List<List<Integer>> Validcombinations = findCombinationsWithNumber(diceResult, PickedTile);
                    if (Validcombinations.isEmpty()){
                        
                    }else{
                        TilesPicked[counter] = PickedTile;
                        counter ++;
                        System.out.println("counter:"+counter);
                        clickedButton.setDisable(true);
                        sum = sum + PickedTile;
                        System.out.println("sum:"+sum);
                        System.out.println(Arrays.toString(TilesPicked));
                        if (sum == diceResult){
                            Button RollDiceButton = rollDiceButtonClickHandler.getDiceButton();

                            RollDiceButton.setDisable(false);
                            sum=0;
                        }


                    }

                }

                
                // List<List<Integer>> combinations = findCombinations(target);
                // for (List<Integer> combination : Validcombinations) {
                //     System.out.println(combination);
                // }

                // System.out.println(diceResult);

                }
    }
    private class RollDiceButtonClickHandler implements EventHandler<ActionEvent> {
        private HBox TilesBox;
        private Button RollDiceButton;
        private Label RollDiceLabel;
        private Random random;
        private int diceResult;

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
            if (counter == 0){
                for (javafx.scene.Node node : TilesBox.getChildren()) {
                    if (node instanceof Button) {
                        ((Button) node).setDisable(false);
                    }
                }
            }
            
            RollDiceButton.setDisable(true); // Disable the button after it's clicked
            diceResult = random.nextInt(6) + 1 + random.nextInt(6) + 1 ;
            RollDiceLabel.setText("Dice Rolled: "+diceResult); // Change the text of the button
            
            // Generate a random number (simulating dice roll)
            
            System.out.println("Dice rolled: " + diceResult);
        }
        // Getter method for diceResult
        public int getDiceResult() {
            return diceResult;
        }
        public Button getDiceButton() {
            return RollDiceButton;
        }
    }
    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }
}
