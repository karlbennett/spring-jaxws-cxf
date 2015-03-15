/*
 * Copyright (C) 2015  Karl Bennett
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
public class UsersXMLTest {

    @Value("${local.server.port}")
    private int port;

    private Users users;

    @Before
    public void setUp() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(Users.class);
        factory.setAddress(format("http://localhost:%d/cxf/xml-users", port));
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
