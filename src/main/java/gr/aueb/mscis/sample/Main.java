package gr.aueb.mscis.sample;
import gr.aueb.mscis.sample.persistence.Initializer;


public class Main {

    public static void main(String[] args) {
        Initializer a = new Initializer();
        a.prepareData();
        
        System.out.println("\nEnd of code");

    }
}
