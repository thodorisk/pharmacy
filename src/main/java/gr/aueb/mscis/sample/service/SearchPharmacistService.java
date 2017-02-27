package gr.aueb.mscis.sample.service;

import gr.aueb.mscis.sample.model.Pharmacist;

import java.util.List;

public class SearchPharmacistService {

    private PharmacistService ps;

    public SearchPharmacistService(PharmacistService ps) {
        this.ps = ps;
    }

    public void searchAllPharmacist() {
        List<Pharmacist> allPharmacists = ps.findAllPharmacists();
        for (Pharmacist p : allPharmacists) {
            showPharmacistDetails(p);
        }
    }

    private void showPharmacistDetails(Pharmacist pharmacist) {
        System.out.println("Pharmacist firstName: " + pharmacist.getFirstName());
        System.out.println("Pharmacist lastName: " + pharmacist.getLastName());
        System.out.println("Pharmacist Vat Nubmer: " + pharmacist.getPerson().getVatNo());
        System.out.println("-----------------------------------------------");

    }
}
