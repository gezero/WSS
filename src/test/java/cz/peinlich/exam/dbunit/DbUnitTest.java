package cz.peinlich.exam.dbunit;

import cz.peinlich.exam.Application;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class is an abstract class that I will use for tests that will need data in db
 * <p/>
 * User: George
 * Date: 20.5.2014
 * Time: 5:42
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public abstract class DbUnitTest {
    @Autowired
    private DataSource dataSource;

    @Before
    public void initDb() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
    }


    private IDataSet getDataSet() throws DataSetException, IOException {
        InputStream inputStream = retrieveDataSet().getInputStream();
        InputSource xmlSource = new InputSource(inputStream);
        ReplacementDataSet dataSet = new ReplacementDataSet(new FlatXmlDataSet(xmlSource));
        dataSet.addReplacementObject("[null]", null);
        return dataSet;

    }


    protected abstract Resource retrieveDataSet();

    private IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(dataSource.getConnection());
    }
}