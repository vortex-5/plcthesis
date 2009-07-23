/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plcedit;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author huangkf
 */
public class Bounds {
    public double x;
    public double y;
    public double w;
    public double h;

    public Bounds(double x,double y,double w,double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Bounds(Rectangle2D.Double rect) {
        this.x = rect.getX();
        this.y = rect.getY();
        this.w = rect.getWidth();
        this.h = rect.getHeight();
    }

    public Point2D.Double getTopLeft() {
        return new Point2D.Double(x,y);
    }

    public Point2D.Double getBottomRight() {
        return new Point2D.Double(x+w,x+h);
    }


}
