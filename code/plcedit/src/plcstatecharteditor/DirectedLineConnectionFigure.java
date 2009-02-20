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

        addTextLabel("true");
    }


    @Override
    public void addNotify(Drawing arg0) {
        super.addNotify(arg0);
    }  
    
    public CodeType getType() {
        return CodeType.Goto;
    }

    public String getTypeString() {
        return "GOTO";
    }

    
    private void addTextLabel(String text)
    {
        this.setLayouter(new LocatorLayouter());
        TextFigure label = new TextFigure(text);
        LocatorLayouter.LAYOUT_LOCATOR.set(label, new BezierLabelLocator(0.3, -Math.PI / 4, 10.0));
        this.add(label);
    }

    public String getGuardCode() //this is used during code generation to update the guard conditions
    {
        TextFigure label = (TextFigure)getChild(0);
        String buffer = "";
        if (!label.getText().equals("true"))
            buffer = "if (" + label.getText() + ") ";
        return buffer;
    }


}
    
