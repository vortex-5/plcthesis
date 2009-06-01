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
     * Checks to see if the input data can be converted to the correct data type
     * and if any parsing errors could be created by the compliled coded.
     * @return
     */
    public boolean isDataValid() throws Exception
    {
        throw new Exception("Not Implimented yet!");
    }
}
