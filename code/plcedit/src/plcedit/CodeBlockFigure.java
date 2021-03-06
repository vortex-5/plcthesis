/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.awt.geom.Rectangle2D;
import org.jhotdraw.draw.*;
import org.jhotdraw.samples.pert.figures.SeparatorLineFigure;

import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;
import java.io.IOException;


/**
 *
 * @author Vortex-5
 */
public abstract class CodeBlockFigure extends GraphicalCompositeFigure implements Typed {
    protected CodeBlock accociatedcode;
    
    protected TextFigure name;
    protected GraphicalCompositeFigure attrib = new GraphicalCompositeFigure();

    protected TextFigure spacer;

    protected Bounds SavedBounds = null; //bug bounds not loaded per object
    /**
     * Creates a code block of various types provided with a correct prototype
     * block of code
     * @param cde StartBlock EndBlock... etc blocks of code from their derived classes
     */
    public CodeBlockFigure()
    {
        this(new RectangleFigure());
        spacer = new TextFigure("  ");
        spacer.setEditable(false);
    }
    
    public CodeBlockFigure(Figure newBackgroundFigure)
    {
        super(newBackgroundFigure);
    }


    @Override
    public void addNotify(Drawing drawing) {
        super.addNotify(drawing);
        
        refreshFromData();
    }

    
    public void setAttributes (GraphicalCompositeFigure attrib)
    {
        this.attrib = attrib;
    }
    
    /**
     * Must be called after updating any drawing object to ensure the model
     * is consistent with the displayed figure.
     */
    protected void update_base()
    {
        removeAllChildren();
        
        name = new TextFigure(accociatedcode.getTypeString());
        name.setEditable(false);

        GraphicalCompositeFigure line = new GraphicalCompositeFigure();
        line.setLayouter(new HorizontalLayouter());

        line.add(spacer);
        line.add(name);
        line.add(spacer);

        add(line);
        add(new SeparatorLineFigure());   
        add(attrib);
        
        setLayouter(new VerticalLayouter()); //set the default layouter

        updatePositionFromSaved(); //extract pervious saved position if found
    }
    
    
    protected abstract void createModel();
    
    //done by each individual inherited class this overrides any defaults that were done
    protected abstract void initialize_component();

    protected void readSavedBounds(DOMInput in) throws IOException
    {
        SavedBounds = readBoundingBox(in);
    }

    protected void updatePositionFromSaved()
    {
        if (SavedBounds != null) {
            setBounds(SavedBounds.getTopLeft(), SavedBounds.getBottomRight());
            SavedBounds = null;
        }
    }
    
    public void refreshFromData()
    {
        createModel();
        initialize_component();
    }

    public void createAttributeDisplay()
    {
        attrib.removeAllChildren();
        attrib = new GraphicalCompositeFigure();
    }
    
    
    public TextFigure getNameFigure()
    {
        return name;
    }
    
    public GraphicalCompositeFigure getAttributesFigure()
    {
        return attrib;
    }
    
    protected void setModel(CodeBlock cde)
    {
        accociatedcode = cde;
    }
    
    public CodeBlock getModel()
    {
        return accociatedcode;
    }

    public CodeType getType() {
        return accociatedcode.getType();
    }

    public String getTypeString() {
        return accociatedcode.getTypeString();
    }

    @Override
    public void write(DOMOutput out) throws IOException {
        throw new IOException("Save to disk feature not yet implimented!");

    }

    @Override
    public void read(DOMInput in) throws IOException {
        throw new IOException("Read from disk feature not yet implimented!");
    }

    /**
     * Saves the bounding box of an drawing object
     */
    public void writeBoundingBox (DOMOutput out) throws IOException {
        Rectangle2D.Double r = getBounds();
        out.addAttribute("x", r.x);
        out.addAttribute("y", r.y);
        out.addAttribute("w", r.width);
        out.addAttribute("h", r.height);
    }

    private Bounds readBoundingBox (DOMInput in) throws IOException {
        double x = in.getAttribute("x", 0d);
        double y = in.getAttribute("y", 0d);
        double w = in.getAttribute("w", 0d);
        double h = in.getAttribute("h", 0d);

        return new Bounds(x,y,w,h);
    }

    public void writeUID (DOMOutput out) throws IOException {
        out.addAttribute("uid", Integer.toString(getModel().getUID()));
    }

    public int readUID (DOMInput in) throws IOException {
        String uid = in.getAttribute("uid", "UNDEFINED");
        UIDGenerator.jumpTo(Integer.parseInt(uid));
        return Integer.parseInt(uid);
    }
}
