package game.utils;

import javax.swing.*;
import java.awt.*;

public class DebugInfo extends JLabel {

    public DebugInfo() {
        super("BRAK DANYCH");
    }

    public void updateInfo(boolean jumpingLeft, boolean jumpingRight){
        this.setText("jumpingLeft: " + jumpingLeft + " jumpingRight: " + jumpingRight);
    }
}
