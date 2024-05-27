package com.labAirApi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.helper.Request;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Carrello;
import com.labAirApi.model.Indirizzo;
import com.labAirApi.model.Login;
import com.labAirApi.model.OrdineNelCarrello;
import com.labAirApi.model.ScarpaDisp;
import com.labAirApi.model.Utente;
import com.labAirApi.service.CarrelloService;
import com.labAirApi.service.IndirizzoService;
import com.labAirApi.service.LoginService;
import com.labAirApi.service.OrdineNelCarrelloService;
import com.labAirApi.service.ScarpaDispService;
import com.labAirApi.service.UtenteService;

@RestController
@RequestMapping("/")
public class AreaClienteController {

	@Autowired
	private CarrelloService carrelloService;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private OrdineNelCarrelloService ordineNelCarrelloService;

	@Autowired
	private ScarpaDispService scarpaDispService;

	@Autowired
	private IndirizzoService indirizzoService;

	boolean isLogin = false;
	int id_utente;
	int idUtenteNonLoggato;

	@PostMapping("/register")
	public Utente register(@RequestBody Utente utente) {
		id_utente = utenteService.salva(utente).getId();
		return utente;
	}

	// usaimo questo per impostare per la prima volta i dati di login
	// dell'utente che si è registrato ora usando la path register
	@PostMapping("/setLogin")
	public ObjectNode setLogin(@RequestBody Login login) {
		try {
			// qui uso l'id utente di quello che si è registrato prima
			login.setUtente(utenteService.getUtenteById(id_utente));
			return loginService.salva(login);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseManager man = new ResponseManager(400,
					"esiste già un utente con questi dati," + "cambia username o password");
			return man.getResponse();
		}
	}

// usiamo questo per modificare i dati di un login già salvato 
	// o per assegnarne uno nuovo ad'uno specifico utente
	@PostMapping("/setLogin/{idUtente}")
	public ObjectNode setLoginByIdUtente(@PathVariable("idUtente") int idUtente, @RequestBody Login login) {
		// verifico se l'idUtente passato esiste nel DB
		if (utenteService.getUtenteById(idUtente) != null) {
			Utente utente = utenteService.getUtenteById(idUtente);
			// verifico se l'utente passato ha già un login assegnato
			if (utente.getLogin() != null) {
				utente.getLogin().setUsername(login.getUsername());
				utente.getLogin().setPassword(login.getPassword());
				utenteService.salva(utente);
//			utente.setLogin(login);
//          così invece non funziona correttamente!!perchè ti chiede di salvare prima il login e poi salvare l'utente.
//          ma così ti crea un nuovo login,non solo la modifica di quello esistente
				ResponseManager man = new ResponseManager(200, "Dati salvati con successo");
				return man.getResponse();
			} else {
				// nel caso non abbia già un login assegnato
				login.setUtente(utente);
				loginService.salva(login);
				ResponseManager man = new ResponseManager(200, "Dati salvati con successo");
				return man.getResponse();
			}
		} else {
			ResponseManager man = new ResponseManager(404, "Utente inesistente");
			return man.getResponse();
		}
	}

	@PostMapping("/login")
	public String controlloLogin(@RequestBody Login login) {
        //uso il servizio di controllo autenticazione
		if (loginService.controlloLogin(login) != null) {
			// se il controllo va a buon fine setto la variabile login in true
			// e ricupero l'id utente da usare poi negli altri metodi che neccessitano di autenticazione
			isLogin = true;
			id_utente = loginService.controlloLogin(login).getUtente().getId();
			return "Autorizzazione concessa";
		} else {
			isLogin = false;
			id_utente = 0;
			return "Autorizzazione non concessa";
		}
	}

	@PostMapping("/aggiungiIndirizzo")
	public Indirizzo aggiungiIndirizzoSpedizione(@RequestBody Indirizzo indirizzo) {
		// se l'utente è loggato
		if (isLogin == true) {
			// salvo i dati dell'indirizzo passato nella requestBody
			indirizzoService.salva(indirizzo);
			Utente utente = utenteService.getUtenteById(id_utente);
			// ricupero l'utente loggato è gli assegno l'indirizzo salvato
			utente.setIndirizzo(indirizzo);
			utente.getIndirizzo().setInfo("Dati di spedizione salvati con successo");
			// salvo nel DB e torno i dati dell'indirizzo
			utenteService.salva(utente);
			return indirizzo;
		} else {
			// altrimenti rimando a fare prima il login con un msg
			Indirizzo msg = new Indirizzo("Si prega di eseguire prima il login");
			return msg;
		}
	}

	@GetMapping("scarpe/listaDettagliata")
	public List<ScarpaDisp> getScarpe() {
		return scarpaDispService.getScarpe();
	}

	@GetMapping("scarpe/listaSoloNome")
	public List<String> getNomiScarpe() {
		// creo una lista che conterrà solo i nomi delle scarpe disponibili nel DB
		List<String> nomiScarpe = new ArrayList<>();
		// ciclo usando il metodo sopra per ricuperare tutte le scarpe
		for (ScarpaDisp scarpaDisp : getScarpe())
			// e per ogni scarpa della lista ricupero il nome e lo aggiungo alla lista
			// creata prima
			nomiScarpe.add(scarpaDisp.getNome());
		// torno la lista
		return nomiScarpe;
	}

	@GetMapping("scarpe/dettaglioById/{idScarpa}")
	public ScarpaDisp getScarpaById(@PathVariable("idScarpa") int id) {
		return scarpaDispService.getScarpaById(id);
	}

	@GetMapping("scarpe/creaNuovaScarpa")
	public ScarpaDisp creaNuovaScarpa(@RequestBody ScarpaDisp scarpaDisp) {
		return scarpaDispService.salva(scarpaDisp);
	}

	// localhost:3000/aggiungi
	@PostMapping("/aggiungi")
	public ObjectNode aggiungiAlCarrello(@RequestBody Request request) {
		// se è stato eseguito il login ricupero idUtente con cui è stato fatto il login
		if (isLogin == true)
			return carrelloService.aggiungiAlCarrello(request.getIdScarpa(), request.getIdColore(),
					request.getIdTaglia(), request.getQuantita(), id_utente);
		else {
			// altrimenti creo un nuovo utente anonimo e ricupero il suo id da usare poi
			Utente utente = new Utente();
			idUtenteNonLoggato = utenteService.salva(utente).getId();
			return carrelloService.aggiungiAlCarrello(request.getIdScarpa(), request.getIdColore(),
					request.getIdTaglia(), request.getQuantita(), idUtenteNonLoggato);
		}
	}

	// modifica quantità scarpa scelta ed aggiunta al carrello,senza dover annullare
	// tutto 'lordine
	// valido sia per l'utente autenticato che non
	@PostMapping("/modQta/{idOrdine}")
	public OrdineNelCarrello modQtaScarpaNelCarrello(@PathVariable("idOrdine") int idOrdine,
			@RequestBody Request request) {
		// verifico che il cliente sia loggato e se l'ordine che vuole modificare ci sia
		// veramente nel carrello assegnato a lui
		// e quindi ciclo sulla lista ordini del cliente
		if (isLogin == true) {
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(id_utente).getCarrello()
					.getListaScarpeNelCarrello()) {
				// e se l'id ordine che mi ha passato come parametro è presente nella
				// sua lista ordini procedo con la modifica
				if (idOrdine == ordineNelCarrello.getIdOrdine())
					return carrelloService.modQtaScarpaNelCarrello(idOrdine, request.getQuantita());
				// altrimenti torno un messaggio di errore
				OrdineNelCarrello ordine = new OrdineNelCarrello(
						"Utente non autorizzato ad effettuare questa operazione");
				return ordine;
			}
		} else {
			// se l'utente non è loggato verifico se l'ordine che vuole modificare ci sia
			// veramente nel carrello assegnato a lui con"idUtenteNonLoggato"
			// e quindi ciclo sulla lista ordini del cliente
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(idUtenteNonLoggato).getCarrello()
					.getListaScarpeNelCarrello()) {

				if (idOrdine == ordineNelCarrello.getIdOrdine())
					// e se l'id ordine che mi ha passato come parametro è presente nella
					// sua lista ordini procedo con la modifica
					return carrelloService.modQtaScarpaNelCarrello(idOrdine, request.getQuantita());
				// altrimenti torno un messaggio di errore
				OrdineNelCarrello ordine = new OrdineNelCarrello(
						"Utente non autorizzato ad effettuare questa operazione");
				return ordine;
			}
		}

		// e se non è nessuna di queste opzioni torno un messaggio di errore
		OrdineNelCarrello ordine = new OrdineNelCarrello("Utente non autorizzato ad effettuare questa operazione");
		return ordine;
	}

	@GetMapping("/contenutoOrdine/{idOrdine}")
	public OrdineNelCarrello getContenutoOrdineById(@PathVariable("idOrdine") int idOrdine) {
		// verifico che il cliente sia loggato e se l'ordine che vuole visualizzare ci sia
		// veramente nel carrello assegnato a lui
		// e quindi ciclo sulla lista ordini del cliente
		if (isLogin == true) {
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(id_utente).getCarrello()
					.getListaScarpeNelCarrello()) {
				// e se l'id ordine che mi ha passato come parametro è presente nella
				// sua lista ordini procedo con l'eseguire la richiesta
				if (idOrdine == ordineNelCarrello.getIdOrdine())
					return ordineNelCarrelloService.getScarpaNelCarrelloById(idOrdine);
				// questo oggetto mi serve solo per notificare un messaggio di risposta
				// quindi creo un nuovo oggetto e gli setto l'attributo
				// info con il messaggio e il resto a null
				OrdineNelCarrello ordine = new OrdineNelCarrello(
						"Utente non autorizzato ad effettuare questa operazione");
				return ordine;
			}
		} else {
			// se l'utente non è loggato verifico se l'ordine che vuole visualizzare ci sia
			// veramente nel carrello assegnato a lui
			// e quindi ciclo sulla lista ordini del cliente
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(idUtenteNonLoggato).getCarrello()
					.getListaScarpeNelCarrello()) {
				if (idOrdine == ordineNelCarrello.getIdOrdine())
					// e se l'id ordine che mi ha passato come parametro è presente nella
					// sua lista ordini procedo con con l'eseguire la richiesta
					return ordineNelCarrelloService.getScarpaNelCarrelloById(idOrdine);
				// altrimenti torno un messaggio di errore
				OrdineNelCarrello ordine = new OrdineNelCarrello(
						"Utente non autorizzato ad effettuare questa operazione");
				return ordine;
			}
		}
		// e se non è nessuna di queste opzioni torno un messaggio di errore
		OrdineNelCarrello ordine = new OrdineNelCarrello("Utente non autorizzato ad effettuare questa operazione");
		return ordine;
	}

	// localhost:3000/eliminaOrdine/1
	@DeleteMapping("/eliminaOrdine/{idOrdine}")
	public ObjectNode eliminaOrdineDalCarrello(@PathVariable("idOrdine") int idOrdine) {
		// verifico che il cliente sia loggato e se l'ordine che vuole eliminare ci sia
		// veramente nel carrello assegnato a lui
		// e quindi ciclo sulla lista ordini del cliente
		if (isLogin == true) {
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(id_utente).getCarrello()
					.getListaScarpeNelCarrello()) {
				// e se l'id ordine che mi ha passato come parametro è presente nella
				// sua lista ordini procedo con l'eliminazione
				if (idOrdine == ordineNelCarrello.getIdOrdine())
					return carrelloService.eliminaScarpaDalCarrello(idOrdine);
				// altrimenti torno un messaggio di errore
				ResponseManager man = new ResponseManager(404,
						"Utente non autorizzato ad effettuare questa operazione");
				return man.getResponse();
			}
		} else {
			// se l'utente non è loggato verifico se l'ordine che vuole eliminare ci sia
			// veramente nel carrello assegnato a lui
			// e quindi ciclo sulla lista ordini del cliente
			for (OrdineNelCarrello ordineNelCarrello : utenteService.getUtenteById(idUtenteNonLoggato).getCarrello()
					.getListaScarpeNelCarrello()) {

				if (idOrdine == ordineNelCarrello.getIdOrdine())
					// e se l'id ordine che mi ha passato come parametro è presente nella
					// sua lista ordini procedo con l'eliminazione
					return carrelloService.eliminaScarpaDalCarrello(idOrdine);
				// altrimenti torno un messaggio di errore
				ResponseManager man = new ResponseManager(404,
						"Utente non autorizzato ad effettuare questa operazione");
				return man.getResponse();
			}
		}

		// e se non è nessuna di queste opzioni torno un messaggio di errore
		ResponseManager man = new ResponseManager(404, "Utente non autorizzato ad effettuare questa operazione");
		return man.getResponse();
	}

	@GetMapping("carrello/list")
	// fornisce tutta la lista dei carrelli con i loro utenti
	public List<Carrello> getCarrelli() {
		return carrelloService.getCarrelli();

	}

	// localhost:3000/dettaglioCarrello/1
	@GetMapping("dettaglioCarrello/{idCarrello}")
	// fornisce info sul carrellio dato un id
	public Carrello getCarrelloById(@PathVariable("idCarrello") int idCarrello) {
		// verifico che il cliente sia loggato e che il carrello che vuole visualizzare
		// sia assegnato a lui
		if (isLogin == true) {
			if (idCarrello == utenteService.getUtenteById(id_utente).getCarrello().getId())
				// e se l'id carrello che mi ha passato come parametro è il suo
				return carrelloService.getCarrelloById(idCarrello);
			Carrello carrello = new Carrello("Utente non autorizzato ad effettuare questa operazione");
			return carrello;
		} else {

			if (idCarrello == utenteService.getUtenteById(idUtenteNonLoggato).getCarrello().getId())
				return carrelloService.getCarrelloById(idCarrello);
			Carrello carrello = new Carrello("Utente non autorizzato ad effettuare questa operazione");
			return carrello;
		}
	}

	// localhost:3000/contenutoCarrello/1
	@GetMapping("/contenutoCarrello/{idCarrello}")
	public List<OrdineNelCarrello> getContenutoCarrelloById(@PathVariable("idCarrello") int idCarrello) {
		// verifico che il cliente sia loggato e che il carrello che vuole visualizzare
		// sia assegnato a lui
		if (isLogin == true) {
			if (idCarrello == utenteService.getUtenteById(id_utente).getCarrello().getId())
				// e se l'id carrello che mi ha passato come parametro è il suo torno
				// l'informazione richiesta
				return carrelloService.getCarrelloById(idCarrello).getListaScarpeNelCarrello();
			// altrimenti torno un messaggio di non autorizzazione
			// visto che devo tornare una lista<ordineNelCarrello>,
			// ne creo una e gli aggiungo il messaggio e poi la torno
			List<OrdineNelCarrello> listMsg = new ArrayList<>();
			OrdineNelCarrello msg = new OrdineNelCarrello("Utente non autorizzato ad effettuare questa operazione");
			listMsg.add(msg);
			return listMsg;
		} else {
            //se il cliente non è loggato verifico che il carrello che vuole visualizzare sia il suo
			if (idCarrello == utenteService.getUtenteById(idUtenteNonLoggato).getCarrello().getId())
				return carrelloService.getCarrelloById(idCarrello).getListaScarpeNelCarrello();
			// altrimenti torno un messaggio di non autorizzazione
			// visto che devo tornare una lista<ordineNelCarrello>,
			// ne creo una e gli aggiungo il messaggio e poi la torno
			List<OrdineNelCarrello> listMsg = new ArrayList<>();
			OrdineNelCarrello msg = new OrdineNelCarrello("Utente non autorizzato ad effettuare questa operazione");
			listMsg.add(msg);
			return listMsg;
		}
	}

	// localhost:3000/eliminaCarrello/1
	@DeleteMapping("/eliminaCarrello/{idCarrello}")
	public ObjectNode eliminaCarrello(@PathVariable("idCarrello") int idCarrello) {
		// verifico che il cliente sia loggato e che il carrello che vuole eliminare
		// sia assegnato a lui
		if (isLogin == true) {
			if (idCarrello == utenteService.getUtenteById(id_utente).getCarrello().getId())
				//se è così eseguo la cancellazione del carrello assegnato a lui
				return carrelloService.cancella(idCarrello);
			// altrimenti torno un messaggio di non autorizzazione
			ResponseManager man = new ResponseManager(404, "Utente non autorizzato ad effettuare questa operazione");
			return man.getResponse();
		} else {
			//se il cliente non è loggato verifico che il carrello che vuole eliminare sia il suo
			if (idCarrello == utenteService.getUtenteById(idUtenteNonLoggato).getCarrello().getId())
				//se è così eseguo la cancellazione del carrello assegnato a lui
				return carrelloService.cancella(idCarrello);
			// altrimenti torno un messaggio di non autorizzazione
			ResponseManager man = new ResponseManager(404, "Utente non autorizzato ad effettuare questa operazione");
			return man.getResponse();
		}
	}

}
