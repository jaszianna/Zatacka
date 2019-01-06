package sample.AddPlayer;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.HumanPlayer;
import sample.Player;



public class AddPlayerWindow
{
    private ColorPicker colorPicker;
    private TextField textField;
    private KeyCode LeftKeyCode;
    private KeyCode RightKeyCode;
    private ObservableList<Player> PlayersList;

    public KeyCode getLeftKeyCode() {
        return LeftKeyCode;
    }

    public void setLeftKeyCode(KeyCode leftKeyCode) {
        LeftKeyCode = leftKeyCode;
    }

    public KeyCode getRightKeyCode() {
        return RightKeyCode;
    }

    public void setRightKeyCode(KeyCode rightKeyCode) {
        RightKeyCode = rightKeyCode;
    }

    public AddPlayerWindow(ObservableList<Player> p)
    {
        PlayersList = p;
        MakeUI();
    }

    public void MakeUI()
    {
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setWidth(240);
        primaryStage.setHeight(400);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        textField = new TextField();
        textField.setPromptText("Name:");
        textField.setPrefSize(180, 30);
        textField.setLayoutX(30);
        textField.setLayoutY(40);

        colorPicker = new ColorPicker();
        colorPicker.setPrefSize(180,30);
        colorPicker.setLayoutX(30);
        colorPicker.setLayoutY(90);

        Label turnLeftLabel = new Label();
        turnLeftLabel.setText("Left turn key:");
        turnLeftLabel.setPrefSize(130,30);
        turnLeftLabel.setLayoutX(30);
        turnLeftLabel.setLayoutY(140);

        Label turnRightLabel = new Label();
        turnRightLabel.setText("Right turn key:");
        turnRightLabel.setPrefSize(130,30);
        turnRightLabel.setLayoutX(30);
        turnRightLabel.setLayoutY(190);

        Button submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setLayoutX(30);
        submitButton.setLayoutY(240);
        submitButton.setPrefSize(180,30);
        submitButton.setOnAction(e->CloseWindowAndAddPalyerEv(primaryStage));

        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setLayoutX(30);
        cancelButton.setLayoutY(290);
        cancelButton.setPrefSize(180,30);

        Label leftKeyLabel = new Label();
        leftKeyLabel.setPrefSize(50,30);
        leftKeyLabel.setLayoutX(160);
        leftKeyLabel.setLayoutY(140);
        leftKeyLabel.setText("none");
        leftKeyLabel.setOnMouseClicked(e->PickKeyEv("Left",primaryStage,leftKeyLabel));


        Label rightKeyLabel = new Label();
        rightKeyLabel.setPrefSize(50,30);
        rightKeyLabel.setLayoutX(160);
        rightKeyLabel.setLayoutY(190);
        rightKeyLabel.setText("none");
        rightKeyLabel.setOnMouseClicked(e->PickKeyEv("Right",primaryStage,rightKeyLabel));


        anchorPane.getChildren().addAll(colorPicker, textField, turnLeftLabel,
                turnRightLabel, submitButton, cancelButton,
                leftKeyLabel, rightKeyLabel);

        primaryStage.setTitle("New Player");
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public ColorPicker getPicker() {
        return colorPicker;
    }

    public TextField getNameText() {
        return textField;
    }

    private void PickKeyEv(String Name,Stage stage, Label OwnerLabel)
    {
        PickKeyWindow window=new PickKeyWindow(Name,stage,OwnerLabel, this);
    }

    private void CloseWindowAndAddPalyerEv(Stage OwnerStage)
    {
        Color c=this.getPicker().getValue();
        String Name=this.getNameText().getText();

        //TODO - Passing width and height from main window

        HumanPlayer hp=new HumanPlayer(Name,1000,1000,c,this.getLeftKeyCode(),this.getRightKeyCode());
        PlayersList.add(hp);
        OwnerStage.close();
    }
}