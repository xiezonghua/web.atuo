package com.huayu.web.platform.constant;

public enum ClassTempleteEnum {
	BO("bo/bean.vm" , ".java" , "bo/"),
	BO_BASE("bo/baseBean.vm" , "Base.java" , "bo/base/"),
	DAO("dao/interface.vm" , "Dao.java" , "dao/" ) , 
	DAO_IMPL("dao/interfaceImpl.vm" , "DaoImpl.java" , "dao/impl/"),
	SERVICE("service/interface.vm" , "Service.java" , "service/") ,
	SERVICE_IMPL("service/interfaceImpl.vm" , "ServiceImpl.java" , "service/impl/"), 
	IBATIS_MAPPING_XML("mapper/mapper.vm" , "Mapper.xml" , "conf/"),
	IBATIS_MAPPING_BASE_XML("mapper/basemapper.vm" , "BaseMapper.xml" , "conf/"),
	IBATIS_MAPPING_IMPL("mapper/daomapper.vm" , "Mapper.java" , "dao/mapper/");
	
	private String origin ;
	private String classSuffix;
	private String packagePath ;
	
    ClassTempleteEnum(String origin , String classSuffix , String packagePath){
    	this.origin =  "template/" + origin;
    	this.classSuffix = classSuffix;
    	this.packagePath = packagePath;
    }

	public String getOrigin() {
		return origin;
	}

	public String getClassSuffix() {
		return classSuffix;
	}

	public String getPackagePath() {
		return packagePath;
	}
    
} 