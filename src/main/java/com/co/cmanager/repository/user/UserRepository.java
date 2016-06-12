package com.co.cmanager.repository.user;

import com.co.cmanager.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author by Cyprian Omenuko on 6/9/2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select d from User d WHERE d.firstName like %:searchParam% " +
            " or LOWER(d.lastName) like LOWER(%:searchParam%) " +
            " or LOWER(d.email) like LOWER(%:searchParam%) " +
            "or LOWER(d.phoneNumber) like LOWER(%:searchParam%) ")
    Page<User> search(@Param("searchParam") String searchParam, Pageable pageable);

}
