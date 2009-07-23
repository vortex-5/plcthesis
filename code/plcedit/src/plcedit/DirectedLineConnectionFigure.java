/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import org.jhotdraw.draw.*;

import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;
import org.jhotdraw.geom.BezierPath;

import java.io.IOException;

/**
 *
 * @author Vortex-5
 */


public class DirectedLineConnectionFigure extends LabeledLineConnectionFigure implements ConnectionFigure, Typed {
    private LineDecoration decoration;
    private TextFigure label = null;

    public String getGuard()
    {
        return label.getText();
    }
    
    public DirectedLineConnectionFigure()
    {
        super(); //call the parent make sure all the house keeping operations are done
        decoration = new ArrowTip(Math.PI / 11, 13, 0, false, true, true); //prepare our arrow tip
        
        this.setAttribute(AttributeKeys.END_DECORATION, decoration); //make our graph object directed

        addTextLabel("true");
    }

    
    @Override
    public void addNotify(Drawing drawing) {
        super.addNotify(drawing);
        
        refreshFromData();
    }
    
    private void refreshFromData()
    {
        willChange();
        setTextLabel(getGuard());
        changed();
    }


    public CodeType getType() {
        return CodeType.Goto;
    }

    public String getTypeString() {
        return "GOTO";
    }

    private void setTextLabel(TextFigure text)
    {
        removeChild(0);
        addTextLabel(text);
    }

    private void setTextLabel(String text)
    {
        removeChild(0);
        addTextLabel(text);
    }

    private void addTextLabel(String text)
    {
        this.setLayouter(new LocatorLayouter());
        addTextLabel(new TextFigure(text));
    }

    private void addTextLabel(TextFigure text)
    {
        label = text;
        LocatorLayouter.LAYOUT_LOCATOR.set(label, new BezierLabelLocator(0.3, -Math.PI / 4, 10.0));
        this.add(label);
    }

    public String getGuardCode() //this is used during code generation to update the guard conditions
    {
        label = (TextFigure)getChild(0);
        String buffer = "";
        if (!label.getText().equals("true"))
            buffer = "if (" + label.getText() + ") ";
        return buffer;
    }

    @Override
    public void write(DOMOutput out) throws IOException {
        writePoints(out);

        
        out.openElement("startConnector");
        out.writeObject(getStartConnector());
        out.closeElement();

        out.openElement("endConnector");
        out.writeObject(getEndConnector());
        out.closeElement();

        
        out.openElement("data_label");
        out.writeObject(label);
        out.closeElement();

        
        writeAttributes(out);
    }

    @Override
    protected void writePoints(DOMOutput out) throws IOException {
        out.openElement("points");
        if (isClosed()) {
            out.addAttribute("closed", true);
        }
        for (int i=0, n = getNodeCount(); i < n; i++) {
            BezierPath.Node node = getNode(i);
            out.openElement("p");
            out.addAttribute("mask", node.mask, 0);
            out.addAttribute("colinear", true);
            out.addAttribute("x", node.x[0]);
            out.addAttribute("y", node.y[0]);
            out.addAttribute("c1x", node.x[1], node.x[0]);
            out.addAttribute("c1y", node.y[1], node.y[0]);
            out.addAttribute("c2x", node.x[2], node.x[0]);
            out.addAttribute("c2y", node.y[2], node.y[0]);
            out.closeElement();
        }
        out.closeElement();
    }


    @Override
    public void read(DOMInput in) throws IOException {

        readAttributes(in);
        readPoints(in);

        willChange();
        
        in.openElement("startConnector");
        setStartConnector((Connector) in.readObject());
        in.closeElement();
        
        in.openElement("endConnector");
        setEndConnector((Connector) in.readObject());
        in.closeElement();
        
        in.openElement("data_label");
        setTextLabel((TextFigure) in.readObject());
        in.closeElement();

        changed();
    }

    @Override
    protected void readPoints(DOMInput in) throws IOException {
        path.clear();
        in.openElement("points");
        setClosed(in.getAttribute("closed", false));

        for (int i=0, n = in.getElementCount("p"); i < n; i++) {
            in.openElement("p", i);
            BezierPath.Node node = new BezierPath.Node(
                    in.getAttribute("mask", 0),
                    in.getAttribute("x", 0d),
                    in.getAttribute("y", 0d),
                    in.getAttribute("c1x", in.getAttribute("x", 0d)),
                    in.getAttribute("c1y", in.getAttribute("y", 0d)),
                    in.getAttribute("c2x", in.getAttribute("x", 0d)),
                    in.getAttribute("c2y", in.getAttribute("y", 0d))
                    );
            node.keepColinear = in.getAttribute("colinear", true);
            path.add(node);
            path.invalidatePath();
            in.closeElement();
        }
        in.closeElement();
    }


}

    
