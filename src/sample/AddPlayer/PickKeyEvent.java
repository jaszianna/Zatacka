package sample.AddPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class PickKeyEvent implements EventHandler<ActionEvent>
{

    private Stage OwnerStage;
    private String Name;
    private Label OwnerLabel;
    private KeyCode Key;

    public Stage getOwnerStage() {
        return OwnerStage;
    }

    public String getName() {
        return Name;
    }

    public Label getOwnerLabel() {
        return OwnerLabel;
    }

    public KeyCode getKey() {
        return Key;
    }

    public PickKeyEvent(String n, Stage ownerStage, Label l) {
        Name=n;
        OwnerStage = ownerStage;
        OwnerLabel =l;
    }

    @Override
    public void handle(ActionEvent event)
    {
        PickKeyWindow window=new PickKeyWindow(Name,OwnerStage,OwnerLabel);
        Key=window.getSelectedKey();
    }
}
