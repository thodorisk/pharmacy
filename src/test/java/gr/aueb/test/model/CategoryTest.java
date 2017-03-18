package gr.aueb.test.model;

import gr.aueb.mscis.sample.model.Category;
import org.junit.Assert;
import org.junit.Test;


public class CategoryTest {

    @Test
    public void getCategory() {
        String EXPECTED_DESCR = "Vitamins";
        Category cat = new Category();
        Assert.assertNotNull(cat);
        cat.setDescription("Vitamins");
        Assert.assertEquals(EXPECTED_DESCR, cat.getDescription());
    }

    @Test
    public void getId() {
        int EXPECTED_ID = 5;
        Category cat = new Category();
        Assert.assertNotNull(cat);
        cat.setId(5);
        Assert.assertEquals(EXPECTED_ID, cat.getId());
    }


}
