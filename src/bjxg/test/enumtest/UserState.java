/* 
 * @(#)UserState.java    Created on 2006-6-29
 * Copyright (c) 2006 ZDSoft Networks, Inc. All rights reserved.
 * $Header: /project/etoh2/src/net/zdsoft/etoh2/entity/basic/UserState.java,v 1.10 2009/03/25 08:15:26 niwl Exp $
 */
package bjxg.test.enumtest;

/**
 * 
 * 
 * @author huangwj
 * @version $Revision: 1843 $, $Date: 2009-09-01 15:12:31 +0800 (星期�?, 01 九月 2009) $
 */
public enum UserState {

    NORMAL(1), FROZEN(2), OWING(3), MATURE(4), USER_CANCEL(5), SUPPORT_CANCEL(6), INTERFACE_CANCEL(7), UN_SYNC(8), PAUSE(
            11), UNKNOWN(-1);

    private int value;

    UserState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return String.valueOf(value);
    }

    public static UserState valueOf(int value, int a) {
        UserState state = null;
        switch (value) {
        case 1:
            state = NORMAL;
            break;
        case 2:
            state = FROZEN;
            break;
        case 3:
            state = OWING;
            break;
        case 4:
            state = MATURE;
            break;
        case 5:
            state = USER_CANCEL;
            break;
        case 6:
            state = SUPPORT_CANCEL;
            break;
        case 7:
            state = INTERFACE_CANCEL;
            break;
        case 8:
            state = UN_SYNC;
            break;
        case 11:
            state = PAUSE;
            break;
        default:
            state = UNKNOWN;
            break;
        }
        return state;
    }

    public String getDescription() {
        String desc = null;
        switch (this) {
        case NORMAL:
            desc = "正常";
            break;
        case FROZEN:
            desc = "冻结";
            break;
        case OWING:
            desc = "欠费";
            break;
        case MATURE:
            desc = "服务到期";
            break;
        case USER_CANCEL:
            desc = "用户注销";
            break;
        case SUPPORT_CANCEL:
            desc = "运营注销";
            break;
        case INTERFACE_CANCEL:
            desc = "接口注销";
            break;
        case UN_SYNC:
            desc = "用户未同�?";
            break;
        case PAUSE:
            desc = "暂停";
            break;
        default:
            desc = "未知状�??";
            break;
        }
        return desc;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public boolean equals(UserState state) {
        if (state == null) {
            return false;
        }
        return value == state.value;
    }
}
