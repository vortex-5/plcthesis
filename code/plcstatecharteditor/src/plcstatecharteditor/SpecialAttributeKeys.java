/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

import org.jhotdraw.draw.AttributeKey;

/**
 *
 * @author huangkf
 */
public class SpecialAttributeKeys {
    /*Used in store blocks*/
    public static final AttributeKey<StoreBlock.storeobj> MODIFY_LINK_ID = new AttributeKey("MODIFY_LINK_ID");
    public static final AttributeKey<StoreBlock.storeobj> MODIFY_LINK_VAL = new AttributeKey("MODIFY_LINK_VAL");
    public static final AttributeKey<CodeVarType> MODIFY_LINK_TYPE = new AttributeKey("MODIFY_LINK_TYPE");
    
    /*Used in transitions*/
    public static final AttributeKey<CodeVarType> MODIFY_LINK_TRANSITION = new AttributeKey("MODIFY_LINK_TRANSITION");
}
