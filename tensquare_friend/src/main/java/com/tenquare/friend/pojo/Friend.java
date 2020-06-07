package com.tenquare.friend.pojo;

import org.hibernate.annotations.Proxy;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: tensquare_parent
 * @author: 大禹
 * @create: 2020-06-03 18:34
 **/

@Entity
@Table(name="tb_friend")
@IdClass(Friend.class)
@Proxy(lazy = false)
public class Friend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    private String islike;

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }
}