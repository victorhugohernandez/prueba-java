package com.prueba.tecnica.services;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.prueba.tecnica.dtos.MovimientoFechaDTO;

@Service
public class ReporteService {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	
	public String getPdf(List<MovimientoFechaDTO> movimientos) {
		
		ByteArrayOutputStream stream = null;
		
		try {
			Document document = new Document();
			stream = new ByteArrayOutputStream();

			PdfWriter.getInstance(document, stream);
			document.open();
			
			document.add(addTitlePage());
			
			PdfPTable table = new PdfPTable(8);
			addTableHeader(table);
			addRows(table, movimientos);
			document.add(table);
				
			document.close();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return Base64.getEncoder().encodeToString(stream.toByteArray());
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("Fecha", "Cliente", "Cuenta", "Tipo", "Saldo Inicial", "Estado", "Movimiento", "Saldo Disponible")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(1);
	        header.setPhrase(new Phrase(columnTitle));
	        header.setVerticalAlignment(Element.ALIGN_CENTER);
	        header.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(header);
	    });
	}
	
	private void addRows(PdfPTable table, List<MovimientoFechaDTO> movimientos) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		movimientos.forEach(m -> {
			table.addCell(simpleDateFormat.format(m.getFecha()));
			table.addCell(m.getCliente());
			table.addCell(m.getNumeroCuenta().toString());
			table.addCell(m.getTipo());
			table.addCell(m.getSaldoInicial().toPlainString());
			table.addCell(m.getEstado().toString());
			table.addCell(m.getMovimiento().toPlainString());
			table.addCell(m.getSaldoDisponible().toPlainString());
		});
	    
	}
	
	private Paragraph addTitlePage() {
		Paragraph preface = new Paragraph();
		Paragraph title = new Paragraph("Movimientos", catFont);
		title.setAlignment(Element.ALIGN_CENTER);
		
		preface.add(new Paragraph(" "));
        preface.add(title);
        preface.add(new Paragraph(" "));
        
        return preface;
	}
	
}
