package br.com.rafaelchagasb.infinitspan.cliente;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

import org.infinispan.client.hotrod.RemoteCacheManager;

@Stateless
public class InfinitspanClient {
	
	private RemoteCacheManager rmc;
	
	private static final String HOST = "localhost";
	
	private static final int PORT = 11222;
	
	private static final String USER = "admin";
	
	private static final String PASSWORD = "password";
	
	@PostConstruct
	public void init() {
		org.infinispan.client.hotrod.configuration.ConfigurationBuilder cb = new org.infinispan.client.hotrod.configuration.ConfigurationBuilder();
		cb.marshaller(new org.infinispan.commons.marshall.ProtoStreamMarshaller())
				.statistics()
				.enable().addServer()
				.host(HOST)
				.port(PORT)
				.connectionTimeout(5000)
				.socketTimeout(5000)
				.security()
				.authentication()
				.username(USER)
				.password(PASSWORD);
		this.rmc = new RemoteCacheManager(cb.build());
	}
	
	@PreDestroy
	public void destruir() {
		this.rmc.close();
	}
	
	public RemoteCacheManager getClientCache() {
		return this.rmc;
	}
}
