package demo.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockEtablissementRepository extends ReactiveCrudRepository<StockEtablissementRepository, Long>, ReactiveQueryByExampleExecutor<StockEtablissementRepository> {

}
