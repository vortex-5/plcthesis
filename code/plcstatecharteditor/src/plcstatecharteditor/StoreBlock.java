/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author Vortex-5
 */
public class StoreBlock extends CodeBlock{
    public enum Type {Integer, Float, Double, String, Char, Boolean};   
    
    
    public class storeobj 
    {       
        public Type type;
        public String identifier;
        public String value;
        
        storeobj()
        {
            type = Type.String;
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
                    if (this.type != var.type)
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
            strout += store.identifier + " = " + store.value + ";" + newline;
        }
        
        return strout;
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
        removeItemWith("<name>");
        
        addItem();
    }
    
    public List<storeobj> getStores()
    {
        return storelist;
    }
}