package sample.AddPlayer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PickKeyWindow {

    private String Name;
    private Stage OwnerStage;
    private KeyCode SelectedKey;
    private Label OwnerLabel;

    public KeyCode getSelectedKey() {
        return SelectedKey;
    }

    public PickKeyWindow(String name, Stage owner, Label l) {
        Name = name;
        OwnerStage=owner;
        OwnerLabel=l;
        MakeUI();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void MakeUI()
    {
        Label AddPlayer = new Label("Chose your "+Name+" Key");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(AddPlayer);

        Scene secondScene = new Scene(secondaryLayout, 300, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Chosing "+Name+ " Key");
        newWindow.setScene(secondScene);

        secondScene.setOnKeyPressed(new KeyPickerEvent(newWindow,SelectedKey, OwnerLabel));


        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(OwnerStage);

        newWindow.show();
    }
}
