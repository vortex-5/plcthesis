/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.util.List;
import org.jhotdraw.draw.Figure;

/**
 *
 * @author Vortex-5
 */
public class UIDGenerator {
    private static int current = -1;
    
    
    public static void reset()
    {
        current = -1;
    }

    public static void resetFigures(List<Figure> figures)
    {
        reset(); //reset base counter

        //reset all BUID's based on the list

        for (Figure fig : figures)
        {
            if (fig instanceof CodeBlockFigure)
            {
                ((CodeBlockFigure)fig).getModel().setUID(getNext());
            }
        }

    }

    /**
     * When loading from saved file we need to make sure our newly generated
     * UID's don't conflict with existing UID's
     * @param index
     */
    public static void jumpTo(int index)
    {
        if (current < index) current = index;
        out();
    }

    public static int getNext()
    {
        current++;

        out();

        return current;
    }

    private static void out()
    {
        //System.out.println("CURRENT UID: [" + Integer.toString(current) + "]");
    }

}
