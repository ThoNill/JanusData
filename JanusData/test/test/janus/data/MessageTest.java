package test.janus.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.janus.data.DataContext;
import org.janus.data.DataContextImpl;
import org.janus.data.DataDescriptionImpl;
import org.janus.data.MessageImpl;
import org.junit.Test;

/**
 * Test der Messages
 * 
 * @author THOMAS NILL Lizenz GPLv3
 * 
 */
public class MessageTest {
 
    
    public void singleTest(String text, String[] result) {
        singleTest(text,result,"$(",")");
    }

    public void singleTest(String text, String[] result,String pre,String post) {
        MessageImpl ts = new MessageImpl(text, pre, post);
        String[] erg = ts.getTexte();
        if (erg.length == result.length) {
            for (int i = 0; i < result.length; i++) {
                if (!result[i].equals(erg[i])) {
                    fail(" erg [" + erg[i] + " erwartet " + result[i]);
                }
            }
        } else {
            fail(" Length at [" + text + "]");
        }
    }

    @Test
    public void t1() {
 
        singleTest("$(first) test", new String[] { "", "first", " test" });
        singleTest("$(first)$(zwei) test", new String[] { "", "first", "",
                "zwei", " test" });
        singleTest(" first test", new String[] { " first test" });
        singleTest("", new String[] { "" });
        singleTest(" first $(test)", new String[] { " first ", "test", "" });
        singleTest(" first $(test) ", new String[] { " first ", "test", " " });

        singleTest(" first $(test) and $(second) test ", new String[] {
                " first ", "test", " and ", "second", " test " });
    }

    @Test
    public void t2() {
    

        singleTest(" first test", new String[] { " first test" },"?","?");
        singleTest("", new String[] { "" },"?","?");
        singleTest(" first ?test?", new String[] { " first ", "test", "" },"?","?");
        singleTest(" first ?test? and ?second? test ", new String[] {
                " first ", "test", " and ", "second", " test " },"?","?");
    }

    @Test
    public void t3() {
        DataDescriptionImpl model = new DataDescriptionImpl();
        MessageImpl ts = new MessageImpl("Das ist ein $(test)");
        ts.initHandles(model);

        int handle = model.getHandle("test");
        DataContext ctx = new DataContextImpl(model);
        ctx.setObject(handle, "123");

        String t = ts.getMessage(ctx);

        assertEquals("Das ist ein 123", t);
    }

    @Test
    public void t4() {
    }

}
