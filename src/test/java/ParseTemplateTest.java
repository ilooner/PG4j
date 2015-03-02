
import com.topekalabs.pg4j.annotations.PGField;
import com.topekalabs.pg4j.parse.ParseTemplate;
import com.topekalabs.testutils.TestUtils;
import java.io.File;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Topeka Labs
 */
public class ParseTemplateTest
{
    private static final Logger logger = LoggerFactory.getLogger(ParseTemplateTest.class.getName());
    public static final File TEST_CLASSES_FILE = new File(TestUtils.RESOURCES_TEST_DIR, "testclasses");
    
    @Test
    public void simpleTest() throws Exception
    {
        logger.debug(PGField.class.getName());
        ParseTemplate.generateTemplates(new File(TEST_CLASSES_FILE, "TestClass.java"), null);
    }
}
