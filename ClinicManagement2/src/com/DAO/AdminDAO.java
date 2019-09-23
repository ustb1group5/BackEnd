package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.Model.AdminBean;
import com.Model.FrontOfficeBean;

public class AdminDAO {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	/*************************** MEDICINE *********************************************/
	// view medicine List
	public List<AdminBean> viewMedList() {
		return template
				.query("select medId,medName,medPrice,isActive,manufacturer,createdDate from cm_medicineTable where isActive=1",
						new RowMapper<AdminBean>() {

							@Override
							public AdminBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub

								// creating object
								AdminBean ad = new AdminBean();

								ad.setMedId(rs.getInt(1));
								ad.setMedName(rs.getString(2));
								ad.setMedPrice(rs.getDouble(3));
								ad.setmIsActive(rs.getInt(4));
								ad.setManufacturer(rs.getString(5));
								ad.setMcreatedDate(rs.getDate(6));

								return ad;
							}
						});

	}

	// save medicine
	public int saveMed(AdminBean ad) {

		String sql = "insert into cm_medicineTable (medName,medPrice,isActive,manufacturer,createdDate) values ('"
				+ ad.getMedName()
				+ "',"
				+ ad.getMedPrice()
				+ ",1,'"
				+ ad.getManufacturer()
				+ "',TO_DATE('"
				+ java.time.LocalDate.now() + "','YYYY-MM-DD'))";
		return template.update(sql);

	}

	// update medicine
	public int updateMed(AdminBean ad) {

		String sql = "update cm_medicineTable set medName='" + ad.getMedName()
				+ "',medPrice=" + ad.getMedPrice() + ",isActive=" + "1"
				+ ",manufacturer='" + ad.getManufacturer() + "' where medId="
				+ ad.getMedId() + "";
		return template.update(sql);
	}

	// disable medicine
	public int disableMed(AdminBean aBean) {
		String sql = "update cm_medicineTable set isActive=0 where medId=? ";
		return template.update(sql, new Object[] { aBean.getMedId() });

	}

	// get medicine List by medicine name
	public AdminBean findMed(String medName) {
		String sql = "select * from cm_medicineTable where medName='" + medName
				+ "'";
		return template.queryForObject(sql, new Object[] {},
				new BeanPropertyRowMapper<AdminBean>(AdminBean.class));
	}

	// view medicine list
	public List<AdminBean> getMedicineByName(String searchString) {

		return template
				.query("select medId,medName,manufacturer,medPrice from cm_medicinetable where medId like'"
						+ searchString
						+ "%' or medName like '"
						+ searchString
						+ "%'", new RowMapper<AdminBean>() {
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						AdminBean ab = new AdminBean();
						ab.setMedId(rs.getInt(1));
						ab.setMedName(rs.getString(2));
						ab.setManufacturer(rs.getString(3));
						ab.setMedPrice(rs.getInt(4));
						return ab;
					}
				});

	}

	// get Medicine by id
	public AdminBean getMedById(int medId) {
		String sql = "select medId,medName,manufacturer,medPrice from cm_medicinetable where medId=?";
		return template.queryForObject(sql, new Object[] { medId },
				new BeanPropertyRowMapper<AdminBean>(AdminBean.class));
	}

	/***************************************** DOCTOR **********************************/

	// get doctor by name
	public List<AdminBean> getDoctorByName(String searchString) {

		return template
				.query("select sId,sName,dSpec,sPhNo from cm_doctorTable join cm_staffTable using (sId) where sId like '"
						+ searchString
						+ "%' or sName like '"
						+ searchString
						+ "%'", new RowMapper<AdminBean>() {
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						AdminBean ab = new AdminBean();
						ab.setsId(rs.getInt(1));
						ab.setsName(rs.getString(2));
						ab.setdSpec(rs.getString(3));
						ab.setsPhNo(rs.getString(4));
						return ab;
					}
				});

	}

	// view doctors List
	public List<AdminBean> viewDocList() {
		return template
				.query("select sId,dob,dId,dSpec,dQualification,consultFee,sName,sExp,sPhNo,isActive,roleId,sunday,monday,tuesday,wednesday,thursday,friday,saturday from cm_doctorTable join cm_staffTable using (sId) join cm_consultDaysTable using (dId) where roleId=2 and isactive='y'",
						new RowMapper<AdminBean>() {

							@Override
							public AdminBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub

								// creating object
								AdminBean ad = new AdminBean();

								ad.setsId(rs.getInt(1));
								ad.setsDOB(rs.getDate(2));

								ad.setdId(rs.getInt(3));

								ad.setdSpec(rs.getString(4));
								ad.setdQualification(rs.getString(5));
								ad.setConsultFee(rs.getDouble(6));
								ad.setsName(rs.getString(7));
								ad.setsExp(rs.getString(8));
								ad.setsPhNo(rs.getString(9));
								ad.setsIsActive(rs.getString(10));
								ad.setRoleId(rs.getInt(11));

								ad.setsunday(rs.getString(12));
								ad.setmonday(rs.getString(13));
								ad.settuesday(rs.getString(14));
								ad.setwednesday(rs.getString(15));
								ad.setthursday(rs.getString(16));
								ad.setfriday(rs.getString(17));
								ad.setsaturday(rs.getString(18));

								return ad;
							}
						});

	}

	/************************************************ Doctor *************************************/
	// insert doctor
	public int insertDoctorStaff(AdminBean aBean) {

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = ft.format(aBean.getsDOB());

		String sql = "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,sExp,sPhNo,sEmail,username,password,isActive,createdDate) values(2,'"
				+ aBean.getsName()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','yyyy-MM-dd')"

				// + aBean.getsDOB()
				+ ",'"
				+ aBean.getsGender()
				+ "','"
				+ aBean.getsAddr()
				+ "','"
				+ aBean.getsExp()
				+ "','"
				+ aBean.getsPhNo()
				+ "','"
				+ aBean.getsEmail()
				+ "','"
				+ aBean.getUsername()
				+ "','"
				+ aBean.getPassword()
				+ "','"
				+ "y"
				+ "',"
				+ "TO_DATE('"
				+ java.time.LocalDate.now() + "','yyyy/MM/dd'))";

		if (template.update(sql) != 0) {

			if (insertDoctor(aBean) != 0) {
				return insertAvailableDays(aBean);
			} else {
				return 0;
			}

		} else {
			return 0;
		}

	}

	// get maximum sId
	public int insertDoctor(AdminBean aBean) {

		String sql = "select max(sId) from cm_staffTable";
		int sId = template.queryForObject(sql, Integer.class);

		String sql2 = "insert into cm_doctorTable(sId,dSpec,dQualification,consultFee) values(?,?,?,?)";

		return template.update(sql2, new Object[] { sId, aBean.getdSpec(),
				aBean.getdQualification(), aBean.getConsultFee() });
	}

	// get maximum dId
	public int insertAvailableDays(AdminBean aBean) {

		String sql = "select max(dId) from cm_doctorTable";
		int dId = template.queryForObject(sql, Integer.class);

		String sql2 = "insert into cm_consultDaysTable(dId,sunday,monday,tuesday,wednesday,thursday,friday,saturday) values(?,?,?,?,?,?,?,?)";
		return template.update(sql2, new Object[] { dId, aBean.getsunday(),
				aBean.getmonday(), aBean.gettuesday(), aBean.getwednesday(),
				aBean.getthursday(), aBean.getfriday(), aBean.getsaturday() });
	}

	// update doctor
	public int updateDoctor(AdminBean aBean) {

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		long t = date.getTime();
		java.sql.Date sqlDate = new java.sql.Date(t);

		// update stafftable
		String sql = "update cm_staffTable set sName='" + aBean.getsName()
				+ "',DOB=" + "TO_DATE('" + sqlDate + "','YYYY-MM-dd')"
				+ ",sGender='" + aBean.getsGender() + "',sAddr='"
				+ aBean.getsAddr() + "',sExp='" + aBean.getsExp() + "',sPhNo='"
				+ aBean.getsPhNo() + "',sEmail='" + aBean.getsEmail()
				+ "',username='" + aBean.getUsername() + "',password='"
				+ aBean.getPassword() + "',isActive='y' where sId= "
				+ aBean.getsId() + "";
		template.update(sql, new Object[] {});

		// update doctor table
		String sql1 = "update cm_doctorTable set dSpec='" + aBean.getdSpec()
				+ "',dQualification='" + aBean.getdQualification()
				+ "',consultFee=" + aBean.getConsultFee() + " where sId="
				+ aBean.getsId() + "";
		template.update(sql1, new Object[] {});

		int dId = getSeqDid(aBean.getsId());

		// update consultdays table
		String sql2 = "update cm_consultdaysTable set sunday='"
				+ aBean.getsunday() + "',monday='" + aBean.getmonday()
				+ "',tuesday='" + aBean.gettuesday() + "',wednesday='"
				+ aBean.getwednesday() + "',thursday='" + aBean.getthursday()
				+ "',friday='" + aBean.getfriday() + "',saturday='"
				+ aBean.getsaturday() + "'where dId=" + dId + "";
		return template.update(sql2, new Object[] {});

	}

	// get did from doctor table by sid
	public int getSeqDid(int sId) {
		int id1;
		String sql = "select dId from cm_doctorTable where sId=?";

		id1 = template.queryForObject(sql, new Object[] { sId }, Integer.class);
		return id1;
	}

	

	// disable doctor

	public int disableDoctor(AdminBean aBean) {
		String sql = "update cm_staffTable set isActive='n' where sId=(select sId from cm_doctorTable where dId=?)";
		return template.update(sql, new Object[] { aBean.getdId() });
	}

	/*
	 * public AdminBean getDoctorById(int sId) {
	 * 
	 * 
	 * String sql =
	 * "select sId,dob,sGender,username,dId,dSpec,dQualification,consultFee,sName,sExp,sPhNo,isActive,roleId,sunday,monday,tuesday,wednesday,thursday,friday,saturday from cm_doctorTable join cm_staffTable using (sId) join cm_consultDaysTable using (dId) where sId=?"
	 * ; return template.queryForObject(sql, new Object[] { sId }, new
	 * BeanPropertyRowMapper<AdminBean>(AdminBean.class));
	 * 
	 * 
	 * 
	 * }
	 */

	public List<AdminBean> getDoctorById(int sId) {
		return template
				.query("select sId,dob,sGender,sName,password,username,dId,dSpec,dQualification,consultFee,sExp,sPhNo,isActive,roleId,sAddr,sEmail,sunday,monday,tuesday,wednesday,thursday,friday,saturday from cm_doctorTable join cm_staffTable using (sId) join cm_consultDaysTable using (dId) where sId="
						+ sId + "", new RowMapper<AdminBean>() {
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						AdminBean ab = new AdminBean();
						ab.setsId(rs.getInt(1));
						ab.setsDOB(rs.getDate(2));
						ab.setsGender(rs.getString(3));

						ab.setsName(rs.getString(4));
						ab.setPassword(rs.getString(5));
						ab.setUsername(rs.getString(6));
						ab.setdId(rs.getInt(7));
						ab.setdSpec(rs.getString(8));
						ab.setdQualification(rs.getString(9));
						ab.setConsultFee(rs.getDouble(10));
						ab.setsExp(rs.getString(11));
						ab.setsPhNo(rs.getString(12));
						ab.setsIsActive(rs.getString(13));
						ab.setRoleId(rs.getInt(14));
						ab.setsAddr(rs.getString(15));
						ab.setsEmail(rs.getString(16));
						ab.setsunday(rs.getString(17));
						ab.setmonday(rs.getString(18));
						ab.settuesday(rs.getString(19));
						ab.setwednesday(rs.getString(20));
						ab.setthursday(rs.getString(21));
						ab.setfriday(rs.getString(22));
						ab.setsaturday(rs.getString(23));

						return ab;
					}
				});

	}

	/**************** STAFF ***************************/
	/* Date dob; */

	// get roleId from roleName
	public int getRoleId(String role) {
		// finding roleId using roleName
		String sqlRoleId = "select roleId from cm_roleTable where roleName=? ";
		return template.queryForObject(sqlRoleId, new Object[] { role },
				Integer.class);

	}

	public List<AdminBean> getStaffByName(String searchString) {

		return template
				.query("select sId,sName,roleName,sPhNo from cm_roleTable join cm_staffTable using (roleId) where sId like '"
						+ searchString
						+ "%' or sName like '"
						+ searchString
						+ "%'", new RowMapper<AdminBean>() {
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						AdminBean ab = new AdminBean();
						ab.setsId(rs.getInt(1));
						ab.setsName(rs.getString(2));
						ab.setRoleName(rs.getString(3));
						ab.setsPhNo(rs.getString(4));
						return ab;
					}
				});

	}

	// insert staff
	/*
	 * public int insertStaff(AdminBean aBean) {
	 * 
	 * int id = getRoleId(aBean.getRoleName());
	 * 
	 * SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd"); String sqlDate
	 * = ft.format(aBean.getsDOB());
	 * 
	 * String sql =
	 * "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,sExp,sPhNo,sEmail,username,password,isActive,createdDate) values("
	 * + id + ",'" + aBean.getsName() + "'," + "TO_DATE('" + sqlDate +
	 * "','yyyy-MM-dd')" + ",'" + aBean.getsGender() + "','" + aBean.getsAddr()
	 * + "','" + aBean.getsExp() + "','" + aBean.getsPhNo() + "','" +
	 * aBean.getsEmail() + "','" + aBean.getUsername() + "','" +
	 * aBean.getPassword() + "','y'," + "TO_DATE('" + java.time.LocalDate.now()
	 * + "','yyyy/mm/dd'))"; return template.update(sql, new Object[] {id}); }
	 */

	public int insertStaff(AdminBean aBean) {

		int id = getRoleId(aBean.getRoleName());

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = ft.format(aBean.getsDOB());

		String sql = "insert into cm_staffTable(roleId,sName,DOB,sGender,sAddr,sExp,sPhNo,sEmail,username,password,isActive,createdDate) values("
				+ id
				+ ",'"
				+ aBean.getsName()
				+ "',"
				+ "TO_DATE('"
				+ sqlDate
				+ "','yyyy-MM-dd')"
				+ ",'"
				+ aBean.getsGender()
				+ "','"
				+ aBean.getsAddr()
				+ "','"
				+ aBean.getsExp()
				+ "','"
				+ aBean.getsPhNo()
				+ "','"
				+ aBean.getsEmail()
				+ "','"
				+ aBean.getUsername()
				+ "','"
				+ aBean.getPassword()
				+ "','y',"
				+ "TO_DATE('" + java.time.LocalDate.now() + "','yyyy/mm/dd'))";
		return template.update(sql, new Object[] {});
	}

	// update staff
	public int updateStaff(AdminBean ab) {

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDate = ft.format(ab.getsDOB());

		String sql = "update cm_staffTable set roleId=" + ab.getRoleId()
				+ ",sName='" + ab.getsName() + "',DOB= TO_DATE('" + sqlDate
				+ "' ,'yyyy-MM-dd')" + ",sGender='" + ab.getsGender()
				+ "',sAddr='" + ab.getsAddr() + "',sExp='" + ab.getsExp()
				+ "',sPhNo='" + ab.getsPhNo() + "',sEmail='" + ab.getsEmail()
				+ "',username='" + ab.getUsername() + "',password='"
				+ ab.getPassword() + "',isActive='y'  where sId=" + ab.getsId()
				+ "";
		return template.update(sql);
	}

	// view staff list
	public List<AdminBean> viewStaffList() {
		return template
				.query("select sId,sName,DOB,sGender,sAddr,sExp,sPhNo,sEmail,username,password,isActive,createdDate,roleName,roleId from cm_staffTable join cm_roleTable using(roleId) where isActive='y' and roleName!='doctor' and roleName!='admin'",
						new RowMapper<AdminBean>() {

							@Override
							public AdminBean mapRow(ResultSet rs, int row)
									throws SQLException {
								// TODO Auto-generated method stub

								// creating object
								AdminBean ad = new AdminBean();

								ad.setsId(rs.getInt(1));
								ad.setsName(rs.getString(2));
								ad.setsDOB(rs.getDate(3));
								ad.setsGender(rs.getString(4));
								ad.setsAddr(rs.getString(5));
								ad.setsExp(rs.getString(6));
								ad.setsPhNo(rs.getString(7));
								ad.setsEmail(rs.getString(8));
								ad.setUsername(rs.getString(9));
								ad.setPassword(rs.getString(10));
								ad.setsIsActive(rs.getString(11));
								ad.setScreatedDate(rs.getDate(12));
								ad.setRoleName(rs.getString(13));
								ad.setRoleId(rs.getInt(14));

								return ad;
							}
						});

	}

	// disable staff

	public int disableStaff(AdminBean aBean) {
		String sql = "update cm_staffTable set isActive='n' where sId=?";
		return template.update(sql, new Object[] { aBean.getsId() });
	}

	/*
	 * public AdminBean getRole() { String sql="select * from cm_roleTable";
	 * return template.queryForObject(sql, new Object[] {}, new
	 * BeanPropertyRowMapper<AdminBean>(AdminBean.class)); }
	 */

	public List<AdminBean> viewrole() {
		return template.query("select * from cm_roleTable",
				new RowMapper<AdminBean>() {

					@Override
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						// TODO Auto-generated method stub

						// creating object
						AdminBean ad = new AdminBean();

						ad.setRoleId(rs.getInt(1));
						ad.setRoleName(rs.getString(2));

						return ad;
					}
				});

	}

	public List<AdminBean> getStaffById(int sId) {
		return template
				.query("select sId,sName,password,username,sGender,DOB,sAddr,sPhNo,sEmail,sExp,roleName,roleId from cm_roletable join cm_staffTable using (roleId) where sId="
						+ sId + "", new RowMapper<AdminBean>() {
					public AdminBean mapRow(ResultSet rs, int row)
							throws SQLException {
						AdminBean ab = new AdminBean();
						ab.setsId(rs.getInt(1));
						ab.setsName(rs.getString(2));
						ab.setPassword(rs.getString(3));
						ab.setUsername(rs.getString(4));
						ab.setsGender(rs.getString(5));
						ab.setsDOB(rs.getDate(6));
						ab.setsAddr(rs.getString(7));
						ab.setsPhNo(rs.getString(8));
						ab.setsEmail(rs.getString(9));
						ab.setsExp(rs.getString(10));
						ab.setRoleName(rs.getString(11));
						ab.setRoleId(rs.getInt(12));
						return ab;
					}
				});

	}

	/*
	 * public AdminBean roleChecker(String userName,String password){
	 * 
	 * AdminBean aBean=new AdminBean();
	 * 
	 * String sql="select roleId from cm_staffTable where username='"+userName+
	 * "'and password='"+password+"'";
	 * 
	 * 
	 * try{ aBean=(AdminBean)template.queryForObject(sql,new Object[]{},new
	 * BeanPropertyRowMapper<AdminBean>(AdminBean.class));
	 * //System.out.println(user);
	 * 
	 * }catch(Exception e){ e.printStackTrace(); } return aBean;
	 * 
	 * 
	 * }
	 */

	public AdminBean roleChecker(String uname, String pass) {
		String sql = "select cm_staffTable.sId,cm_staffTable.sName,cm_roleTable.roleName,cm_roleTable.roleId from cm_staffTable join cm_roleTable on  cm_roleTable.roleId= cm_staffTable.roleId where userName = ? and password=? and cm_staffTable.isActive='y'";
		AdminBean ab = template.queryForObject(sql,
				new Object[] { uname, pass },
				new BeanPropertyRowMapper<AdminBean>(AdminBean.class));

		String roleName = ab.getRoleName();

		if (roleName.equals("doctor")) {
			String sql2 = "Select dId from cm_doctorTable where sId=?";
			Integer dId = template.queryForObject(sql2,
					new Object[] { ab.getsId() }, Integer.class);
			ab.setdId(dId);
		}
		return ab;

	}

}
