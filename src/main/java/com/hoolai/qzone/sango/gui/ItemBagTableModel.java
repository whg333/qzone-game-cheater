package com.hoolai.qzone.sango.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hoolai.qzone.sango.vo.ItemBag;

@SuppressWarnings("serial")
public class ItemBagTableModel extends AbstractTableModel {

	private static final String[] colNames = { "道具XmlId", "道具名称", "道具数量" };

	private final List<ItemBag> itemBagList;

	public ItemBagTableModel(List<ItemBag> itemBagList) {
		this.itemBagList = itemBagList;
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
		return itemBagList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemBag itemBag = itemBagList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return itemBag.getItemXmlId();
		case 1:
			return itemBag.getName();
		case 2:
			return itemBag.getNum();
		}
		return "错误的值";
	}

}
