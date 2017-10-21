package com.chilicool.hdtools.model;

/**
 * Created by chilicool on 2017/10/20.
 */
public class RoomSumyModel {
    private String belongAreaCode;
    private String belongAreaName;
    private String currentRoomCode;
    private String currentRoomName;

    public String getBelongAreaCode() {
        return belongAreaCode;
    }

    public void setBelongAreaCode(String belongAreaCode) {
        this.belongAreaCode = belongAreaCode;
    }

    public String getBelongAreaName() {
        return belongAreaName;
    }

    public void setBelongAreaName(String belongAreaName) {
        this.belongAreaName = belongAreaName;
    }

    public String getCurrentRoomCode() {
        return currentRoomCode;
    }

    public void setCurrentRoomCode(String currentRoomCode) {
        this.currentRoomCode = currentRoomCode;
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }

    public void setCurrentRoomName(String currentRoomName) {
        this.currentRoomName = currentRoomName;
    }
}
