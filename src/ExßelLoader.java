import java.awt.*;
import java.io.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExñelLoader implements DataSource {

    @Override
    public List<Custumer> loadCustumers(String sourceName) {
        List<Custumer> custumerList = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(new File(sourceName));

            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                Cell cell = cellIterator.next();
                int id = (int) cell.getNumericCellValue();

                cell = cellIterator.next();
                String name = cell.getRichStringCellValue().getString();

                cell = cellIterator.next();
                int years = (int) cell.getNumericCellValue();

                cell = cellIterator.next();
                String type = cell.getStringCellValue();
                Type custumerType = Type.valueOf(type.toUpperCase());

                Custumer custumer = new Custumer( name, years);
                custumer.setSubscription(custumerType);
                custumerList.add(custumer);
            }

            workbook.close();
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return custumerList;
    }

    @Override
    public void saveCustumers(String sourceName, List<Custumer> custumers) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int row_index = 0;
        for (Custumer item: custumers){
            Row row = sheet.createRow(row_index);

            Cell id = row.createCell(0);
            id.setCellValue(item.getId());

            Cell name = row.createCell(1);
            name.setCellValue(item.getName());

            Cell years = row.createCell(2);
            years.setCellValue(item.getYears());

            Cell type = row.createCell(3);
            type.setCellValue(item.getSubscription().toString());

            row_index++;
        }
        try{
            FileOutputStream out = new FileOutputStream(new File(sourceName));
            workbook.write(out);
            out.close();
            System.out.println("Çáåðåæåííÿ..." );
            System.out.println("Çáåðåæåíî." );

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
