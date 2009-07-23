/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

/**
 *
 * @author Vortex-5
 */
public interface CreatesContextMenu {
    public JPopupMenu getContextMenu(ActionListener listener);
}
