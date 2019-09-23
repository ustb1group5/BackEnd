package com.Model;

import java.time.DayOfWeek;
import java.util.Date;

public class FrontOfficeBean {

	
	//declaring instance variable
	//day
	private DayOfWeek day;
	
	
	// Role table
		private int roleId;
		private String roleName;

		// Staff table
		private int sId;
		private String sName;
		private String sDOB;
		private String sGender;
		private String sAddr;
		private String sExp;
		private String sPhNo;
		private String sEmail;
		private String username;
		private String password;
		private String isActive;
		private String screatedDate;

		// Doctor table
		private int dId;
		private String dSpec;
		private String dQualification;
		private double consultFee;

		// Patient Table
		private int regId;
		private String pFName;
		private String pLName;
		private String pGender;
		private Date pDOB;
		private String pAddr;
		private String pPhNo;
		private String pBloodGrp;
		private Date pcreatedDate;
		private int age;

		// Getter and Setter for age
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		// Appointment table
		private int appId;
		private String dateOfApp;
		private String consultStatus;

		// FrontOffice Billing table
		private int fBillId;
		private String billDate;
		private double admFee;

		// Day Table
		private int dayId;
		private String dayName;
		
		// Consult Day Table
		private int consultId;
		
		
		
		public FrontOfficeBean(int consultId) {
			super();
			this.consultId = consultId;
		}

		public FrontOfficeBean(String dayName) {
			super();
			this.dayName = dayName;
		}

		public FrontOfficeBean(int dayId, String dayName) {
			super();
			this.dayId = dayId;
			this.dayName = dayName;
		}

		public int getDayId() {
			return dayId;
		}

		public String getDayName() {
			return dayName;
		}

		public int getConsultId() {
			return consultId;
		}

		public void setDayId(int dayId) {
			this.dayId = dayId;
		}

		public void setDayName(String dayName) {
			this.dayName = dayName;
		}

		public void setConsultId(int consultId) {
			this.consultId = consultId;
		}

		public String getsDOB() {
			return sDOB;
		}

		public void setsDOB(String sDOB) {
			this.sDOB = sDOB;
		}

		public String getScreatedDate() {
			return screatedDate;
		}

		public void setScreatedDate(String screatedDate) {
			this.screatedDate = screatedDate;
		}

		public int getRegId() {
			return regId;
		}

		public void setRegId(int regId) {
			this.regId = regId;
		}

		public String getpFName() {
			return pFName;
		}

		public void setpFName(String pFName) {
			this.pFName = pFName;
		}

		public String getpLName() {
			return pLName;
		}

		public void setpLName(String pLName) {
			this.pLName = pLName;
		}

		public String getpGender() {
			return pGender;
		}

		public void setpGender(String pGender) {
			this.pGender = pGender;
		}

		public Date getpDOB() {
			return pDOB;
		}

		public void setpDOB(Date pDOB) {
			this.pDOB = pDOB;
		}

		public String getpAddr() {
			return pAddr;
		}

		public void setpAddr(String pAddr) {
			this.pAddr = pAddr;
		}

		public String getpPhNo() {
			return pPhNo;
		}

		public void setpPhNo(String pPhNo) {
			this.pPhNo = pPhNo;
		}

		public String getpBloodGrp() {
			return pBloodGrp;
		}

		public void setpBloodGrp(String pBloodGrp) {
			this.pBloodGrp = pBloodGrp;
		}

		public Date getPcreatedDate() {
			return pcreatedDate;
		}

		public void setPcreatedDate(Date pcreatedDate) {
			this.pcreatedDate = pcreatedDate;
		}

		// With all fields
		public FrontOfficeBean(int roleId, String roleName, int sId, String sName, String sDOB, String sGender,
				String sAddr, String sExp, String sPhNo, String sEmail, String username, String password, String isActive,
				String screatedDate, int dId, String dSpec, String dQualification, double consultFee, int regId,
				String pFName, String pLName, String pGender, Date pDOB, String pAddr, String pPhNo, String pBloodGrp,
				Date pcreatedDate, int appId, String dateOfApp, String consultStatus, int fBillId, String billDate,
				double admFee) {
			super();
			this.roleId = roleId;
			this.roleName = roleName;
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
			this.isActive = isActive;
			this.screatedDate = screatedDate;
			this.dId = dId;
			this.dSpec = dSpec;
			this.dQualification = dQualification;
			this.consultFee = consultFee;
			this.regId = regId;
			this.pFName = pFName;
			this.pLName = pLName;
			this.pGender = pGender;
			this.pDOB = pDOB;
			this.pAddr = pAddr;
			this.pPhNo = pPhNo;
			this.pBloodGrp = pBloodGrp;
			this.pcreatedDate = pcreatedDate;
			this.appId = appId;
			this.dateOfApp = dateOfApp;
			this.consultStatus = consultStatus;
			this.fBillId = fBillId;
			this.billDate = billDate;
			this.admFee = admFee;
		}

		public FrontOfficeBean() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		

		public DayOfWeek getDay() {
			return day;
		}

		public void setDay(DayOfWeek day) {
			this.day = day;
		}

		public int getfBillId() {
			return fBillId;
		}

		public void setfBillId(int fBillId) {
			this.fBillId = fBillId;
		}

		public String getBillDate() {
			return billDate;
		}

		public void setBillDate(String billDate) {
			this.billDate = billDate;
		}

		public double getAdmFee() {
			return admFee;
		}

		public void setAdmFee(double admFee) {
			this.admFee = admFee;
		}

		public int getAppId() {
			return appId;
		}

		public void setAppId(int appId) {
			this.appId = appId;
		}

		public String getDateOfApp() {
			return dateOfApp;
		}

		public void setDateOfApp(String dateOfApp) {
			this.dateOfApp = dateOfApp;
		}

		public String getConsultStatus() {
			return consultStatus;
		}

		public void setConsultStatus(String consultStatus) {
			this.consultStatus = consultStatus;
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
/*
		public String getDOB() {
			return sDOB;
		}

		public void setDOB(String dOB) {
			sDOB = dOB;
		}*/

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

		public String getIsActive() {
			return isActive;
		}

		public void setIsActive(String isActive) {
			this.isActive = isActive;
		}

		@Override
		public String toString() {
			return "FrontOfficeBean [roleId=" + roleId + ", roleName="
					+ roleName + ", sId=" + sId + ", sName=" + sName
					+ ", sDOB=" + sDOB + ", sGender=" + sGender + ", sAddr="
					+ sAddr + ", sExp=" + sExp + ", sPhNo=" + sPhNo
					+ ", sEmail=" + sEmail + ", username=" + username
					+ ", password=" + password + ", isActive=" + isActive
					+ ", screatedDate=" + screatedDate + ", dId=" + dId
					+ ", dSpec=" + dSpec + ", dQualification=" + dQualification
					+ ", consultFee=" + consultFee + ", regId=" + regId
					+ ", pFName=" + pFName + ", pLName=" + pLName
					+ ", pGender=" + pGender + ", pDOB=" + pDOB + ", pAddr="
					+ pAddr + ", pPhNo=" + pPhNo + ", pBloodGrp=" + pBloodGrp
					+ ", pcreatedDate=" + pcreatedDate + ", age=" + age
					+ ", appId=" + appId + ", dateOfApp=" + dateOfApp
					+ ", consultStatus=" + consultStatus + ", fBillId="
					+ fBillId + ", billDate=" + billDate + ", admFee=" + admFee
					+ ", dayId=" + dayId + ", dayName=" + dayName
					+ ", consultId=" + consultId + "]";
		}
		
		

	
}
