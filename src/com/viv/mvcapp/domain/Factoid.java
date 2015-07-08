package com.viv.mvcapp.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vxn3760
 */
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
