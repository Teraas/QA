package com.famly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="relations")
public class UserRelation {
    @Column( nullable = false)
    Long userXid;
    @Column(nullable = false)
    Long userYid;
    @Column( nullable = false)
    String relation;
    @Id
    String uniqueId;

    public Long getUserXid() {
        return userXid;
    }

    public void setUserXid(Long userXid) {
        this.userXid = userXid;
    }

    public Long getUserYid() {
        return userYid;
    }

    public void setUserYid(Long userYid) {
        this.userYid = userYid;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public UserRelation() {
    }
}
