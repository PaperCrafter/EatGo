package kr.co.papercraft.eatgo.domain.Repository;

import kr.co.papercraft.eatgo.domain.Model.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Long> {
    List<Region> findAll();
}
