/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import org.jhotdraw.draw.*;

import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;
import java.io.IOException;

/**
 *
 * @author Vortex-5
 */
public class DelayBlockFigure extends CodeBlockFigure{
    private final AttributeKey<DelayBlock> MODIFY_LINK_VAL = new AttributeKey("MODIFY_LINK_VAL");
    private DelayBlock SavedBlock = null;
    //private TextChangedListener txtchange;

    @Override
    protected void createModel() {
        if (SavedBlock == null) {
            accociatedcode = new DelayBlock();
        }
        else {
            accociatedcode = SavedBlock;
            SavedBlock = null;
        }
    }

    @Override
    public DelayBlock getModel()
    {
        return (DelayBlock)accociatedcode;
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



    @Override
    public void write(DOMOutput out) throws IOException {
        writeBoundingBox(out); //save our position data

        out.addAttribute("delay", getModel().getStringDelayTime());

        writeAttributes(out); //export all attributes
    }

    @Override
    public void read(DOMInput in) throws IOException {
        Bounds box = readBoundingBox(in);
        setBounds(box.getTopLeft(), box.getBottomRight()); //set our object location

        SavedBlock = new DelayBlock();
        SavedBlock.setDelayTime(in.getAttribute("delay", "-1"));

        readAttributes(in);
    }
}
