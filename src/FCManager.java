import jdk.jshell.JShell;

import java.util.Scanner;

import static org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties.type;

public class FCManager {

    private Scanner in = new Scanner(System.in);
    private FitnessCentre FC = new FitnessCentre("FC");

    public void printMenu(){
        System.out.println();
        System.out.println ((char) 27 + "[36m___________________________________________" + (char)27 + "[0m");
        System.out.println(
                "(1) - список клієнтів  " +
                "(2) - за алфавітом \n(3) - за тривалістю відвідування(у роках)\n(4) - клієнти, " +
                        "які ходять в басейн  \n(5) - додати клієнта   (6) - редагувати \n(7) - " +
                        "видалити " + "        (0) -" +(char) 27 + "[31m Quit" + (char)27 + "[0m");
        System.out.println ((char) 27 + "[36m___________________________________________" + (char)27 + "[0m");
        System.out.println();

    }
//______________________________________________________________________________________________________________________
    public void selectMenu(){

        int fileType = DataSource.SOURCE_EXCEL;
        String sourseName = "C:\\Users\\Super Yanka\\my-app\\FitnessCentre\\src\\main\\java\\Custumers.xlsx";

        FC.loadCustumers(fileType, sourseName);

        boolean quit = false;
        int menuItem;

        do {
            System.out.println("Виберіть пункт меню: ");
            menuItem = in.nextInt();

            switch (menuItem) {
                case 1:
                    System.out.println((char)27 +"[34m                             ~Список клієнтів~ "+ (char)27 + "[0m");
                    FC.sortCustumers(FitnessCentre.SORT_BY_ID);
                    FC.printCustumers();
                    break;
                case 2:
                    System.out.println((char)27 +"[34m                             ~Клієнти за алфавітом~ "+ (char)27 + "[0m");
                    FC.sortCustumers(FitnessCentre.SORT_BY_NAME);
                    FC.printCustumers();
                    break;
                case 3:
                    System.out.println((char)27 +"[34m                          ~Клієнти за роком відвідування~ "+ (char)27 + "[0m");
                    FC.sortCustumers(FitnessCentre.SORT_BY_YEARS);
                    FC.printCustumers();
                    break;
                case 4:
                  System.out.println((char)27 +"[35mКлієнти, які ходять в бассейн:"+ (char)27 + "[0m");
                   findCustumer();
                   break;
                case 5:
                    System.out.println((char)27 +"[34mДодати клієнта "+ (char)27 + "[0m");
                    Custumer с = enterCustumer();
                    FC.saveCustumers(fileType, sourseName);
                    FC.addCustumer(с);
                    break;
                case 6:
                    System.out.println((char)27 +"[34mРедагування "+ (char)27 + "[0m");
                    FC.printCustumers();
                    editCustumer();
                    FC.saveCustumers(fileType, sourseName);
                    break;
                case 7:
                    System.out.println((char)27 +"[34mВидалення"+ (char)27 + "[0m");
                    FC.printCustumers();
                    deleteCustumer();
                    FC.saveCustumers(fileType, sourseName);
                    break;
                case 0:
                    quit = true;
                    FC.saveCustumers(fileType, sourseName);
                    break;
                default:
                    System.out.println((char)27 +"[31mInvalid choice."+ (char)27 + "[0m");
            }
            if(!quit){
                printMenu();
            }
        } while (!quit);
        System.out.println((char)27 +"[36mBye-bye!"+ (char)27 + "[0m");
    }
//______________________________________________________________________________________________________________________

    private void findCustumer() {
     FC.findCustumerByPool(type);

     /*
        System.out.println((char)27 +"[34mВиберіть тип абонементу"+ (char)27 + "[0m");
        System.out.println("(1) - VIP, (2) - бассейн , (3) - тренажерний зал");
        int type = in.nextInt();

        Type custumerType;
        switch (type) {
            case 1:
                custumerType = Type.VIP;
                break;
            case 2:
                custumerType = Type.POOL;
                break;
            case 3:
                custumerType = Type.GYM;
                break;
        }
        FC.findCustumerByType(type);
        */
    }

//______________________________________________________________________________________________________________________

    private Custumer enterCustumer() {
        System.out.println("Введіть ім'я клієнта: ");
        String name = in.next();

        System.out.println("Введіть тривалість відвідування клієнта: ");
        int years = in.nextInt();

        System.out.println("Введіть тип абонементу: " );
        System.out.println( "(1) - " + (char)27 +"[36mVIP " +
                ""+ (char)27 + "[0m" + "(2) - " + (char)27 +"[36mбассейн " +
                ""+ (char)27 + "[0m" +"(3) - " + (char)27 +"[36mтренажерний зал "+ (char)27 + "[0m"  );

        int type = in.nextInt();

            Type custumerType;
            switch(type){
                case 1:
                    custumerType = Type.VIP;
                    break;
                case 2:
                    custumerType = Type.POOL;
                    break;
                case 3:
                    custumerType = Type.GYM;
                    break;
                default:
                    custumerType = Type.VIP;
            }
        Custumer c = new Custumer(name, years);
        c.setSubscription(custumerType);
        return c;
    }
//______________________________________________________________________________________________________________________

    private void deleteCustumer() {
        System.out.println ((char) 27 + "[36mВведіть номер клієнта" + (char)27 + "[0m");
        int custumerID = in.nextInt();

        Custumer c = FC.findCustumerByID(custumerID);
        if(c == null){
            System.err.println((char)27 +"[31mCustumer not found"+ (char)27 + "[0m");
        } else {
            FC.deleteCustumer(custumerID);
        }
    }
//______________________________________________________________________________________________________________________

    private void editCustumer(){
        System.out.println ((char) 27 + "[36mВведіть номер клієнта" + (char)27 + "[0m");
        int custumerID = in.nextInt();
        Custumer c = FC.findCustumerByID(custumerID);
        if(c == null){
             System.err.println((char)27 +"[31mCustumer not found"+ (char)27 + "[0m");
        } else {
            System.err.println("Ім'я: " + c.getName());
            System.out.println("Введіть нове ім'я клієнта");
            String name = in.next();
            if(!name.isEmpty()){
                c.setName(name);
            } else if(name.isEmpty()){
              c.getName();
            }
            System.err.println("Тривалість тренувваня: " + c.getYears() + "років");
            System.out.println("Введіть новий рік тренування:");
            int years = in.nextInt();
            if(years != 0){
                c.setYears(years);
            }
            System.err.println("Тип абонементу:: " + c.getSubscription());
            System.out.println("Введіть тип абонементу: " );
            System.out.println( "(1) - " + (char)27 +"[36mVIP " +
                    ""+ (char)27 + "[0m" + "(2) - " + (char)27 +"[36mбассейн " +
                    ""+ (char)27 + "[0m" +"(3) - " + (char)27 +"[36mтренажерний зал "+ (char)27 + "[0m"  );
            int type = in.nextInt();

            Type custumerType;
            switch(type){
                case 1:
                    custumerType = Type.VIP;
                    break;
                case 2: custumerType = Type.POOL;
                    break;
                case 3: custumerType = Type.GYM;
                    break;
                default:
                    custumerType = Type.VIP;
            }
            c.setSubscription(custumerType);
            FC.editCustumer(c);

        }
    }
}


