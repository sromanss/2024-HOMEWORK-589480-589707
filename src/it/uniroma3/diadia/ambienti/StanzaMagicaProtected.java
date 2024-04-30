package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected {

	private int contatoreAttrezziPosati;

	private int sogliaMagica;

	private static final int SOGLIA_MAGICA_DEFAULT = 2;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int soglia) {
		super(nome);
		this. contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}


	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI ) {
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else if(this.contatoreAttrezziPosati>this.sogliaMagica){
			this.modificaAttrezzo(attrezzo);
			this.attrezzi[numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else 
			return false;
	}

}
