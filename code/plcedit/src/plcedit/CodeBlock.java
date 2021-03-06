/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author huangkf
 */
public abstract class CodeBlock implements Typed {
    
    protected CodeType ctype;
    protected int uid=-1; //unique     identifier used in debugging
    
    protected static String newline = System.getProperty("line.separator");

    public int getUID() {
        return uid;
    }

    public void setUID(int newuid) {
        uid = newuid;
    }
    
    /**
     * Can be used to recieve the type of code block being rendered
     * @return Returns the enumerated type of code block being queried
     */
    public CodeType getType()
    {
        return ctype;
    }

    
    /**
     * Returns a formatted string for the type of block currently being
     * modeled
     * @return String equivalent for the enum type
     */
    abstract public String getTypeString();
    
    
    /**
     * Returns the actual code implementation for the block note the code
     * will not include the requisite jump statements this is added through
     * graph traversal
     * 
     * @return A string corresponding to the code
     */
    abstract public String getCode() throws Exception;
    
    public String getUIDStringLabel()
    {
        return "BLUID" + String.valueOf(uid)+ ":";
    }
    
    public String getUIDStringJump()
    {
        return "goto BLUID" + String.valueOf(uid) + ";";
    }
}
