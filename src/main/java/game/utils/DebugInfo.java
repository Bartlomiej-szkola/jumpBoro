package game.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.StringJoiner;

public class DebugInfo extends JLabel {

    public DebugInfo() {
        super("BRAK DANYCH");
    }

    public void updateInfo(Map<String, Boolean> info){
        StringJoiner sj = new StringJoiner("  ");

        for (Map.Entry<String, Boolean> entry : info.entrySet()) {
            sj.add(entry.getKey() + ": " + entry.getValue());
        }

        this.setText(sj.toString());
    }
}
