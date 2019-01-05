package sample.AddPlayer;


import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyPickerEvent implements EventHandler<KeyEvent>
{
    private KeyCode Key;
    private Label OwnerLabel;

    public KeyCode getKey() {
        return Key;
    }

    private Stage stage;

    public KeyPickerEvent(Stage stage,KeyCode outputKey, Label l) {
        this.stage = stage;
        Key=outputKey;
        OwnerLabel=l;
    }

    @Override
    public void handle(KeyEvent event) {
        Key=event.getCode();
        OwnerLabel.setText(Key.getName());
        stage.close();
    }
}