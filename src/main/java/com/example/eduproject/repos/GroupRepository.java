package com.example.eduproject.repos;

import com.example.eduproject.entity.Group;
import com.example.eduproject.entity.GroupAccessibility;
import com.example.eduproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Query("select g from Group g where :user member g.students or :user member g.teachers")
    Page<Group> findGroupByUser(User user, Pageable pageable);

}
