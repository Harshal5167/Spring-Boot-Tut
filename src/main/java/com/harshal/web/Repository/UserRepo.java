package com.harshal.web.Repository;

import com.harshal.web.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username); //its implementation is done by spring data jpa, no need to write the implementation.
    //spring data jpa parses the method name and creates the relevant query for us.
}
