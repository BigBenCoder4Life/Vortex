import io.ResourceFinder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import event.Metronome;
import event.MetronomeListener;
import visual.Visualization;
import visual.VisualizationView;
import visual.dynamic.sampled.Screen;
import visual.dynamic.sampled.Superimposition;
import visual.dynamic.sampled.TransformableContentSuperimposition;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;
import visual.statik.sampled.ImageFactory;
import app.AbstractMultimediaApp;

/**
 * This class is the driver class for applications and applets.
 * 
 * @author Ben Kirtley This work complies with the James Madison University
 *         Honor code.
 */
public class AboutApp extends AbstractMultimediaApp
{
    private JPanel contentPane; // The content pane
    Screen screen; // The movie screen
    VisualizationView view; // The view
    Content message; // A message
    TransformableContentSuperimposition superImp; // Used to add content
    int max = 3; // Max number of words in the message

    /**
     * Called when this MultimediaApp has been loaded
     */
    public void init()
    {
        // Get the content pane
        contentPane = (JPanel) rootPaneContainer.getContentPane();

        // Create a visualization to hold simple content
        screen = new Screen(6);

        // Load the movie frames into the visualization
        loadMovie();

        // Create a message for the movie
        message = InstantiateMessageContent(concatenateMessage());

        // Get the view from the visualization
        view = screen.getView();

        // Set the bounds of the view
        view.setBounds(0, 0, 400, 400);

        // Add the view to the content pane
        contentPane.add(view);
        
        //Add visual content to frames when rendering
        superImp = new TransformableContentSuperimposition(message, 11, 12, SwingConstants.EAST);

        screen.addSuperimposition(superImp);

        screen.start();
    }

    /**
     * Creates a message of type string from different inputs
     * 
     * @return str The concatenated String
     */
    private String concatenateMessage()
    {
        String str = ""; // Temp

        for (int i = 0; i < max; i++)
        {
            if (rootPaneContainer.getParameter("" + i) != null)
                str += rootPaneContainer.getParameter("" + i) + " ";
        }

        return str;
    }

    /**
     * This creates a message for the movie
     */
    private Content InstantiateMessageContent(String message)
    {
        Content content;

        content = new Message(message);

        return content;
    }

    /**
     * Loads the movie frames into the visualization
     */
    public void loadMovie()
    {
        ResourceFinder finder; // Finds file resources
        ContentFactory factory; // Creates sampled content
        Content frame;

        finder = ResourceFinder.createInstance(this);

        factory = new ContentFactory(finder);
        try
        {
            for (int i = 0; i < 10; i++)
            {
                frame = new Content();
                frame = factory.createContent(
                        ImageIO.read(finder.findURL("vortex/vortex0" + i + ".png")), 3);
                screen.add(frame);
            }

            for (int i = 10; i < 24; i++)
            {
                frame = new Content();
                frame = factory.createContent(
                        ImageIO.read(finder.findURL("vortex/vortex" + i + ".png")), 3);
                screen.add(frame);
            }
        }
        catch (IOException ex)
        {
            // Do nothing
            ex.printStackTrace();
        }
    }
}
