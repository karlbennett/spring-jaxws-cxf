package example.spring.jaxws.cxf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDataUsers implements Users {

    private final UsersRepository repository;

    @Autowired
    public SpringDataUsers(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long create(User user) {
        return repository.save(user).getId();
    }

    @Override
    public User retrieve(Long id) {
        return repository.findOne(id);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
