import org.apache.xmlbeans.SchemaType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FitnessCentre {

// сортування клієнтів за прізвищем
    public static final int SORT_BY_NAME = 1;
// сортування клієнтів за тривалістю відвідування
    public static final int SORT_BY_YEARS = 2;
    public static final int SORT_BY_ID = 3;

    private String title;
    static List<Custumer> custumers;

    private DataSource custumersDataSource;

    public FitnessCentre(String title) {
        this.title = title;
    }

    public void loadCustumers(int sourceType, String fileName){
        switch(sourceType) {
            case DataSource.SOURCE_CSV:
                custumersDataSource = new CSVLoader();
                custumers = custumersDataSource.loadCustumers(fileName);
                break;

            case DataSource.SOURCE_EXCEL:
                custumersDataSource = new ExсelLoader();
                custumers = custumersDataSource.loadCustumers(fileName);
                break;

            case DataSource.SOURCE_GENERATE:
                custumers = new ArrayList<>();

                try{
                    Custumer currentCustumer = new Custumer("XXX", 0);
                    currentCustumer.setSubscription(Type.VIP);
                    custumers.add(currentCustumer);

                    custumers.add(new Custumer("Alina", 3));
                    custumers.get(1).setSubscription(Type.GYM);
                    custumers.add(new Custumer("Tanya", 4));
                    custumers.get(2).setSubscription(Type.POOL);
                    custumers.add(new Custumer("Alice", 6));
                    custumers.get(3).setSubscription(Type.VIP);
                    custumers.add(new Custumer("Nastya", 2));
                    custumers.get(4).setSubscription(Type.GYM);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }  break;
        }
    }
//______________________________________________________________________________________________________________________
    public void saveCustumers(int sourceType, String sourceName){
        switch(sourceType){
            case DataSource.SOURCE_CSV:
                custumersDataSource = new CSVLoader();
                custumersDataSource.saveCustumers(sourceName, custumers);
                break;

            case DataSource.SOURCE_EXCEL:
                custumersDataSource = new ExсelLoader();
                custumersDataSource.saveCustumers(sourceName, custumers);
                break;
        }
    }
//______________________________________________________________________________________________________________________

    public void addCustumer(Custumer c){
        custumers.add(c);
    }
    //______________________________________________________________________________________________________________________

    public Custumer findCustumerByPool(SchemaType type){
        for (Custumer pool : custumers){
            switch (pool.getSubscription()){
                case POOL:
                    System.out.println(pool.getName()  + " " + " тренується в бассейні");
                    break;
            }
        }
        return null;
    }

/*
    public Custumer findCustumerByType(int type){
        for (Custumer currentCustumer : custumers){
            if (currentCustumer.getSubscription().equals(type)){

                System.out.format("|%10s |%-10s |%10s |%15s |\n",
                        "Номер", "Прізвище", "Тривалість відвідування(у роках)", "Тип абонементу");

                System.out.format("|%10d |%-10s |%32d |%15s |\n",
                        currentCustumer.getId(), currentCustumer.getName(),
                        currentCustumer.getYears(), currentCustumer.getSubscription().name());
            }
        }
        return null;
    }
*/

//______________________________________________________________________________________________________________________

    public boolean deleteCustumer(int custumerID){
        for (Iterator<Custumer> it = custumers.iterator(); it.hasNext();) {
            Custumer currentCustumer = it.next();
            if (currentCustumer.getId() == custumerID) {
                it.remove();
                return true;
            }
        }return false;
    }
//______________________________________________________________________________________________________________________

       public Custumer findCustumerByID(int id){
        for (Custumer custumer : custumers){
            if (custumer.getId() == id){
                return custumer;
            }
        } return null;
    }

//______________________________________________________________________________________________________________________

        public void editCustumer(Custumer c){
            for (Iterator<Custumer> it = custumers.iterator(); it.hasNext();){
                Custumer currentCustumer = it.next();
                if(currentCustumer.getId() == c.getId()){
                    currentCustumer.setName(c.getName());
                    currentCustumer.setYears(c.getYears());
                    currentCustumer.setSubscription(c.getSubscription());

                }
            }
        }
//______________________________________________________________________________________________________________________

    public static void sortCustumers(int orderID){
        switch(orderID){
            case SORT_BY_NAME:
                Collections.sort(custumers, new CustumersNameComparator());
                break;
            case SORT_BY_YEARS:
                Collections.sort(custumers, new CustumersYearsComparator());
                break;
            case SORT_BY_ID:
                Collections.sort(custumers, new CustumersIDComparator());
                break;
        }
    }
//______________________________________________________________________________________________________________________

    public static void printCustumers(){
        System.out.format("|%5s |%-10s |%10s |%15s |\n",
                "Номер", "Прізвище", "Тривалість відвідування(у роках)", "Тип абонементу");

        for (Custumer currentCustumer : custumers) {
            System.out.format("|%5d |%-10s |%32d |%15s |\n",
                    currentCustumer.getId(), currentCustumer.getName(),
                    currentCustumer.getYears(), currentCustumer.getSubscription().name());
        }
    }
}
