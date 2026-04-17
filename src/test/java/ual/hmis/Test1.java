package ual.hmis;

import org.junit.jupiter.api.Test;

public class Test1 {
    @Test
    public void test1() {
        Coche coche1 = new Coche("rojo", "ford", 100, 4);
        Coche coche2 = new Coche("rojo", "ford", 100, 4);
        assert(coche1.equals(coche2));
    }
    @Test
    public void test2() {
        Coche coche1 = new Coche("rojo", "ford", 100, 4);
        Coche coche2 = new Coche("azul", "ford", 100, 4);
        assert(!coche1.equals(coche2));
    }
    @Test
    public void test3() {
        Coche coche1 = new Coche();
        assert(coche1.equals(coche1));
    }
    @Test
    public void test4() {
        Coche coche1 = new Coche();
        assert(!coche1.equals(null));
    }
    @Test
    public void testToString() {
        Coche coche1 = new Coche("rojo", "ford", 100, 4);
        String toString1 = coche1.toString();
        String toString2 = "Coche [marca=" + coche1.getMarca() + ", modelo=" + coche1.getModelo() + ", año=" + coche1.getAño() + ", precio=" + coche1.getPrecio() + "]";
        assert(toString1.equals(toString2));
    }
    @Test
    public void testCompareTo2() {
        Coche coche1 = new Coche("rojo", "ford", 100, 4);
        String marca1 = coche1.getMarca();
        String marca2 = "chevrolet";
        assert(marca1.compareTo(marca2) > 0);
    }
    @Test
    public void testCompareToDistintasClasese() {
        Coche coche1 = new Coche("rojo", "ford", 100, 4);
        String marca1 = coche1.getMarca();
        Integer numero = 5;
        assert(marca1.compareTo(numero.toString()) != 0);
    }
    @Test
    public void setMarca() {
        Coche coche1 = new Coche();
        coche1.setMarca("ford");
        assert(coche1.getMarca().equals("ford"));
    }
    @Test
    public void setModelo() {
        Coche coche1 = new Coche();
        coche1.setModelo("fiesta");
        assert(coche1.getModelo().equals("fiesta"));
    }
    @Test
    public void setAño() {
        Coche coche1 = new Coche();
        coche1.setAño(2020);
        assert(coche1.getAño() == 2020);
    }
    @Test
    public void setPrecio() {
        Coche coche1 = new Coche();
        coche1.setPrecio(20000);
        assert(coche1.getPrecio() == 20000);
    }
    @Test
    public void testMain() {
        Main.main(new String[]{});
    }
}
