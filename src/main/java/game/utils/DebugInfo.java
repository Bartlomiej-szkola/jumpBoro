package game.utils;

import javax.swing.*;

public class DebugInfo extends JLabel {

    public DebugInfo() {
        super("BRAK DANYCH");
    }

    public void updateInfo(boolean jumpingLeft, boolean jumpingRight){
        this.setText("jumpingLeft: " + jumpingLeft + " jumpingRight: " + jumpingRight);
    }

    public void updateInfo(boolean jumpingLeft, boolean jumpingRight, int level, double gravityVerticalSpeed, double panelWidth, double panelHeight){
        this.setText("jumpingLeft: " + jumpingLeft + " jumpingRight: " + jumpingRight + "   level: " + "   gravityVerticalSpeed: " + gravityVerticalSpeed + "   Resolution: " + panelWidth + "x" + panelHeight);
    }
}