/*
 * @(#)DrawApplicationModel.java  1.0  June 10, 2006
 *
 * Copyright (c) 1996-2006 by the original authors of JHotDraw
 * and all its contributors.
 * All rights reserved.
 *
 * The copyright of this software is owned by the authors and  
 * contributors of the JHotDraw project ("the copyright holders").  
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * the copyright holders. For details see accompanying license terms. 
 */

package plcedit;

import org.jhotdraw.util.*;
import java.util.*;
import javax.swing.*;
import org.jhotdraw.app.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.*;
import java.awt.event.*;

/**
 * DrawApplicationModel.
 * 
 * 
 * 
 * @author Werner Randelshofer.
 * @version 1.0 June 10, 2006 Created.
 */
public class DrawApplicationModel extends DefaultApplicationModel {
    /**
     * This editor is shared by all views.
     */
    private DefaultDrawingEditor sharedEditor;
    
    /** Creates a new instance. */
    public DrawApplicationModel() {
    }
    
    public DefaultDrawingEditor getSharedEditor() {
        if (sharedEditor == null) {
            sharedEditor = new DefaultDrawingEditor();
        }
        return sharedEditor;
    }
    
    public void initView(Application a, View p) {
        if (a.isSharingToolsAmongViews()) {
            ((DrawView) p).setEditor(getSharedEditor());
        }
        
        
        
    }
    /**
     * Creates toolbars for the application.
     * This class always returns an empty list. Subclasses may return other
     * values.
     */
    public List<JToolBar> createToolBars(Application a, View pr) {
        ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        DrawView p = (DrawView) pr;
        
        DrawingEditor editor;
        if (p == null) {
            editor = getSharedEditor();
        } else {
            editor = p.getEditor();
        }
        
        LinkedList<JToolBar> list = new LinkedList<JToolBar>();
        JToolBar tb;
        tb = new JToolBar();
        addCreationButtonsTo(tb, editor);
        tb.setName(labels.getString("drawToolBarTitle"));
        list.add(tb);
        
        tb = new JToolBar();
        addCodeButtonsTo(tb, editor);
        list.add(tb);
        

        return list;
    }


    /**
     * This metod adds any additional buttons to our toolbar in our case we use
     * it to add the compile and simulator buttons.
     * @param tb The toolbar to add the buttons to
     * @param editor The editor the link to so we can pull the drawing parameters
     */
    private void addCodeButtonsTo(JToolBar tb, DrawingEditor editor)
    {
        JButton btnCompile = new JButton("Compile");
        btnCompile.addActionListener(new CodeGenerator(editor));
        tb.setName("Code Generation Tools");
        tb.add(btnCompile);
               
        
    }
    private void addCreationButtonsTo(JToolBar tb, DrawingEditor editor) {
        addDefaultCreationButtonsTo(tb, editor, 
                ButtonFactory.createDrawingActions(editor), 
                ButtonFactory.createSelectionActions(editor)
                );
    }
    public void addDefaultCreationButtonsTo(JToolBar tb, final DrawingEditor editor,
            Collection<Action> drawingActions, Collection<Action> selectionActions) {
        //ResourceBundleUtil labels = ResourceBundleUtil.getLAFBundle("org.jhotdraw.draw.Labels");
        
        ButtonFactory.addSelectionToolTo(tb, editor, drawingActions, selectionActions);
        tb.addSeparator();
        
        AbstractAttributedFigure af;
        CreationTool ct;
        ConnectionTool cnt;
        ConnectionFigure lc;
              
        addToolTo(tb, editor, new CreationTool(new StartBlockFigure()), "Start");
        addToolTo(tb, editor, new CreationTool(new StoreBlockFigure()), "Store");
        addToolTo(tb, editor, new CreationTool(new DelayBlockFigure()), "Delay");
        addToolTo(tb, editor, new CreationTool(new OutputBlockFigure()), "Output");
        addToolTo(tb, editor, new ConnectionTool(new DirectedLineConnectionFigure()), "Transition");

    }     
    
    /**
     * Modified tool adding method for this forum only
     * @param tb
     * @param tool
     * @param label
     * @return
     */
    public static JToggleButton addToolTo(JToolBar tb, DrawingEditor editor, Tool tool, String btnLabel) {
        
        ButtonGroup group = (ButtonGroup) tb.getClientProperty("toolButtonGroup");
        ToolListener toolHandler = (ToolListener) tb.getClientProperty("toolHandler");
        
        JToggleButton t = new JToggleButton();
        t.setText(btnLabel);
        t.addItemListener(new ToolButtonListener(tool, editor));
        t.setFocusable(false);
        tool.addToolListener(toolHandler);
        group.add(t);
        tb.add(t);
        
        return t;
    }
    
    
    
    private static class ToolButtonListener implements ItemListener {
        private Tool tool;
        private DrawingEditor editor;
        public ToolButtonListener(Tool t, DrawingEditor editor) {
            this.tool = t;
            this.editor = editor;
        }
        public void itemStateChanged(ItemEvent evt) {
            if (evt.getStateChange() == ItemEvent.SELECTED) {
                editor.setTool(tool);
            }
        }
    }
}
