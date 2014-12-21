package simplexe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import com.itextpdf.text.BaseColor;
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
    private static DecimalFormat df = new DecimalFormat("#.##");
  
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
            	String Ecole = "ENSA Khouribga - Recherche Opérationnelle";
                table.setWidths(new int[]{24, 24, 2});
                table.setTotalWidth(527);
                table.setLockedWidth(true);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.addCell(header);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(Ecole);
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
    public void createPdf(String filename,String fonction,Vector<String> contraintes,Vector<TableIT> tableau)
	throws DocumentException, IOException {
    	 // step 1
        Document document = new Document(PageSize.A4, 36, 36, 54, 36);
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        TableHeader event = new TableHeader();
        writer.setPageEvent(event);
        // step 3
        document.open();
        // step 4

        
      /*
        Date date = new Date(); 
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:ss");
        Paragraph par = new Paragraph("Resultat Obtenus le "+sdf.format(date),FontFactory.getFont(FontFactory.COURIER, 11f,0));
        par.setAlignment(Element.ALIGN_RIGHT);
        document.add(par);
        */
        Paragraph par;
        par = new Paragraph(fonction);
        par.setAlignment(Element.ALIGN_CENTER);
        
        document.add(par);
        par = new Paragraph("Contraintes : ");
        par.setAlignment(Element.ALIGN_CENTER);
        document.add(par);
        for(int i=0;i<contraintes.size();i++)
        {
            par = new Paragraph(contraintes.get(i));
            par.setAlignment(Element.ALIGN_CENTER);
            document.add(par);
        }
       
        /*Chunk ck = new Chunk("Simplexe V2 2014-2015", FontFactory.getFont(FontFactory.COURIER, 20f,Font.BOLD));
        ck.setUnderline((float) 1, -2);
        Paragraph para = new Paragraph(ck);
        para.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(para);*/
        
        
        PdfPTable table;
        for(int i=0;i<tableau.size();i++)
        {
            table = createTable1(document,tableau.get(i).getData(),tableau.get(i).getLigne_pivot(),tableau.get(i).getCol_pivot());
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);
            document.add(table);
        }
        document.close();
    }

	public static PdfPTable createTable1(Document document,Vector<Vector<String>> tableau,int ligne_pivot,int col_pivot) throws DocumentException {
		Font font = FontFactory.getFont(FontFactory.COURIER, 10f);
        
		int NbrColonne = tableau.firstElement().size();
		PdfPTable table = new PdfPTable(NbrColonne);
        table.setWidthPercentage(500 / 5.23f);
        float tab[] = new float[NbrColonne];
        for(int i = 0;i<NbrColonne;i++)
        {
        	if(i==0){tab[i]=2;}else{tab[i]=3;}

        }
        table.setWidths(tab);
        PdfPCell cell;
       

        	
        	for(int i=0;i<tableau.size() ;i++)
        	{
        		for(int j=0;j<tableau.get(i).size();j++)
        		{
        			cell = new PdfPCell(new Phrase((tableau.get(i).get(j).contains(".") && j!=0)?df.format(Double.parseDouble(tableau.get(i).get(j))):tableau.get(i).get(j)));
               	 	
        			if(i!=0 && j!=0)
                                {
                                    if(i==ligne_pivot && j==col_pivot)
                                            cell.setBackgroundColor(BaseColor.YELLOW);
                                    if(i==ligne_pivot && j!=col_pivot && j!=(tableau.firstElement().size()-1))
                                            cell.setBackgroundColor(BaseColor.ORANGE);
                                    if(i!=ligne_pivot && j==col_pivot)
                                            cell.setBackgroundColor(BaseColor.ORANGE);

                                }
                                
                                table.addCell(cell);
        			
        		}
                        
        	}
                if(tableau.lastElement().size()<tableau.firstElement().size())
                {
                    cell = new PdfPCell(new Phrase("0"));
                    table.addCell(cell);
                }
        	
        
        
        table.setComplete(true);
        return table;
    }
	
   
}