package game.levels;

import static game.utils.GamePanel.panelHeight;
import static game.utils.GamePanel.panelWidth;

interface levelsVariables {
    int groundHeight = (int)(panelHeight * 0.15); // wysokość platformy ziemi
    int platformHeight = (int)(panelHeight * 0.08); // wszystkie inne platformy

    int platformSmallWidth = (int)(panelWidth * 0.15);
    int platformMediumWidth = (int)(panelWidth * 0.2);
    int platformLargeWidth = (int)(panelWidth * 0.25);
    int platformExtraLargeWidth = (int)(panelWidth * 0.3);


    int groundY = panelHeight - groundHeight;
    int veryHighY = (int)(panelHeight * 0.1);
    int highY = (int)(panelHeight * 0.2);
    int middleHighY = (int)(panelHeight * 0.3);
    int middleY = (int)(panelHeight * 0.4);
    int middleLowY = (int)(panelHeight * 0.5);
    int lowHigherY = (int)(panelHeight * 0.6);
    int lowY = (int)(panelHeight * 0.7);
    int veryLowY = (int)(panelHeight * 0.8);
}
