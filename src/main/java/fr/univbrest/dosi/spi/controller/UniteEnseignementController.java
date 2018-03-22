package fr.univbrest.dosi.spi.controller;

import fr.univbrest.dosi.spi.bean.Formation;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.service.UniteEnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ue")
public class UniteEnseignementController {
    @Autowired
    private UniteEnseignementService uniteEnseignementService;


    @RequestMapping(method = RequestMethod.GET , value = "/formation/{codeFormation}")
    public List<UniteEnseignement> getAllUEByFormation(@PathVariable(value = "codeFormation") String codeFormation)
    {
        return uniteEnseignementService.getUniteEnseignementsByFormation(new Formation(codeFormation));
    }
}
