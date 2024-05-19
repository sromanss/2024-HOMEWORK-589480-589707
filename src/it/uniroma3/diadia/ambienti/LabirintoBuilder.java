package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto;
	private Stanza ultimaStanzaAggiunta;
	private Map<String, Stanza> nome2stanza;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<String, Stanza>();
	}
	
	public Map<String, Stanza> getNome2stanza() {
		return nome2stanza;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		Stanza i = new Stanza(stanzaIniziale);
		this.labirinto.setStanzaCorrente(i);
		this.UltimaStanzaAggiuntaEAggiorna(i);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		Stanza s = new Stanza(stanzaVincente);
		this.labirinto.setStanzaVincente(s);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		Stanza s = new Stanza(stanza);
		this.UltimaStanzaAggiuntaEAggiorna(s);
		return this;
	}	

	public LabirintoBuilder addAttrezzo(String attrezzo, int peso) {
		Attrezzo a = new Attrezzo(attrezzo, peso);
		if(this.ultimaStanzaAggiunta==null)
			return this;
		this.ultimaStanzaAggiunta.addAttrezzo(a);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
		Stanza c = this.nome2stanza.get(stanzaCorrente);
		Stanza a = this.nome2stanza.get(stanzaAdiecente);
		c.impostaStanzaAdiacente(direzione, a);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome) {
		Stanza stanza = new StanzaMagica(nome);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		Stanza stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata);
		this.UltimaStanzaAggiuntaEAggiorna(stanza);
		return this;
	}
	
	public void UltimaStanzaAggiuntaEAggiorna(Stanza stanza) {
		this.ultimaStanzaAggiunta = stanza;
		this.nome2stanza.put(stanza.getNome(), stanza);
	}
}
