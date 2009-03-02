/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;
import java.io.IOException;

/**
 *
 * @author Vortex-5
 */
public class StartBlockFigure extends CodeBlockFigure {
    
    protected void createModel()
    {
        accociatedcode = new StartBlock();
    }

    @Override
    protected void initialize_component() {
        willChange();
        update_base();
        changed();
        
    }

    public void write(DOMOutput out) throws IOException {
        Rectangle2D.Double r = getBounds();
        out.addAttribute("x", r.x);
        out.addAttribute("y", r.y);
        out.addAttribute("w", r.width);
        out.addAttribute("h", r.height);
        // Start blocks don't have any useful accociated code so we can accept
        // that they are rebuilt on read
        writeAttributes(out);

    }

    public void read(DOMInput in) throws IOException {
                double x = in.getAttribute("x", 0d);
        double y = in.getAttribute("y", 0d);
        double w = in.getAttribute("w", 0d);
        double h = in.getAttribute("h", 0d);
        setBounds(new Point2D.Double(x,y), new Point2D.Double(x+w,y+h));
        readAttributes(in);
        // Normally we would load the accocciated code here but the start block
        // has none so we can accept auto generating it.
    }
    
    
    
    
}
