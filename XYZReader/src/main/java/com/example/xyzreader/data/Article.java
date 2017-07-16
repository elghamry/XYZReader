package com.example.xyzreader.data;

import java.io.Serializable;

/**
 * Created by ELGHAMRY on 17/05/2017.
 */

public class Article implements Serializable {
    String _ID = "_id";
    /** Type: TEXT */
    String SERVER_ID = "server_id";
    /** Type: TEXT NOT NULL */
    String TITLE = "title";
    /** Type: TEXT NOT NULL */
    String AUTHOR = "author";
    /** Type: TEXT NOT NULL */
    String BODY = "body";
    /** Type: TEXT NOT NULL */
    String THUMB_URL = "thumb_url";
    /** Type: TEXT NOT NULL */
    String PHOTO_URL = "photo_url";
    /** Type: REAL NOT NULL DEFAULT 1.5 */
    String ASPECT_RATIO = "aspect_ratio";
    /** Type: INTEGER NOT NULL DEFAULT 0 */
    String PUBLISHED_DATE = "published_date";

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getSERVER_ID() {
        return SERVER_ID;
    }

    public void setSERVER_ID(String SERVER_ID) {
        this.SERVER_ID = SERVER_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getBODY() {
        return BODY;
    }

    public void setBODY(String BODY) {
        this.BODY = BODY;
    }

    public String getTHUMB_URL() {
        return THUMB_URL;
    }

    public void setTHUMB_URL(String THUMB_URL) {
        this.THUMB_URL = THUMB_URL;
    }

    public String getPHOTO_URL() {
        return PHOTO_URL;
    }

    public void setPHOTO_URL(String PHOTO_URL) {
        this.PHOTO_URL = PHOTO_URL;
    }

    public String getASPECT_RATIO() {
        return ASPECT_RATIO;
    }

    public void setASPECT_RATIO(String ASPECT_RATIO) {
        this.ASPECT_RATIO = ASPECT_RATIO;
    }

    public String getPUBLISHED_DATE() {
        return PUBLISHED_DATE;
    }

    public void setPUBLISHED_DATE(String PUBLISHED_DATE) {
        this.PUBLISHED_DATE = PUBLISHED_DATE;
    }
}
