/*
 * This example is part of the iText 7 tutorial.
 */

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.*;

import javax.management.loading.MLet;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple Hello World example.
 */
public class C01E01_HelloWorld_Floating {

    public static final String DEST = "results/chapter01/hello_world_floating.pdf";


    public static final String APPLE_IMG = "src/main/resources/img/ny_times_apple.jpg";
    public static final String APPLE_TXT = "src/main/resources/data/ny_times_apple.txt";
    public static final String APPLE_1_TXT = "src/main/resources/data/ny_times_apple_1.txt";
    public static final String FACEBOOK_IMG = "src/main/resources/img/ny_times_fb.jpg";
    public static final String FACEBOOK_TXT = "src/main/resources/data/ny_times_fb.txt";
    public static final String INST_IMG = "src/main/resources/img/ny_times_inst.jpg";
    public static final String INST_TXT = "src/main/resources/data/ny_times_inst.txt";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();             //创建目录
        new C01E01_HelloWorld_Floating().createPdf(DEST);        //生成文件
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);
        PageSize ps = PageSize.A4;
        // Initialize document
        Document document = new Document(pdf, ps);
        // 块状
        Div div = new Div();
        // 设置宽度
        div.setWidth(UnitValue.createPercentValue(100));
        // 设置高度
        div.setHeight(ps.getHeight());
        // 设置此元素的水平对齐方式
        div.setHorizontalAlignment(HorizontalAlignment.CENTER);
        div.setVerticalAlignment(VerticalAlignment.MIDDLE);

   /*     //Add paragraph to the document
        Text lineTxt = new Text("Hello World!").setFontSize(9.6f * 2).setHorizontalScaling(0.5f);
        document.add(new Paragraph(lineTxt).setFontSize(9.6f).setMargin(0).setFixedLeading(0));
        {
            Table table = new Table(new float[]{1,1,1});
            table.addHeaderCell(getCell("Text to the left left left left left left left left left left left left left", TextAlignment.LEFT));
            //table.addHeaderCell(getCell("Text in the middle middle middle middle middle middle middle middle middle ", TextAlignment.CENTER));
            table.addHeaderCell(getCell("Text in the middle", TextAlignment.CENTER));
            table.addHeaderCell(getCell("Text to the right right right right right right right right right right right ", TextAlignment.RIGHT));
            table.setPadding(0);
            table.setMargin(0);
            div.add(table);
        }
        {
            Table table = new Table(new float[]{1,1,1});
            table.addCell(getCell("", TextAlignment.LEFT));
            table.addCell(getCell("Text in the middle", TextAlignment.CENTER));
            table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
            table.setBorder(new SolidBorder(Color.BLACK, 0.5f));
            table.setPadding(0);
            table.setMargin(0);
            div.add(table);
        }
        {
            Table table = new Table(new float[]{1,1,1});
            table.addCell(getCell("", TextAlignment.LEFT));
            table.addCell(getCell("Text in the middle", TextAlignment.CENTER));
            table.addCell(getCell("Text in the middle", TextAlignment.CENTER));
            table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
            table.setBorder(new SolidBorder(Color.BLACK, 0.5f));
            table.setPadding(0);
            table.setMargin(0);
            div.add(table);
        }

        document.add(div);*/
        float columnWidth = ps.getWidth() / 3;
        Image apple = new Image(ImageDataFactory.create(APPLE_IMG)).setWidth(columnWidth);
        //apple.setFixedPosition(50,50);

        float x = document.getLeftMargin();
        float y = document.getBottomMargin() + 400;
        System.out.println("y = " + y + "x = " + x);
        apple.setProperty(Property.FLOAT, FloatPropertyValue.LEFT);
        apple.setFixedPosition(x, y, 100);
        String articleApple = new String(Files.readAllBytes(Paths.get(APPLE_1_TXT)), StandardCharsets.UTF_8);


        document.add(apple).add(new Paragraph()
                .setFontSize(10)
                .add(articleApple));
        //Close document
        document.close();
    }

    public Cell getCell(String text, TextAlignment alignment) {
        Cell cell = new Cell(1, 1).add(new Paragraph(text).setFixedLeading(9.6f));
        cell.setPadding(0);
        //cell.setMargins(0,0,12f-9.6f,0);
        cell.setTextAlignment(alignment);
        cell.setBorder(Border.NO_BORDER);
        cell.setFontSize(9.6f);
        cell.setHeight((12f - 9.6f) * 2 + 9.6f);
        return cell;
    }

/*    public Cell getCell(String text, TextAlignment alignment) {
        Cell cell = new Cell(1,1).add(new Paragraph(text).setFixedLeading(9.6f));
        cell.setPadding(0);
        //cell.setMargins(0,0,12f-9.6f,0);
        cell.setTextAlignment(alignment);
        cell.setBorder(Border.NO_BORDER);
        cell.setFontSize(9.6f);
        cell.setHeight((12f-9.6f)*2+9.6f);
        return cell;
    }*/
}
