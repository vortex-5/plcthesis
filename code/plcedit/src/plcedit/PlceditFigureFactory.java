/*
 * WARNING!! this class is required to allow proper saving please make sure you
 * update this file with each new object type to ensure proper saving.
 */

package plcedit;

import java.util.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.xml.DefaultDOMFactory;

/**
 *
 * @author huangkf
 */
public class PlceditFigureFactory extends DefaultDOMFactory {
    private final static Object[][] classTagArray = {
        { DefaultDrawing.class, "drawing" },
        { QuadTreeDrawing.class, "drawing" },
        { DiamondFigure.class, "diamond" },
        { TriangleFigure.class, "triangle" },
        { BezierFigure.class, "bezier" },
        { RectangleFigure.class, "r" },
        { RoundRectangleFigure.class, "rr" },
        { LineFigure.class, "l" },
        { BezierFigure.class, "b" },
        { LineConnectionFigure.class, "lnk" },
        { EllipseFigure.class, "e" },
        { TextFigure.class, "t" },
        { TextAreaFigure.class, "ta" },
        { ImageFigure.class, "image" },
        { GroupFigure.class, "g" },

        { ArrowTip.class, "arrowTip" },
        { ChopRectangleConnector.class, "rConnector" },
        { ChopEllipseConnector.class, "ellipseConnector" },
        { ChopRoundRectangleConnector.class, "rrConnector" },
        { ChopTriangleConnector.class, "triangleConnector" },
        { ChopDiamondConnector.class, "diamondConnector" },
        { ChopBezierConnector.class, "bezierConnector" },

        { ElbowLiner.class, "elbowLiner" },

        //TODO: all savable code blocks will go here once completed
        { StartBlockFigure.class, "startBlockFigure"},
        { StoreBlockFigure.class, "storeBlockFigure"},
    };
    private final static Object[][] enumTagArray = {
        { AttributeKeys.StrokePlacement.class, "strokePlacement" },
        { AttributeKeys.StrokeType.class, "strokeType" },
        { AttributeKeys.Underfill.class, "underfill" },
        { AttributeKeys.Orientation.class, "orientation" },
    };

    /** Creates a new instance. */
    public PlceditFigureFactory() {
        for (Object[] o : classTagArray) {
            addStorableClass((String) o[1], (Class) o[0]);
        }
        for (Object[] o : enumTagArray) {
            addEnumClass((String) o[1], (Class) o[0]);
        }
    }

}

