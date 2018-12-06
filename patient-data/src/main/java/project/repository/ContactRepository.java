package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
