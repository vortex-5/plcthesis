/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

/**
 *
 * @author huangkf
 */
public class DirectedLineConnectionModel extends CodeBlock{
    String guardCondition = "true";

    public DirectedLineConnectionModel() {
    }

    @Override
    public String getCode() {
        return "if (" + guardCondition + ")";
    }

    public String getDisplayLabel() {
        return guardCondition;
    }

    public void inputFix() {
        if (guardCondition.equals(""))
        {
            guardCondition = "true";
        }
    }

    @Override
    public String getTypeString() {
        return "Transition";
    }

    public void setNewGuardCondition(String text) {
        guardCondition = text;
    }
}
