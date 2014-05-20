package cz.peinlich.exam.dbunit;

import cz.peinlich.exam.entities.other.SimpleData;
import cz.peinlich.exam.repositories.other.SimpleDataRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * This class contains simple test to test the infrastructure. I do not know what entities I will use in exam and so
 * I am testing the infrastructure on a dummy entity.
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 5:58
 */
public class SimpleDbUnitTest extends DbUnitTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleDbUnitTest.class);

    ClassPathResource dataSet = new ClassPathResource("dbunit/other/dataSet.xml");

    @Autowired
    SimpleDataRepository repository;

    protected Resource retrieveDataSet() {
        return dataSet;
    }

    @SuppressWarnings("unchecked")
    @Test
    public void criteriaTest() {
        {
            List<SimpleData> data = repository.findByContent("hello_value");
            assertThat(data.size(), is(1));
            SimpleData simpleData = data.get(0);
            assertThat(simpleData.getId(), is(1L));
        }

        logger.info("saving into db");
        for (int i = 0; i < 10; i++) {
            SimpleData test = repository.save(new SimpleData("test "+i));
            assertThat(test.getId(), is((long) (i+2)));
        }

        Iterable<SimpleData> all = repository.findAll();
        long i=0;
        for (SimpleData simpleData : all) {
            ++i;
            logger.info("Id: {}, Content: {}",simpleData.getId(),simpleData.getContent());
            assertThat(simpleData.getId(),is(i));
        }
        assertThat(i,is(11L));

    }


}
