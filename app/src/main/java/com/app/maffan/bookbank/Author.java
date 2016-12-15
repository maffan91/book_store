package com.app.maffan.bookbank;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by maffan on 12/15/2016.
 */
@Entity
public class Author {

    @Id
    private Long id;
    @NotNull
    private String name;

}
