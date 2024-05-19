package it.uniroma3.diadia.ambienti;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO && 589480 && 589707
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private Map<String, Attrezzo> nome2attrezzi;
    private int numeroAttrezzi;
    
    private Map<String, Stanza> direzioni2stanze;
    private int numeroStanzeAdiacenti;
    
    public List<Stanza> getStanzeAdiacenti() {
		return (List<Stanza>) direzioni2stanze.values();
	}

    public void setStanzeAdiacenti(Map<String, Stanza> stanzeAdiacenti) {
		this.direzioni2stanze = stanzeAdiacenti;
	}

	public int getNumeroStanzeAdiacenti() {
		return numeroStanzeAdiacenti;
	}

	public void setNumeroStanzeAdiacenti(int numeroStanzeAdiacenti) {
		this.numeroStanzeAdiacenti = numeroStanzeAdiacenti;
	}

	public int getNumeroAttrezziPossibili() {
		return NUMERO_MASSIMO_ATTREZZI-this.numeroAttrezzi;
	}
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.direzioni2stanze = new HashMap<>();
		this.nome2attrezzi = new HashMap<>();
	}

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	//for(int i=0; i<this.direzioni.length; i++)
        //	if (direzione.equals(this.direzioni[i])) {
        //		this.stanzeAdiacenti[i] = stanza;
        //		aggiornato = true;
        //	}
        if (direzioni2stanze.containsKey(direzione)) {
			this.direzioni2stanze.put(direzione,stanza);
			aggiornato = true;
		}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni2stanze.put(direzione,stanza);
				this.numeroStanzeAdiacenti++;
			}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza = null;
		if (this.direzioni2stanze.containsKey(direzione))
			stanza = this.direzioni2stanze.get(direzione);
		return stanza;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Collection<Attrezzo> getAttrezzi() {
		return this.nome2attrezzi.values();
	}

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo != null && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
			this.numeroAttrezzi++;
			return true;
		}
		else {
			return false;
		}
	}

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.getAttrezzi().toString());
		return risultato.toString();
	}


    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		//boolean trovato;
		//trovato = false;
		//for (Attrezzo attrezzo : this.attrezzi) {
		//	if (attrezzo!=null) {
		//		if (attrezzo.getNome().equals(nomeAttrezzo))
		//			trovato = true;
		//	}
		//}
		//return trovato;
		return this.nome2attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		//for (Attrezzo attrezzo : this.attrezzi) {
		//	if (attrezzo!=null)
		//		if (attrezzo.getNome().equals(nomeAttrezzo))
		//			attrezzoCercato = attrezzo;
		//}
		//return attrezzoCercato;
		if (this.nome2attrezzi.containsKey(nomeAttrezzo))
			attrezzoCercato = this.nome2attrezzi.get(nomeAttrezzo);
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		//if (attrezzo!=null) {
		//	int i=0;
		//	for(Attrezzo a : this.attrezzi) {
		//		if (a!=null) {
		//			if (a.getNome().equals(attrezzo.getNome())) {
		//				this.attrezzi[i]=null;
		//				this.numeroAttrezzi--;
		//			}
		//		}
		//		i++;
		//	}
		//	return true;
		//}
		//else	
		//	return false;
		if(attrezzo!=null){
			this.nome2attrezzi.remove(attrezzo.getNome(), attrezzo);

			return true;
		}
		else
			return false;
	}


	public Set<String> getDirezioni() {

		return direzioni2stanze.keySet();
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stanza that = (Stanza) obj;
		return this.getNome().equals(that.getNome());
	}

}