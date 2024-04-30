package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	private IO io;
	private String nomeAttrezzo;
	private final static String NOME = "posa";


	@Override
	public void esegui(Partita partita) {
		if (nomeAttrezzo==null) {
			io.mostraMessaggio("Specificare l'oggetto da posare.");
			return;
		}
		if (!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			io.mostraMessaggio("Non hai questo oggetto nella borsa!");
			return;
		}
		Attrezzo a = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

		if(partita.getStanzaCorrente().getNumeroAttrezziPossibili()>0) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(a);
			partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
			io.mostraMessaggio("Oggetto posato!");
			return;
		}
		else {
			io.mostraMessaggio("Non c'e' spazio nella stanza per poter inserire questo attrezzo!");
		}
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo  = parametro;

	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
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
