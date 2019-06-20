package de.internship.server.repository;

import de.internship.server.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userprofile", path = "userprofile")
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

}
