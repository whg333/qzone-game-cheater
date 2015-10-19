package com.hoolai.qzone.sango.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hoolai.qzone.LoginUser;

@SuppressWarnings("serial")
public class LoginUserTableModel extends AbstractTableModel {

	private static final String[] colNames = { "序号", "QQ登录信息" };

	private final List<LoginUser> loginUserList;

	public LoginUserTableModel(List<LoginUser> loginUserList) {
		this.loginUserList = loginUserList;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colNames[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return loginUserList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		LoginUser loginUser = loginUserList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return loginUser.toString();
		}
		return "错误的值";
	}

}
