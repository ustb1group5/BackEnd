package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.Model.DoctorBean;

public class DoctorDAO {

	// jdbc template
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;

	}

	// view medicine list
	public List<DoctorBean> viewMedicineList() {
		return template
				.query("select medId,medName,medPrice,isActive,manufacturer,createdDate from cm_medicineTable",

				new RowMapper<DoctorBean>() {

					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorBean db = new DoctorBean();
						db.setMedId(rs.getInt(1));
						db.setMedName(rs.getString(2));
						db.setMedPrice(rs.getInt(3));
						db.setMIsActive(rs.getString(4));
						db.setManufacturer(rs.getString(5));
						db.setMcreatedDate(rs.getString(6));
						System.out.println(db);
						return db;
					}
				});
	}

	// add patient comments
	public int addPatientComments(DoctorBean doc_bean) {

		String sql = "insert into cm_doctorObsTable(obserDate,obsNotes,regId,dId)values(TO_DATE('"
				+ java.time.LocalDate.now()
				+ "', 'YYYY-MM-DD'),'"
				+ doc_bean.getObsNotes()
				+ "',"
				+ doc_bean.getRegId()
				+ ","
				+ doc_bean.getdId() + ")";

		return template.update(sql);
	}

	// get lab test id by lab test name
	public Integer doc_getLabId(String labName) {
		String sql = "select labId from cm_labTestTable where lName=?";
		return template.queryForObject(sql, new Object[] { labName },
				Integer.class);
	}

	// add lab test request
	public int addLabTestRequest(DoctorBean db, String labName) {
		Integer labId = doc_getLabId(labName);
		String sql = "insert into cm_assignLabTable(regId,dId,labId,assigLabDate,sampleStatus,testStatus)"
				+ "values("
				+ db.getRegId()
				+ ","
				+ db.getdId()
				+ ",?,TO_DATE('"
				+ java.time.LocalDate.now()
				+ "', 'YYYY-MM-DD'),'Yes','Yes')";

		return template.update(sql, new Object[] { labId });

	}

	// view medicine list from medicine table
	public List<DoctorBean> list() {
		return template
				.query("select medid,medname,medprice,isActive,manufacturer,createddate from cm_medicinetable",
						new RowMapper<DoctorBean>() {
							public DoctorBean mapRow(ResultSet rs, int row)
									throws SQLException {
								DoctorBean m = new DoctorBean();
								m.setMedId(rs.getInt(1));
								m.setMedName(rs.getString(2));
								m.setMedPrice(rs.getInt(3));
								m.setMIsActive(rs.getString(4));
								m.setManufacturer(rs.getString(5));
								m.setMcreatedDate(rs.getString(6));
								return m;

							}
						});
	}

	// get today's patient appointment list
	public List<DoctorBean> getDoctorList(int dId, String date) {
		return template
				.query("select regId,pFName,pGender,appId,dId from cm_patientTable join cm_appoinmentTable using (regId) where dId="
						+ dId + " and dateOfApp='" + date + "'",
						new RowMapper<DoctorBean>() {

							@Override
							public DoctorBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub
								DoctorBean d = new DoctorBean();
								d.setRegId(rs.getInt(1));
								d.setpFName(rs.getString(2));
								d.setpGender(rs.getString(3));
								d.setAppId(rs.getInt(4));
								d.setdId(rs.getInt(5));

								return d;
							}
						});
	}

	// get lab test details from lab table
	public List<DoctorBean> getLabTestList() {
		return template.query(
				"select labid, lname,lfee,description from cm_labTestTable ",
				new RowMapper<DoctorBean>() {

					@Override
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {
						// TODO Auto-generated method stub
						DoctorBean d = new DoctorBean();
						d.setLabId(rs.getInt(1));
						d.setlName(rs.getString(2));
						d.setlFee(rs.getInt(3));
						d.setDescription(rs.getString(4));

						return d;
					}
				});

	}

	// get medicine id by medicine name from medicine table
	public int getMedicineId(String mName) {
		String sql = "select medId from cm_medicineTable where medName = ?";
		return template.queryForObject(sql, new Object[] { mName },
				Integer.class);

	}

	// insert doctor's prescription
	public int saveDoctorPrescription(DoctorBean d[]) {

		int arrayObjLength = d.length;
		int[] a = new int[arrayObjLength];
		for (int i = 0; i < arrayObjLength; i++) {
			String mName = d[i].getMedName();
			int id = getMedicineId(mName);
			System.out.println("mED iD" + id);
			String sql = "insert into cm_prescriptionTable(dId,regId,medId,medFreq,medDays,pharmacyStatus) values("
					+ d[i].getdId()
					+ ","
					+ d[i].getRegId()
					+ ",?,'"
					+ d[i].getMedFreq()
					+ "',"
					+ d[i].getMedDays()
					+ ",'"
					+ d[i].getPharmacyStatus() + "')";

			a[i] = template.update(sql, new Object[] { id });
		}
		return 1;

	}

	// ////////////////patient history////////////////////////

	// get prescribed medicine history by regId
	public List<DoctorBean> lablist(int regId) {
		return template
				.query("select prescId,prescDate,medName,medDays,medFreq,sName from cm_PrescriptionTable join cm_MedicineTable using(medId) join cm_doctorTable using (dId) join cm_staffTable using (sId) where regId = "
						+ regId + "", new RowMapper<DoctorBean>() {
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorBean db = new DoctorBean();

						db.setPrescId(rs.getInt(1));
						db.setPrescDate(rs.getDate(2));
						db.setMedName(rs.getString(3));
						db.setMedDays(rs.getInt(4));
						db.setMedFreq(rs.getString(5));
						db.setsName(rs.getString(6));
						return db;
					}
				});
	}

	// get prescribed lab test history by regId
	public List<DoctorBean> lablistHistory(int regId) {
		return template
				.query("select labId,lName,sName from cm_assignLabTable join cm_LabTestTable using(labId)join cm_doctorTable using (dId) join cm_staffTable using (sId) where regId="
						+ regId + "", new RowMapper<DoctorBean>() {
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorBean db = new DoctorBean();

						db.setLabId(rs.getInt(1));
						db.setlName(rs.getString(2));
						db.setsName(rs.getString(3));

						return db;

					}
				});
	}

	// get observation history by regId
	public List<DoctorBean> obsHistory(int regId) {
		return template
				.query("select obserDate,obsNotes,sName,dSpec from cm_doctorObsTable join cm_doctorTable using(dId) join cm_staffTable using(sId) join cm_patientTable using(regId) where regId="
						+ regId + "", new RowMapper<DoctorBean>() {
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {

						DoctorBean db = new DoctorBean();

						db.setObserDate(rs.getDate(1));
						db.setObsNotes(rs.getString(2));
						db.setsName(rs.getString(3));
						db.setdSpec(rs.getString(4));

						return db;
					}
				});
	}

	// get patient details
	public List<DoctorBean> getRequiredPatientDetails(int regId) {
		return template.query(
				"select distinct(regId),pFName,DOB from cm_patientTable where regId="
						+ regId + "", new RowMapper<DoctorBean>() {
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {

						DoctorBean db = new DoctorBean();

						db.setRegId(rs.getInt(1));
						db.setpFName(rs.getString(2));
						db.setpDOB(rs.getDate(3));

						return db;
					}
				});
	}

	// Getting dob by id
	public Date getdob(int regId) {
		String sql2 = "select DOB from cm_patientTable where regId=" + regId
				+ "";
		Date dob = template.queryForObject(sql2, new Object[] {}, Date.class);
		return dob;
	}

	// date conversion method
	public void dateConversion(DoctorBean fob) {

		DateTimeFormatter dtf = DateTimeFormatter
				.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

	}

	/************************************* View patient's appointment by doctor *************************************/

	// get today's appointment list
	public List<DoctorBean> getAppointment(int dId) {
		return template
				.query("select regId,pFName,pGender,appId from cm_patientTable join cm_appoinmentTable using (regId) where dId="
						+ dId + " and trunc(dateOfApp) = trunc(sysdate)",
						new RowMapper<DoctorBean>() {

							@Override
							public DoctorBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub

								DoctorBean d = new DoctorBean();

								d.setRegId(rs.getInt(1));
								d.setpFName(rs.getString(2));
								d.setpGender(rs.getString(3));
								d.setAppId(rs.getInt(4));

								return d;
							}
						});
	}

	/*************************** add medicine request ******************************/

	public Integer d_getMedicineId(String medName) {
		String sql = "select medId from cm_medicineTable where medName =?";
		return template.queryForObject(sql, new Object[] { medName },
				Integer.class);

	}

	public int saveDoctorPrescription(DoctorBean doc) {
		Integer medId = d_getMedicineId(doc.getMedName());
		String sql = "insert into cm_prescriptionTable(dId,regId,medId,medFreq,medDays,prescDate) values("
				+ doc.getdId()
				+ ","
				+ doc.getRegId()
				+ ",?,'"
				+ doc.getMedFreq()
				+ "',"
				+ doc.getMedDays()
				+ ",TO_DATE('"
				+ java.time.LocalDate.now() + "', 'YYYY-MM-DD'))";
		return template.update(sql, new Object[] { medId });
	}

	// getAllprescription

	public List<DoctorBean> getAllPrescription(int regId, int dId) {
		return template
				.query("select p.dId,p.regId,p.prescdate,p.medDays , p.medFreq ,m.medName from cm_prescriptiontable p join cm_medicineTable m on p.medid=m.medid where trunc(prescdate) = trunc(sysdate) and regId = "
						+ regId + "and dId=" + dId + " ",

				new RowMapper<DoctorBean>() {
					public DoctorBean mapRow(ResultSet rs, int row)
							throws SQLException {
						DoctorBean db = new DoctorBean();

						db.setdId(rs.getInt(1));
						db.setRegId(rs.getInt(2));
						db.setPrescDate(rs.getDate(3));
						db.setMedDays(rs.getInt(4));
						db.setMedFreq(rs.getString(5));
						db.setMedName(rs.getString(6));
						return db;
					}
				});
	}

	// add patient comments
	public int getPatientComments(DoctorBean doctor) {
		String sql = "insert into cm_doctorObsTable(obserDate,obsNotes,regId,dId)values(TO_DATE('"
				+ java.time.LocalDate.now()
				+ "','YYYY-MM-DD'),'"
				+ doctor.getObsNotes()
				+ "',"
				+ doctor.getRegId()
				+ ","
				+ doctor.getdId() + ")";
		return template.update(sql);
	}
	
	
//////////////////////////////////////////////
public Integer d_getLabId(String lName) {
String sql = "select labId from cm_labTestTable where lName =?";
return template.queryForObject(sql, new Object[] { lName },
Integer.class);

}


//add lab request
public int getAddLabRequest(DoctorBean dr) {

Integer labId = d_getLabId(dr.getlName());
String sql = "insert into cm_assignLabTable(assigLabDate,regId,dId,labId)values(TO_DATE('"
+ java.time.LocalDate.now()
+ "','YYYY-MM-DD'),"
+ dr.getRegId() + "," + dr.getdId() + "," + labId + ")";
return template.update(sql);
}

//get all test
public List<DoctorBean> getAllTest() {
return template.query("select labId,lName from cm_labTestTable",
new RowMapper<DoctorBean>() {

@Override
public DoctorBean mapRow(ResultSet rs, int row)
throws SQLException {

DoctorBean doc = new DoctorBean();

doc.setLabId(rs.getInt(1));
doc.setlName(rs.getString(2));

return doc;

}
});

}


//getAllLabprescription

public List<DoctorBean> getAlllabPrescription(int regId , int dId) {
return template
.query("select labId,lName,assignLabId from cm_assignLabTable join cm_LabTestTable using(labId) join cm_doctorTable using (dId) where trunc(assigLabDate) = trunc(sysdate)and regId="+regId+" and dId="+dId+"",

new RowMapper<DoctorBean>() {
public DoctorBean mapRow(ResultSet rs, int row)
throws SQLException {
DoctorBean db = new DoctorBean();

db.setLabId(rs.getInt(1));
db.setlName(rs.getString(2));
db.setAssignLabId(rs.getInt(3));


return db;
}
});
}


public Integer lab_delete(int assignLabId) {
String sql = "delete from cm_assignLabTable where assignLabId=?";
return template.queryForObject(sql, new Object[] { assignLabId },
Integer.class);
}


	

}
