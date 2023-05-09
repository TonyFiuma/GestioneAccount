package EsercizioGestioneAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GestioneAccount implements IAccount<User> {
    private static final int SORT_ASCENDING = 0;
    private static final int SORT_DESCENDING = 1;
    private final Map<String, User> mapUsers;
    private final List<String> discardedRows;
    private final List<String> allMails;
    private final String inputFile;

    public GestioneAccount(String inputFile) {
        File file = new File(inputFile);
        mapUsers = new TreeMap<>();
        discardedRows = new ArrayList<>();
        allMails = new ArrayList<>();
        if (!file.exists() || file.isDirectory()) {
            throw new RuntimeException("File: " + file + " non trovato");
        }
        this.inputFile = inputFile;
        leggiFile();
    }

    private void leggiFile() {
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine().trim();
                if (row.isEmpty()) {
                    continue;
                }//\\s uno o più spazi e + significa che deve corrispondere a uno o più caratteri
                String[] parts = row.split("\\s+");
                if (parts.length < 4) {
                    addDiscardedLine(row);
                    continue;
                }
                String idUser;
                String nomeOEmail;
                String cognome;
                String indirizzo;
                try (Scanner scannerRow = new Scanner(row)) {
                    idUser = scannerRow.next();
                    nomeOEmail = scannerRow.next();
                    cognome = scannerRow.next();
                    indirizzo = scannerRow.nextLine();
                }
                if (isEmail(nomeOEmail)) {
                    valutaMail(idUser, nomeOEmail);
                } else {
                    boolean isUserInserted = inserisciUser(idUser, nomeOEmail, cognome, indirizzo);
                    if (!isUserInserted) {
                        addDiscardedLine(row);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File: " + inputFile + " non trovato");
        }
    }

    private boolean inserisciUser(String idUser, String nome, String cognome, String indirizzo) {

        if (mapUsers.containsKey(idUser)) {
            return false;
        }
        User user = new User(idUser, nome, cognome, indirizzo);
        mapUsers.put(idUser, user);
        return true;
    }



    private boolean isEmail(String string){
     return string.contains("@") && string.contains(".");
    }
    private void valutaMail(String idUser, String email) {
        User user;
        if (!mapUsers.containsKey(idUser)) {
            user = new User(idUser, "", "", "");
            mapUsers.put(idUser, user);
        } else {
            user = mapUsers.get(idUser);
        }
        user.addEmail(email);
        allMails.add(email);
    }
    private void addDiscardedLine(String row) {
        this.discardedRows.add(row);
    }

    @Override
    public boolean addUser(String userId, String firstName, String lastName, String address) {
        if (userExists(userId) || anyStringIsBlank(userId, firstName, lastName, address)) {
            return false;
        }
        mapUsers.put(userId, new User(userId, firstName, lastName, address));
        return true;
    }

    private boolean userExists(String userId) {
        return mapUsers.containsKey(userId);
    }

    private boolean anyStringIsBlank(String... strings) {
        return Arrays.stream(strings).anyMatch(str -> str == null || str.isBlank());
    }

    private String[] reverseArray(String[] array) {
        List<String> list = Arrays.asList(array);
        Collections.reverse(list);
        return list.toArray(new String[0]);
    }
    @Override
    public boolean addEmail(String idUser, String mail) {
        if (!userExists(idUser) || mail.isBlank()) {
            return false;
        }
        User user = mapUsers.get(idUser);
        user.addEmail(mail);
        mapUsers.put(idUser, user);
        allMails.add(mail);
        return true;
    }

    @Override
    public boolean existsUser(String idUser) {
        return mapUsers.containsKey(idUser);
    }

    @Override
    public boolean userHasMail(String idUser) {
        if (!userExists(idUser)) {
            return false;
        }
        User user = mapUsers.get(idUser);
        return user.hasEmail();
    }

    @Override
    public Optional<User> user(String idUser) {
        return Optional.ofNullable(mapUsers.get(idUser));
    }

    @Override
    public String[] idUsers(int sortType) {
        String[] result = mapUsers.keySet().toArray(new String[0]);
        Arrays.sort(result);
        if (sortType == SORT_DESCENDING) {
            result = reverseArray(result);
        }
        return result;
    }

    @Override
    public String[] userMails(String idUser) {
        if (!mapUsers.containsKey(idUser)) {
            return new String[0];
        }
        User user = mapUsers.get(idUser);
        List<String> emails = user.getEmails();
        return emails.toArray(new String[0]);
    }

    @Override
    public User[] users(int sortType) {
        List<User> userList = new ArrayList<>(mapUsers.values());
        if (sortType == SORT_ASCENDING) {
            userList.sort(Comparator.comparing(User::getId));
        } else {
            userList.sort(Comparator.comparing(User::getId).reversed());
        }
        return userList.toArray(new User[0]);
    }

    @Override
    public User firstUser() {
        if (mapUsers.isEmpty()) {
            return null;
        }
        String firstUserId = mapUsers.keySet().iterator().next();
        return mapUsers.get(firstUserId);
    }
    @Override
    public User lastUser() {
        if (mapUsers.isEmpty()) {
            return null;
        }
        String lastUserId = ((TreeMap<String, User>) mapUsers).lastKey();
        return mapUsers.get(lastUserId);
    }

    @Override
    public User[] firstUsers(int numUsers) {
        User[] users = new User[numUsers];
        int i = 0;
        for (User user : mapUsers.values()) {
            if (i >= numUsers) {
                break;
            }
            users[i++] = user;
        }
        return users;
    }



    @Override
    public User[] lastUsers(int numUsers) {
        User[] users = new User[numUsers];
        int i = numUsers - 1;
        for (User user : mapUsers.values()) {
            if (i < 0) {
                break;
            }
            users[i--] = user;
        }
        return users;
    }

    @Override
    public String[] discardedRows() {
        return discardedRows.stream()
                .map(String::toString)
                .toArray(String[]::new);
    }

    @Override
    public String[] allMails() {
        return allMails.stream()
                .map(String::toString)
                .toArray(String[]::new);
    }

    // getters e setters

    public static int getSortAscending() {
        return SORT_ASCENDING;
    }

    public static int getSortDescending() {
        return SORT_DESCENDING;
    }

    public Map<String, User> getMapUsers() {
        return mapUsers;
    }

    public List<String> getDiscardedRows() {
        return discardedRows;
    }

    public List<String> getAllMails() {
        return allMails;
    }

    public String getInputFile() {
        return inputFile;
    }
}



