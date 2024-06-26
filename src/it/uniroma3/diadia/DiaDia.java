package it.uniroma3.diadia;

import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica; 
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO && 589480 && 589707
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final public String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	
	private Partita partita;
	private IO io;

	public DiaDia(IO console, Labirinto labirinto) {
		this.io = console;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws Exception{
		String istruzione;  
		io.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do	{
			istruzione = io.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}  

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.io);
		try {
			comandoDaEseguire = factory.costruisciComando(istruzione);
		} catch (ClassNotFoundException cne) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		} catch (NullPointerException npe) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		}
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
		/*
		if(comandoDaEseguire.getNome()==null) {
			io.mostraMessaggio("Non hai inserito nessun comando!");
			return false;
		}
		else if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());


		else
			io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 
	private void vai(String direzione) {
		if(direzione==null)
			io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			io.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(--cfu);
		}
		// messaggio che mostra all'utente in che stanza si trova
		io.mostraMessaggio("Stanza corrente: " + partita.getLabirinto().getStanzaCorrente().getDescrizione());
		// messaggio che mostra all'utente il contenuto della borsa
		io.mostraMessaggio("Borsa: " + partita.getGiocatore().getBorsa().toString());
		//messaggio che stampa il numer odi CFU rimanenti
		io.mostraMessaggio("CFU rimanenti: "+this.partita.getGiocatore().getCfu());
	}
	//metodo per prendere un oggetto da una stanza
	public void prendi (String nome) {
		//caso in cui l'utente non inserisce un nome
		if (nome==null) {
			io.mostraMessaggio("Specificare l'oggetto da prendere.");
			return;
		}
		//caso in cui l'utente inserisce un nome non presente nella stanza corrente
		else if (!this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nome)) {
			io.mostraMessaggio("L'oggetto inserito non è presente nella stanza corrente!");
			return;
		}
		else {
			Attrezzo a = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nome);
			this.partita.getGiocatore().getBorsa().addAttrezzo(a);				//aggiungi l'attrezzo nella borsa del giocatore
			this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(a);	//rimuovi l'attrezzo dalla stanza corrente
			io.mostraMessaggio("Oggetto preso!");
		}

	}
	//metodo per posare un oggetto in una stanza
	public void posa (String nome) {
		//caso in cui l'utente non inserisca un nome
		if (nome==null)
			io.mostraMessaggio("Specificare l'oggetto da posare.");
		//caso in cui l'utente inserisce un nome di un oggetto non presente nella borsa del giocatore
		else if (!this.partita.getGiocatore().getBorsa().hasAttrezzo(nome)) {
			io.mostraMessaggio("Non hai questo oggetto nella borsa!");
		}
		else {
			Attrezzo a = this.partita.getGiocatore().getBorsa().getAttrezzo(nome);
			this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);		//aggiungi l'atterzzo nella stanza corrente
			this.partita.getGiocatore().getBorsa().removeAttrezzo(nome);		//rimuovi l'attrezzo dalla borsa del giocatore
			io.mostraMessaggio("Oggetto posato!");
		}
	}

	/**
	 * Comando "Fine".
	 
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	*/

	public static void main(String[] argc) throws Exception {
		Scanner scanner = new Scanner(System.in);
		IO console = new IOConsole(scanner);
		Labirinto labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
													//.addStanzaIniziale("Atrio")
													//.addCane("Fido", "Sono un cane, bau!")
													//.addAttrezzo("osso", 1)
													//.addStanzaVincente("Biblioteca")
													//.addAdiacenza("Atrio", "Biblioteca", "nord")
													// e così via fino ad aggiungere tutte le stanze
		DiaDia gioco = new DiaDia(console, labirinto);
		gioco.gioca();
		scanner.close();
	}
}