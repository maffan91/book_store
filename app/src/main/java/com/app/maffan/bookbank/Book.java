package com.app.maffan.bookbank;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

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

    @ToMany
    @JoinEntity(
            entity = JoinBookWithAuthor.class,
            sourceProperty = "bookId",
            targetProperty = "authorId")
    private List<Author> authors;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;

    @Generated(hash = 1030389255)
    public Book(Long id, @NotNull String title, @NotNull String price,
            long publisher_id) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publisher_id = publisher_id;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getPublisher_id() {
        return this.publisher_id;
    }

    public void setPublisher_id(long publisher_id) {
        this.publisher_id = publisher_id;
    }

    @Generated(hash = 1620969188)
    private transient Long publisher__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 732316418)
    public Publisher getPublisher() {
        long __key = this.publisher_id;
        if (publisher__resolvedKey == null
                || !publisher__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PublisherDao targetDao = daoSession.getPublisherDao();
            Publisher publisherNew = targetDao.load(__key);
            synchronized (this) {
                publisher = publisherNew;
                publisher__resolvedKey = __key;
            }
        }
        return publisher;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1670598531)
    public void setPublisher(@NotNull Publisher publisher) {
        if (publisher == null) {
            throw new DaoException(
                    "To-one property 'publisher_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.publisher = publisher;
            publisher_id = publisher.getId();
            publisher__resolvedKey = publisher_id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 874142657)
    public List<Author> getAuthors() {
        if (authors == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AuthorDao targetDao = daoSession.getAuthorDao();
            List<Author> authorsNew = targetDao._queryBook_Authors(id);
            synchronized (this) {
                if (authors == null) {
                    authors = authorsNew;
                }
            }
        }
        return authors;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 405652703)
    public synchronized void resetAuthors() {
        authors = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }


}
