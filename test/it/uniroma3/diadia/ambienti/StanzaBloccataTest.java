package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {

	private StanzaBloccata stanzaB;
	private Stanza s;
	private Attrezzo a;
	
	@Before
	public void setUp() throws Exception {
		stanzaB= new StanzaBloccata("StanzaBloccata", "sud", "arco");
		s = new Stanza("Stanza2");
		a = new Attrezzo("arco", 1);
		stanzaB.impostaStanzaAdiacente("sud", s);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(stanzaB, stanzaB.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		stanzaB.addAttrezzo(a);
		assertEquals(s, stanzaB.getStanzaAdiacente("sud"));
		
	}

	@Test
	public void testGetDescrizioneDirezioneSbloccata() {
		stanzaB.addAttrezzo(a);
		assertEquals(stanzaB.toString(), stanzaB.getDescrizione());
	}
	
	@Test
	public void testGetDescrizioneDirezioneBloccata() {
		String e = "Stanza bloccata nella direzione: sud"+"\nPrendi arco e posalo nella stanza";
		assertEquals(e, stanzaB.getDescrizione());
		
	}

}
