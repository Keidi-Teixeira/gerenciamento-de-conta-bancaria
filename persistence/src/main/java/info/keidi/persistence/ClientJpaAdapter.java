package info.keidi.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
interface ClientJpaAdapter extends JpaRepository<ClientEntity, String> {

  @Transactional
  @Modifying
  @Query("update ClientEntity c set c.balance = ?2 where c.id = ?1")
  int updateBalance(String clientId, BigDecimal value);
}
