package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.Model.FrontOfficeBean;

public class FrontOfficeDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	// View Patient List
	public List<FrontOfficeBean> viewPatientList() {
		return template
				.query("select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable",
						new RowMapper<FrontOfficeBean>() {
							public FrontOfficeBean mapRow(ResultSet rs, int row)
									throws SQLException {
								FrontOfficeBean fob = new FrontOfficeBean();

								fob.setRegId(rs.getInt(1));
								fob.setpFName(rs.getString(2));
								fob.setpLName(rs.getString(3));
								fob.setpGender(rs.getString(4));
								fob.setpDOB(rs.getDate(5));
								fob.setpAddr(rs.getString(6));
								fob.setpPhNo(rs.getString(7));
								fob.setpBloodGrp(rs.getString(8));
								fob.setPcreatedDate(rs.getDate(9));
								return fob;
							}
						});
	}

	// Get element using id or phone number
	public List getPatientById(String searchString) {

		return template
				.query("select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable where regId like '"
						+ searchString
						+ "%' or pPhNo like'"
						+ searchString
						+ "%'", new RowMapper<FrontOfficeBean>()

				{
					public FrontOfficeBean mapRow(ResultSet rs, int row)
							throws SQLException {
						FrontOfficeBean fob = new FrontOfficeBean();

						fob.setRegId(rs.getInt(1));
						fob.setpFName(rs.getString(2));
						fob.setpLName(rs.getString(3));
						fob.setpGender(rs.getString(4));
						fob.setpDOB(rs.getDate(5));
						fob.setpAddr(rs.getString(6));
						fob.setpPhNo(rs.getString(7));
						fob.setpBloodGrp(rs.getString(8));
						fob.setPcreatedDate(rs.getDate(9));
						return fob;
					}
				});
	}

	// Inserting Patient Details
	public int savePatient(FrontOfficeBean fob) {
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		String sqlDate = ft.format(fob.getpDOB());
		System.out.println(sqlDate);

		String sql1 = "insert into cm_patientTable(pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate) values('"
				+ fob.getpFName()
				+ "','"
				+ fob.getpLName()
				+ "','"
				+ fob.getpGender()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','dd-MM-yyyy')"
				+ ",'"
				+ fob.getpAddr()
				+ "','"
				+ fob.getpPhNo()
				+ "','"
				+ fob.getpBloodGrp()
				+ "',TO_DATE('"
				+ java.time.LocalDate.now() + "','yyyy-MM-dd'))";
		return template.update(sql1);
	}

	// edit patient
	public int updatePatient(FrontOfficeBean fob) {
		String sql = "update cm_patientTable set pFName='" + fob.getpFName()
				+ "',pLName='" + fob.getpLName() + "',pGender='"
				+ fob.getpGender() + "',pAddr='" + fob.getpAddr() + "',pPhNo='"
				+ fob.getpPhNo() + "',pBloodGrp='" + fob.getpBloodGrp()
				+ "',createdDate=TO_DATE('" + java.time.LocalDate.now()
				+ "','YYYY-MM-dd') where regId=" + fob.getRegId() + "";
		return template.update(sql);
	}

	// System Date method
	public String DateConversion() {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		String pcreatedDate = formatter.format(date);
		return pcreatedDate;

	}

	// date conversion method
	public void dateConversion(FrontOfficeBean fob) {

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

	}

	// getting day for doctors
	public DayOfWeek getToday() {
		LocalDate date = LocalDate.now();
		DayOfWeek now = date.getDayOfWeek();
		return now;
	}

	// get available doctors
	public List<FrontOfficeBean> getAvailableDoctor() {
		DayOfWeek today = getToday();
		String sql = "select sName,dSpec,consultFee from cm_doctorTable join cm_staffTable on cm_doctorTable.sId=cm_staffTable.sId join cm_consultDaysTable on cm_consultDaysTable.dId=cm_doctorTable.dId where "
				+ today + "='true' and isActive='y'";

		return template.query(sql, new RowMapper<FrontOfficeBean>() {

			public FrontOfficeBean mapRow(ResultSet rs, int row)
					throws SQLException {
				FrontOfficeBean fb_bean = new FrontOfficeBean();
				fb_bean.setsName(rs.getString(1));
				fb_bean.setdSpec(rs.getString(2));
				fb_bean.setConsultFee(rs.getDouble(3));

				return fb_bean;
			}
		});
	}

	public int ageCalculation(Date s) {

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = ft.format(s);

		String s3 = sqlDate.substring(0, 4);
		int now1 = Integer.parseInt(s3);

		int now2 = s.getMonth();

		int now3 = s.getDate();

		LocalDate l = LocalDate.of(now1, now2, now3);
		// gets localDate
		LocalDate now4 = LocalDate.now();
		// difference between the dates is calculated
		Period diff = Period.between(l, now4);

		int age = diff.getYears();

		return age;

	}

	DayOfWeek now1 = null;

	// Getting day from system date
	public DayOfWeek getDay(DayOfWeek day2) {

		LocalDate now = LocalDate.now();
		now1 = now.getDayOfWeek();
		System.out.println(now1);

		for (int i = 0; i <= 9; i++) {
			/*
			 * if(now1==arr[i]) {
			 * 
			 * String
			 * sql="select dId from cm_consultDaysTable where day='true'";
			 * 
			 * }
			 */
		}
		return now1;

	}

	DayOfWeek day = now1;

	public List getColumn() {
		String sql = "select column_name from user_tab_columns where table_name='CM_CONSULTDAYSTABLE'";
		List day = (List) template.queryForObject(sql, new Object[] {},
				List.class);
		System.out.println(day);
		return day;

	}

	// Today's appointment list of patients
	public List<FrontOfficeBean> list() {
		return template
				.query("select regId,pFName,pLName,pGender,appId,dId,sName,dateOfApp from cm_patientTable join cm_appoinmentTable using (regId) join cm_doctorTable using (dId) join  cm_staffTable using (sId) where trunc(dateOfApp)=trunc(sysdate)",
						new RowMapper<FrontOfficeBean>() {

							public FrontOfficeBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub
								FrontOfficeBean fob = new FrontOfficeBean();
								fob.setRegId(rs.getInt(1));
								fob.setpFName(rs.getString(2));
								fob.setpLName(rs.getString(3));
								fob.setpGender(rs.getString(4));
								fob.setAppId(rs.getInt(5));
								fob.setdId(rs.getInt(6));
								fob.setsName(rs.getString(7));
								fob.setDateOfApp(rs.getString(8));

								return fob;
							}

						});

	}

	// Get patient info only required details
	public FrontOfficeBean getRequiredPatientDetails(int regId,
			FrontOfficeBean fob) {

		Date dob1 = getdob(regId);
		// System.out.println(dob1);
		int newAge = ageCalculation(dob1);

		// System.out.println("New Age:"+newAge);
		String sql = "select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable where regId=?";
		FrontOfficeBean fobNew = template.queryForObject(sql,
				new Object[] { regId },
				new BeanPropertyRowMapper<FrontOfficeBean>(
						FrontOfficeBean.class));

		fobNew.setAge(newAge);
		return fobNew;
	}

	// Getting dob by id
	public Date getdob(int regId) {
		String sql2 = "select DOB from cm_patientTable where regId=" + regId
				+ "";
		Date dob = template.queryForObject(sql2, new Object[] {}, Date.class);
		return dob;
	}

	// Get patient details using id
	public FrontOfficeBean getPatientDetails(int regId) {

		String sql = "select regId,pFName,pLName,pGender,DOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable where regId=?";

		return template.queryForObject(sql, new Object[] { regId },
				new BeanPropertyRowMapper<FrontOfficeBean>(
						FrontOfficeBean.class));
	}

	// **********doctor availability billing**************************
	public List<FrontOfficeBean> getDocFront() {

		return template
				.query("select sid,sname,dspec,consultfee from cm_stafftable join cm_doctortable using(sId)",
						new RowMapper<FrontOfficeBean>() {

							public FrontOfficeBean mapRow(ResultSet rs, int row)
									throws SQLException {
								FrontOfficeBean fob = new FrontOfficeBean();

								fob.setsId(rs.getInt(1));
								fob.setsName(rs.getString(2));
								fob.setdSpec(rs.getString(3));
								fob.setConsultFee(rs.getDouble(4));
								return fob;
							}
						});
	}

	public List<FrontOfficeBean> getPatientDetailsById(int regId) {
		System.out.println("Reached get object by iD");
		return template
				.query("select regId,pFName,pLName,pGender,DOB as pDOB,pAddr,pPhNo,pBloodGrp,createdDate from cm_patientTable where regId="
						+ regId + "", new RowMapper<FrontOfficeBean>() {
					public FrontOfficeBean mapRow(ResultSet rs, int row)
							throws SQLException {
						FrontOfficeBean fob = new FrontOfficeBean();

						fob.setRegId(rs.getInt(1));
						fob.setpFName(rs.getString(2));
						fob.setpLName(rs.getString(3));
						fob.setpGender(rs.getString(4));
						fob.setpDOB(rs.getDate(5));
						fob.setpAddr(rs.getString(6));
						fob.setpPhNo(rs.getString(7));
						fob.setpBloodGrp(rs.getString(8));
						fob.setPcreatedDate(rs.getDate(9));

						System.out.println(fob.getpDOB());
						return fob;
					}
				});

	}

	public FrontOfficeBean getDoctorName(String sName) {
		FrontOfficeBean ab;
		String sql = "select dId,sName,dSpec,consultFee,sPhNo from cm_doctorTable join cm_staffTable using (sId) where sName = ? and isActive='y'";
		ab = template.queryForObject(sql, new Object[] { sName },
				new BeanPropertyRowMapper<FrontOfficeBean>(
						FrontOfficeBean.class));

		return ab;

	}

	// Inserting into appointment table
	public int insertAppointment(FrontOfficeBean fob) {

		String sql1 = "insert into cm_appoinmentTable(regId,dId,dateOfApp,consultStatus) values("
				+ fob.getRegId()
				+ ","
				+ fob.getdId()
				+ ",TO_DATE('"
				+ java.time.LocalDate.now() + "','yyyy-MM-dd'),'yes')";
		template.update(sql1);

		System.out.println(fob.getAdmFee());
		String sql2 = "insert into cm_frontOfficeBilling(regId,dId,billDate,admFee) values("
				+ fob.getRegId()
				+ ","
				+ fob.getdId()
				+ ",TO_DATE('"
				+ java.time.LocalDate.now()
				+ "','yyyy-MM-dd'),"
				+ fob.getAdmFee() + ")";
		return template.update(sql2);

	}

}
