package br.com.rafaelchagasb.infinispan.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.rafaelchagasb.infinispan.ejb.CacheService;

@Path("clients")
public class ClientResource {
	
	
	@Inject
	CacheService cacheEjb;
	
	@Path("{name}")
	@GET
	public String cacheEjb(@PathParam("name") String name) {
		
		 Optional<String> item = cacheEjb.get(name);
		 
		 if(item.isPresent()) {
			 return item.get();
		 }
		 
		 if(!item.isPresent()) {
			 cacheEjb.set(name, name, 60l);
		 } 
		 return null;
	}
}
