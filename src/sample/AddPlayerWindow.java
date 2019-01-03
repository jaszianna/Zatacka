package sample;

import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class AddPlayerWindow {
    private ColorPicker Picker;
    private TextArea NameText;
    private CheckBox IfCanBeHumanPlayer;
    private KeyCode LeftKeyCode;
    private KeyCode RightKeyCode;

    public void MakeUI() {
        Label EmptyLabel1=new Label();
        Label EmptyLabel2=new Label();
        Label EmptyLabel3=new Label();
        Label EmptyLabel4=new Label();
        Label EmptyLabel5=new Label();
        Label NameLab=new Label("Name:");
        NameText=new TextArea();
        NameText.setMaxHeight(20);
        NameText.setMaxWidth(200);
        Label ColorLab=new Label("Color:");
        Picker=new ColorPicker();
        Label HumanPlayerLab=new Label("Human Player");
        IfCanBeHumanPlayer=new CheckBox();
        Button LeftKeyBtn=new Button("Left");
        LeftKeyBtn.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        Button RightKeyBtn=new Button("Right");
        RightKeyBtn.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        Button AddBtn=new Button("Add");

        VBox Box = new VBox();

        Box.getChildren().addAll(NameLab, NameText,EmptyLabel1, ColorLab,  Picker, EmptyLabel2, HumanPlayerLab, IfCanBeHumanPlayer, EmptyLabel3, LeftKeyBtn, EmptyLabel4, RightKeyBtn, EmptyLabel5, AddBtn);

        Scene secondScene = new Scene(Box, 400, 300);

        // New window (Stage)
        javafx.stage.Stage newWindow = new javafx.stage.Stage();
        newWindow.setTitle("Create New Game");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.centerOnScreen();

        newWindow.show();

    }

    public AddPlayerWindow()
    {
        MakeUI();
    }
}