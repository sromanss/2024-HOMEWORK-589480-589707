package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StanzaTest {

	Stanza s1 = new Stanza("s1");
	Stanza s2= new Stanza("s2");
	Attrezzo m = new Attrezzo("spada", 11);
	
	
	@Test
	public void testGetStanzaAdiacente() {
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testGetNome() {
		assertEquals("s1", s1.getNome());
	}
	@Test
	public void testSetStanzaAdiacente() {
		
		assertNull(s1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testImpostaStanzaAdiacente() {
		s1.impostaStanzaAdiacente("nord", s2);
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testImpostaStanzaAdiacente2() {
		s2.impostaStanzaAdiacente("sud", s1);
		assertEquals(s1, s2.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void testSetNumeroStanzeAdiacenti() {
		s1.setNumeroStanzeAdiacenti(4);
		assertEquals(4, s1.getNumeroStanzeAdiacenti());
	}

	@Test
	public void testGetNumeroStanzeAdiacenti() {
		assertEquals(0, s1.getNumeroStanzeAdiacenti());
	}
	@Test
	public void testHasAttrezzo () {
		s1.addAttrezzo(m);
		assertTrue(s1.hasAttrezzo("spada"));
	}
	
}
	
	
