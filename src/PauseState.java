import java.util.HashMap;
import java.util.Map;

public class PauseState implements State{

    private HashMap<String, String[]> nameToAttrMap;

    public PauseState(){
        nameToAttrMap = new HashMap<>();
        nameToAttrMap.put("NEW GAME", new String[]{"C", "startScreen"});
        nameToAttrMap.put("CONTINUE", new String[]{"C", "playScreen"});
        nameToAttrMap.put("SAVE GAME", new String[]{"A", "saveScreen"});
        nameToAttrMap.put("SETTINGS", new String[]{"D", "settingsScreen"});
        nameToAttrMap.put("HELP", new String[]{"E", "helpScreen"});
        nameToAttrMap.put("EXIT", new String[]{"F", "exitScreen"});
        nameToAttrMap.put("BACK", new String[]{"G", "pauseScreen"});
        nameToAttrMap.put("MENU", new String[]{"G", "pauseScreen"});
    }

    public Map<String, String[]> getNameToAttrMap(){
        return nameToAttrMap;
    }

    @Override
    public void changeContext(Context context) {
        context.setState(this);
    }

    public String toString(){
        return "pauseState";
    }
}
