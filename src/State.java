import java.util.Map;

public interface State {

    Map<String, String[]> getNameToAttrMap();
    void changeContext(Context context);
    String toString();
}
