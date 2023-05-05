import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.border.Border;

public class CustomSolidBorder extends Border {
    boolean[] borders;

    /**
     * Creates a SolidBorder with the specified width and sets the color to black.
     *
     * @param width   width of the border
     * @param borders 上右下左
     */
    public CustomSolidBorder(float width, boolean[] borders) {
        super(width);
        this.borders = borders;
    }


    /**
     * Creates a SolidBorder with the specified width and the specified color.
     *
     * @param color   color of the border
     * @param width   width of the border
     * @param borders 上右下左
     */
    public CustomSolidBorder(Color color, float width, boolean[] borders) {
        super(color, width);
        this.borders = borders;
    }

    /**
     * Creates a SolidBorder with the specified width, color and opacity.
     *
     * @param color   color of the border
     * @param width   width of the border
     * @param opacity width of the border
     * @param borders 上右下左
     */
    public CustomSolidBorder(Color color, float width, float opacity, boolean[] borders) {
        super(color, width, opacity);
        this.borders = borders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getType() {
        return Border.SOLID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(PdfCanvas canvas, float x1, float y1, float x2, float y2, float borderWidthBefore, float borderWidthAfter) {
        float x3 = 0, y3 = 0;
        float x4 = 0, y4 = 0;

        Border.Side borderSide = getBorderSide(x1, y1, x2, y2);
        switch (borderSide) {
            case TOP:
                x3 = x2 + borderWidthAfter;
                y3 = y2 + width;
                x4 = x1 - borderWidthBefore;
                y4 = y1 + width;
                break;
            case RIGHT:
                x3 = x2 + width;
                y3 = y2 - borderWidthAfter;
                x4 = x1 + width;
                y4 = y1 + borderWidthBefore;
                break;
            case BOTTOM:
                x3 = x2 - borderWidthAfter;
                y3 = y2 - width;
                x4 = x1 + borderWidthBefore;
                y4 = y1 - width;
                break;
            case LEFT:
                x3 = x2 - width;
                y3 = y2 + borderWidthAfter;
                x4 = x1 - width;
                y4 = y1 - borderWidthBefore;
                break;
        }
        if ((borders[0] && borderSide == Side.TOP) ||
                (borders[1] && borderSide == Side.RIGHT) ||
                (borders[2] && borderSide == Side.BOTTOM) ||
                (borders[3] && borderSide == Side.LEFT)) {
            canvas.saveState()
                    .setFillColor(transparentColor.getColor());
            transparentColor.applyFillTransparency(canvas);
            canvas
                    .moveTo(x1, y1).lineTo(x2, y2).lineTo(x3, y3).lineTo(x4, y4).lineTo(x1, y1).fill()
                    .restoreState();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCellBorder(PdfCanvas canvas, float x1, float y1, float x2, float y2) {
        canvas.
                saveState().
                setStrokeColor(transparentColor.getColor());
        transparentColor.applyStrokeTransparency(canvas);
        canvas.
                setLineWidth(width).
                moveTo(x1, y1).
                lineTo(x2, y2).
                stroke().
                restoreState();
    }
}