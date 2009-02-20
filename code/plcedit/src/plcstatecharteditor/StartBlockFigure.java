/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcstatecharteditor;

/**
 *
 * @author Vortex-5
 */
public class StartBlockFigure extends CodeBlockFigure {
    
    protected void createModel()
    {
        accociatedcode = new StartBlock();
    }

    @Override
    protected void initialize_component() {
        willChange();
        update_base();
        changed();
        
    }
    
    
    
    
}
