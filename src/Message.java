import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import visual.statik.sampled.Content;

/**
 * This class is used for standard purposes 
 * @author Ben Kirtley
 * This work complies with the James Madison University honor code
 */
public class Message extends Content
{
    String message;
    
    /**
     * Explicit Constructor
     * @param message The String to use
     */
    Message(String message)
    {
        this.message = message;
    }
    
    /**
     * Renders the simple content
     * @param g The graphics engine
     */
    public void render(Graphics g)
    {
       Graphics2D g2; //Graphics engine
       FontRenderContext frc; //Renders Fonts on screen
       GlyphVector glyphs; //Contain geometric information about fonts
       Shape shape; // A Shape
       Font font; //A collection of glyphs
       
       g2 = (Graphics2D) g;
       
       font = new Font(Font.SANS_SERIF, Font.ITALIC, 16);
       
       frc = g2.getFontRenderContext();
       
       glyphs = font.createGlyphVector(frc, message);
       
       shape = glyphs.getOutline(0.0f,100.0f);
       
       g2.setColor(Color.BLACK);
       
       g2.draw(shape);       
    } 
}
