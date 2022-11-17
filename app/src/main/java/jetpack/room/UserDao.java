package jetpack.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * data access object from the testuser table
 */
@Dao
public interface UserDao {
    //取查询的第一个数据
    @Query("select * from testuser limit 1")
    User getUser();
    //插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
}
