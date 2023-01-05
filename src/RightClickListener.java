import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightClickListener extends MouseAdapter
{
    public void mousePressed(MouseEvent e) {
        possiblyShowRightClick(e);
    }

    public void mouseReleased(MouseEvent e) {
        possiblyShowRightClick(e);
    }

    private void possiblyShowRightClick(MouseEvent e) {
        if (e.isPopupTrigger()) {
            GUI.popupMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}
