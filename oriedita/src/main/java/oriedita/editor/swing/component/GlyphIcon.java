package oriedita.editor.swing.component;

import oriedita.editor.Colors;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GlyphIcon implements Icon, PropertyChangeListener {
    private final String glyph;
    private Color color;

    public GlyphIcon(String glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        Font font = g2.getFont();
        g2.setFont(new Font("Icons", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g2.setColor(Colors.get(color));
        g2.drawString(glyph, x, y + 20);
        g2.setFont(font);
    }

    @Override
    public int getIconWidth() {
        return 20;
    }

    @Override
    public int getIconHeight() {
        return 20;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() != null && evt.getPropertyName().equals("foreground")) {
            color = (Color) evt.getNewValue();
        }
    }
}