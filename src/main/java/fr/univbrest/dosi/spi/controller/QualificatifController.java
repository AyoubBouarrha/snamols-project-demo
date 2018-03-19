package fr.univbrest.dosi.spi.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.univbrest.dosi.spi.bean.Qualificatif;
import fr.univbrest.dosi.spi.service.QualificatifService;

@RestController
@RequestMapping("/qualificatifs")
public class QualificatifController {

	@Autowired
	private QualificatifService qualificatifService;

	@RequestMapping(method = RequestMethod.POST)
	public boolean addQualificatif(@RequestBody final Qualificatif qualificatif) {
		try {
			qualificatifService.addQualificatif(qualificatif);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "/{idqualificatif}", method = RequestMethod.DELETE)
	public boolean deleteQualificatif(@PathVariable(value = "idqualificatif") final long idqualificatif) {
		try {
			qualificatifService.deleteQualificatif(idqualificatif);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public boolean updateQualificatif(@RequestBody Qualificatif qualificatif) {
		try {
			qualificatifService.updateQualificatif(qualificatif);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Qualificatif> qualificatif() {
		return qualificatifService.listqual();
	}
	

	@RequestMapping(value = "/{idqualificatif}")
	public Qualificatif getQualificatif(@PathVariable(value = "idqualificatif") final long idQualificatif) {
		return qualificatifService.getQualificatif(idQualificatif);
	}

	@RequestMapping(value = "/existqual/{idqualificatif}")
	public Boolean existRubrique(@PathVariable(value = "idqualificatif") final long idQualificatif) {
		try {
			return qualificatifService.existQualificatif(idQualificatif);
		} catch (Exception e) {
			return false;
		}
	}


	public QualificatifService getQualificatifService() {
		return qualificatifService;
	}

}
