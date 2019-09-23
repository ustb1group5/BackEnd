package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.FrontOfficeDAO;
import com.Model.FrontOfficeBean;

@RestController
public class FrontOfficeController {

	@Autowired
	FrontOfficeDAO fobDao;
	
	

	//front office view patient list   
	@ResponseBody
	@RequestMapping(value="/patients",headers="Accept=Application/json",method = RequestMethod.GET)
	public List AllPatients(@ModelAttribute("fob") FrontOfficeBean fob) {
		
		List patList=fobDao.viewPatientList();
		return patList;
	}
	
	//Add patient in front office
	@ResponseBody
			@RequestMapping(value="/insertpatient",method={RequestMethod.POST,RequestMethod.PUT})
			public void insertPatient(@RequestBody FrontOfficeBean fob) {
				System.out.println("hel");
				if(fob.getRegId()!=0)
				{
				fobDao.updatePatient(fob);}
				else {
					System.out.println("hello");
					fobDao.savePatient(fob);
				}
			}
	
	//search by optional parameters
 		@ResponseBody
		@RequestMapping(value="/patientsearch/{searchString}",headers="Accept=Application/json",method = RequestMethod.GET)
		public List patientsDetails(@PathVariable("searchString")String searchString) {
			
			
			List patList=fobDao.getPatientById(searchString);
			//fobDao.dateConversion(patList);
			return patList;
		}
	
	
	/*//front office add new patient
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void insertnewPatient(@RequestBody FrontOfficeBean fob) {
		fobDao.savePatient(fob);
		
		
	}
	
	//Search by patient id
	@ResponseBody
	@RequestMapping(value="/searchbyid/{regId}",
					produces="application/json",
					method=RequestMethod.GET)
	public FrontOfficeBean getPatientById(@PathVariable("regId") int regId)
	{
		FrontOfficeBean fob=fobDao.getPatientById(regId);
		System.out.println("Reached Bean");
		System.out.println(fob);
		return fob;
	}*/
		/*
		//Patient required details  
				@ResponseBody
				@RequestMapping(value="/patientdetails/{regId}",headers="Accept=Application/json",method = RequestMethod.GET)
				public FrontOfficeBean patientsDetails(@ModelAttribute("f")FrontOfficeBean f,@PathVariable("regId")int regId) {
					FrontOfficeBean fob=fobDao.getRequiredPatientDetails(regId,f);
					fobDao.dateConversion(fob);
					return fob;
				}*/
		
				
	
	//Today's appointment list of patients
	@RequestMapping(value="/appointment",method=RequestMethod.GET)
	@ResponseBody
	public List getappointments(@ModelAttribute("fob")FrontOfficeBean fob)
	{ System.out.println("hai");
		List list=fobDao.list();
		//List list=fobDao.getColumn();
		return list;
	}
	
	
	@RequestMapping(value = "/api/getAvailableDoctor", method = RequestMethod.GET)
	public List<FrontOfficeBean> getAvailableDoctors() {

	List list= fobDao.getAvailableDoctor();
	return list;
	}
	
	
	/*********ERROR//Patient details  
			@ResponseBody
			@RequestMapping(value="/patdetails/{regId}",headers="Accept=Application/json",method = RequestMethod.GET)
			public FrontOfficeBean patientsDetails( @PathVariable("regId")int regId) {
				FrontOfficeBean fob=fobDao.getAllPatientDetails(regId);
				fobDao.dateConversion(fob);
				System.out.println("123");
				return fob;
			}
*/
	
	/*@RequestMapping(value="/patdetails/{regId}",method=RequestMethod.GET)
	@ResponseBody
	public FrontOfficeBean getappointments(@PathVariable("regId")int regId)
	{ System.out.println("hai");
		return fobDao.getPatientDetails(regId);
		//List list=fobDao.getColumn();
		//return list;
	}*/
	
	
	
	
	
	
	
	// patient list by id
		@RequestMapping(value="/patdetails/{regId}",method=RequestMethod.GET)
		@ResponseBody
		public FrontOfficeBean getPatient(@ModelAttribute("fob")FrontOfficeBean fob,@PathVariable ("regId") int regId)
		
		{ 
			//System.out.println("hai");
			List list=fobDao.getPatientDetailsById(regId);
			fob=(FrontOfficeBean) list.get(0);
			return fob;
		}
		
		//get doctors spec and consultfee
		@RequestMapping(value = "/api/searchDoctorByName/{sName}", method = RequestMethod.GET)
		public FrontOfficeBean getDoctorByName(@PathVariable("sName") String sName) {
		return fobDao.getDoctorName(sName);

		}
		

		//Appointment addition in front office
				@ResponseBody
						@RequestMapping(value="/insertAppointment",method=RequestMethod.POST)
						public void insertAppointmentTable(@RequestBody FrontOfficeBean fob) {
							
								fobDao.insertAppointment(fob);
							}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	//front office view patient list   
	@ResponseBody
	@RequestMapping(value="/patients",headers="Accept=Application/json",method = RequestMethod.GET)
	public List AllPatients(@ModelAttribute("fob") FrontOfficeBean fob) {
		
		List patList=fobDao.viewPatientList();
		return patList;
	}
	
	//Add patient in front office
	@ResponseBody
			@RequestMapping(value="/insertpatient",method={RequestMethod.POST,RequestMethod.PUT})
			public void insertPatient(@RequestBody FrontOfficeBean fob) {
				System.out.println("hel");
				if(fob.getRegId()!=0)
				{
				fobDao.updatePatient(fob);}
				else {
					System.out.println("hello");
					fobDao.savePatient(fob);
				}
			}
	
	//search by optional parameters
 		@ResponseBody
		@RequestMapping(value="/patientsearch/{searchString}",headers="Accept=Application/json",method = RequestMethod.GET)
		public FrontOfficeBean patientsDetails(@PathVariable("searchString")String searchString) {
			FrontOfficeBean fob=fobDao.getPatientById(searchString);
			fobDao.dateConversion(fob);
			return fob;
		}
	
	
	//front office add new patient
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void insertnewPatient(@RequestBody FrontOfficeBean fob) {
		fobDao.savePatient(fob);
		
		
	}
	
	//Search by patient id
	@ResponseBody
	@RequestMapping(value="/searchbyid/{regId}",
					produces="application/json",
					method=RequestMethod.GET)
	public FrontOfficeBean getPatientById(@PathVariable("regId") int regId)
	{
		FrontOfficeBean fob=fobDao.getPatientById(regId);
		System.out.println("Reached Bean");
		System.out.println(fob);
		return fob;
	}
		
		//Patient required details  
				@ResponseBody
				@RequestMapping(value="/patientdetails/{regId}",headers="Accept=Application/json",method = RequestMethod.GET)
				public FrontOfficeBean patientsDetails(@ModelAttribute("f")FrontOfficeBean f,@PathVariable("regId")int regId) {
					FrontOfficeBean fob=fobDao.getPatientDetails(regId,f);
					fobDao.dateConversion(fob);
					return fob;
				}
			
	
	//available doctors
	@ResponseBody
	@RequestMapping(value="/availdoc",headers="Accept=Application/json",method = RequestMethod.GET)
	public List doctorAvailability(@ModelAttribute("fob") FrontOfficeBean fob ) {
		
		//fobDao.getDay();
		
		List availDocList=fobDao.getAvailableDoctor(fob);
		
		return availDocList;
	}
	
	//Today's appointment list of patients
	@RequestMapping(value="/appointment/{date}",method=RequestMethod.GET)
	@ResponseBody
	public List getappointments(@ModelAttribute("fob")FrontOfficeBean fob,@PathVariable("date")String date)
	{
		List list=fobDao.list(date);
		return list;
	}*/
}
