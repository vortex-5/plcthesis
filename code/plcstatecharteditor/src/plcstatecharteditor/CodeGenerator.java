/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import org.jhotdraw.draw.*;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author huangkf
 */
public class CodeGenerator implements ActionListener {
    private DrawingEditor editor;
    
    public CodeGenerator(DrawingEditor editor)
    {
        this.editor = editor;
    }

    public void actionPerformed(ActionEvent arg0) {
        
        List<Figure> allfigs = new ArrayList<Figure>(editor.getActiveView().getDrawing().getChildren());
        
        //Search for start block
        int startblocksfound = 0;
        
        String compiledbuffer = "CODE GENERATION FAILED!\n";

        //TODO: check and test the variable declaration block
        //Variable declarations are done first before any code is actually compiled


        //we perform a for each on every block and extract all the variables from
        //each store block first

        List<String> declarelist = new ArrayList<String>();

        for (int i=0;i<allfigs.size();i++) {
            Typed t = (Typed)allfigs.get(i);

            if (t.getType() == CodeType.Store) {
                for(String declaration : (StoreBlockFigure)allfigs.get(i).get)

            }
        }




        
        CodeBlockFigure startfig=null; //used to maintain start code priority aka at top of file
        
        for (int i=0;i<allfigs.size();i++) {
            Typed t = (Typed)allfigs.get(i);
            
            if (t.getType() == CodeType.Start) {
                startblocksfound++;
                startfig = (CodeBlockFigure)allfigs.get(i);
                compiledbuffer = startfig.getModel().getCode() + "\n"; 

                allfigs.remove(i);
                i--;
            }
        }
        
        //check condition for start blocks (must only have one)
        if (startblocksfound != 1)
        {
            compiledbuffer += "must have exactly 1 start block! Blocks found =" + String.valueOf(startblocksfound);
        }
        
        //move all edges out of the block check routine to make it faster to search for edges.
        List<DirectedLineConnectionFigure> edges = new Vector<DirectedLineConnectionFigure>();
        
        for (int i=0;i<allfigs.size();i++) {
            if (((Typed)allfigs.get(i)).getType() == CodeType.Goto) {
                edges.add((DirectedLineConnectionFigure)allfigs.get(i));
                
                allfigs.remove(i);
                i--;
            }
        }
        
        System.out.println("edges extracted successfully!");
        
        
        //perform start block edges
        
        compiledbuffer += getJumps(startfig,edges);
        
        
        //can through the list of all remaining code blocks and add them to the compiled code.
        //must ensure that goto's are checked per block source.
        for (Figure blockfig : allfigs) {
            CodeBlockFigure block = (CodeBlockFigure)blockfig; //recast makes things easier to work with
            compiledbuffer +=  block.accociatedcode.getCode() + "\n";
            
            compiledbuffer += getJumps(block,edges);
        }
        
        compiledbuffer += "EOF:\n"; //add the EOF directive so that any unmapped edges get dumped to end.
        
        CodeViewer cdview = new CodeViewer();
        cdview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cdview.setCode(compiledbuffer);
        cdview.setVisible(true);
        
    }
    
    private String getJumps(CodeBlockFigure block, List<DirectedLineConnectionFigure> edges)
    {
        String gotobuffer = "";
        //find any edges that exit this block
        for (DirectedLineConnectionFigure edge : edges) {
            if (edge.getStartFigure().equals(block)) {
                    gotobuffer += ((CodeBlockFigure)edge.getEndFigure()).accociatedcode.getUIDStringJump() + "\n";
            }
        }
        
        gotobuffer += "goto EOF;\n\n";
        
        return gotobuffer;        
    }
}
