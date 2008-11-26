/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

/**
 *
 * @author Vortex-5
 */
public class StoreBlock extends CodeBlock{
    

    public class storeobj 
    {              

        public CodeVarType type;
        public String identifier;
        public String value;
        
        storeobj()
        {
            type = new CodeVarType(CodeVarType.VarType.Undefined);
            identifier = "";
            value = "";
        }
        
        /**
         * When the compile button is hit a type conflict on all variables are checked. Since in the gui editor design currently
         * there will be no support for local variables we are doing a type conflict check on all variables.
         * 
         * @param allvariables
         * @return
         */
        public boolean isInTypeConflict(List<storeobj> allvariables)
        {
            boolean ret = false;
            for(storeobj var : allvariables)
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
    
    private List<storeobj> storelist; 
    
    
    public StoreBlock()
    {
        this.uid = UIDGenerator.getNext();
        this.ctype = CodeType.Store;
        this.storelist = new Vector<storeobj>();
    }

    @Override
    public String getTypeString() {
        return "STORE";
    }


    @Override
    public String getCode() {
        String strout = getUIDStringLabel() + newline +
               "//////////////////////////////////////" + newline +
               "//        STORE                     //" + newline +
               "//////////////////////////////////////" + newline;
        
        for (storeobj store : storelist)
        {
            if (store.type.getType() != CodeVarType.VarType.Undefined)
                strout += store.identifier + " = " + store.value + ";" + newline;
        }
        
        return strout;
    }

    public List<String> getDeclaration() {
        List<String> listout = new ArrayList<String>();

        //TODO: finish implementing the variable declaration blocks
        for(storeobj item : storelist)
        {
            if (item.type.getType() == CodeVarType.VarType.Undefined)
            {
                //throw new TypeNotPresentException(item.identifier, new Exception());
            }
            else
            {
                //valid type found attempt to compile into a declaration string;
                String newitem = item.type.toCompileString() + " " + item.identifier + ";\n";
                listout.add(newitem);
            }
        }


        return listout;

    }
    
    public void addItem()
    {
        storeobj newitem = new storeobj();
        newitem.identifier = "<name>";
        newitem.value = "<value>";
        storelist.add(newitem);
    }
    
    public void removeItem(storeobj ref)
    {
        storelist.remove(ref);
    }


    public void removeItemWith(CodeVarType.VarType type, String identifier)
    {
        for(int i=0;i<storelist.size();i++)
        {
            if (storelist.get(i).type.getType() == type && storelist.get(i).identifier.equals(identifier))
            {
                storelist.remove(i);
                i--;
            }
        }
    }
    public void removeItemWith(String identifier, String value)
    {
        for(int i=0;i<storelist.size();i++)
        {
            if (storelist.get(i).identifier.equals(identifier) && storelist.get(i).value.equals(value))
            {
                storelist.remove(i);
                i--;
            }
        }
    } 
    
    public void removeItemWith(String identifier)
    {
        for(int i=0;i<storelist.size();i++)
        {
            if (storelist.get(i).identifier.equals(identifier))
            {
                storelist.remove(i);
                i--;
            }
        }
    }
    
    public void updateEntries()
    {
        removeItemWith("");
        removeItemWith(CodeVarType.VarType.Undefined,"<name>");
        
        addItem();
    }

    public boolean contains(String identifier)
    {
        for(storeobj item : storelist)
        {
            if (item.identifier.equals(identifier))
            {
                return true;
            }
        }
        return false;
    }
    
    public List<storeobj> getStores()
    {
        return storelist;
    }
}