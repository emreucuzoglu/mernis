package tr.gov.nvi.tckimlik.tests;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tr.gov.nvi.tckimlik.utility.TcknUtility;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    @Test
    public void test0() {
        Assert.assertTrue(TcknUtility.tcknKontrolu(11111111110L));
    }

    @Test
    public void test1() {
        Assert.assertFalse(TcknUtility.tcknKontrolu(11111111111L));
    }

    @Test
    public void test2() {
        Assert.assertFalse(TcknUtility.tcknDogrula(11111111110L, "Mehmet", "Sarıçizmeli", 1962));
    }

    @Test
    public void test3() {
        Assert.assertFalse(TcknUtility.tcknDogrula(11111111111L, "Mehmet", "Sarıçizmeli", 1962));
    }

}
