package com.boot.core.kernel_generator.engine.config;

import com.boot.core.kernel_model.enums.YesOrNotEnum;
import com.boot.core.kernel_core.util.ToolUtil;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SqlConfig {
    private String sqlPathTemplate;
    private ContextConfig contextConfig;
    private Connection connection;
    private String parentMenuName;
    private List<Menu> menus = new ArrayList(6);

    public void init() {
        this.sqlPathTemplate = "/src/main/java/{}.sql";

        if (this.parentMenuName == null) {
            return;
        }


        String[] pcodeAndPcodes = getPcodeAndPcodes();
        if (pcodeAndPcodes == null) {
            System.err.println("父级菜单名称输入有误!!!!");
            return;
        }


        Menu menu = new Menu();
        menu.setId(Long.valueOf(IdWorker.getId()));
        menu.setCode(this.contextConfig.getBizEnName());
        menu.setPcode(pcodeAndPcodes[0]);
        menu.setPcodes(pcodeAndPcodes[1] + "[" + pcodeAndPcodes[0] + "],");
        menu.setName(this.contextConfig.getBizChName());
        menu.setIcon("");
        menu.setUrl("/" + this.contextConfig.getBizEnName());
        menu.setNum(Integer.valueOf(99));

        if (this.parentMenuName.equals("顶级")) {
            menu.setLevels(Integer.valueOf(1));
        } else {
            menu.setLevels(Integer.valueOf(2));
        }
        menu.setIsmenu(YesOrNotEnum.Y.getCode());
        menu.setStatus(Integer.valueOf(1));
        menu.setIsopen(Integer.valueOf(0));
        this.menus.add(menu);


        Menu list = createSubMenu(menu);
        list.setCode(this.contextConfig.getBizEnName() + "_list");
        list.setName(this.contextConfig.getBizChName() + "列表");
        list.setUrl("/" + this.contextConfig.getBizEnName() + "/list");
        this.menus.add(list);


        Menu add = createSubMenu(menu);
        add.setCode(this.contextConfig.getBizEnName() + "_add");
        add.setName(this.contextConfig.getBizChName() + "添加");
        add.setUrl("/" + this.contextConfig.getBizEnName() + "/add");
        this.menus.add(add);


        Menu update = createSubMenu(menu);
        update.setCode(this.contextConfig.getBizEnName() + "_update");
        update.setName(this.contextConfig.getBizChName() + "更新");
        update.setUrl("/" + this.contextConfig.getBizEnName() + "/update");
        this.menus.add(update);


        Menu delete = createSubMenu(menu);
        delete.setCode(this.contextConfig.getBizEnName() + "_delete");
        delete.setName(this.contextConfig.getBizChName() + "删除");
        delete.setUrl("/" + this.contextConfig.getBizEnName() + "/delete");
        this.menus.add(delete);


        Menu detail = createSubMenu(menu);
        detail.setCode(this.contextConfig.getBizEnName() + "_detail");
        detail.setName(this.contextConfig.getBizChName() + "详情");
        detail.setUrl("/" + this.contextConfig.getBizEnName() + "/detail");
        this.menus.add(detail);
    }

    private Menu createSubMenu(Menu parentMenu) {
        Menu menu = new Menu();
        menu.setId(Long.valueOf(IdWorker.getId()));
        menu.setPcode(parentMenu.getCode());
        menu.setPcodes(parentMenu.getPcodes() + "[" + parentMenu.getCode() + "],");
        menu.setIcon("");
        menu.setNum(Integer.valueOf(99));
        menu.setLevels(Integer.valueOf(parentMenu.getLevels().intValue() + 1));
        menu.setIsmenu(YesOrNotEnum.N.getCode());
        menu.setStatus(Integer.valueOf(1));
        menu.setIsopen(Integer.valueOf(0));
        return menu;
    }

    public String[] getPcodeAndPcodes() {
        if (this.parentMenuName.equals("顶级")) {
            return new String[]{"0", ""};
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement("select * from sys_menu where name like ?");
            preparedStatement.setString(1, "%" + this.parentMenuName + "%");
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                String pcode = results.getString("code");
                String pcodes = results.getString("pcodes");
                String[] strings;
                if ((ToolUtil.isNotEmpty(pcode)) && (ToolUtil.isNotEmpty(pcodes))) {
                    strings = new String[]{pcode, pcodes};
                    return strings;
                }
                return null;
            }


            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public String getParentMenuName() {
        return this.parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getSqlPathTemplate() {
        return this.sqlPathTemplate;
    }

    public void setSqlPathTemplate(String sqlPathTemplate) {
        this.sqlPathTemplate = sqlPathTemplate;
    }

    public List<Menu> getMenus() {
        return this.menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
