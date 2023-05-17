import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.barcodes.qrcode.EncodeHintType;
import com.itextpdf.barcodes.qrcode.ErrorCorrectionLevel;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    public static final String SRC = "document.pdf";
    public static final String DEST = "results/chapter01/qrcode.pdf";

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String url = "https://itextpdf.com/";

        //PdfReader reader = new PdfReader(SRC);
        PdfWriter writer = new PdfWriter(DEST, new WriterProperties());
        PdfDocument pdfDocument = new PdfDocument(writer);
        PageSize ps = PageSize.A4;
        Document document = new Document(pdfDocument, ps);
        document.setMargins(0, 0, 0, 0);

        Text lineTxt = new Text("Hello World!").setFontSize(9.6f * 2).setHorizontalScaling(0.5f);
        //document.add(new Paragraph(lineTxt).setFontSize(9.6f).setMargin(0).setFixedLeading(0));
        //Close document
        //document.close();
        // Adding QRCode to first page
        //PdfPage pdfPage = pdfDocument.getFirstPage();
        //PdfCanvas pdfCanvas = new PdfCanvas(pdfPage);
        Rectangle rect = new Rectangle(100, 600, 100, 100);

        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
       // hints.put(EncodeHintType.MIN_VERSION_NR, 40);
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(url, hints);
        PdfFormXObject pdfFormXObject =
                barcodeQRCode.createFormXObject(Color.BLACK, pdfDocument);
        Image qrCodeImage =
                new Image(pdfFormXObject).setWidth(rect.getWidth()).setHeight(rect.getHeight());
        qrCodeImage.setBorder(new SolidBorder(1));
        qrCodeImage.setMargins(0, 0, 0, 0);
        document.add(new Paragraph().add(qrCodeImage).setMargin(0));

      /*  Canvas qrCanvas = new Canvas(pdfCanvas, pdfDocument, rect);
        qrCanvas.add(qrCodeImage);
        qrCanvas.close();*/

        pdfDocument.close();
    }

}