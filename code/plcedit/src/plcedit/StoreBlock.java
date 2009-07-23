package plcedit;

import java.util.List;
import java.util.Vector;
import java.util.ArrayList;

/**
 *
 * @author Vortex-5
 */
public class StoreBlock extends CodeBlock{
       
    private List<StoreObj> storelist;
    
    
    public StoreBlock(int uid)
    {
        this.uid = uid;
        this.ctype = CodeType.Store;
        this.storelist = new Vector<StoreObj>();
    }

    public StoreBlock()
    {
        this(UIDGenerator.getNext());
    }

    @Override
    public String getTypeString() {
        return "STORE";
    }


    @Override
    public String getCode() throws Exception{
        String strout = getUIDStringLabel() + newline +
               "//////////////////////////////////////" + newline +
               "//        STORE                     //" + newline +
               "//////////////////////////////////////" + newline;
        
        for (StoreObj store : storelist)
        {
            if (store.type.getType() != CodeVarType.VarType.Undefined)
                strout += store.identifier + " = " + store.value + ";" + newline;
        }
        
        return strout;
    }

    public List<String> getDeclaration() {
        List<String> listout = new ArrayList<String>();

        for(StoreObj item : storelist)
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
        StoreObj newitem = new StoreObj();
        newitem.identifier = "<name>";
        newitem.value = "<value>";
        storelist.add(newitem);
    }

    public void addItem(String id, String val, CodeVarType type)
    {
        StoreObj newitem = new StoreObj(type,id,val);
        storelist.add(newitem);

    }
    
    public void removeItem(StoreObj ref)
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
        removeUnsavedEntries();
        
        addItem();
    }

    public void removeUnsavedEntries()
    {
        removeItemWith("");
        removeItemWith(CodeVarType.VarType.Undefined,"<name>");
    }

    public boolean contains(String identifier)
    {
        for(StoreObj item : storelist)
        {
            if (item.identifier.equals(identifier))
            {
                return true;
            }
        }
        return false;
    }
    
    public List<StoreObj> getStores()
    {
        return storelist;
    }
}