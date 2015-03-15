package example.spring.jaxws.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.lang.String.format;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CXFServletConfiguration.class)
@WebAppConfiguration("classpath:")
@IntegrationTest({"server.port=0", "management.port=0"})
public class UsersJavaTest {

    @Value("${local.server.port}")
    private int port;

    private Users users;

    @Before
    public void setUp() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(Users.class);
        factory.setAddress(format("http://localhost:%d/cxf/java-users", port));
        users = (Users) factory.create();
    }

    @Test
    public void Can_create_a_user() throws Exception {

        // Given
        final User user = new User("Test", "User");

        // When
        final long id = users.create(user);

        // Then
        assertThat(id, greaterThan(0L));
    }

    @Test
    public void Can_retrieve_a_user() throws Exception {

        // Given
        final String firstName = "Test";
        final String lastName = "User";
        final long id = users.create(new User(firstName, lastName));

        // When
        final User actual = users.retrieve(id);

        // Then
        assertEquals(new User(id, firstName, lastName), actual);
    }

    @Test
    public void Can_update_a_user() throws Exception {

        // Given
        final String firstName = "Test";
        final String lastName = "User";
        final long id = users.create(new User(firstName, lastName));

        // When
        users.update(new User(id, "Other", lastName));

        // Then
        assertNotEquals(new User(id, firstName, lastName), users.retrieve(id));
    }

    @Test
    public void Can_delete_a_user() throws Exception {

        // Given
        final String firstName = "Test";
        final String lastName = "User";
        final long id = users.create(new User(firstName, lastName));

        // When
        users.delete(id);

        // Then
        assertNull(users.retrieve(id));
    }
}
