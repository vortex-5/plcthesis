/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author Vortex-5
 */
public class Variable {
    public StoreBlock.storeobj identity;
    public Object value;

    public Variable(StoreBlock.storeobj id, Object value)
    {
        this.identity = id;
        this.value = value;
    }

}
