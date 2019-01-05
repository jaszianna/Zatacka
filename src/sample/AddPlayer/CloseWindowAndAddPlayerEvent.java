package sample.AddPlayer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.HumanPlayer;
import sample.Player;

import java.util.LinkedList;

public class CloseWindowAndAddPlayerEvent implements EventHandler<ActionEvent>
{
    Stage OwnerStage;
    private ObservableList<Player> PlayersList;
    AddPlayerWindow OwnerWindow;

    public CloseWindowAndAddPlayerEvent(Stage ownerStage, AddPlayerWindow ownerWindow, ObservableList<Player> playersList) {
        OwnerStage = ownerStage;
        PlayersList = playersList;
        OwnerWindow = ownerWindow;
    }

    @Override
    public void handle(ActionEvent event)
    {
        KeyCode leftKey, rightKey;

            leftKey = OwnerWindow.getLeftKeyCode();
            rightKey = OwnerWindow.getRightKeyCode();
            Color c=OwnerWindow.getPicker().getValue();
            String Name=OwnerWindow.getNameText().getText();

            //TO DO - Passing width and height from main window

            HumanPlayer hp=new HumanPlayer(Name,1000,1000,c,leftKey,rightKey);
            PlayersList.add(hp);
            OwnerStage.close();
    }
}
