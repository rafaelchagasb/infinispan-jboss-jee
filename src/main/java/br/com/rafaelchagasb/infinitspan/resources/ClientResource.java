package br.com.rafaelchagasb.infinitspan.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.rafaelchagasb.infinitspan.ejb.CacheService;

@Path("clients")
public class ClientResource {
	
	
	@Inject
	CacheService cacheEjb;
	
	@Path("{name}")
	@GET
	public void cacheEjb(@PathParam("name") String name) {
		
		 Optional<String> item = cacheEjb.get(name);
		 
		 if(!item.isPresent()) {
			 cacheEjb.set(name, name, 60l);
		 }
	}
}
