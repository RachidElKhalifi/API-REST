package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.CarrelloDao;
import com.labAirApi.dao.QtaColoreDispDao;
import com.labAirApi.dao.QtaTagliaDispDao;
import com.labAirApi.dao.OrdineNelCarrelloDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Carrello;
import com.labAirApi.model.ColoreDisp;
import com.labAirApi.model.OrdineNelCarrello;
import com.labAirApi.model.QtaColoreDisp;
import com.labAirApi.model.QtaTagliaDisp;
import com.labAirApi.model.TagliaDisp;

@Service
public class CarrelloServiceImp implements CarrelloService {
	
	@Autowired
	private CarrelloDao carrelloDao;
	
	@Autowired
	private QtaTagliaDispDao qtaTagliaDispDao;
	
	@Autowired
	private QtaColoreDispDao qtaColoreDispDao;
	
	@Autowired
	private OrdineNelCarrelloDao ordineNelCarrelloDao;
	
	@Lazy
	@Autowired
	private OrdineNelCarrelloService ordineNelCarrelloService;
	
	@Lazy
	@Autowired
	private ScarpaDispService scarpaDispService;
	
	@Lazy
	@Autowired
	private UtenteService utenteService;
	
	@Lazy
    @Autowired
	private ColoreDispService coloreDispService;
	
	@Lazy
	@Autowired
    private TagliaDispService tagliaDispService;
	
    @Override
	public List<Carrello> getCarrelli() {
		return (List<Carrello>) carrelloDao.findAll();
	}

	@Override
	public Carrello getCarrelloById(int id) {
		// se il carrello risulta nel DB lo restituisco
	    Optional<Carrello> carrelloOptional = carrelloDao.findById(id);
	    if (carrelloOptional.isPresent()) {
	        return carrelloOptional.get();
	        //altrimenti 
	    } else {
	    	//altrimenti torno un messaggio di errore (uso questo se devo tornare un carrello)
	    	Carrello msgPerCarrello =new Carrello("Carrello non trovato");
	    	//messaggio di errore se uso la lista scarpe nel carrello
			OrdineNelCarrello msgPerOrdine=new OrdineNelCarrello("Carrello non trovato");
			msgPerCarrello.getListaScarpeNelCarrello().add(msgPerOrdine);
			//torno il carrello e la lista scarpe nel carrello con i 2 messaggi
	        return msgPerCarrello;
	    }
	}

	@Override
	public Carrello salva(Carrello carrello) {
		return carrelloDao.save(carrello);
	}
	
	@Override
	public ObjectNode cancella(int idCarrello) {
		ResponseManager man;
		//se il metodo getCarrelloById mi torna una carrello con la stringa "info" vuota
		//segnifica che ha trovato il carrello e quindi posso eseguire il delete
		if (getCarrelloById(idCarrello).getInfo() == "" ) {
            // prima di eseguire il delete ricarico la quantità scarpe con i relativi colori e taglie
			// quindi (ciclo foreach)per ogni ordine che si trova nella lista ordiniNelCarrello
        for(OrdineNelCarrello ordineNelCarrello : getCarrelloById(idCarrello).getListaScarpeNelCarrello()) {
//preparo le variabili che mi servono per ricaricare nel DB ogni cosa nel ordine
        	int idScarpa=ordineNelCarrello.getIdentificativoScarpa();
        	int quantita=ordineNelCarrello.getQuantita();
        	String colore=ordineNelCarrello.getColore();
//        	int idColore=0;
        	String taglia=ordineNelCarrello.getTaglia();
//        	int idTaglia=0;
        	
        	// (ciclo foreach)per ogni coloreDisponibile della lista di coloriDisponibili della scarpa nell'ordine
       for(ColoreDisp coloreDisp : 	scarpaDispService.getScarpaById(idScarpa).getColoriDisponibili()) {
    	   //confronto se il colore della scarpa nel ordine è uguale allo iesimo colore della lista di coloriDisponibili della scarpa nell'ordine
           if(colore.equals(coloreDisp.getNome())) {
//          int idColore= coloreDisp.getId(); 
        	  //una volta trovato ricupero il suo id per eseguire il findById nella entity che modella la tabella di
        	  // bridge(colore-scarpa) che contiene la quantità colore disponibili per ogni modello di scarpa   
		   Optional<QtaColoreDisp> qtaColoreDisp= qtaColoreDispDao.findById(coloreDisp.getId());
		   //setto la quantità e salvo nel DB e esco dal for con break
		   qtaColoreDisp.get().setQuantita(qtaColoreDisp.get().getQuantita()+quantita);
		   qtaColoreDispDao.save(qtaColoreDisp.get());
		   break;
		   }
       }
       // faccio lo stesso con la taglia
       for(TagliaDisp tagliaDisp : 	scarpaDispService.getScarpaById(idScarpa).getTaglieDisponobili()) {
    	   if(taglia.equals(tagliaDisp.getTaglia())) {
//    	   int idTaglia= tagliaDisp.getId(); 
           Optional<QtaTagliaDisp> qtaTagliaDisp= qtaTagliaDispDao.findById(tagliaDisp.getId());
		   qtaTagliaDisp.get().setQuantita(qtaTagliaDisp.get().getQuantita()+quantita);
		   qtaTagliaDispDao.save(qtaTagliaDisp.get());
		   break;
    	   }
       }
       // per ogni ciclo di ordine nel carrello eseguo il delete dell'ordine
       ordineNelCarrelloDao.deleteById(ordineNelCarrello.getIdOrdine());
        }		
       // alla fine del ciclo eseguo il delete di turro il carrello
        carrelloDao.deleteById(idCarrello);
        // notifico il risultato dell'operazione		
		man = new ResponseManager(200, "Carrello eliminato con successo");
		return man.getResponse();	
	}
		else {
			man = new ResponseManager(404, "Carrello non trovato");
			return man.getResponse();	
		}
	}
	
    //----------------------------------------------------------------------------------------------------------
	
	public ObjectNode aggiungiAlCarrello(int idScarpa, int idColore, int idTaglia, int qta, int idUtente) {
		//dichiaro un oggetto di tipo ResponseManager che poi 
		//inizializzo all'occorenza con la stringa che mi serve in quel momento
		ResponseManager man;
		//cerco la taglia per id per gestire lo scarico dei pz da gestionale
		Optional<QtaTagliaDisp> qtaTagliaDisp= qtaTagliaDispDao.findById(idTaglia);
		//cerco il colore per id per gestire lo scarico dei pz da gestionale
		Optional<QtaColoreDisp> qtaColoreDisp= qtaColoreDispDao.findById(idColore);

		// controllo che l'id utente inserito esista nella lista utenti
		if(!utenteService.getUtenti().contains(utenteService.getUtenteById(idUtente))) {
		man = new ResponseManager(404, "Utente non trovato"); 
		return man.getResponse();
		}
		// controllo se l'idScarpa esiste nel listaScarpeDisp.
	       if(scarpaDispService.getScarpaById(idScarpa) == null) {
		 man = new ResponseManager(404, "Scarpa non dispinibile");
		return man.getResponse();
	       }
			// controllo se idColore esiste nel listaColoriDisp.
	         if(!scarpaDispService.getScarpaById(idScarpa).getColoriDisponibili().contains(coloreDispService.getColoreDispById(idColore)) || qtaColoreDisp.get().getQuantita()< qta) {
	        	 man = new ResponseManager(404, "Colore non dispinibile");
	     		return man.getResponse();
	         }	
	 		// controllo se idTaglia esiste nel listaTaglieDisp.
	     		  if(!scarpaDispService.getScarpaById(idScarpa).getTaglieDisponobili().contains(tagliaDispService.getTagliaDispById(idTaglia)) || qtaTagliaDisp.get().getQuantita()< qta){
	     			 man = new ResponseManager(404, "Taglia non dispinibile");
	 	     		return man.getResponse();   		
    }	
		else {
			// recupero i dati per creare una nuova scarpa
			int id = idScarpa;
			String nome = scarpaDispService.getScarpaById(idScarpa).getNome();
			String taglia = tagliaDispService.getTagliaDispById(idTaglia).getTaglia();
			String colore = coloreDispService.getColoreDispById(idColore).getNome();
			float prezzo = scarpaDispService.getScarpaById(idScarpa).getPrezzo();
			int quantita = qta;
			
			// controllo se devo creare un nuovo carrello oppure usare un carrello già
			// esistente(se questo if è true segnifica che è un nuovo utente e quindi devo creare un nuovo carrello)
			if(!utenteService.getUtentiDeiCarrelli().contains(utenteService.getUtenteById(idUtente))) {
				Carrello carrello = new Carrello();
				
				// creo la scarpa e setto tutti i dati necessari con i parametri passati nella requestBody
				OrdineNelCarrello scarpaScelta = new OrdineNelCarrello(id, nome, taglia, colore, prezzo,quantita);
				carrello.setUtente(utenteService.getUtenteById(idUtente));
				carrello.getListaScarpeNelCarrello().add(scarpaScelta);
				carrello.setTotale(carrello.getTotale() + (prezzo*quantita));
				
				// aggiungo il carrello alla lista carrelli presente nella classe
				// OrdineNelCarrello essendo una relazione molti a molti
				scarpaScelta.getListaCarrelli().add(carrello);
				
				// salvo nel DB il tutto
				salva(carrello);
				ordineNelCarrelloDao.save(scarpaScelta);
				
				//scalo dal DB le quantità aggiunte al carrello
				qtaTagliaDisp.get().setQuantita(qtaTagliaDisp.get().getQuantita()-quantita);
				qtaTagliaDispDao.save(qtaTagliaDisp.get());
				qtaColoreDisp.get().setQuantita(qtaColoreDisp.get().getQuantita()-quantita);
				qtaColoreDispDao.save(qtaColoreDisp.get());
				
				// notifico nel responseBody il risultato dell'operazione
				man = new ResponseManager(200,
						"Scarpa aggiunta con successo al carrello con id: " + carrello.getId());
				return man.getResponse();
			}
			
			 //nel caso in cui l'idUtente inserito sia già stato assegnato ad un carrello:
			else {

				// istanzio un nuovo oggetto scarpa con i dati passati nel requestBody
				OrdineNelCarrello scarpaScelta = new OrdineNelCarrello(id, nome, taglia, colore, prezzo,quantita);
				
				// ricupero il carrello dell'utente inserito e gli aggiungo la scarpa scelta
				Carrello carrello = utenteService.getUtenteById(idUtente).getCarrello();
				carrello.getListaScarpeNelCarrello().add(scarpaScelta);
				
				//aggiorno il totale carrello
				carrello.setTotale(utenteService.getUtenteById(idUtente).getCarrello().getTotale() +( prezzo*quantita));
				scarpaScelta.getListaCarrelli().add(carrello);

				//salvo il tutto nel DB
				carrelloDao.save(carrello);
				ordineNelCarrelloDao.save(scarpaScelta);
				
                //scalo dal DB le quantità aggiunte al carrello
				qtaTagliaDisp.get().setQuantita(qtaTagliaDisp.get().getQuantita()-quantita);
				qtaTagliaDispDao.save(qtaTagliaDisp.get());
				qtaColoreDisp.get().setQuantita(qtaColoreDisp.get().getQuantita()-quantita);
				qtaColoreDispDao.save(qtaColoreDisp.get());
				
                //notifico nel responseBody il risultato dell'operazione
			    man = new ResponseManager(200, "Scarpa aggiunta con successo al carrello con id: "
						+ carrello.getId());
				return man.getResponse();
			    }
		    }
       }

	@Override
	public ObjectNode eliminaScarpaDalCarrello(int idOrdine) {	
	       return ordineNelCarrelloService.cancella(idOrdine);	  
	}

	@Override
	public OrdineNelCarrello modQtaScarpaNelCarrello(int idOrdine, int qta) {
		//ricupero l'ordine 
		OrdineNelCarrello ordineNelCarrello = ordineNelCarrelloService.getScarpaNelCarrelloById(idOrdine);
		//ricupero la quantità che ha questo ordine prima di fare ogni modifica
		int qtaPreModifica=ordineNelCarrello.getQuantita();
		//setto la quantità con la nuova quantità passata come parametro
		ordineNelCarrello.setQuantita(qta);
		//ricupero il carrello di quest'ordine
		Carrello carrello = ordineNelCarrello.getListaCarrelli().getFirst();
		//ricupero il prezzo unitario della scarpa nel carrello
		float prezzoScarpa = ordineNelCarrello.getPrezzo();
		//setto il totale sottraendo tutto l'ordine dal totale ordini e poi gli sommo il nuovo totale ordine
		carrello.setTotale((carrello.getTotale()-(qtaPreModifica*prezzoScarpa))+(qta*prezzoScarpa));
		
		//ricarico a gestionale la differenza di quella scarpa con quella taglia
		//quindi per ogni taglia che è nella lista di taglie disponibili
		for(TagliaDisp tagliaDisp:tagliaDispService.getTaglieDisp()) {
			//se la iesima taglia e uguale alla taglia nel ordine
			if(tagliaDisp.getTaglia().equals(ordineNelCarrello.getTaglia())) {
				QtaTagliaDisp qtaTagliaDisp = qtaTagliaDispDao.findById(tagliaDisp.getId()).get();
				//quindi ricupero la quantita e la setto con quella nuova
				qtaTagliaDisp.setQuantita((qtaTagliaDisp.getQuantita()+qtaPreModifica)-qta);
				qtaTagliaDispDao.save(qtaTagliaDisp);
				// se trovo la taglia esco dal loop e non lo faccio finire
				break;
			}
		}
		//faccio lo stesso con i colori disponibili
		for(ColoreDisp coloreDisp:coloreDispService.getColoriDisp()) {
			if(coloreDisp.getNome().equals(ordineNelCarrello.getColore())) {
				int idColore=coloreDisp.getId();
				QtaColoreDisp qtaColoreDisp = qtaColoreDispDao.findById(idColore).get();
				qtaColoreDisp.setQuantita((qtaColoreDisp.getQuantita()+qtaPreModifica)-qta);
				qtaColoreDispDao.save(qtaColoreDisp);
				break;
			}
		}
			//salvo le modifiche e torno l'ordine con le nuove quantità
		carrelloDao.save(carrello);
		return ordineNelCarrelloService.salva(ordineNelCarrello);
}

}




