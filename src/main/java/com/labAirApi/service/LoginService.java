package com.labAirApi.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.labAirApi.model.Login;

public interface LoginService {
	
	public Login controlloLogin(Login login);
	public ObjectNode salva(Login login);

}
