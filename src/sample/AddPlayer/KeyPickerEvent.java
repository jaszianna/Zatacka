package sample.AddPlayer;


import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyPickerEvent implements EventHandler<KeyEvent>
{
    private Label OwnerLabel;
    private AddPlayerWindow OwnerWindow;
    private String Keyname;

    private Stage stage;

    public KeyPickerEvent(Stage stage, Label l,AddPlayerWindow w,String n) {
        this.stage = stage;
        OwnerLabel=l;
        OwnerWindow=w;
        Keyname=n;
    }

    @Override
    public void handle(KeyEvent event) {
        if(Keyname=="Left")
        OwnerWindow.setLeftKeyCode(event.getCode());
        else
            OwnerWindow.setRightKeyCode(event.getCode());
        OwnerLabel.setText(event.getCode().getName());
        stage.close();
    }
}