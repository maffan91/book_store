package com.app.maffan.bookbank;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by maffan on 12/15/2016.
 */
@Entity
public class Book {

    @Id
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String price;

    private long publisher_id;

    @ToOne(joinProperty = "publisher_id")
    private Publisher publisher;


}
