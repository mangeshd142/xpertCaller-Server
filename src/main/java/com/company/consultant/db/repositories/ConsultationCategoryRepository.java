package com.company.consultant.db.repositories;

import com.company.consultant.db.entities.ConsultationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsultationCategoryRepository  extends JpaRepository<ConsultationCategory, String> {
    public ConsultationCategory findByConsultationId(String collegeId);
    public Optional<ConsultationCategory> findByConsultationName(String userName);
}
