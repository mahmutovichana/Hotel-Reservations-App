package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all POJO beans to have ID field.
 * @author Hana Mahmutović
 */
public interface Idable {

    void setId(int id);

    int getId();
}