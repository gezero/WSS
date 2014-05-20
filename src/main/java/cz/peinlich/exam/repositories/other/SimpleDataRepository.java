package cz.peinlich.exam.repositories.other;

import cz.peinlich.exam.entities.other.SimpleData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * This repository is here to work with the SimpleData entity. I do not know what entities I will use in exam so this
 * repository works with a dummy entity.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 6:40
 */
public interface SimpleDataRepository extends CrudRepository<SimpleData, Long> {
    List<SimpleData> findByContent(String content);
}
