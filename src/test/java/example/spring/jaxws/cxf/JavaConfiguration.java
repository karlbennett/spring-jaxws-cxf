package example.spring.jaxws.cxf;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.xml.ws.Endpoint;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource({"classpath:META-INF/cxf/cxf.xml"})
public class JavaConfiguration {

    @Autowired
    private Bus bus;

    @Autowired
    private SpringDataUsers users;

    @Bean
    public ServletRegistrationBean cxfServlet() {
        ServletRegistrationBean servletDef = new ServletRegistrationBean(new CXFServlet(), "/cxf/*");
        servletDef.setLoadOnStartup(1);
        return servletDef;
    }

    @Bean
    public Endpoint calculator() {
        EndpointImpl endpoint = new EndpointImpl(bus, users);
        endpoint.publish("/java-users");
        return endpoint;
    }
}
