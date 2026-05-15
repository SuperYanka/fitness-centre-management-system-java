public class App {
    public static void main(String[] args) {
        FCManager ui = new FCManager();
        System.out.println();
        System.out.println ((char) 27 + "[35m---------------Fitness Centre--------------- \n ----------- True Body Positive -----------" + (char)27 + "[0m");
        ui.printMenu();
        ui.selectMenu();
    }
}
