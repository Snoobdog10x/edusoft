package com.company.Process;

import com.company.Class.HocPhan;
import com.company.Class.Vienchuc;
import com.company.DatabaseConnection.MonHocDatabase;
import com.company.DatabaseConnection.VienchucDatabse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ProcessQLVC {
    private List<Vienchuc> lvc;
    public ProcessQLVC(){

    }
    private void loadListVienChuc(){
        VienchucDatabse vcdb=new VienchucDatabse();
        lvc=vcdb.getListVC();
        vcdb.closedb();
    }
    public void ExportPDF(){
        Document document = new Document();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("FILE BÁO CÁO VIÊN CHỨC.pdf"));
            document.open();
            document.add(new Paragraph("Danh Sách viên chức"));
            PdfPTable table = new PdfPTable(8); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(0f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f, 1f,1f, 1f, 1f,1f, 1f};
            table.setWidths(columnWidths);
            for(int col=0;col<8 ; col++){
                String[] colum = new String[]{"Mã Viên Chức","Loại Viên Chức", "Tên Viên Chức","Họ lót", "SĐT","Ngày Sinh", "Nơi Sinh", "Email"};
                table.addCell(colum[col]);
            }
            loadListVienChuc();
            //for(int aw=0;aw<16 ; aw++){
            //    table.addCell("hi");
            //}
            for(Vienchuc i:lvc){
                
                    table.addCell(String.valueOf(i.getMVC()));
                    table.addCell(i.getLoaivc());
                    table.addCell(i.getTen());
                    table.addCell(i.getHolot());
                    table.addCell(i.getSdt());
                    table.addCell(String.valueOf(i.getNgaysinh()));
                    table.addCell(i.getNoisinh());
                    table.addCell(i.getEmail());
            }

            document.add(table);
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public DefaultTableModel loadTableModel(){
        loadListVienChuc();
        String[] col = new String[]{"Mã Viên Chức","Loại Viên Chức", "Tên Viên Chức","Họ lót", "SĐT","Ngày Sinh", "Nơi Sinh", "Email"};
        DefaultTableModel defaultTableModel=new DefaultTableModel(col,0);
        for(Vienchuc i:lvc){
            Object[] objects=i.toObjectArray();
            defaultTableModel.addRow(objects);
        }
        return defaultTableModel;
    }
    public DefaultTableModel reloadTableModel(DefaultTableModel model, int rowcount){
        loadListVienChuc();
        for (int i = rowcount; i > 0; i--) {
            model.removeRow(0);
        }
        for(Vienchuc i:lvc){
            Object[] objects=i.toObjectArray();
            model.addRow(objects);
        }
        return model;
    }
    public boolean addVC(Vienchuc vienchuc){
        VienchucDatabse db= new VienchucDatabse();
        boolean check = db.addVC(vienchuc);
        return check;
    }

    public boolean updateVC(Vienchuc vienchuc){
        VienchucDatabse db= new VienchucDatabse();
        boolean check = db.updateVC(vienchuc);
        return check;
    }
}
