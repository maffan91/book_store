package com.app.maffan.bookbank;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by maffan on 12/15/2016.
 */
@Entity
public class Publisher {


    @Id
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @Generated(hash = 1476282599)
    public Publisher(Long id, @NotNull String name, @NotNull String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    @Generated(hash = 1150157344)
    public Publisher() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
