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
        this.ctype = CodeType.Output;


        //TODO: determine if we want a default port at all
        this.variable = "0x0";
    }

    public String getDisplayedPort() {
        return "PORTIN";
    }


    public String getCompiledPort() {
        return "PORTIN";
    }

    public void setValue (String value) {
        this.variable = value;
    }

    public String getValue () {
        return this.variable;
    }

    @Override
    public String getTypeString() {
        return "OUTPUT";
    }

    @Override
    public String getCode() {
        return getUIDStringLabel() + newline +
                "//////////////////////////////////////" + newline +
                "//        Input                    //" + newline +
                "//////////////////////////////////////" + newline +
                getCompiledPort() + " = " + getValue() + ";";
    }



}
