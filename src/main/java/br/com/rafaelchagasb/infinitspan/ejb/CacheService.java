package br.com.rafaelchagasb.infinitspan.ejb;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import br.com.rafaelchagasb.infinitspan.cliente.InfinitspanClient;

@Stateless
public class CacheService{
	
	private static final String KEY_CACHE = "clients";

	private RemoteCache<Object, Object> cache;
    
    @Inject
    private InfinitspanClient client;
    
    @PostConstruct
    public void init() {
        this.cache = build();
    }
    
    public void set(String chave, String conteudo, Long duracao) {
    	cache.put(chave, conteudo, duracao, TimeUnit.SECONDS);
    }

    public Optional<String> get(String chave) {
      return Optional.ofNullable((String) cache.get(chave));
    }
    
    private RemoteCache<Object, Object> build() {
    	RemoteCacheManager rmc = client.getClientCache();
        rmc.start();
        return rmc.getCache(KEY_CACHE);
    }
}

