package com.xpertcaller.server.db.sql.repositories;

import com.xpertcaller.server.db.sql.entities.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, String> {
    public CollegeEntity findByCollegeId(String collegeId);
    public Optional<CollegeEntity> findByCollege(String userName);

    @Query(value = "SELECT * FROM college_list WHERE college ILIKE LOWER(CONCAT('%', :college, '%')) LIMIT 10", nativeQuery = true)
    List<CollegeEntity> findCollegeByQueryMethod(@Param("college") String college);
}
