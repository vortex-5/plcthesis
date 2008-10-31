/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import java.awt.geom.Point2D.Double;
import java.util.Collection;
import javax.swing.Action;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.FigureEvent;
import org.jhotdraw.draw.FigureListener;
import org.jhotdraw.draw.GraphicalCompositeFigure;
import org.jhotdraw.draw.HorizontalLayouter;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.VerticalLayouter;

import plcstatecharteditor.StoreBlock.storeobj;
/**
 *
 * @author Vortex-5
 */
public class StoreBlockFigure extends CodeBlockFigure{  
    private final AttributeKey<storeobj> MODIFY_LINK_ID = new AttributeKey("MODIFY_LINK_ID");
    private final AttributeKey<storeobj> MODIFY_LINK_VAL = new AttributeKey("MODIFY_LINK_VAL");
    
    private TextChangedListener txtchange;

    @Override
    public GraphicalCompositeFigure clone() {
        return super.clone();
    }
    
    
    @Override
    protected void createModel() {
        accociatedcode = new StoreBlock();
        txtchange = new TextChangedListener(this,(StoreBlock)accociatedcode);
    }

    @Override
    protected void initialize_component() {  
        update();
    }
    
    
    
    protected void updateAttributesFromData()
    {
        ((StoreBlock)accociatedcode).updateEntries();
       
        attrib = new GraphicalCompositeFigure();
        
        attrib.setLayouter(new VerticalLayouter());
        GraphicalCompositeFigure line;
        for(storeobj store : ((StoreBlock)accociatedcode).getStores())
        {
            line = new GraphicalCompositeFigure();
            line.setLayouter(new HorizontalLayouter());
            
            TextFigure storeid = new TextFigure(store.identifier);
            storeid.addFigureListener(txtchange);
            storeid.setAttribute(MODIFY_LINK_ID, store);
            line.add(storeid);
            
            TextFigure eq = new TextFigure(" = ");
            eq.setEditable(false);
            
            line.add(eq);
            
            TextFigure storeval = new TextFigure(store.value);
            storeval.addFigureListener(txtchange);
            storeval.setAttribute(MODIFY_LINK_VAL, store);
            line.add(storeval);
            attrib.add(line);
        }
        
        System.out.println(accociatedcode.getCode());
        
        update_base();
    }
    
    public void update()
    {
        this.willChange();
        updateAttributesFromData();
        this.changed();
    }
    
    protected class TextChangedListener implements FigureListener
    {
        StoreBlockFigure caller;
        StoreBlock accociatedcode;
        
        public TextChangedListener(StoreBlockFigure fig, StoreBlock code)
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
            storeobj edit;
            
            System.out.println("Got Change Event");
            if (e.getFigure().getAttribute(MODIFY_LINK_ID) != null)
            {
                edit = (storeobj)e.getFigure().getAttribute(MODIFY_LINK_ID);
                edit.identifier = ((TextFigure)e.getFigure()).getText();
                
                System.out.println("got ID");
                System.out.println(edit.identifier);
            }
            else if(e.getFigure().getAttribute(MODIFY_LINK_VAL) != null)
            {
                edit = (storeobj)e.getFigure().getAttribute(MODIFY_LINK_VAL);
                edit.value = ((TextFigure)e.getFigure()).getText();
                
                System.out.println("got Val");
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
