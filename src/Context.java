public class Context {

    // singleton
    private static Context context;

    private State state;

    private Context(){
        // set some default state
        state = new StartState();
    }

    public static synchronized Context getContext() {
        if (context == null){
            context = new Context();
        }
        return context;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
