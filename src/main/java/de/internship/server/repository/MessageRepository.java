package de.internship.server.repository;

import de.internship.server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface MessageRepository extends JpaRepository<Message, Long> {

}
