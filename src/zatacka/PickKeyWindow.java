package zatacka;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PickKeyWindow {

    private String name;
    private Stage ownerStage;
    private Label ownerLabel;
    private AddPlayerWindow ownerWindow;


    public PickKeyWindow(String name, Stage owner, Label l, AddPlayerWindow w)
    {
        this.name = name;
        ownerStage = owner;
        ownerLabel = l;
        ownerWindow = w;
        InitializeWindow();
    }

    public void InitializeWindow()
    {
        Label addKey = new Label("Chose your " + name + " Key");
        addKey.setTextAlignment(TextAlignment.CENTER);
        addKey.setFont(Font.font(16));

        Pane pane = new Pane();
        pane.getChildren().add(addKey);

        Scene secondScene = new Scene(pane);

        Stage stage = new Stage();
        stage.setTitle("Chosing " + name + "Key");

        secondScene.setOnKeyPressed(e -> KeyPickerEv(stage, e));

        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ownerStage);
        stage.setScene(secondScene);
        stage.centerOnScreen();
        stage.show();
    }
    private void KeyPickerEv(Stage window, KeyEvent event)
    {
        if(name == "Left")
            ownerWindow.setLeftKeyCode(event.getCode());
        else
            ownerWindow.setRightKeyCode(event.getCode());
        ownerLabel.setText(event.getCode().getName());
        window.close();
    }
}
