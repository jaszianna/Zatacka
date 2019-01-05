package sample.AddPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PickKeyEvent implements EventHandler<MouseEvent>
{

    private Stage OwnerStage;
    private String Name;
    private Label OwnerLabel;
    private AddPlayerWindow OwnerWindow;


    public Stage getOwnerStage() {
        return OwnerStage;
    }

    public String getName() {
        return Name;
    }

    public Label getOwnerLabel() {
        return OwnerLabel;
    }



    public PickKeyEvent(String n,AddPlayerWindow w, Stage ownerStage, Label l) {
        Name=n;
        OwnerStage = ownerStage;
        OwnerLabel =l;
        OwnerWindow=w;
    }

    @Override
    public void handle(MouseEvent event)
    {
        PickKeyWindow window=new PickKeyWindow(Name,OwnerStage,OwnerLabel, OwnerWindow);
    }
}
