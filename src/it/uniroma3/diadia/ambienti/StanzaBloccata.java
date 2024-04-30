package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata (String nome, String direzione) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}
	@Override
	public Stanza getStanzaAdiacente (String direzione) {
		if (direzioneBloccata.equals(direzione) && !this.hasAttrezzo(attrezzoSbloccante)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);
	}
	@Override
	public String getDescrizione() {
		String bloccata = "Stanza bloccata nella direzione: "+ direzioneBloccata + 
				"\nnPrendi l'oggetto" + attrezzoSbloccante + " e posalo nella stanza";
		
		if (!this.hasAttrezzo(attrezzoSbloccante))
			return bloccata;
		return super.getDescrizione();
	}

}
