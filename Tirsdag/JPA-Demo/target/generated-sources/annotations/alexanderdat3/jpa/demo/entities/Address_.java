package alexanderdat3.jpa.demo.entities;

import alexanderdat3.jpa.demo.entities.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-09-23T14:48:45")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, Integer> zipcode;
    public static volatile SingularAttribute<Address, Long> a_id;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, Person> person;

}