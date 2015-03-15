package example.spring.jaxws.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Users {

    Long create(@WebParam(name = "user") User user);

    User retrieve(@WebParam(name = "id") Long id);

    void update(User user);

    void delete(Long id);
}
