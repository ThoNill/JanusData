package test.janus.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.janus.data.DataContext;
import org.janus.data.DataContextImpl;
import org.janus.data.DataDescription;
import org.janus.data.DataDescriptionImpl;
import org.junit.Test;

public class DataDescriptionTest {
    private static final String THIS_EXCEPTION_IS_OK = "This Exception is ok";
    private static final String THIS_EXCEPTION_IS_NOT_OK = "This Exception is not ok";
    
    public static final Logger LOG = Logger.getLogger(DataDescriptionTest.class
            .getSimpleName());

    private DataDescription createTestDescription() {
        DataDescription description = new DataDescriptionImpl();
        description.getHandle("first");
        description.getHandle("second");
        return description;
    }

    @Test
    public void testGetHandle() {
        DataDescription description = new DataDescriptionImpl();
        int h0 = description.getHandle("first");
        int h1 = description.getHandle("second");

        assertEquals(0, h0);
        assertEquals(1, h1);

        assertEquals(0, description.getHandle("first"));
        assertEquals(1, description.getHandle("second"));
    }

    @Test
    public void testExistsHandleName() {

        DataDescription description = createTestDescription();

        assertEquals(true, description.existsHandleName("first"));
        assertEquals(false, description.existsHandleName("name"));
    }

    @Test
    public void testGetHandleName() {
        DataDescription description = createTestDescription();

        assertEquals("first",
                description.getHandleName(description.getHandle("first")));
        assertEquals("second",
                description.getHandleName(description.getHandle("second")));
    }

    @Test
    public void testCreateAnonymousHandle() {
        DataDescription description = createTestDescription();
        assertNotEquals(description.getHandle("first"),
                description.createAnonymousHandle());
        assertNotEquals(description.getHandle("second"),
                description.createAnonymousHandle());
    }

    @Test
    public void testFix() {
        try {
            DataDescription description = createTestDescription();
            DataContext ctx = description.newContext();
            description.getHandle("gehtNichtMehr");
            fail("not fixed");
        } catch (Exception rx) {
            LOG.error(THIS_EXCEPTION_IS_OK,rx);
        }
    }

    @Test
    public void testFixAnonymous() {
        try {
            DataDescription description = createTestDescription();
            DataContext ctx = description.newContext();
            description.createAnonymousHandle();
            fail("not fixed");
        } catch (Exception rx) {
            LOG.error(THIS_EXCEPTION_IS_OK,rx);
        }
    }

    @Test
    public void testFixErlaubt() {
        try {
            DataDescription description = createTestDescription();
            DataContext ctx = description.newContext();
            description.getHandle("second");
        } catch (Exception rx) {
            LOG.error(THIS_EXCEPTION_IS_NOT_OK,rx);
            fail("not fixed");
        }
    }

    @Test
    public void testGetSize() {
        DataDescription description = createTestDescription();
        assertEquals(2, description.getSize());
    }

    @Test
    public void testNewContext() {
        DataDescription description = createTestDescription();
        DataContext ctx = description.newContext();
        assertTrue(ctx instanceof DataContextImpl);
    }

}
