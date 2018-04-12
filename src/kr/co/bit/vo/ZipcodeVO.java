package kr.co.bit.vo;

public class ZipcodeVO {

	String zipcode;
	String sido;
	String gugun;
	String dong;
	String ri;
	String bldg;
	String bunji;
	String seq;
	
	
	
	public ZipcodeVO() {
	}
	
	
	public ZipcodeVO(String zipcode, String sido, String gugun, String dong, String ri, String bldg, String bunji,
			String seq) {
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.ri = ri;
		this.bldg = bldg;
		this.bunji = bunji;
		this.seq = seq;
	}
	
	
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getBldg() {
		return bldg;
	}
	public void setBldg(String bldg) {
		this.bldg = bldg;
	}
	public String getBunji() {
		return bunji;
	}
	public void setBunji(String bunji) {
		this.bunji = bunji;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}


	@Override
	public String toString() {
		return String.format("%s %s %s %s %s %s %s", zipcode,sido,gugun,dong,ri,bldg,bunji);
	}
	
	
	
	
}
