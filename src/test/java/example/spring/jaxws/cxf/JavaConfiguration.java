package example.spring.jaxws.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;

@Configuration
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class JavaConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private SpringDataUsers users;

    @Bean
    public Endpoint usersEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, users);
        endpoint.publish("/java-users");
        return endpoint;
    }
}
