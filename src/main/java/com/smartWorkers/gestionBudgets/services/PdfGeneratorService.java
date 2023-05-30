package com.smartWorkers.gestionBudgets.services;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.smartWorkers.gestionBudgets.entities.Categories;
import com.smartWorkers.gestionBudgets.entities.Transactions;
import com.smartWorkers.gestionBudgets.entities.Users;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfGeneratorService {
    @Autowired
    TransactionsService transactionService;

    @Autowired
    UsersServiceImpl userService;

    public void export(HttpServletResponse response, Authentication authentication) throws IOException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc, response.getOutputStream());
        doc.open();

        // Define fonts
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Font.BOLD, new Color(36, 64, 98));
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD, new Color(255, 255, 255));
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, new Color(36, 64, 98));

        // Create title paragraph
        Paragraph title = new Paragraph("Economic Report", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        doc.add(title);

        // Add user information
        Users currentUser = userService.getUserById(authentication);
        
        // Create user section with border
        PdfPTable userTable = new PdfPTable(2);
        userTable.setWidthPercentage(100);
        userTable.setSpacingAfter(10f);
        userTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        PdfPCell userNameCell = new PdfPCell(new Phrase("User: " + currentUser.getName(), cellFont));
        userNameCell.setBorder(Rectangle.NO_BORDER);
        userNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        userNameCell.setPadding(5f);
        userTable.addCell(userNameCell);
        
        
        List<Transactions> transactions = transactionService.getTransactions(currentUser.getUser_id());
        
        Double income = 0.0;
        Double expenses = 0.0;
        for (Transactions transaction : transactions) {
        	System.out.println(transaction.getType());
        	if (transaction.getType().equals("INCOME")) {
        		income = income + transaction.getAmount();
        		System.out.println(income);
        	}else if (transaction.getType().equals("EXPENSE")){
        		expenses = expenses + transaction.getAmount();
        	}
        }
        if (expenses < 0) {
        	expenses = 0.0;
        }
        if (income < 0) {
        	income = 0.0;
        }
        Double balance = 0.0;
        balance = income - expenses;
       
        PdfPCell userBalanceCell = new PdfPCell(new Phrase("User Balance: " +  balance, cellFont));
        userBalanceCell.setBorder(Rectangle.NO_BORDER);
        userBalanceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        userBalanceCell.setPadding(5f);
        userTable.addCell(userBalanceCell);
        
        doc.add(userTable);

        // Create table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Create table header cells
        PdfPCell amountHeaderCell = new PdfPCell(new Phrase("Amount", headerFont));
        amountHeaderCell.setBackgroundColor(new Color(36, 64, 98));
        amountHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        amountHeaderCell.setPadding(5f);
        table.addCell(amountHeaderCell);

        PdfPCell createdAtHeaderCell = new PdfPCell(new Phrase("Created At", headerFont));
        createdAtHeaderCell.setBackgroundColor(new Color(36, 64, 98));
        createdAtHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        createdAtHeaderCell.setPadding(5f);
        table.addCell(createdAtHeaderCell);

        PdfPCell categoryHeaderCell = new PdfPCell(new Phrase("Category", headerFont));
        categoryHeaderCell.setBackgroundColor(new Color(36, 64, 98));
        categoryHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        categoryHeaderCell.setPadding(5f);
        table.addCell(categoryHeaderCell);
        
        PdfPCell typeHeaderCell = new PdfPCell(new Phrase("Type", headerFont));
        typeHeaderCell.setBackgroundColor(new Color(36, 64, 98));
        typeHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        typeHeaderCell.setPadding(5f);
        table.addCell(typeHeaderCell);

        // Populate table with transaction data
        for (Transactions tra : transactions) {
            Double amount = tra.getAmount();
            PdfPCell amountCell = new PdfPCell(new Phrase(amount.toString(), cellFont));
            amountCell.setPadding(5f);
            table.addCell(amountCell);

            Date createdAt = tra.getCreated_at();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(createdAt);
            PdfPCell createdAtCell = new PdfPCell(new Phrase(formattedDate, cellFont));
            createdAtCell.setPadding(5f);
            table.addCell(createdAtCell);

            String category = tra.getCategorie().getName();
            PdfPCell categoryCell = new PdfPCell(new Phrase(category, cellFont));
            categoryCell.setPadding(5f);
            table.addCell(categoryCell);
            
            String type = tra.getType();
            PdfPCell typeCell = new PdfPCell(new Phrase(type, cellFont));
            typeCell.setPadding(5f);
            table.addCell(typeCell);
        }

        doc.add(table);
        doc.close();
    }
}

