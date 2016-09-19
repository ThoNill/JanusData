package test.janus.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.janus.data.DataContext;
import org.janus.data.DataContextImpl;
import org.janus.data.DataDescription;
import org.janus.data.DataDescriptionImpl;
import org.junit.Test;

public class DataContextTest {
    private static final String THIS_EXCEPTION_IS_OK = "This Exception is ok";
    
    public static final Logger LOG = Logger.getLogger(DataContextTest.class
            .getSimpleName());

    private DataContext creatTestContext() {
        DataDescription description = new DataDescriptionImpl();
        description.getHandle("first");
        description.getHandle("second");
        DataContext ctx = description.newContext();
        return ctx;
    }

    @Test
    public void testDataContextImpl() {
        assertNotNull(creatTestContext());
    }

    @Test
    public void testGetObject() {
        DataContext ctx = creatTestContext();

        ctx.setObject(0, "wert");
        String wert = (String) ctx.getObject(0);
        assertEquals("wert", wert);
    }

    @Test
    public void testSetObject() {
        DataContext ctx = creatTestContext();

        ctx.setObject(0, "wert");
        ctx.setObject(0, "wert");
        String wert = (String) ctx.getObject(0);
        assertEquals("wert", wert);
    }

    @Test
    public void testSetObjects() {
        DataContextImpl ctx = (DataContextImpl) creatTestContext();
        ctx.setObjects(new int[] { 0, 1 }, new String[] { "a", "b" });
        assertEquals("a", ctx.getObject(0));
        assertEquals("b", ctx.getObject(1));
    }

    @Test
    public void testSetObjectsGehtNicht() {
        try {
            DataContextImpl ctx = (DataContextImpl) creatTestContext();
            ctx.setObjects(new int[] { 0, 1 }, new String[] { "a", "b", "c" });
            fail("Not yet implemented");
        } catch (Exception es) {
            LOG.error(THIS_EXCEPTION_IS_OK,es);
        }
    }

    @Test
    public void testDebug1() {
        org.apache.log4j.BasicConfigurator.configure();
        DataContext ctx = creatTestContext();
        ctx.setObject(0, "wert1");
        ctx.setObject(1, "wert2");

        TestLogger logger = new TestLogger();
        ctx.debug(logger);

        List<String> v = logger.getLog();
        List<String> erg = new ArrayList<String>();
        erg.add("first= [wert1]");
        erg.add("second= [wert2]");
        assertEquals(erg, v);

    }

    @Test
    public void testDebug2() {
        org.apache.log4j.BasicConfigurator.configure();
        DataContextImpl ctx = (DataContextImpl) creatTestContext();
        ctx.setObject(0, "wert1");
        ctx.setObject(1, "wert2");

        TestLogger logger = new TestLogger();
        ctx.debug(logger, 1);

        List<String> v = logger.getLog();
        List<String> erg = new ArrayList<String>();
        erg.add("second= [wert2]");
        assertEquals(erg, v);

    }

    @Test
    public void testDebug3() {
        org.apache.log4j.BasicConfigurator.configure();
        DataContextImpl ctx = (DataContextImpl) creatTestContext();
        ctx.setObject(0, "wert1");
        ctx.setObject(1, "wert2");

        TestLogger logger = new TestLogger();
        logger.setDebugEnabled(false);
        ctx.debug(logger, 1);

        List<String> v = logger.getLog();
        List<String> erg = new ArrayList<String>();
        assertEquals(erg, v);

    }

    @Test
    public void testDebug4() {
        org.apache.log4j.BasicConfigurator.configure();
        DataContextImpl ctx = (DataContextImpl) creatTestContext();
        ctx.setObject(0, "wert1");
        ctx.setObject(1, "wert2");

        TestLogger logger = new TestLogger();
        logger.setDebugEnabled(false);
        ctx.debug(logger);

        List<String> v = logger.getLog();
        List<String> erg = new ArrayList<String>();
        assertEquals(erg, v);

    }

    @Test
    public void testGetDataDescriptin() {
        DataDescription description = new DataDescriptionImpl();
        description.getHandle("first");
        description.getHandle("second");
        DataContext ctx = description.newContext();
        assertTrue(description == ctx.getDataDescription());
    }

}
