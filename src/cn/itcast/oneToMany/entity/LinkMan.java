package cn.itcast.oneToMany.entity;

public class LinkMan {
    private Integer lid;
    private String lname;
    private String lgender;
    private String lphone;
    private Customer customer;

    @Override
    public String toString() {
        return "LinkMan{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", lgender='" + lgender + '\'' +
                ", lphone='" + lphone + '\'' +
                ", customer=" + customer +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLgender() {
        return lgender;
    }

    public void setLgender(String lgender) {
        this.lgender = lgender;
    }

    public String getLphone() {
        return lphone;
    }

    public void setLphone(String lphone) {
        this.lphone = lphone;
    }

    public LinkMan() {
    }
}
