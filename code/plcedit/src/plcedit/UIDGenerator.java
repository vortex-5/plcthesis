/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

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
