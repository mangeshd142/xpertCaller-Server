package com.xpertcaller.server.user.db.sql.repositories;

import com.xpertcaller.server.user.db.sql.entities.profileEntities.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<PricingEntity, String> {

}
