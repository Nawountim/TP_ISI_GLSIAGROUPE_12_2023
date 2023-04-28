package TPISIGLSI.CrudApi.Repository;

import TPISIGLSI.CrudApi.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClientRepository extends JpaRepository<Client, Long>  {
}
