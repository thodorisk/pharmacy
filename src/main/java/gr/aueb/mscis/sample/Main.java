package gr.aueb.mscis.sample;
import gr.aueb.mscis.sample.persistence.Initializer;

/**
 * Created by thodoriskaragiannis on 22/02/2017.
 */

public class Main {

    public static void main(String[] args) {
        Initializer a = new Initializer();
        a.prepareData();
        
        System.out.println("\nEnd of code");

    }
}
