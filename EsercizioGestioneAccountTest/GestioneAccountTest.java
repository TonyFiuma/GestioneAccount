package EsercizioGestioneAccountTest;

import EsercizioGestioneAccount.GestioneAccount;
import EsercizioGestioneAccount.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Considerando l' utilizzo dello scanner classe di test in parte basata sul file userAccount.txt
 * presente nel progetto
 */
class GestioneAccountTest {

    static GestioneAccount gestioneAccount;
    static User user;

    @BeforeEach
    void setUp() {
        try {
            gestioneAccount = new GestioneAccount("userAccount.txt");
            user = new User("id1", "Mario", "Rossi", "Via Roma 1");
        } catch (Exception e) {
            fail("Classe non inizializzata");
        }
    }

    @Test
    void addUserTest() {
        boolean result = gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        assertTrue(result);
    }

    @Test
    void addMailTest() {
        boolean result = gestioneAccount.addEmail("U001", "mario.rossi@example.com");
        assertTrue(result);
    }

    @Test
    void existsUserTest() {
        boolean result = gestioneAccount.existsUser("NONINSERITO");
        assertFalse(result);
        gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        result = gestioneAccount.existsUser(user.getId());
        assertTrue(result);
    }

    @Test
    void userHasMailTest() {
        gestioneAccount.addEmail("U001", "mariano@gmail.com");
        boolean mail = gestioneAccount.userHasMail("U001");
        assertTrue(mail);
    }

    @Test
    void userTest() {
        assertNotNull(gestioneAccount.user(user.getId()));
        gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        assertNotNull(gestioneAccount.user(user.getId()));
    }

    @Test
    void idUserTest() {
        assertNotNull(gestioneAccount.idUsers(2));
        gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        assertNotNull(gestioneAccount.idUsers(1));
    }

    @Test
    void userMailsTest() {
        List<String> emails = List.of(gestioneAccount.userMails(user.getId()));
        assertTrue(emails.isEmpty());
        gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        gestioneAccount.addEmail(user.getId(), "mario.rossi@example.com");
        emails = List.of(gestioneAccount.userMails(user.getId()));
        assertEquals(1, emails.size());
        assertEquals("mario.rossi@example.com", emails.get(0));
    }

    @Test
    void usersTest() {
        List<User> users = List.of(gestioneAccount.users(1));
        assertFalse(users.isEmpty());
        gestioneAccount.addUser(user.getId(), user.getNome(), user.getCognome(), user.getIndirizzo());
        users = List.of(gestioneAccount.users(1));
        assertFalse(users.isEmpty());
        assertEquals(user.getNome(), users.get(0).getNome());
    }

    @Test
    void firstUserTest() {
        User firstUser = gestioneAccount.firstUser();
        assertNotNull(firstUser);
        assertEquals("U001", firstUser.getId());
        assertEquals("Alberto", firstUser.getNome());
        assertEquals("Gabbai", firstUser.getCognome());
        assertEquals(" Via Servais 16/A Torino", firstUser.getIndirizzo());
    }

    @Test
    void lastUserTest() {
        User lastUser = gestioneAccount.lastUser();
        assertNotNull(lastUser);
        assertEquals("U056", lastUser.getId());
        assertEquals("Carlo", lastUser.getNome());
        assertEquals("Navone", lastUser.getCognome());
        assertEquals(" Via exilles 12", lastUser.getIndirizzo());
    }

    @Test
    void firstUsersTest() {
        List<User> firstUsers = List.of(gestioneAccount.firstUsers(1));
        assertNotNull(firstUsers);

    }

    @Test
    void lastUsersTest() {
        List<User> lastUsers = List.of(gestioneAccount.lastUsers(1));
        assertNotNull(lastUsers);
    }

    @Test
    void discardedRowsTest() {
        List<String> discardedRows = List.of(gestioneAccount.discardedRows());
        assertNotNull(discardedRows);
        assertEquals(10, discardedRows.size());
    }

    @Test
    void allMailsTest() {
        List<String> allMails = List.of(gestioneAccount.allMails());
        assertNotNull(allMails);

    }
}