package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {
	
	Partita p = new Partita();
	Stanza s = new Stanza("Stanza");
	
	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", p.getLabirinto().getStanzaVincente().getNome());
	}
	@Test
	public void testSetStanzaCorrente() {
		p.getLabirinto().setStanzaCorrente(s);
		assertEquals(s, p.getLabirinto().getStanzaCorrente());
	}
	@Test
	public void testIsFinita() {
		assertFalse(p.isFinita());
	}
	@Test
	public void testSetFinita() {
		p.setFinita();
		assertTrue(p.isFinita());
	}
	@Test
	public void testVinta() {
		assertFalse(p.vinta());
	}
	@Test
	public void testGetStanzaCorrente () {
		p.getLabirinto().setStanzaCorrente(s);
		assertEquals(s, p.getLabirinto().getStanzaCorrente());
	}
	@Test
	public void testGetGiocatore () {
		assertEquals(20, p.getGiocatore().getCfu());
	}
	
}