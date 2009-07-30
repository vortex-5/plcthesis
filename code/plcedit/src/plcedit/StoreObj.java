/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.util.List;

/**
 *
 * @author Vortex-5
 */
public class StoreObj
{

    public CodeVarType type;
    public String identifier;
    public String value;

    StoreObj()
    {
        type = new CodeVarType(CodeVarType.VarType.Undefined);
        identifier = "";
        value = "";
    }

    StoreObj(CodeVarType type, String id, String val)
    {
        this.type = type;
        this.identifier = id;
        this.value = val;
    }

    /**
     * When the compile button is hit a type conflict on all variables are checked. Since in the gui editor design currently
     * there will be no support for local variables we are doing a type conflict check on all variables.
     *
     * @param allvariables
     * @return
     */
    public boolean isInTypeConflict(List<StoreObj> allvariables)
    {
        boolean ret = false;
        for(StoreObj var : allvariables)
        {
            if(this.identifier.equals(var.identifier))
            {
                if (this.type.getType() != var.type.getType())
                {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }


    /**
     * returns the inital value a variable should be initialized to
     * @return
     */
    public String getInitalValue()
    {
        switch (this.type.getType())
        {
            case Bool:
                return " = 0";
            case Byte:
                return " = 0x0";
            case Double:
                return " = 0.0";
            case Float:
                return " = 0";
            case Int:
                return " = 0";
            case Long:
                return " = 0";
            default:
                return "";
        }
    }
}
