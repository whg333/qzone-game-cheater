package com.hoolai.qzone.sango.vo;

public enum CountryType{
	unkonwn{
		@Override
		public String toString(){
			return "未知";
		}
	},wei{
		@Override
		public String toString(){
			return "魏";
		}
	},shu{
		@Override
		public String toString(){
			return "蜀";
		}
	},wu{
		@Override
		public String toString(){
			return "吴";
		}
	};
	
	public static CountryType getCountryType(int countryIndex){
		return CountryType.values()[countryIndex];
	}
}
