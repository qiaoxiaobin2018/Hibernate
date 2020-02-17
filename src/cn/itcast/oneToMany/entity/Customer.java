package cn.itcast.oneToMany.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Integer cid;
    private String cname;
    private String clevel;
    private String csource;
    private String cphone;
    private String cmobile;
    /*
    * 在客户实体类中表示多个联系人，因为一个客户有多个联系人
    * 注意： Hibernate 要求使用集合表示多个数据，必须使用  set 集合
    * */
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", clevel='" + clevel + '\'' +
                ", csource='" + csource + '\'' +
                ", cphone='" + cphone + '\'' +
                ", cmobile='" + cmobile + '\'' +
                '}';
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getClevel() {
        return clevel;
    }

    public void setClevel(String clevel) {
        this.clevel = clevel;
    }

    public String getCsource() {
        return csource;
    }

    public void setCsource(String csource) {
        this.csource = csource;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCmobile() {
        return cmobile;
    }

    public void setCmobile(String cmobile) {
        this.cmobile = cmobile;
    }

    public Customer() {
    }
}
