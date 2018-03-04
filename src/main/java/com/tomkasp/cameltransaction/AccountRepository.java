package com.tomkasp.cameltransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomasz Kasprzycki
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
