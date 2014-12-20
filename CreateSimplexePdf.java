package simplexe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
 










import java.util.Vector;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
 
public class CreateSimplexePdf {
 
	 /** Path to the resulting PDF file. */
    public static final String RESULT = "Simplexe-V2.pdf";
 
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
    	new CreateSimplexePdf().createPdf(RESULT);
    }
    class TableHeader extends PdfPageEventHelper {
        /** The header text. */
        String header;
        /** The template with the total number of pages. */
        PdfTemplate total;
 
        /**
         * Allows us to change the content of the header.
         * @param header The new header String
         */
        public void setHeader(String header) {
            this.header = header;
        }
 
        /**
         * Creates the PdfTemplate that will hold the total number of pages.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }
 
        /**
         * Adds a header to every page
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(3);
            try {
            	String Ecole = "Ecole Nationale des Sciences Appliquées Khouribga 2015 -";
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.addCell(header);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.format(Ecole+" Page %d of", writer.getPageNumber()));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
            }
            catch(DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
 
        /**
         * Fills out the total number of pages before the document is closed.
         * @see com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(
         *      com.itextpdf.text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        public void onCloseDocument(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
                    2, 2, 0);
        }
    }
    
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
    	 // step 1
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        TableHeader event = new TableHeader();
        writer.setPageEvent(event);;
        // step 3
        document.open();
        // step 4
      //Recupere la date système
        Date date = new Date(); 
        //formater la date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy 'à' HH:mm:ss");
        Paragraph par = new Paragraph("Resultat Obtenus le "+sdf.format(date),FontFactory.getFont(FontFactory.COURIER, 11f,0));
        par.setAlignment(Element.ALIGN_RIGHT);
        document.add(par);
        
        

       
        Chunk ck = new Chunk("Simplexe V2 2014-2015", FontFactory.getFont(FontFactory.COURIER, 20f,Font.BOLD));
        ck.setUnderline((float) 1, -2);
        Paragraph para = new Paragraph(ck);
        para.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(para);
        
        //tableau(nbrecolonnes,nbrelignes)
        //Vector<TableIT> tableau=new Vector();
        String[][] tableau = new String[][]{{"lol","mdr","ptdr"},{"ligne21","ligne22","ligne23"}};
        for(int i=0;i<tableau.length;i++)
        {
        	System.out.println();
        	for(int j = 0;j<tableau[i].length;j++)
        	{
        		System.out.print(tableau[i][j]+" ");
        		
        	}
        }
       
        /*
         * for(int i=0;i<tableau.size();i++)
        {
        	for(int j=0;j<tableau.get(i).getData().size() ;j++)
        	{
        		for(int k=0;k<tableau.get(i).getData().get(j).size();k++)
        		{
        			   System.out.println(new Paragraph(tableau.get(i).getData().get(j).elementAt(k)));
        		}
        	}
        }
         * */
        
        // step 5
        document.close();
    }
}