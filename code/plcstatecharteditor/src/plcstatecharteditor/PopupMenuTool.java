/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import java.awt.event.MouseEvent;
import org.jhotdraw.draw.AbstractTool;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.TextHolderFigure;
import org.jhotdraw.draw.TextTool;

/**
 *
 * @author huangkf
 */
public class PopupMenuTool extends TextTool{
    TextFigure _caller;
    CodeVarType _source;



    PopupMenuTool(CodeVarType source, TextFigure prototype)
    {
        super(prototype);

        _caller = prototype;
        _source = source;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        System.out.println("clicked!");

        super.mouseClicked(evt);
    }

    /*
    @Override
    protected void beginEdit(TextHolderFigure textHolder) {
        
        _source.createContextMenu();


        //super.beginEdit(textHolder);
    }
    */









}

/*

public class PopupMenuTool extends AbstractTool{
    TextFigure _displaytarget;
    CodeVarType _source;

    public PopupMenuTool(CodeVarType source, TextFigureWithClickEvent target) {
        _displaytarget = target;
        _source = source;

        System.out.println("Tool created");
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        System.out.println("Recieved click");

        super.mouseClicked(evt);


    }



    public void mouseDragged(MouseEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }







}

*/


