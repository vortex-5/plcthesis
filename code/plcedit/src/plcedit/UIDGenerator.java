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
    
    public static int getNext()
    {
        current++;
        return current;
    }

}
