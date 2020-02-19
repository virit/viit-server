package com.viit.base.entity.constant;


import com.viit.base.constants.SysEnumItem;
import com.viit.base.util.EasyList;
import lombok.Data;

import java.util.List;

/**
 * 系统菜单类型
 *
 * @author virit
 * @version 2019-11-20
 */
@Data
public final class SysMenuType {

    public static final int MENU = 10;

    public static final int BUTTON = 20;

    public static final List<SysEnumItem> ITEMS;

    static {
        ITEMS = new EasyList<SysEnumItem>()
                .append(new SysEnumItem(SysMenuType.MENU, "菜单"))
                .append(new SysEnumItem(SysMenuType.BUTTON, "按钮"));
    }
}
