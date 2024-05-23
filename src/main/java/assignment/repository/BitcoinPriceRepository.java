package assignment.repository;

import assignment.entity.BitcoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BitcoinPriceRepository extends JpaRepository<BitcoinPrice, Long> {
}
