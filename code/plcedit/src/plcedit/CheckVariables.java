/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package plcedit;

import java.util.*;
import org.jhotdraw.draw.*;


/**
 *
 * @author huangkf
 */
public class CheckVariables {
    public boolean isInConflict = false;
    public List<String> VariableList = new ArrayList<String>();

    public CheckVariables(List<Figure> figs)
    {
        List<StoreBlock.storeobj> allstores = new ArrayList<StoreBlock.storeobj>();

        for (int i=0;i<figs.size();i++) { //sort all declarations make sure we have no duplicates
            Typed t = (Typed)figs.get(i);

            if (t.getType() == CodeType.Store) {

                //Sanity check get precheck type conflict
                for(StoreBlock.storeobj store : ((StoreBlockFigure)figs.get(i)).getModel().getStores())
                {
                    if (store.isInTypeConflict(allstores))
                    {
                        isInConflict = true;
                        break;
                    }
                }

                if (!isInConflict)
                {
                    for(String declaration : ((StoreBlockFigure)figs.get(i)).getModel().getDeclaration())
                    {
                        if (!VariableList.contains(declaration))
                        {
                            VariableList.add(declaration);
                        }
                    }
                }
            }
        }
    }
}
