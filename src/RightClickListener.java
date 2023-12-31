import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Handles right-click in the input counter.
 */
public class RightClickListener extends MouseAdapter
{
    /**
     * Runs when the right click button is clicked
     * @param e The event to be processed
     */
    public void mousePressed(MouseEvent e) {
        possiblyShowRightClick(e);
    }

    /**
     * Runs when the right click button is released.
     * @param e The event to be processed.
     */
    public void mouseReleased(MouseEvent e) {
        possiblyShowRightClick(e);
    }

    /**
     * Calls the right click menu.
     * @param e The event to be processed.
     */
    private void possiblyShowRightClick(MouseEvent e) {
        if (e.isPopupTrigger()) {
            GUI.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}
