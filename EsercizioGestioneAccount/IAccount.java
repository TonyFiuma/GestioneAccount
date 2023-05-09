package EsercizioGestioneAccount;

import java.util.Optional;

/*
 * Interfaccia generica di gestione account utente
 * La classe utente è specificata in modo generi da T
 * T verrà implementata nell'esempio dalla classe User
 */
public interface IAccount<T> {

    /* Inserisce un utente, true se inserimento effettuato */
    boolean addUser(String idUser, String nome, String cognome, String indirizzo);

    /* Inserisce una mail utente, true se inserimento effettuato  */
    boolean addEmail(String idUser, String mail);

    /* Restituisce true se l'utente esiste */
    boolean existsUser(String idUser);

    /* Restituisce true se l'utente ha almeno una mail */
    boolean userHasMail(String idUser);

    /* Restituisce l'utento con l'id fornito */
    Optional<T> user(String idUser);

    /* Restituisce tutti i codici utente sortati */
    String[] idUsers(int sortType);

    /* Restituisce tutte le mail dell'utente */
    String[] userMails(String idUser);

    /* Restituisce tutti gli oggetti utente sortati (nome, cognome) */
    T[] users(int sortType);

    /* Restituisce il primo utente (ordinati per nome, cognome) */
    T firstUser();

    /* Restituisce l'utente (ordinati per nome, cognome) */
    T lastUser();

    /* Restituisce i primi numUsers utenti (ordinati per nome, cognome) */
    T[] firstUsers(int numUsers);

    /* Restituisce gli ultimi numUsers utenti (ordinati per nome, cognome) */
    T[] lastUsers(int numUsers);

    /* Restituisce tutte le righe scartate, perchè doppe o errate */
    String[] discardedRows();

    /* Restituisce tutte le mail in ordine ascendente */
    String[] allMails();
}
