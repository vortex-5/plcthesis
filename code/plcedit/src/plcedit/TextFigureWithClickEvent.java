/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.Tool;
import org.jhotdraw.draw.Figure;

/**
 *
 * @author huangkf
 */
public class TextFigureWithClickEvent extends TextFigure{
    public static final boolean DEBUG = true;
    public ActionListener _listener = null;


    public TextFigureWithClickEvent(String label)
    {
        this(label, null);
    }

    public TextFigureWithClickEvent(String label, ActionListener listener)
    {
        super(label);
        _listener = listener;
    }


    @Override
    public boolean handleMouseClick(Double p, MouseEvent evt, DrawingView view) {
        if (DEBUG) {System.out.println("Got click event attempting to float a open a menu item");}

        Figure source = this.findFigureInside(p);

        //safety of object is ensured by making sure the type attribute is linked to an object
        CreatesContextMenu target = (CreatesContextMenu)source.getAttribute(SpecialAttributeKeys.MODIFY_LINK_CREATESCONTEXTMENU);
        if (target != null)
        {
            //attempt to retrieve our object it would be unsafe but luckilywe
            (target.getContextMenu(_listener)).show(view.getComponent(), (int)p.x, (int)p.y);
        }

        return super.handleMouseClick(p, evt, view);
    }

    public void assignChangeListener(ActionListener listener)
    {
        _listener = listener;
    }

    public void removeChangeListener(ActionListener listener)
    {
        _listener = null;
    }

}
