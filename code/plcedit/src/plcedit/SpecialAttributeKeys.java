/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import org.jhotdraw.draw.AttributeKey;

/**
 *
 * @author huangkf
 */
public class SpecialAttributeKeys {
    /*Used globally*/
    public static final AttributeKey<StoreBlock.storeobj> MODIFY_LINK_CREATESCONTEXTMENU = new AttributeKey("MODIFY_LINK_CREATESCONTEXTMENU");


    /*Used in store blocks*/
    public static final AttributeKey<StoreBlock.storeobj> MODIFY_LINK_STORE_ID = new AttributeKey("MODIFY_LINK_STORE_ID");
    public static final AttributeKey<StoreBlock.storeobj> MODIFY_LINK_STORE_VAL = new AttributeKey("MODIFY_LINK_STORE_VAL");
    public static final AttributeKey<CodeVarType> MODIFY_LINK_STORE_TYPE = new AttributeKey("MODIFY_LINK_STORE_TYPE");
    
    /*Used in transitions*/
    public static final AttributeKey<CodeVarType> MODIFY_LINK_TRANSITION = new AttributeKey("MODIFY_LINK_TRANSITION");

    /*Used in Output */
    public static final AttributeKey<CodeVarType> MODIFY_LINK_OUTPUT_BLOCK = new AttributeKey("MODIFY_LINK_OUTPUT_BLOCK");

    /*Used in Delay */
    public static final AttributeKey<CodeVarType> MODIFY_LINK_DELAY = new AttributeKey("MODIFY_LINK_DELAY");
}
