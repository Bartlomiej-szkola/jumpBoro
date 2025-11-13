package game.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageUtils {

    /**
     * Odbija obraz względem osi Y (poziomo).
     * @param img Obraz do odbicia (Image)
     * @return Nowy obraz Image z odbitym obrazem
     */
    public static Image flipHorizontally(Image img) {
        // Konwersja Image do BufferedImage
        BufferedImage buffered = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g2d = buffered.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        // Tworzymy nowy BufferedImage na odbity obraz
        BufferedImage flipped = new BufferedImage(
                buffered.getWidth(),
                buffered.getHeight(),
                buffered.getType()
        );

        Graphics2D g = flipped.createGraphics();
        AffineTransform transform = AffineTransform.getScaleInstance(-1, 1); // odbicie poziome
        transform.translate(-buffered.getWidth(), 0); // przesunięcie
        g.drawImage(buffered, transform, null);
        g.dispose();

        return (Image) flipped;
    }
}
