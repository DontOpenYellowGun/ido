package com.fangao.lib_common.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/7 0007.
 */
@Entity
public class User extends BaseObservable implements Parcelable {

    /**
     * showName : 15213461316
     * nickName : null
     * num : 20031545
     * headUrl : http://file.tenbody.com/file/download/account/avatar/default/e395dc865580422d819e925ad1d65bd4.jpg
     * signStr : null
     * userToken : 11AURVAohQEo9N2VYsn/O8VqEiqiYz2U9M0MQxpPe8tBxXbU75Z/ChimkvnXJM6R9Zjm9/bShnuSQ7cT4heiFQ==
     * sexCode : null
     * phone : 15213461316
     * phoneChecked : true
     * sexName : null
     * name : 15213461316
     * emailChecked : false
     * id : 1376
     * email : null
     * activated : true
     * * dealed : false
     * requestDt : 2018-02-16 17:42:47
     */
    @Id
    private Long id;
    private String friendId;
    private String showName;
    private String nickName;
    private String sexName;
    @Bindable
    private String name;
    private String namePingyin;
    private String num;
    @SerializedName(value = "headUrl", alternate = {"url"})
    private String headUrl;
    private String userToken;
    private String sexCode;
    private String phone;
    private boolean phoneChecked;
    private boolean emailChecked;
    private String email;
    private boolean activated;
    private boolean myFriend;
    private boolean sysFlag;
    private boolean dealed;//是否同意
    private String requestDt;//发送好友请求时间
    private String area;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String districtCode;
    private String districtName;
    private String detailAddress;
    private String address;
    private String qrcodeUrl;
    private String signStr;
    private String postAddress;
    private boolean hasQuestion;
    private String score;
    private String points;
    private String coins;
    private boolean isCheck = false;
    private boolean isEnable = true;
    private String pwd;
    private boolean isRememberPwd;
    private boolean autoLogin;
    private long loginTime;
    private String loginName;
    private boolean isTop;

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    @Transient
    public final ObservableBoolean isDeal = new ObservableBoolean(false);

    public boolean isRememberPwd() {
        return isRememberPwd;
    }

    public void setRememberPwd(boolean rememberPwd) {
        isRememberPwd = rememberPwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(boolean sysFlag) {
        this.sysFlag = sysFlag;
    }


    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public boolean isHasQuestion() {
        return hasQuestion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePingyin() {
        return namePingyin;
    }

    public void setNamePingyin(String namePingyin) {
        this.namePingyin = namePingyin;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPhoneChecked() {
        return phoneChecked;
    }

    public void setPhoneChecked(boolean phoneChecked) {
        this.phoneChecked = phoneChecked;
    }

    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isMyFriend() {
        return myFriend;
    }

    public void setMyFriend(boolean myFriend) {
        this.myFriend = myFriend;
    }

    public boolean isDealed() {
        return dealed;
    }

    public void setDealed(boolean dealed) {
        this.dealed = dealed;
    }

    public String getRequestDt() {
        return requestDt;
    }

    public void setRequestDt(String requestDt) {
        this.requestDt = requestDt;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getSignStr() {
        return signStr;
    }

    public void setSignStr(String signStr) {
        this.signStr = signStr;
    }

    public void setHasQuestion(boolean hasQuestion) {
        this.hasQuestion = hasQuestion;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.friendId);
        dest.writeString(this.showName);
        dest.writeString(this.nickName);
        dest.writeString(this.sexName);
        dest.writeString(this.name);
        dest.writeString(this.namePingyin);
        dest.writeString(this.num);
        dest.writeString(this.headUrl);
        dest.writeString(this.userToken);
        dest.writeString(this.sexCode);
        dest.writeString(this.phone);
        dest.writeByte(this.phoneChecked ? (byte) 1 : (byte) 0);
        dest.writeByte(this.emailChecked ? (byte) 1 : (byte) 0);
        dest.writeString(this.email);
        dest.writeByte(this.activated ? (byte) 1 : (byte) 0);
        dest.writeByte(this.myFriend ? (byte) 1 : (byte) 0);
        dest.writeByte(this.dealed ? (byte) 1 : (byte) 0);
        dest.writeString(this.requestDt);
        dest.writeString(this.area);
        dest.writeString(this.provinceCode);
        dest.writeString(this.provinceName);
        dest.writeString(this.cityCode);
        dest.writeString(this.cityName);
        dest.writeString(this.districtCode);
        dest.writeString(this.districtName);
        dest.writeString(this.detailAddress);
        dest.writeString(this.address);
        dest.writeString(this.qrcodeUrl);
        dest.writeString(this.signStr);
        dest.writeByte(this.hasQuestion ? (byte) 1 : (byte) 0);
        dest.writeString(this.score);
        dest.writeString(this.points);
        dest.writeString(this.coins);
        dest.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isEnable ? (byte) 1 : (byte) 0);
    }

    public boolean getPhoneChecked() {
        return this.phoneChecked;
    }

    public boolean getEmailChecked() {
        return this.emailChecked;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public boolean getMyFriend() {
        return this.myFriend;
    }

    public boolean getDealed() {
        return this.dealed;
    }

    public boolean getHasQuestion() {
        return this.hasQuestion;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean getIsEnable() {
        return this.isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public boolean getSysFlag() {
        return this.sysFlag;
    }

    public boolean getIsRememberPwd() {
        return this.isRememberPwd;
    }

    public void setIsRememberPwd(boolean isRememberPwd) {
        this.isRememberPwd = isRememberPwd;
    }

    public boolean getAutoLogin() {
        return this.autoLogin;
    }

    public boolean getIsTop() {
        return this.isTop;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop;
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.friendId = in.readString();
        this.showName = in.readString();
        this.nickName = in.readString();
        this.sexName = in.readString();
        this.name = in.readString();
        this.namePingyin = in.readString();
        this.num = in.readString();
        this.headUrl = in.readString();
        this.userToken = in.readString();
        this.sexCode = in.readString();
        this.phone = in.readString();
        this.phoneChecked = in.readByte() != 0;
        this.emailChecked = in.readByte() != 0;
        this.email = in.readString();
        this.activated = in.readByte() != 0;
        this.myFriend = in.readByte() != 0;
        this.dealed = in.readByte() != 0;
        this.requestDt = in.readString();
        this.area = in.readString();
        this.provinceCode = in.readString();
        this.provinceName = in.readString();
        this.cityCode = in.readString();
        this.cityName = in.readString();
        this.districtCode = in.readString();
        this.districtName = in.readString();
        this.detailAddress = in.readString();
        this.address = in.readString();
        this.qrcodeUrl = in.readString();
        this.signStr = in.readString();
        this.hasQuestion = in.readByte() != 0;
        this.score = in.readString();
        this.points = in.readString();
        this.coins = in.readString();
        this.isCheck = in.readByte() != 0;
        this.isEnable = in.readByte() != 0;
    }

    @Generated(hash = 1434560191)
    public User(Long id, String friendId, String showName, String nickName, String sexName, String name,
            String namePingyin, String num, String headUrl, String userToken, String sexCode, String phone,
            boolean phoneChecked, boolean emailChecked, String email, boolean activated, boolean myFriend,
            boolean sysFlag, boolean dealed, String requestDt, String area, String provinceCode,
            String provinceName, String cityCode, String cityName, String districtCode, String districtName,
            String detailAddress, String address, String qrcodeUrl, String signStr, String postAddress,
            boolean hasQuestion, String score, String points, String coins, boolean isCheck, boolean isEnable,
            String pwd, boolean isRememberPwd, boolean autoLogin, long loginTime, String loginName,
            boolean isTop) {
        this.id = id;
        this.friendId = friendId;
        this.showName = showName;
        this.nickName = nickName;
        this.sexName = sexName;
        this.name = name;
        this.namePingyin = namePingyin;
        this.num = num;
        this.headUrl = headUrl;
        this.userToken = userToken;
        this.sexCode = sexCode;
        this.phone = phone;
        this.phoneChecked = phoneChecked;
        this.emailChecked = emailChecked;
        this.email = email;
        this.activated = activated;
        this.myFriend = myFriend;
        this.sysFlag = sysFlag;
        this.dealed = dealed;
        this.requestDt = requestDt;
        this.area = area;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.districtCode = districtCode;
        this.districtName = districtName;
        this.detailAddress = detailAddress;
        this.address = address;
        this.qrcodeUrl = qrcodeUrl;
        this.signStr = signStr;
        this.postAddress = postAddress;
        this.hasQuestion = hasQuestion;
        this.score = score;
        this.points = points;
        this.coins = coins;
        this.isCheck = isCheck;
        this.isEnable = isEnable;
        this.pwd = pwd;
        this.isRememberPwd = isRememberPwd;
        this.autoLogin = autoLogin;
        this.loginTime = loginTime;
        this.loginName = loginName;
        this.isTop = isTop;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
