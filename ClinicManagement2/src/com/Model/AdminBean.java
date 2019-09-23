package com.Model;

import java.util.Date;

public class AdminBean {

	private int roleId;
	private String roleName;

	// staffTable
	private int sId;
	private String sName;
	private Date sDOB;
	private String sGender;
	private String sAddr;
	private String sExp;
	private String sPhNo;
	private String sEmail;
	private String username;
	private String password;
	private String sIsActive;
	private Date screatedDate;

	// doctorTable
	private int dId;
	// private int sId;
	private String dSpec;
	private String dQualification;
	private double consultFee;

	// medicineTable
	private int medId;
	private String medName;
	private double medPrice;
	private int mIsActive;
	private String manufacturer;
	private Date mcreatedDate;

	private String monday;
	private String tuesday;
	private String wednesday;

	private String thursday;
	private String friday;
	private String saturday;
	private String sunday;

	// getters and setters
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public Date getsDOB() {
		return sDOB;
	}

	public void setsDOB(Date dOB) {
		sDOB = dOB;
	}

	public String getsGender() {
		return sGender;
	}

	public void setsGender(String sGender) {
		this.sGender = sGender;
	}

	public String getsAddr() {
		return sAddr;
	}

	public void setsAddr(String sAddr) {
		this.sAddr = sAddr;
	}

	public String getsExp() {
		return sExp;
	}

	public void setsExp(String sExp) {
		this.sExp = sExp;
	}

	public String getsPhNo() {
		return sPhNo;
	}

	public void setsPhNo(String sPhNo) {
		this.sPhNo = sPhNo;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getsIsActive() {
		return sIsActive;
	}

	public void setsIsActive(String sIsActive) {
		this.sIsActive = sIsActive;
	}

	public Date getScreatedDate() {
		return screatedDate;
	}

	public void setScreatedDate(Date screatedDate) {
		this.screatedDate = screatedDate;
	}

	public int getdId() {
		return dId;
	}

	public void setdId(int dId) {
		this.dId = dId;
	}

	public String getdSpec() {
		return dSpec;
	}

	public void setdSpec(String dSpec) {
		this.dSpec = dSpec;
	}

	public String getdQualification() {
		return dQualification;
	}

	public void setdQualification(String dQualification) {
		this.dQualification = dQualification;
	}

	public double getConsultFee() {
		return consultFee;
	}

	public void setConsultFee(double consultFee) {
		this.consultFee = consultFee;
	}

	public int getMedId() {
		return medId;
	}

	public void setMedId(int medId) {
		this.medId = medId;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public double getMedPrice() {
		return medPrice;
	}

	public void setMedPrice(double medPrice) {
		this.medPrice = medPrice;
	}

	public int getmIsActive() {
		return mIsActive;
	}

	public void setmIsActive(int mIsActive) {
		this.mIsActive = mIsActive;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Date getMcreatedDate() {
		return mcreatedDate;
	}

	public void setMcreatedDate(Date mcreatedDate) {
		this.mcreatedDate = mcreatedDate;
	}

	public String getmonday() {
		return monday;
	}

	public void setmonday(String monday) {
		this.monday = monday;
	}

	public String gettuesday() {
		return tuesday;
	}

	public void settuesday(String tuesday) {
		this.tuesday = tuesday;
	}

	public String getwednesday() {
		return wednesday;
	}

	public void setwednesday(String wednesday) {
		this.wednesday = wednesday;
	}

	public String getthursday() {
		return thursday;
	}

	public void setthursday(String thursday) {
		this.thursday = thursday;
	}

	public String getfriday() {
		return friday;
	}

	public void setfriday(String friday) {
		this.friday = friday;
	}

	public String getsaturday() {
		return saturday;
	}

	public void setsaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getsunday() {
		return sunday;
	}

	public void setsunday(String sunday) {
		this.sunday = sunday;
	}

	// parameterized constructor
	public AdminBean(String sName, Date sDOB, String sGender, String sAddr,
			String sExp, String sPhNo, String sEmail, String username,
			String password, String sIsActive, Date screatedDate) {
		super();
		this.sName = sName;
		this.sDOB = sDOB;
		this.sGender = sGender;
		this.sAddr = sAddr;
		this.sExp = sExp;
		this.sPhNo = sPhNo;
		this.sEmail = sEmail;
		this.username = username;
		this.password = password;
		this.sIsActive = sIsActive;
		this.screatedDate = screatedDate;
	}

	public AdminBean(int sId, String sName, Date sDOB, String sGender,
			String sAddr, String sExp, String sPhNo, String sEmail,
			String username, String password, String sIsActive,
			Date screatedDate, String roleName) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sDOB = sDOB;
		this.sGender = sGender;
		this.sAddr = sAddr;
		this.sExp = sExp;
		this.sPhNo = sPhNo;
		this.sEmail = sEmail;
		this.username = username;
		this.password = password;
		this.sIsActive = sIsActive;
		this.screatedDate = screatedDate;
		this.roleName = roleName;
	}

	public AdminBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	// constructor without sId
	public AdminBean(int roleId, String sName, Date sDOB, String sGender,
			String sAddr, String sExp, String sPhNo, String sEmail,
			String username, String password, String sIsActive,
			Date screatedDate) {
		super();
		this.roleId = roleId;
		this.sName = sName;
		this.sDOB = sDOB;
		this.sGender = sGender;
		this.sAddr = sAddr;
		this.sExp = sExp;
		this.sPhNo = sPhNo;
		this.sEmail = sEmail;
		this.username = username;
		this.password = password;
		this.sIsActive = sIsActive;
		this.screatedDate = screatedDate;
	}

}
