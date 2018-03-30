package org.lmy.open.wanandroid.business.login.bean;

import java.util.List;

/**********************************************************************
 *
 *
 * @类名 BeanRespLogin
 * @包名 org.lmy.open.wanandroid.business.login.bean
 * @author lmy
 * @创建日期 2018/3/27
 ***********************************************************************/
public class BeanRespLogin {
    /**
     * 电子邮箱
     */
    private String mEmail;
    /**
     * 头像
     */
    private String mIcon;
    /**
     * mId
     */
    private int mId;
    /**
     * 密码
     */
    private String mPassword;
    /**
     * 类型？
     */
    private int mType;
    /**
     * 用户名
     */
    private String mUsername;
    /**
     * 收藏
     */
    private List<Integer> mCollectIds;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public List<Integer> getCollectIds() {
        return mCollectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.mCollectIds = collectIds;
    }
}
