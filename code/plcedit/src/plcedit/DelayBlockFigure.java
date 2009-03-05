/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import org.jhotdraw.draw.*;

/**
 *
 * @author Vortex-5
 */
public class DelayBlockFigure extends CodeBlockFigure{
    private final AttributeKey<DelayBlock> MODIFY_LINK_VAL = new AttributeKey("MODIFY_LINK_VAL");
    //private TextChangedListener txtchange;

    @Override
    protected void createModel() {
        accociatedcode = new DelayBlock();
    }

    @Override
    protected void initialize_component() {
        update();
    } 
   
    public void update(){
        willChange();
        createAttributeDisplay(); //create our display area

        attrib.setLayouter(new HorizontalLayouter());
        TextFigure textdelay = new TextFigure(((DelayBlock)accociatedcode).getStringDelayTime());
        textdelay.setAttribute(MODIFY_LINK_VAL, (DelayBlock)accociatedcode);
        attrib.add(textdelay);

        update_base();
        changed();
    }
            
    protected class TextChangedListener implements FigureListener
    {
        DelayBlockFigure caller;
        DelayBlock accociatedcode;
        
        public TextChangedListener(DelayBlockFigure fig, DelayBlock code)
        {
            caller = fig;
            accociatedcode = code;
        }

        public void areaInvalidated(FigureEvent e) {
            System.out.println("detected area invalid");
        }

        public void attributeChanged(FigureEvent e) {
            System.out.println("detected attrib changed");
        }

        public void figureAdded(FigureEvent e) {  
            System.out.println("detected fig added");
        }

        public void figureChanged(FigureEvent e) {          
            System.out.println("Got Change Event (DELAY)");
            if(e.getFigure().getAttribute(MODIFY_LINK_VAL) != null)
            {
                accociatedcode.setDelayTime(Integer.parseInt(((TextFigure)e.getFigure()).getText()));
            }
            
            //accociatedcode.updateEntries();
            caller.update();
            //System.out.println(accociatedcode.getCode());
        }

        public void figureHandlesChanged(FigureEvent e) {
            System.out.println("Detected handles changed");
        }

        public void figureRemoved(FigureEvent e) {   
            
        }

        public void figureRequestRemove(FigureEvent e) {
            System.out.println("Detected removed req");
        }
    }
}
