package com.hoolai.qzone.sango.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hoolai.qzone.sango.vo.Friend;

@SuppressWarnings("serial")
public class FriendTableModel extends AbstractTableModel {

	private static final String[] colNames = { "序号", "UID", "名称", "等级", "国家" };

	private final List<Friend> friendList;

	public FriendTableModel(List<Friend> friendList) {
		this.friendList = friendList;
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
		return friendList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Friend friend = friendList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return friend.getUserId();
		case 2:
			return friend.getName();
		case 3:
			return friend.getRank();
		case 4:
			return friend.getCountryName();
		}
		return "错误的值";
	}

}
