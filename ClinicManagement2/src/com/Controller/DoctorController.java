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

import com.DAO.DoctorDAO;
import com.Model.DoctorBean;
import com.Model.FrontOfficeBean;


@RestController
public class DoctorController {
	@Autowired
	DoctorDAO ddao;
	
	
	

	//@ResponseBody
	@RequestMapping(value = "/doctor/medicine", method = RequestMethod.GET)
	public List viewMedicineList() {

		List medicinelist = ddao.viewMedicineList();
		return medicinelist;
	}

	@RequestMapping(value = "/doctor/{dId}/{date}", method = RequestMethod.GET)
	//@ResponseBody
	public List getappointments(@ModelAttribute("db") DoctorBean db,
			@PathVariable("dId") int dId, @PathVariable("date") String date) {
		List list = ddao.getDoctorList(dId, date);
		return list;
	}

	// assigning lab test
	@RequestMapping(value = "/api/assignLab/{labName}", method = RequestMethod.POST)
	public void insertBook(@RequestBody DoctorBean db,
			@PathVariable("labName") String labName) {
		ddao.addLabTestRequest(db, labName);
	}

	// adding patient comments
	@RequestMapping(value = "/api/addPatientComment", method = RequestMethod.POST)
	public void insertObservation(@RequestBody DoctorBean doc_bean) {
		ddao.addPatientComments(doc_bean);
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/doctor/viewtest", method = RequestMethod.GET)
	 * public String viewtest(Model m) { List<Doctor>list=dao.getAllTest();
	 * m.addAttribute("list",list); return "viewtest";
	 * 
	 * }
	 */

	// getting medicine
	@RequestMapping(value = "/api/medicine", method = RequestMethod.GET)
	//@ResponseBody
	public List<DoctorBean> getAllMedicines() {
		List list = ddao.list();
		return list;

	}

	// getting lab test
	@RequestMapping(value = "/api/getAllLabTest", method = RequestMethod.GET)
	public List<DoctorBean> getAllLabTest() {
		List list = ddao.getLabTestList();
		return list;
	}

	// prescription
	@RequestMapping(value = "api/insertdoctortwo", method = RequestMethod.POST)
	public void insertDoctorPrescription(@RequestBody DoctorBean d[]) {
		System.out.println("Insde Doctor prescription");
		ddao.saveDoctorPrescription(d);

	}
	
	/*//patient history
	
		@RequestMapping(value = "/patientHistory", headers = "Accept=application/json", method = RequestMethod.GET)
		public List viewPatientHistory(@ModelAttribute("db") DoctorBean db) {
			List patHis = ddao.viewPatientHistory(regId);
			//List patHis = ddao.viewPatientHistory();
			return patHis;
		}*/
	
	@RequestMapping(value = "/api/preschis/{regId}", method = RequestMethod.GET)
	public List<DoctorBean> getAllLabTests(@PathVariable("regId")int regId) {
	List list = ddao.lablist(regId);
	return list;
	}

	@RequestMapping(value = "/api/labtestshis/{regId}", method = RequestMethod.GET)
	public List<DoctorBean> getAllLabTestsHistory(@PathVariable("regId")int regId) {
	List list = ddao.lablistHistory(regId);
	return list;
	}
	
	@RequestMapping(value = "/api/obshis/{regId}", method = RequestMethod.GET)
	public List<DoctorBean> getobsHistory(@PathVariable("regId")int regId) {
	List list = ddao.obsHistory(regId);
	return list;
	}
	

	@RequestMapping(value = "/patientdetails/{regId}", method = RequestMethod.GET)
	public List<DoctorBean> patientsDetails(@PathVariable("regId")int regId) {
	List list = ddao.getRequiredPatientDetails(regId);
	return list;
	}
	
	
	
	/*//Patient required details  
	
	@RequestMapping(value="/patientdetails/{regId}",headers="Accept=Application/json",method = RequestMethod.GET)
	public DoctorBean patientsDetails(@ModelAttribute("f")DoctorBean f,@PathVariable("regId")int regId) {
	
		DoctorBean fob=ddao.getRequiredPatientDetails(regId,f);
		System.out.println("incorrect");
		ddao.dateConversion(f);
		return fob;
	}*/
	
	/************************************view patient appoint by doctor**********************/
	
	
	@RequestMapping(value="/doctor/{dId}",method=RequestMethod.GET)
	public List getAppointmentDoc(@ModelAttribute("db")DoctorBean db,@PathVariable("dId")int dId/*,@PathVariable("date")String date*/)
	{
		List list=ddao.getAppointment(dId);
		return list;
	}
	
	
	//insert patient comments
	@RequestMapping(value="/api/doctor/docObservation",method= RequestMethod.POST)
	public void getPatientComments(@RequestBody DoctorBean d )
	{
		System.out.println(d.getdId() + d.getRegId() + d.getObsNotes());
		ddao.getPatientComments(d);
		
	}
	

	
	/************** add medicine prescription**************************************************/
	
	
	@RequestMapping(value = "/api/insertpres",method = RequestMethod.POST)
	public void insertDoctorPrescription(@RequestBody DoctorBean doc )
	{
	System.out.println("insert prescr");
	ddao.saveDoctorPrescription(doc);

	}
	
	@RequestMapping(value = "/api/allpreslist/{regId}/{dId}", method = RequestMethod.GET)
	  public List<DoctorBean> getAllPrescription(@PathVariable("regId") int regId ,@PathVariable("dId") int dId )
	  {
	     List presList=ddao.getAllPrescription(regId , dId);
	 return presList;
	  }
	 @RequestMapping(value = "/api/medlists", method = RequestMethod.GET)
	  public List<DoctorBean> getAllMedicineList()
	  {
	     List list=ddao.list();
	 return list;
	  }
	
	
	 /************** add lab prescription**************************************************/
	
	
	//get all test details
	//@ResponseBody
	@RequestMapping(value = "/api/doctor/test", method = RequestMethod.GET)
	public List getAllTest() {

	List testlist = ddao.getAllTest();
	return testlist;
	}

	//insert lab Request
	@RequestMapping(value="/api/doctor/insertlab",method= RequestMethod.POST)
	public void getAddLabRequest(@RequestBody DoctorBean d )
	{
	ddao.getAddLabRequest(d);

	}

	//getall lab prescription
	@RequestMapping(value = "/api/alllabpreslist/{regId}/{dId}", method = RequestMethod.GET)
	 public List<DoctorBean> getAllLabPrescription(@PathVariable("regId") int regId ,@PathVariable("dId") int dId )
	 {
	    List presList=ddao.getAlllabPrescription(regId , dId);
	return presList;
	 }



	//delete lab presc
	@RequestMapping(value="/api/doctor/deletelab/{assignLabId}",headers="Accept=Application/json",method= RequestMethod.DELETE)
	public void deleteLab(@RequestBody DoctorBean d,@PathVariable("assignLabId") int assignLabId )
	{
	ddao.lab_delete(assignLabId);

	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	@ResponseBody
	@RequestMapping(value = "/doctor/medicine", method = RequestMethod.GET)

	public List viewMedicineList() {

		List medicinelist = ddao.viewMedicineList();
		return medicinelist;
	}
	
	@RequestMapping(value="/doctor/{dId}/{date}",method=RequestMethod.GET)
	@ResponseBody
	public List getappointments(@ModelAttribute("db")DoctorBean db,@PathVariable("dId")int dId,@PathVariable("date")String date)
	{
		List list=ddao.getDoctorList(dId,date);
		return list;
	}
	
	
	//assigning lab test
	@RequestMapping(value="/api/assignLab/{labName}",method=RequestMethod.POST)
	public void insertBook(@RequestBody DoctorBean db , @PathVariable("labName") String labName){
		ddao.addLabTestRequest(db, labName);
	}
	
	
	

	//adding patient comments
	@RequestMapping(value="/api/addPatientComment",method=RequestMethod.POST)
	public void insertObservation(@RequestBody DoctorBean doc_bean){
		ddao.addPatientComments(doc_bean);
	}
	
	@ResponseBody
	@RequestMapping(value = "/doctor/viewtest", method = RequestMethod.GET)
	public String viewtest(Model m) {
	List<Doctor>list=dao.getAllTest();
	m.addAttribute("list",list);
	return "viewtest";
		
	}
	
	
	//getting medicine
	@RequestMapping(value="/api/medicine",method=RequestMethod.GET)
	@ResponseBody
	public List<DoctorBean> getAllMedicines(){
		List list=ddao.list();
		return list;
		
		
	}
	//getting lab test
	@RequestMapping(value="/api/getAllLabTest",method=RequestMethod.GET)
	public List<DoctorBean> getAllLabTest(){
	List list=ddao.getLabTestList();
	return list;
	}
	
	
	
//prescription
@RequestMapping(value = "api/insertdoctortwo",method = RequestMethod.POST)
public void insertDoctorPrescription(@RequestBody DoctorBean d[])
{
System.out.println("Insde Doctor prescription");
ddao.saveDoctorPrescription(d);

}*/

}
