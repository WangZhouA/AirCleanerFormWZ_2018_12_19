package saiyi.com.aircleanerformwz_2018_12_19.action;

import com.lib.fast.common.event.EventAction;

/**
 * created by siwei on 2018/11/5
 */
public class Action implements EventAction {

    /**
     * 添加新的设备
     */

    public  static  final String TRANSFERLOCATIONINFORMATION="WWW.TRANSFERLOCATIONINFORMATION.COM";
    public  static  final String CITY="WWW.CITY.COM";  // 市
    public  static  final String DISTRICT="WWW.DISTRICT.COM"; //区



    private int actionId;
    private String actionIdMessage;


    @Override
    public int getActionId() {
        return actionId;
    }

    @Override
    public String getActionMessage() {
        return actionIdMessage;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getActionIdMessage() {
        return actionIdMessage;
    }

    public void setActionIdMessage(String actionIdMessage) {
        this.actionIdMessage = actionIdMessage;
    }

    public  Action(int actionId, String actionIdMessage ) {
        this.actionId = actionId;
        this.actionIdMessage = actionIdMessage;
    }
}
