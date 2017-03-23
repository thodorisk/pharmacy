package gr.aueb.mscis.sample.resource;


import gr.aueb.mscis.sample.model.Pharmacist;
import gr.aueb.mscis.sample.service.PharmacistService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author      Thodoris Karagiannis
 * @author      Dionisis Koropoulis
 * @author      Tereza Kaparakou
 * @version     3.0 (current version)
 *
 */

/**
 * Class PharmacistInfo is a utility class for JAX-RS implementation.
 */

@XmlRootElement
public class PharmacistInfo {

	private Integer pharmacistNo;
	private String firstName;
	private String lastName;

	private String phone;
	private String vatNo;

	public PharmacistInfo() {

	}

	public PharmacistInfo(Integer pharmacistNo, String firstName, String lastName, String phone, String vatNo) {
		this(firstName, lastName, phone, vatNo);
		this.pharmacistNo = pharmacistNo;
	}

	public PharmacistInfo(String firstName, String lastName, String phone, String vatNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;

		this.phone = phone;
		this.vatNo = vatNo;
	}

	public PharmacistInfo(Pharmacist pharmacist) {
		pharmacistNo = pharmacist.getPharmacistNo();
		firstName = pharmacist.getPerson().getFirstName();
		lastName = pharmacist.getPerson().getLastName();

		phone = pharmacist.getPerson().getPhone();
		vatNo = pharmacist.getPerson().getVatNo();
	}

	public int getPharmacistNo() {
		return pharmacistNo;
	}

	public void setPharmacistNo(int pharmacistNo) {
		this.pharmacistNo = pharmacistNo;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {return phone;}

	public void setPhone (String phone) {this.phone = phone;}

	public String getVatNo() {return vatNo;}

	public void setVatNo(String vatNo) {this.vatNo = vatNo;}

	public static PharmacistInfo wrap(Pharmacist p) {
		return new PharmacistInfo(p);
	}

	public static List<PharmacistInfo> wrap(List<Pharmacist> pharmacists) {

		List<PharmacistInfo> pharmacistInfoList = new ArrayList<>();

		for (Pharmacist ph : pharmacists) {
			pharmacistInfoList.add(new PharmacistInfo(ph));
		}

		return pharmacistInfoList;

	}

	public Pharmacist getPharmacist(EntityManager em) {

		Pharmacist pharmacist = null;
		PharmacistService ps = new PharmacistService(em);
		if (vatNo != null) {
			pharmacist = ps.findPharmacistsByAFM(vatNo).get(0);
		} else {
			pharmacist = new Pharmacist();
		}

		pharmacist.getPerson().setFirstName(firstName);
		pharmacist.getPerson().setLastName(lastName);
		pharmacist.getPerson().setPhone(phone);
		pharmacist.getPerson().setVatNo(vatNo);


		if (pharmacist.getPerson().getVatNo() == null || !pharmacist.getPerson().getVatNo().equals(vatNo)) {
			pharmacist.getPerson().setVatNo(vatNo);
		}

		return pharmacist;
	}
}
