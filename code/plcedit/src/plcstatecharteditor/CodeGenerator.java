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
import javax.swing.JOptionPane;

/**
 *
 * @author huangkf
 */
public class CodeGenerator implements ActionListener {
    private DrawingEditor editor;
    private CodeViewer codeview;
    
    public CodeGenerator(DrawingEditor editor)
    {
        this.editor = editor;
    }

    public void actionPerformed(ActionEvent arg0) {
        
        List<Figure> allfigs = new ArrayList<Figure>(editor.getActiveView().getDrawing().getChildren());
        
        //Search for start block
        int startblocksfound = 0;
        
        String compiledbuffer = "";

        //SANITY CHECKS

        List<StoreBlock.storeobj> allstores = new ArrayList<StoreBlock.storeobj>();

        //Check the variable types and make sure we don't have a type conflict
        for (int i=0;i<allfigs.size();i++)
        { //Grab all declarations
            Typed t = (Typed)allfigs.get(i);

            if (t.getType() == CodeType.Store) {
                allstores.addAll(((StoreBlockFigure)allfigs.get(i)).getModel().getStores());
            }
        }


        //we perform a for each on every block and extract all the variables from
        //each store block first

        compiledbuffer += "// VARIABLE DECLARATIONS //\n";

        List<String> declarelist = new ArrayList<String>();

        for (int i=0;i<allfigs.size();i++) { //sort all declarations make sure we have no duplicates
            Typed t = (Typed)allfigs.get(i);

            if (t.getType() == CodeType.Store) {

                //Sanity check get precheck type conflict
                for(StoreBlock.storeobj store : ((StoreBlockFigure)allfigs.get(i)).getModel().getStores())
                {
                    if (store.isInTypeConflict(allstores))
                    {
                        compiledbuffer = "COMPILE FAILED DUE TO TYPE CONFLICT ON " + store.identifier;
                        ShowViewer(compiledbuffer);

                        return;
                    }
                }

                for(String declaration : ((StoreBlockFigure)allfigs.get(i)).getModel().getDeclaration())
                {
                    if (!declarelist.contains(declaration))
                    {
                        declarelist.add(declaration);
                    }
                }
            }
        }

        for (String declaration : declarelist) //output list of non duplicated declarations
        {
            compiledbuffer += declaration;
        }




        
        CodeBlockFigure startfig=null; //used to maintain start code priority aka at top of file
        
        for (int i=0;i<allfigs.size();i++) {
            Typed t = (Typed)allfigs.get(i);
            
            if (t.getType() == CodeType.Start) {
                startblocksfound++;
                startfig = (CodeBlockFigure)allfigs.get(i);
                compiledbuffer += startfig.getModel().getCode() + "\n";

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

        ShowViewer(compiledbuffer);
    }

    private void ShowViewer(String compiledbuffer)
    {
        if (codeview != null)
        {
            codeview.dispose();
            codeview = null;
        }
        codeview = new CodeViewer();

        codeview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        codeview.setCode(compiledbuffer);
        codeview.setVisible(true);
    }
    
    private String getJumps(CodeBlockFigure block, List<DirectedLineConnectionFigure> edges)
    {
        String gotobuffer = "";
        //find any edges that exit this block
        for (DirectedLineConnectionFigure edge : edges) {
            if (edge.getStartFigure().equals(block)) {
                    gotobuffer += edge.getGuardCode() + ((CodeBlockFigure)edge.getEndFigure()).accociatedcode.getUIDStringJump() + "\n";
            }
        }
        
        gotobuffer += "goto EOF;\n\n";
        
        return gotobuffer;        
    }
}
