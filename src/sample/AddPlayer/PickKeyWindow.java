package sample.AddPlayer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PickKeyWindow {

    private String Name;
    private Stage OwnerStage;
    private Label OwnerLabel;
    private AddPlayerWindow OwnerWindow;


    public PickKeyWindow(String name, Stage owner, Label l,AddPlayerWindow w)
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
        Label AddPlayer = new Label("Chose your " + Name + "Key");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(AddPlayer);

        Scene secondScene = new Scene(secondaryLayout, 300, 100);

        // New window (Round)
        Stage newWindow = new Stage();
        newWindow.setTitle("Chosing " + Name + "Key");
        newWindow.setScene(secondScene);

        secondScene.setOnKeyPressed(e->KeyPickerEv(newWindow,e));


        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(OwnerStage);

        newWindow.show();
    }
    private void KeyPickerEv(Stage window,KeyEvent event)
    {
        if(Name=="Left")
            OwnerWindow.setLeftKeyCode(event.getCode());
        else
            OwnerWindow.setRightKeyCode(event.getCode());
        OwnerLabel.setText(event.getCode().getName());
        window.close();
    }
}
