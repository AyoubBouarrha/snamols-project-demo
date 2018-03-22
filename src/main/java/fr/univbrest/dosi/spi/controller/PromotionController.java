package fr.univbrest.dosi.spi.controller;

import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;


    @RequestMapping(method = RequestMethod.GET , value = "/count")
    public long count()
    {
        return  promotionService.count();
    }


    @RequestMapping(method = RequestMethod.GET , value = "/getAllAnneeUniv")
    public List<String> getAllAnneeUniv()
    {
        List<Promotion> listPromotions = promotionService.getPromotions();
        List<String> listAnneeUniv = new ArrayList<String>();
        for (Promotion promotion : listPromotions)
            if(!listAnneeUniv.contains(promotion.getId().getAnneeUniversitaire()))
                listAnneeUniv.add(promotion.getId().getAnneeUniversitaire());

        return listAnneeUniv;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/getCodeFormationsByAnneUniv/{anneeUniv}")
    public List<String> getCodeFormationsByAnneUniv(@PathVariable(value = "anneeUniv") String anneeUniv){
        List<Promotion> listPromotions = promotionService.getPromotions();
        List<String> listCodeFormations = new ArrayList<String>();
        for(Promotion promotion : listPromotions)
            if(promotion.getId().getAnneeUniversitaire().equals(anneeUniv))
                listCodeFormations.add(promotion.getId().getCodeFormation());

        return listCodeFormations;
    }



}
