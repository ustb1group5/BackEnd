package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.AdminDAO;
import com.Model.AdminBean;
import com.Model.FrontOfficeBean;



@RestController
public class AdminController {

	@Autowired
	AdminDAO adao;

	

	/***************************** DOCTOR ******************/

	// view doctors list
	@RequestMapping(value = "/viewDoc", headers = "Accept=application/json", method = RequestMethod.GET)
	public List viewDocList(@ModelAttribute("ad") AdminBean ad) {
		List DocList = adao.viewDocList();
		return DocList;
	}
	

	// Insert and update doctor
		@RequestMapping(value = "/api/insertDoctor", method ={RequestMethod.POST,RequestMethod.PUT})
		public void docInsert(@RequestBody AdminBean aBean){
	
			//adao.insertDoctorStaff(aBean);
			
				//adao.updateDoctor(aBean);
			

		
			if(aBean.getsId()==0){	
				System.out.println("sid 00");
			adao.insertDoctorStaff(aBean);
			}
			else
			{
				System.out.println("sid not null");
				adao.updateDoctor(aBean);
			}
		}
		
		
		
		
	

		// find doctor by name
		
		@RequestMapping(value = "api/doc/{searchString}", method = RequestMethod.GET, produces = "application/json")
		@ResponseBody
		public List<AdminBean> findDoc(@PathVariable("searchString") String searchString) {
			
			List DocList=adao.getDoctorByName(searchString);
			return DocList;
		}

	// disable doctor
	@RequestMapping(value = "/disableDoctor", method = RequestMethod.PUT, produces = "application/json")
	public void disableDoctor(@RequestBody AdminBean aBean) {
		adao.disableDoctor(aBean);
	}
	
	/*//get doctor by id
	@RequestMapping(value = "/getDoctorById/{sId}", method = RequestMethod.GET, produces = "application/json")
	public AdminBean findDoc(@PathVariable("sId") int sId) {
		return adao.getDoctorById(sId);
	}*/
	
	@RequestMapping(value = "/getDoctorById/{sId}", method = RequestMethod.GET, produces = "application/json")
	public AdminBean getDoctoree(@ModelAttribute("ad") AdminBean ad,@PathVariable("sId") int sId) {
		List doc=adao.getDoctorById(sId);
		ad=(AdminBean) doc.get(0);
		return  ad;
	}


	/******************************** MEDICINE ***************************************/
	// medicine insert and update
	@RequestMapping(value = "/insertMed", method = { RequestMethod.POST,
			RequestMethod.PUT }, produces = "application/json")
	public void updateMed(@RequestBody AdminBean ad) {

		if (ad.getMedId() != 0) {
			System.out.println(ad.getMedId());

			adao.updateMed(ad);
		} else {
			adao.saveMed(ad);
		}
		
		
	}

	// view medicine list
	@RequestMapping(value = "/viewMed", headers = "Accept=application/json", method = RequestMethod.GET)
	public List viewMedList(@ModelAttribute("ad") AdminBean ad) {
		List MedList = adao.viewMedList();
		return MedList;
	}

	// find medicine by med Name
	
	@RequestMapping(value = "api/med/{searchString}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<AdminBean> findMed(@PathVariable("searchString") String searchString) {
		
		List MedList=adao.getMedicineByName(searchString);
		return MedList;
	}

	// disable medicine
	@RequestMapping(value = "/disableMed", method = RequestMethod.PUT, produces = "application/json")
	public void disableMed(@RequestBody AdminBean aBean) {
		adao.disableMed(aBean);
	}
	
	//get medicine by id
	
	@RequestMapping(value = "api/getMedicineById/{medId}", method = RequestMethod.GET, produces = "application/json")
	public AdminBean getMedById(@PathVariable("medId") int medId) {
		return adao.getMedById(medId);
	}
	

	
	
	

	/**************** STAFF ***************************/
	// view staff list
	@RequestMapping(value = "/viewStaff", headers = "Accept=application/json", method = RequestMethod.GET)
	public List viewStaffList(@ModelAttribute("ad") AdminBean ad) {
		List staffList = adao.viewStaffList();
		return staffList;
	}

	// Insert and update staff
	@RequestMapping(value = "/insertStaff", method = { RequestMethod.POST,
			RequestMethod.PUT })
	public void staffInsert(@RequestBody AdminBean aBean) {
		if (aBean.getsId() == 0) {
			System.out.println("sf");
			adao.insertStaff(aBean);
		} else {
			System.out.println("sDFYf");
			adao.updateStaff(aBean);
		}

	}

	// disable staff
	@RequestMapping(value = "/disableStaff", method = RequestMethod.PUT, produces = "application/json")
	public void disableStaff(@RequestBody AdminBean aBean) {
		adao.disableStaff(aBean);
	}

	// find staff by sId and sPhn
	@RequestMapping(value = "/staff/{searchString}", method = RequestMethod.GET, produces = "application/json")
	public List<AdminBean> findStaff(@PathVariable("searchString") String searchString) {
		List StaffList=adao.getStaffByName(searchString);
		return StaffList;
	}
	
	/*@RequestMapping(value = "/viewrole", method = RequestMethod.GET, produces = "application/json")
	public void getRole(@ModelAttribute("ad") AdminBean ad) {
          adao.getRole();
	}*/
	
	@RequestMapping(value = "/viewrole", headers = "Accept=application/json", method = RequestMethod.GET)
	public List viewrole(@ModelAttribute("ad") AdminBean ad) {
		List RoleList = adao.viewrole();
		return RoleList;
	}
	
	@RequestMapping(value = "/getStaffById/{sId}", method = RequestMethod.GET, produces = "application/json")
	public AdminBean getStaff(@ModelAttribute("ad") AdminBean ad,@PathVariable("sId") int sId) {
		List staff=adao.getStaffById(sId);
		ad=(AdminBean) staff.get(0);
		return  ad;
	}
	
	
	/*@RequestMapping(value="/api/login/{uName}/{uPass}",method=RequestMethod.GET)
	public AdminBean getBookById(@PathVariable("uName") String userName,@PathVariable("uPass") String Password){
		AdminBean role=adao.roleChecker(userName,Password);
	System.out.println(role);
	return role;
}*/
	

@RequestMapping(value = "/api/verifyLogin/{uname}/{pass}", method = RequestMethod.GET)
public AdminBean verifyLogin(@PathVariable("uname") String uname,
@PathVariable("pass") String pass) {
return adao.roleChecker(uname, pass);
}
}
