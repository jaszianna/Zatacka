package sample.AddPlayer;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import sample.Player;

import java.util.LinkedList;


public class AddPlayerWindow {
    private ColorPicker Picker;
    private TextArea NameText;
    private CheckBox IfCanBeHumanPlayer;
    private PickKeyEvent LeftKeyEventObject;
    private PickKeyEvent RightKeyEventObject;
    private LinkedList<Player> PlayersList;

    public AddPlayerWindow(LinkedList<Player> p)
    {
        PlayersList=p;
        MakeUI();
    }

    public void MakeUI() {
        // New window (Stage)
        javafx.stage.Stage newWindow = new javafx.stage.Stage();
        newWindow.setTitle("Create New Game");

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

        Label LeftKeyLab=new Label("none");
        LeftKeyLab.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        Button LeftKeyBtn=new Button("Left Key");
        LeftKeyBtn.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        LeftKeyEventObject=new PickKeyEvent("Left",newWindow,LeftKeyLab);
        LeftKeyBtn.setOnAction(LeftKeyEventObject);

        Label RightKeyLab=new Label("none");
        RightKeyLab.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        Button RightKeyBtn=new Button("Right Key");
        RightKeyBtn.visibleProperty().bind(IfCanBeHumanPlayer.selectedProperty());
        RightKeyEventObject=new PickKeyEvent("Right",newWindow,RightKeyLab);
        RightKeyBtn.setOnAction(RightKeyEventObject);

        Button AddBtn=new Button("Add");
        AddBtn.setOnAction(new CloseWindowAndAddPlayerEvent(newWindow,this,PlayersList));

        VBox Box = new VBox();
        Box.getChildren().addAll(NameLab, NameText,EmptyLabel1, ColorLab,  Picker, EmptyLabel2, HumanPlayerLab, IfCanBeHumanPlayer, EmptyLabel3,  LeftKeyBtn, LeftKeyLab, EmptyLabel4,  RightKeyBtn, RightKeyLab, EmptyLabel5, AddBtn);
        Scene secondScene = new Scene(Box, 400, 400);
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.centerOnScreen();
        newWindow.show();
    }

    public ColorPicker getPicker() {
        return Picker;
    }

    public TextArea getNameText() {
        return NameText;
    }

    public CheckBox getIfCanBeHumanPlayer() {
        return IfCanBeHumanPlayer;
    }

    public PickKeyEvent getLeftKeyEventObject() {
        return LeftKeyEventObject;
    }

    public PickKeyEvent getRightKeyEventObject() {
        return RightKeyEventObject;
    }
}