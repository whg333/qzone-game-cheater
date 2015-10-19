package com.hoolai.qzone.sango.gui;

import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.hoolai.qzone.sango.vo.Officer;
import com.hoolai.qzone.sango.vo.OwnCity;

@SuppressWarnings("serial")
public class OwnCityTableModel extends AbstractTableModel {

	private static final String[] colNames = { "城池id", "名称", "等级", "状态" };

	private final List<OwnCity> ownCityList;
	private final Map<Integer, Officer> officerMap;

	public OwnCityTableModel(List<OwnCity> ownCityList, Map<Integer, Officer> officerMap) {
		this.ownCityList = ownCityList;
		this.officerMap = officerMap;
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
		return ownCityList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		OwnCity ownCity = ownCityList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			return ownCity.isMainCity() ? "主城-100" : "辅城" + ownCity.getId();
		case 2:
			return ownCity.getLevel();
		case 3:
			Officer officer = ownCity.isSelfDefend() ? officerMap.get(ownCity.getOfficerId()): ownCity.getOfficer();
			return ownCity.isSelfDefend() 
					? officer.getLevel() + "级将领【" + officer.getName() + "】在驻守" 
					: !ownCity.isOccupied() 
						? "无人驻守" : ownCity.isOccupiedByRobber() ? "被" + ownCity.getRobberOfficerLevel() + "级【倭寇】占领" 
						: "被" + ownCity.getLord().getUserProperty().getRank() + "级【" + ownCity.getLordId() + "：" + ownCity.getLord().getName() + "】的" 
							+ officer.getLevel() + "级【" + officer.getName() + "】占领";
		}
		return "错误的值";
	}

}
