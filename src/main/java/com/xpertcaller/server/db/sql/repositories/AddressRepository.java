package com.xpertcaller.server.db.sql.repositories;

import com.xpertcaller.server.db.sql.entities.profileEntities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {
}
