/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author Vortex-5
 */
public class StartBlock extends CodeBlock{
    
    public StartBlock()
    {
        this.uid = UIDGenerator.getNext();
        this.ctype = CodeType.Start;
    }

    @Override
    public String getTypeString() {
        return "START";
    }

    @Override
    public String getCode() {
        return getUIDStringLabel() + newline +
               "//////////////////////////////////////" + newline +
               "//        PROGRAM START             //" + newline +
               "//////////////////////////////////////";
        
    }
   
}
