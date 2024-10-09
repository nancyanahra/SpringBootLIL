package com.nancynahra.lil.learning_spring.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    Guest findByLastNameAndFirstName(String lastName, String firstName);

}
