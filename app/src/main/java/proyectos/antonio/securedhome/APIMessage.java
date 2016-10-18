package proyectos.antonio.securedhome;

/**
 * Created by Antonio on 09/10/2016.
 */

public class APIMessage {
    String title;
    String body;

    @Override
    public String toString() {
        return(title+": "+body);
    }
}
