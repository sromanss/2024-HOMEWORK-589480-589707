package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	
	static final private String[] ELENCO_COMANDI = {"vai <direzione> -> per spostarsi in un'altra stanza", 
			"\nprendi <nome_attrezzo> -> per prendere l'attrezzo specificato dalla stanza corrente",
			"\nposa <nome_attrezzo> -> per posare l'attrezzo specificato nella stanza corrente",
			"\nguarda -> per conoscere la propria posizione, i cfu rimanenti e gli attrezzi nella borsa",
			"\naiuto -> per conoscere la lista dei comandi possibili",
			"\nfine -> per terminare il programma"};

	private IO io;
	private final static String NOME = "aiuto";

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< ELENCO_COMANDI.length; i++) 
			io.mostraMessaggio(ELENCO_COMANDI[i]+" ");
		io.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
		
	}

	@Override
	public String getNome() {
		return NOME;
	}

}
