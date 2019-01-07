package sample.AddPlayer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PickKeyWindow {

    private String Name;
    private Stage OwnerStage;
    private Label OwnerLabel;
    private AddPlayerWindow OwnerWindow;


    public PickKeyWindow(String name, Stage owner, Label l, AddPlayerWindow w)
    {
        Name = name;
        OwnerStage = owner;
        OwnerLabel = l;
        OwnerWindow = w;
        InitializeWindow();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void InitializeWindow()
    {
        Label addKey = new Label("Chose your " + Name + " Key");
        addKey.setPrefSize(300,100);
        addKey.setTextAlignment(TextAlignment.CENTER);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(addKey);
        Scene secondScene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Chosing " + Name + "Key");

        secondScene.setOnKeyPressed(e -> KeyPickerEv(stage, e));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(OwnerStage);
        stage.setScene(secondScene);
        stage.show();
    }
    private void KeyPickerEv(Stage window, KeyEvent event)
    {
        if(Name == "Left")
            OwnerWindow.setLeftKeyCode(event.getCode());
        else
            OwnerWindow.setRightKeyCode(event.getCode());
        OwnerLabel.setText(event.getCode().getName());
        window.close();
    }
}
