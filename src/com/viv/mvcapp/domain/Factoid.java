package com.viv.mvcapp.domain;

public class Factoid {
	
    private String txt;
    
    @ValidFileName
    private String pic;
    
    public String getPic(){
        return "resources/" + this.pic;
    }

	public String getTxt() {
		return this.txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
