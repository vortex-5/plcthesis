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
    
    public StartBlock(int uid)
    {
        this.uid = uid;
        this.ctype = CodeType.Start;
    }

    public StartBlock()
    {
        this(UIDGenerator.getNext());
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
