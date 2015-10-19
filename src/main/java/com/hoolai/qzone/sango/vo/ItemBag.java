package com.hoolai.qzone.sango.vo;

public class ItemBag {

	private int itemXmlId;
    private int num;
    private String name = "未映射名称";
    
    public boolean isDefault(){
    	return itemXmlId == 0;
    }
    
	@Override
	public String toString() {
		return "ItemBag [itemXmlId=" + itemXmlId + ", " + (name != null ? "name=" + name + ", " : "") + "num=" + num + "]";
	}

	public int getItemXmlId() {
		return itemXmlId;
	}
	
	public void setItemXmlId(int itemXmlId) {
		this.itemXmlId = itemXmlId;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getName() {
		return name;
	}
    
}
