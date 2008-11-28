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
    private DirectedLineConnectionModel accociatedcode = new DirectedLineConnectionModel();
    
    DirectedLineConnectionFigure()
    {
        super(); //call the parent make sure all the house keeping operations are done
        decoration = new ArrowTip(Math.PI / 11, 13, 0, false, true, true); //prepare our arrow tip
        
        this.setAttribute(AttributeKeys.END_DECORATION, decoration); //make our graph object directed

        update();
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

    private void update()
    {
        this.removeAllChildren();
        addTextLabel(accociatedcode.getDisplayLabel());
        this.invalidate();
    }
    
    private void addTextLabel(String text)
    {
        this.setLayouter(new LocatorLayouter());
        TextFigure label = new TextFigure(text);
        LocatorLayouter.LAYOUT_LOCATOR.set(label, new BezierLabelLocator(0.3, -Math.PI / 4, 10.0));
        //TODO: add logic to hook onto a text change event so we can update the fields
        this.add(label);
    }
}
    
