package EsercizioGestioneAccount;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private List<String> emails;

    public User() {
    }

    public User(String id, String nome, String cognome, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        emails = new ArrayList<>();
    }
    public void addEmail(String mail) {
        if (!emails.contains(mail)) {
            emails.add(mail);
        }
    }
    public boolean hasEmail() {
        return emails != null;
    }

    public List<String> getEmails() {
        if (emails != null) {
           emails.forEach(System.out::println);
        }
        return emails;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", nome='" + nome + '\'' + ", cognome='" + cognome + '\'' +
                ", indirizzo='" + indirizzo + '\'' + ", emails=" + emails + '}';
    }
}
