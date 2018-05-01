package com.fangao.lib_common.model;

import java.util.List;

/**
 * 文件描述：
 * <p>
 * 作者：   Created by sven on 2018/2/6.
 */

public class InitData {

    /**
     * hasNewVersion : false
     * forceUpgrade : false
     * publicKey : MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCf0jBpwdoou2IQ9JUDL9Tj1IZyxXQ3NTNre4RQpkMJbTEkBMaIX9Lj1eJgIKtBQ3lVZMpnhWWtXQBEocSsE1xA2tjyuxybC1kXC8PryhZ1F1iYGtDOsd/fwHa4nVe+4+TOXh6NAODie8vhDdKwj4cKMDqP1Oooob8k7eJu7J5AnwIDAQAB
     * secret : 24F5447B0D29CE2FA92E5D444DB56F87
     * url :
     * content :
     * token : SXg2dmxpZ1RBUDdQaVlic3BVbGhsY000Rm5tNXIxRlFENkdFa3NsYXZyWTJrM2JQblZtSVpJY0ErRXdSYnFxR25VdmR2a0xGcFh2dE1SdUJtMk5TallKZU80S3VkQzIzVFo1b0hlSkdIcDlWUnpNYVFHQTJURDNaY2haUzY3S1B3WjdoVGE4WEJDSUpIMUVsQ3FJTjFaTTgxcUhjZU1DbTQ3Mi9vbHo3LzMzUFVWVHNrSXhhdXNuMDlmc09lUDZh
     * newVersion :
     */

    private boolean hasNewVersion;
    private boolean forceUpgrade;
    private String publicKey;
    private String secret;
    private String url;
    private String content;
    private String token;
    private String newVersion;
    private String advert;
    private String loginImageUrl;
    private List<String> list;

    public String getLoginImageUrl() {
        return loginImageUrl;
    }

    public void setLoginImageUrl(String loginImageUrl) {
        this.loginImageUrl = loginImageUrl;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getAdvert() {
        return advert;
    }

    public void setAdvert(String advert) {
        this.advert = advert;
    }

    public boolean isHasNewVersion() {
        return hasNewVersion;
    }

    public void setHasNewVersion(boolean hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public boolean isForceUpgrade() {
        return forceUpgrade;
    }

    public void setForceUpgrade(boolean forceUpgrade) {
        this.forceUpgrade = forceUpgrade;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }
}
