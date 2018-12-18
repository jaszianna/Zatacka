package sample;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class KeyPressed implements EventHandler<KeyEvent> {
    List<HumanPlayer> l;
    KeyPressed(List<HumanPlayer> l)
    {
        this.l=l;
    }

    @Override
    public void handle(KeyEvent event) {
        for(HumanPlayer p:l)
        {
            if(event.getCode()==p.getLeft())
            {
                p.setAlpha(p.getAlpha() - Math.PI / 30);
            }
            if(event.getCode()==p.getRight())
            {
                p.setAlpha(p.getAlpha() + Math.PI / 30);
            }
        }
    }
}
