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
    public List<StoreObj> VariableList = new ArrayList<StoreObj>();

    public CheckVariables(List<Figure> figs)
    {
        List<StoreObj> allstores = new ArrayList<StoreObj>();

        for (int i=0;i<figs.size();i++) { //sort all declarations make sure we have no duplicates
            Typed t = (Typed)figs.get(i);

            if (t.getType() == CodeType.Store) {

                //Sanity check get precheck type conflict
                for(StoreObj store : ((StoreBlockFigure)figs.get(i)).getModel().getStores())
                {
                    if (store.isInTypeConflict(allstores))
                    {
                        isInConflict = true;
                        break;
                    }
                }

                if (!isInConflict)
                {
                    for(StoreObj declaration : ((StoreBlockFigure)figs.get(i)).getModel().getStores())
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
