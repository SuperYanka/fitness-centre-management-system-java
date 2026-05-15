import java.util.List;

import com.opencsv.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader implements DataSource {

    @Override
    public List<Custumer> loadCustumers(String sourceName) {
    List<Custumer> custumersList = new ArrayList<>();

    try {
        FileReader filereader = new FileReader(sourceName);

        CSVReader reader = new CSVReader(filereader);

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            int id = Integer.parseInt(nextLine[0]);
            String name = nextLine[1];
            int years = Integer.parseInt(nextLine[2]);
            String subscription = nextLine[3];

            Type custumerSubscription= Type.valueOf(subscription.toUpperCase());

            Custumer custumer = new Custumer( name, years);

            custumersList.add(custumer);
        }
    } catch (Exception ex) {
        System.err.println("Load File Error " + ex.getMessage());
        return null;
    }
    return custumersList;
}
    @Override
    public void saveCustumers(String sourceName, List<Custumer> custumers) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(sourceName));

            for (Custumer custumer : custumers) {
                String[] entity = new String[5];
                entity[0] = Integer.toString(custumer.getId());
                entity[1] = custumer.getName();
                entity[2] = Integer.toString(custumer.getYears());
                entity[3] = custumer.getSubscription().toString();

                writer.writeNext(entity);
            }
            writer.close();
        } catch (Exception ex) {
            System.err.println("Save File Error " + ex.getMessage());
        }
    }
}
