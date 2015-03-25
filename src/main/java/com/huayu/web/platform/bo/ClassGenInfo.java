package com.huayu.web.platform.bo;

public class ClassGenInfo {

	private String classFileName;
	private String templeteOrigin;
	private String classStorePath;

	public String getClassFileName() {
		return classFileName;
	}

	public void setClassFileName(String classFileName) {
		this.classFileName = classFileName;
	}

	public String getTempleteOrigin() {
		return templeteOrigin;
	}

	public void setTempleteOrigin(String templeteOrigin) {
		this.templeteOrigin = templeteOrigin;
	}

	public String getClassStorePath() {
		return classStorePath;
	}

	public void setClassStorePath(String classStorePath) {
		this.classStorePath = classStorePath;
	}
	
	public String getClassFullPath(){
		return classStorePath + classFileName;
	}

}
