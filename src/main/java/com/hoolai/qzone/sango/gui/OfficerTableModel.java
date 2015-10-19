package com.hoolai.qzone.sango.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hoolai.qzone.sango.vo.Officer;

@SuppressWarnings("serial")
public class OfficerTableModel extends AbstractTableModel {

	private static final String[] colNames = { "将领id", "名称", "等级", "体力", "状态", "技能1", "技能2", "技能3", "技能4", "技能5" };
	
	private final List<Officer> officerList;

	public OfficerTableModel(List<Officer> officerList) {
		this.officerList = officerList;
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
		return officerList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Officer officer = officerList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return officer.getId();
		case 1:
			return officer.getName();
		case 2:
			return officer.getLevel();
		case 3:
			return officer.getEnergy();
		case 4:
			return officer.getStation();
		case 5:
			return officer.isHasSkill(0) ? officer.getSkills()[0] : officer.isSkillClose(0) ? "闭" : "";
		case 6:
			return officer.isHasSkill(1) ? officer.getSkills()[1] : officer.isSkillClose(1) ? "闭" : "";
		case 7:
			return officer.isHasSkill(2) ? officer.getSkills()[2] : officer.isSkillClose(2) ? "闭" : "";
		case 8:
			return officer.isHasSkill(3) ? officer.getSkills()[3] : officer.isSkillClose(3) ? "闭" : "";
		case 9:
			return officer.isHasSkill(4) ? officer.getSkills()[4] : officer.isSkillClose(4) ? "闭" : "";
		}
		return "错误的值";
	}

}
