package ch06.one2many;

import javax.persistence.*;

/**
 * Created by Amysue on 2016/4/19.
 */
@Entity
@Table(name = "t_address8")
public class Address8 {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private int id;

    private String addressInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
