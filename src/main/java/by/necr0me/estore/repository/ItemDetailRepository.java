package by.necr0me.estore.repository;

import by.necr0me.estore.entity.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
    List<ItemDetail> findByNameStartingWithIgnoreCase(String prefix);
}
