package EsercizioGestioneAccount;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GestioneAccountStart {
    public static void main(String[] args) throws FileNotFoundException {
        GestioneAccount gestioneAccount = new GestioneAccount("userAccount.txt");

        gestioneAccount.getMapUsers().forEach((key,value)-> System.out.println(key+" "+value));

        gestioneAccount.addEmail("U001","gigi@gmail.com");

        gestioneAccount.getMapUsers().forEach((key,value)-> System.out.println(key+" "+value));

        gestioneAccount.addEmail("U001","gigi!!!!!!!@gmail.com");

        gestioneAccount.getMapUsers().forEach((key,value)-> System.out.println(key+" "+value));

        Arrays.stream(gestioneAccount.allMails()).forEach(System.out::println);

        System.out.println(gestioneAccount.existsUser("U001"));
    }
}
