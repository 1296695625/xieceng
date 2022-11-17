package jetpack.room;

import android.content.Context;

import androidx.annotation.NonNull;

public class UserDataSource {
    private static volatile UserDataSource INSTANCE;
    private UserDao mUserDao;

    private UserDataSource(UserDao userDao) {
        mUserDao = userDao;
    }

    public static UserDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            synchronized (UserDataSource.class) {
                if (INSTANCE == null) {
                    UserDatadabase datadabase = UserDatadabase.getInstance(context);
                    INSTANCE = new UserDataSource(datadabase.userDao());
                }
            }
        }
        return INSTANCE;
    }

    public User getUser() {
        return mUserDao.getUser();
    }

    public void insertorupdate(User user) {
        mUserDao.insert(user);
    }

    public void deleteAll() {
//        mUserDao.
    }
}
