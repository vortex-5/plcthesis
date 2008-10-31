/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import org.jhotdraw.draw.*;


/**
 *
 * @author Vortex-5
 */
public class DirectedLineConnectionFigure extends LabeledLineConnectionFigure implements ConnectionFigure, Typed {
    private LineDecoration decoration;
    
    DirectedLineConnectionFigure()
    {
        super(); //call the parent make sure all the house keeping operations are done
        decoration = new ArrowTip(Math.PI / 11, 13, 0, false, true, true); //prepare our arrow tip
        
        this.setAttribute(AttributeKeys.END_DECORATION, decoration); //make our graph object directed
    }

    @Override
    public void addNotify(Drawing arg0) {
        super.addNotify(arg0);
        
        //TODO: add section to get code 
    }  
    
    public CodeType getType() {
        return CodeType.Goto;
    }

    public String getTypeString() {
        return "GOTO";
    }
    
    private void addTextBox()
    {
        this.setLayouter(new LocatorLayouter());
    }
}
    
