/**
 * Created by CuteKe on 2017/7/10.
 */

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Simple table example.
 */
public class C01E04_UnitedStates {
    public static final String DATA = "src/main/resources/data/united_states.csv";

    public static final String DEST = "results/chapter01/united_states.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new C01E04_UnitedStates().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        {
            Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
            table.setWidthPercent(100);
            table.setFixedLayout();
            BufferedReader br = new BufferedReader(new FileReader(DATA));
            String line = br.readLine();
            process(table, line, bold, true);
            while ((line = br.readLine()) != null) {
                process(table, line, font, false);
            }
            br.close();

            document.add(table);
        }

        {
            Table table = new Table(new float[]{1});
            table.setWidthPercent(100);
        }
        //Close document
        document.close();
    }

    public void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            if (isHeader) {
                Cell cell = new Cell();
                cell.setTextAlignment(TextAlignment.CENTER);
                //cell.setPaddings(12f-9.6f,0,12f-9.6f,0);
                cell.setHeight((12f - 9.6f) * 2 + 9.6f);
                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                cell.setMargin(0);
                Text text = new Text(tokenizer.nextToken()).setFont(font).setFontSize(9.6f);
                //cell.setMargins(12f-9.6f,0,12f-9.6f,0);
                table.addHeaderCell(cell.add(new Paragraph(text).setFixedLeading(9.6f).setPadding(0).setMargin(0).setVerticalAlignment(VerticalAlignment.MIDDLE)));
            } else {
                Cell cell = new Cell();
                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                //cell.setPaddings(12f-9.6f,0,12f-9.6f,0);
                cell.setHeight((12f - 9.6f) * 2 + 9.6f);
                cell.setTextAlignment(TextAlignment.CENTER);

                cell.setMargin(0);
                //cell.setMargins(12f-9.6f,0,12f-9.6f,0);
                Text text = new Text(tokenizer.nextToken() + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").setFont(font).setFontSize(9.6f);
                table.addCell(cell.add(new Paragraph(text).setFixedLeading(9.6f).setPadding(0).setMargin(0).setVerticalAlignment(VerticalAlignment.MIDDLE)));
            }
        }
    }
}
