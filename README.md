# Integration with Infinispan 

## How do I run?

1 Compile project

```mvn package```

2 Deploy war and run Jboss

```run jboss```

3 Run Infinispan  Server

```docker run -it -p 11222:11222 -p 80:80 -e USER="admin" -e PASS="password" quay.io/infinispan/server:13.0```

4 Open http://localhost:11222/console/ and create your key cache.


## How do I test?

```curl --location --request GET 'http://localhost:8080/cache-infinispan-jboss/api/clients/Robert'```
