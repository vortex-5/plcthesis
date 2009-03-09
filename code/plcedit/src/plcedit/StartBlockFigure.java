/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

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

    @Override
    public void write(DOMOutput out) throws IOException {
        writeBoundingBox(out);
        // Start blocks don't have any useful accociated code so we can accept
        // that they are rebuilt on read
        writeAttributes(out);

    }

    @Override
    public void read(DOMInput in) throws IOException {
        Bounds box = readBoundingBox(in);
        setBounds(box.getTopLeft(), box.getBottomRight());
        readAttributes(in);
        // Normally we would load the accocciated code here but the start block
        // has none so we can accept auto generating it.
    }
    
    
    
    
}
