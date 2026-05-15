public class Custumer implements Comparable<Custumer>{

    private int id;

// пр≥звище кл≥Їнта Ц строка
    private String name;
//к≥льк≥сть рок≥в, €к≥ займаЇтьс€ кл≥Їнт - ц≥ле число
    private int years;
//    тип абонемента Ц ф≥ксований наб≥р значень (vip, басейн, тренажерний зал)
    private Type subscription;


    private static int numberOfCustumers = 1;
    public Custumer(String name, int years) {
        this.name = name;
        this.years = years;
        id = numberOfCustumers++;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYears() {
        return years;
    }
    public void setYears(int years) {
        this.years = years;
    }
    public Type getSubscription() {return subscription;}
    public void setSubscription(Type subscription) {this.subscription = subscription;}

    @Override
    public int compareTo(Custumer anotherCustumer) {
        if(this.years == anotherCustumer.years ){
            return -1;
        }else {
            return 1;
        }
    }
}
