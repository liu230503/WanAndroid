package org.lmy.open.widgetlibrary.banner;

import android.os.Parcel;
import android.os.Parcelable;

/**********************************************************************
 *
 *
 * @类名 BeanBanner
 * @包名 org.lmy.open.widgetlibrary.banner
 * @author lmy
 * @创建日期 2018/3/6
 ***********************************************************************/
public class BeanBanner implements Parcelable {
    /**
     * Title
     */
    private String mText;
    /**
     * 图片地址
     */
    private String mUrl;
    /**
     * 连接
     */
    private String mLinkUrl;

    public BeanBanner(String text, String url, String linkUrl) {
        this.mText = text;
        this.mUrl = url;
        this.mLinkUrl = linkUrl;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getLinkUrl() {
        return mLinkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.mLinkUrl = linkUrl;
    }

    protected BeanBanner(Parcel in) {
        mText = in.readString();
        mUrl = in.readString();
        mLinkUrl = in.readString();
    }

    /**
     *
     */
    public static final Creator<BeanBanner> CREATOR = new Creator<BeanBanner>() {
        @Override
        public BeanBanner createFromParcel(Parcel in) {
            return new BeanBanner(in);
        }

        @Override
        public BeanBanner[] newArray(int size) {
            return new BeanBanner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeString(mUrl);
        dest.writeString(mLinkUrl);
    }
}