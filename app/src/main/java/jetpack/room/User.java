package jetpack.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "testuser") //创建表
public class User {
    @PrimaryKey
    @ColumnInfo(name = "userid")
    @NonNull
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ColumnInfo(name="username")
    private String username;

    @ColumnInfo(name = "last_update")
    private Date date;

    public User() {
    }

    public User(String id, String username, Date mDate) {
        this.id = id;
        this.username = username;
        this.date = date;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
