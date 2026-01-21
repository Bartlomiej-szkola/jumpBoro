package game.utils;

import javax.swing.*;

public class DebugInfo extends JLabel {
    private double maxVerticalSpeed = 0;

    public DebugInfo() {
        super("BRAK DANYCH");
    }

    public void updateInfo(boolean jumpingLeft, boolean jumpingRight){
        this.setText("jumpingLeft: " + jumpingLeft + " jumpingRight: " + jumpingRight);
    }

    public void updateInfo(boolean jumpingLeft, boolean jumpingRight, int level, double gravityVerticalSpeed){
        if(gravityVerticalSpeed > maxVerticalSpeed) maxVerticalSpeed = gravityVerticalSpeed;
        this.setText("jumpingLeft: " + jumpingLeft + " jumpingRight: " + jumpingRight + "   level: " + "   gravityVerticalSpeed: " + gravityVerticalSpeed + "   maxVerticalSpeed: " + maxVerticalSpeed);
    }
}
