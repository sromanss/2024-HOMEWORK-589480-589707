package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoCheIllumina;
	public StanzaBuia (String nome, String attrezzoCheIllumina) {
		super(nome);
		this.attrezzoCheIllumina = attrezzoCheIllumina;
	}
	@Override
	public String getDescrizione () {
		String buio = new String();
		buio = "qui c'è buio pesto";
		if (!this.hasAttrezzo(attrezzoCheIllumina))
			return buio;
		return super.getDescrizione();
	}
}
