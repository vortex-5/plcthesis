/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author huangkf
 */
public class InputBlock extends CodeBlock{

    private String variable;

    public InputBlock() {
        this(UIDGenerator.getNext());
    }

    public InputBlock(int uid) {
        this.uid = uid;
        this.ctype = CodeType.Input;


        //TODO: determine if we want a default port at all
        this.variable = "<unspecified>";
    }

    public String getDisplayedPort() {
        return "PORTIN";
    }


    public String getCompiledPort() {
        return "PORTIN";
    }

    public void setVariable (String var) {
        this.variable = var;
    }

    public String getVariable () {
        return this.variable;
    }

    @Override
    public String getTypeString() {
        return "INPUT";
    }

    @Override
    public String getCode() throws Exception{
        if (getVariable().equals("<unspecified>"))
        {
            throw new Exception ("Compile Error: Input assigned to an <unspecified> variable\n");
        }

        return getUIDStringLabel() + newline +
                "//////////////////////////////////////" + newline +
                "//        Input                    //" + newline +
                "//////////////////////////////////////" + newline +
                getVariable() + " = " + getCompiledPort() + ";";
    }



}
