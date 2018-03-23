package test;

import java.math.BigDecimal;
import java.util.Date;

import fr.univbrest.dosi.spi.service.EvaluationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Iterables;

import fr.univbrest.dosi.spi.bean.ElementConstitutif;
import fr.univbrest.dosi.spi.bean.ElementConstitutifPK;
import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Evaluation;
import fr.univbrest.dosi.spi.bean.Promotion;
import fr.univbrest.dosi.spi.bean.PromotionPK;
import fr.univbrest.dosi.spi.bean.UniteEnseignement;
import fr.univbrest.dosi.spi.bean.UniteEnseignementPK;
import fr.univbrest.dosi.spi.exception.SPIException;

@RunWith(SpringJUnit4ClassRunner.class)
public class EvaluationServiceTest {

	//@Autowired
	private EvaluationService evaluationService;

	private String designation;
	private long idEvaluation;
	private Date debutReponse;
	private String etat;
	private Date finReponse;
	private BigDecimal noEvaluation;
	private String anneeUniversitaire;
	private String codeFormation;
	private long noEnseignant;
	private String codeUe;
	private String codeEc;

	@Test
	public final void addEvaluation() {
		final Evaluation evaluation = new Evaluation();
		evaluation.setIdEvaluation(1546);
		evaluation.setDebutReponse(new Date());
		evaluation.setDesignation("designation 1");
		ElementConstitutifPK EcPk = new ElementConstitutifPK();
		EcPk.setCodeEc(null);
		EcPk.setCodeFormation(null);
		EcPk.setCodeUe(null);
		ElementConstitutif Ec = new ElementConstitutif();
		Ec.setId(EcPk);
		evaluation.setElementConstitutif(Ec);
		Enseignant ens = new Enseignant();
		ens.setNoEnseignant(7);
		evaluation.setEnseignant(ens);
		evaluation.setFinReponse(new Date());
		Promotion promotion = new Promotion();
		PromotionPK promoPk = new PromotionPK();
		promoPk.setAnneeUniversitaire("2014-2015");
		promoPk.setCodeFormation("M2DOSI");
		promotion.setId(promoPk);
		evaluation.setPromotion(promotion);
		int i = 1;
		noEvaluation  = new BigDecimal(i);
		evaluation.setNoEvaluation(noEvaluation);
		UniteEnseignement ue = new UniteEnseignement();
		UniteEnseignementPK UePk = new UniteEnseignementPK();
		UePk.setCodeFormation("M2DOSI");
		UePk.setCodeUe("PCO");
		ue.setId(UePk);
		evaluation.setUniteEnseignement(ue);
		System.out.println("bonjour");

		try {
			final Evaluation newEvaluation = evaluationService.addEvaluation(evaluation);
			Assert.assertNotNull(newEvaluation.getIdEvaluation());
			Assert.assertEquals(evaluation.getIdEvaluation(), newEvaluation.getIdEvaluation());
			// Assert.fail();

		} catch (final SPIException ex) {
			Assert.assertEquals("l'evaluation que vous souhaitez ajouter exsite déja ", ex.getMessage());
		}

		  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	/*@Test
	public final void deleteEvaluation() throws Exception {
		final long id = 1546;
		try {
			evaluationService.deleteEvaluation(id);
			Assert.fail();
		} catch (final SPIException ex) {
			Assert.assertEquals("Cant delete Evaluation", ex.getMessage());
		}
	}
	@Test
	public void listeEvaluations() {
		final Iterable<Evaluation> listEvaluations = evaluationService.listEvaluations();
		Assert.assertNotNull(listEvaluations);
		Assert.assertEquals(1, Iterables.size(listEvaluations));
	}
	*/
}
