package example.spring.jaxws.cxf;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

import static java.lang.String.format;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

@Entity
@XmlRootElement
@XmlAccessorType(FIELD)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Column(nullable = false)
    private final String firstName;

    @Column(nullable = false)
    private final String lastName;

    public User() {
        this(null, null, null);
    }

    public User(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final User that = (User) object;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return format("User {id=%d, firstName='%s', lastName='%s'}", id, firstName, lastName);
    }
}
