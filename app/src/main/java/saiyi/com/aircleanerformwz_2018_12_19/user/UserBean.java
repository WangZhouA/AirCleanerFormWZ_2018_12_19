package saiyi.com.aircleanerformwz_2018_12_19.user;

import java.io.Serializable;

/**
 * Created by 陈姣姣 on 2018/12/28.
 */
public class UserBean  implements Serializable{



    public  int   userId;
    public  String   userName;
    public  String   userPhone;
    public  String   userTitle;
    public  String   userDate;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getUserDate() {
        return userDate;
    }

    public void setUserDate(String userDate) {
        this.userDate = userDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserBean{");
        sb.append("userId=").append(userId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", userPhone='").append(userPhone).append('\'');
        sb.append(", userTitle='").append(userTitle).append('\'');
        sb.append(", userDate='").append(userDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
