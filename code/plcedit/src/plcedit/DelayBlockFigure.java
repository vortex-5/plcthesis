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
    private TextChangedListener txtchange; //used for user set text box
    private DelayBlock SavedBlock = null;

    @Override
    protected void createModel() {
        if (SavedBlock == null) {
            accociatedcode = new DelayBlock();
        }
        else {
            accociatedcode = SavedBlock;
            SavedBlock = null;
        }

        txtchange = new TextChangedListener(this, (DelayBlock)accociatedcode);
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
        textdelay.setAttribute(SpecialAttributeKeys.MODIFY_LINK_DELAY, (DelayBlock)accociatedcode);
        textdelay.addFigureListener(txtchange);
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
        }

        public void attributeChanged(FigureEvent e) {
        }

        public void figureAdded(FigureEvent e) {  
        }

        public void figureChanged(FigureEvent e) {          
            DelayBlock edit = (DelayBlock)e.getFigure().getAttribute(SpecialAttributeKeys.MODIFY_LINK_DELAY);
            edit.setDelayTime(((TextFigure)e.getFigure()).getText());

            caller.update();
        }

        public void figureHandlesChanged(FigureEvent e) {
        }

        public void figureRemoved(FigureEvent e) {   
            
        }

        public void figureRequestRemove(FigureEvent e) {
        }
    }



    @Override
    public void write(DOMOutput out) throws IOException {
        writeBoundingBox(out); //save our position data

        writeUID(out);
      
        out.addAttribute("data_delay", getModel().getStringDelayTime());

        //writeAttributes(out); //export all attributes
    }

    @Override
    public void read(DOMInput in) throws IOException {
        readSavedBounds(in);

        String delay = in.getAttribute("data_delay", "-1");

        SavedBlock = new DelayBlock();
        SavedBlock.setUID(readUID(in));
        SavedBlock.setDelayTime(Integer.parseInt(delay));

        readAttributes(in);
    }
}
