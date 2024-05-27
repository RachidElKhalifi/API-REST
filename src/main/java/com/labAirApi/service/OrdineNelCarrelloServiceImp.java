package com.labAirApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.dao.CarrelloDao;
import com.labAirApi.dao.OrdineNelCarrelloDao;
import com.labAirApi.helper.ResponseManager;
import com.labAirApi.model.Carrello;
import com.labAirApi.model.OrdineNelCarrello;

@Service
public class OrdineNelCarrelloServiceImp implements OrdineNelCarrelloService {

	
	@Autowired
	OrdineNelCarrelloDao ordineNelCarrelloDao;
	
	@Autowired
	CarrelloDao carrelloDao;
	
	@Override
	public List<OrdineNelCarrello> getScarpeNelCarrello() {
		return (List<OrdineNelCarrello>) ordineNelCarrelloDao.findAll();
	}

	@Override
	public OrdineNelCarrello getScarpaNelCarrelloById(int idScarpaNelCarrello) {
		Optional<OrdineNelCarrello> scarpaNelCarrelloOptional = ordineNelCarrelloDao.findById(idScarpaNelCarrello);
		if (scarpaNelCarrelloOptional.isPresent()) {
			return scarpaNelCarrelloOptional.get();
		} else
			return null;
	}

	@Override
	public OrdineNelCarrello salva(OrdineNelCarrello ordineNelCarrello) {
		return ordineNelCarrelloDao.save(ordineNelCarrello);
	}
	
	@Override
	public ObjectNode cancella(int idScarpaNelCarrello) {
		ResponseManager man;
		//controllo se l'id della scarpa da cancellare esiste 
		if (getScarpaNelCarrelloById(idScarpaNelCarrello) != null){
			//ricupero l'ordine dall'id della scarpa e lo metto in un oggetto di tipo OrdineNelCarrello
			OrdineNelCarrello ordineNelCarrello = ordineNelCarrelloDao.findById(idScarpaNelCarrello).get();
			//ricupero il carrello assegnato a quell'ordine e lo metto in un oggetto di tipo Carrello
            Carrello carrello = ordineNelCarrello.getListaCarrelli().getFirst();
            //setto il totale del carrello
			carrello.setTotale(carrello.getTotale() - (ordineNelCarrello.getPrezzo()*ordineNelCarrello.getQuantita()));
			//cancello l'ordine e salvo l'aggiornamento al carrello
            carrelloDao.save(carrello);
			ordineNelCarrelloDao.deleteById(idScarpaNelCarrello);
            //notifico il risultato della operazione
		man = new ResponseManager(200, "Ordine eliminato con successo dal carrello "+carrello.getId());
		return man.getResponse();	
	}
		else {
			man = new ResponseManager(404, "Ordine non trovato");
			return man.getResponse();	
		}
	}

}
