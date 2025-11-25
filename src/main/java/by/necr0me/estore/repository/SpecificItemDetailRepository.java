package by.necr0me.estore.repository;

import by.necr0me.estore.entity.SpecificItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificItemDetailRepository extends JpaRepository<SpecificItemDetail, Long> {
}
