import java.util.List;

public interface DataSource {

    int SOURCE_CSV = 1;
    int SOURCE_EXCEL = 2;
    int SOURCE_GENERATE = 3;

    List<Custumer> loadCustumers(String sourceName);
    void saveCustumers(String sourceName, List<Custumer> custumers);



}