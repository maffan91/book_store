package com.app.maffan.bookbank;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by maffan on 12/15/2016.
 */
@Entity
public class JoinBookWithAuthor {

    @Id
    private Long id;
    private Long authorId;
    private Long bookId;
    @Generated(hash = 189198719)
    public JoinBookWithAuthor(Long id, Long authorId, Long bookId) {
        this.id = id;
        this.authorId = authorId;
        this.bookId = bookId;
    }
    @Generated(hash = 1740798238)
    public JoinBookWithAuthor() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAuthorId() {
        return this.authorId;
    }
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    public Long getBookId() {
        return this.bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
