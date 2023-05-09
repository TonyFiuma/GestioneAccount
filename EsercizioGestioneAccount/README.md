
# GestioneAccount
* ----------------
*
* Scopo: utilizzare Generics, Collections, Scanner, classi anonime,
*       
*
* 1)Si ha in input un file con informazioni per ogni codice utente, univoco.
*
* 2)Le righe del file, per ogni utente, devono contenere una sola riga con
*   nome, cognome e indirizzo.
*
* 3)Righe successive doppie o righe in generale errate, vanno segnalate
*   e memorizzate per essere visualizzate.
*
* 4)Per lo stesso utente possono essere presenti una o più righe con email.
*
* 5)Deve essere definita una classe User con nome, cognome e indirizzo da poter
*   restituire a un metodo chiamante. User conterrà anche le mail;
*
* 6)GestioneAccount e l'interface relative utilizzano i Generics per dichiarare
*   genericamente la classe User.
*
* 7)GestioneAccount deve definire i valori di scelta come costanti statiche
*   static int SORT_ASCENDING = 0;
*   static int SORT_DESCENDING = 1;
*
* 8)Gli utenti e le mail vengono caricati nelle strutture all'istanziazione
*   di GestioneAccount.
*   I metodi addUser() e addmail() permetteranno di inserire ulteriori utenti
*   e mail
*
* Il file sarà della forma:
*
* utente1 nome cognome indirizzo
* ...
* utenteN nomeN cognomeN indirizzoN  (composto da più parole)
* utentex aaa@bbb.cc
* ...
* utentex xxx@yyy.zz
*
* Le funzionalità previste sono definite dall'interface IAccount
*
* interface IAccount
  /* Inserisce un utente */
  //	 boolean addUser(String idUser, String nome, String Cognome, String indirizzo);

  /* Inserisce una mail utente */	 
  //	 boolean addMail(String idUser, String mail);

  /* Restituisce true se l'utente esiste */
  //	 boolean existsUser(String idUser);

  /* Restituisce true se l'utente ha almeno una mail */
  //	 boolean userHasMail(String idUser);

  /* Restituisce tutte le mail dell'utente */
  //	 String[] userMails(String idUser);

//   /* Restituisce l'utente con l'id fornito */	 	 
//   T user(String idUser);

	 /* Restituisce tutti i codici utente sortati */	 
//	 String[] idUsers(int sortType);

	 /* Restituisce tutti gli oggetti utente sortati (nome, cognome) */	 
//	 T[] users(int sortType);

	 /* Restituisce il primo utente (ordinati per nome, cognome) */	 
//	 T firstUser();

	 /* Restituisce l'utente (ordinati per nome, cognome, indirizzo) */	 
//	 T lastUser();

	 /* Restituisce i primi numUsers utenti (ordinati per nome, cognome) */	 
//	 T[] firstUsers(int numUsers);

	 /* Restituisce gli ultimi numUsers utenti (ordinati per nome, cognome) */	 
//	 T[] lastUsers(int numUsers);

     /* Restituisce tutte le righe scartate, perchè doppie o errate */
//   String[] discardedRows();

     /* Restituisce tutte le mail in ordine ascendente */
//   String[] allMails();