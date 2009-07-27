/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huangkf
 */
public class InputBlock extends CodeBlock{

    private StoreObj variable;

    public InputBlock() {
        this(UIDGenerator.getNext());
    }

    public InputBlock(int uid) {
        this.uid = uid;
        this.ctype = CodeType.Input;


        //TODO: determine if we want a default port at all
        this.variable = new StoreObj();
        this.variable.type.SetType(CodeVarType.VarType.Undefined);
        this.variable.identifier = "<unspecified>";
        this.variable.value = "PORTIN";
    }

    public String getDisplayedPort() {
        return this.variable.value;
    }


    public String getCompiledPort() {
        return this.variable.value;
    }

    public void setVariable (String var) {
        this.variable.identifier = var;
    }

    public String getVariable () {
        return this.variable.identifier;
    }

    public StoreObj getStoreObj () {
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

    public List<String> getDeclaration() {
        List<String> listout = new ArrayList<String>();

        listout.add(variable.type.toCompileString() + " " + variable.identifier + ";\n");

        return listout;
    }



}
