package cn.itcast.manyToMany.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private Integer rid;

    @Override
    public String toString() {
        return "Role{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rdes='" + rdes + '\'' +
                ", userSet=" + userSet +
                '}';
    }

    private String rname;
    private String rdes;

    //一个角色可以有多个用户
    private Set<User> userSet = new HashSet<User>();

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdes() {
        return rdes;
    }

    public void setRdes(String rdes) {
        this.rdes = rdes;
    }

    public Role() {
    }
}
