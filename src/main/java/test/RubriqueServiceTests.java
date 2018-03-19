package test;

import java.math.BigDecimal;

import fr.univbrest.dosi.spi.service.RubriqueService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Iterables;

import fr.univbrest.dosi.spi.bean.Enseignant;
import fr.univbrest.dosi.spi.bean.Rubrique;

/**
 * @author DOSI
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RubriqueServiceTests {

	@Autowired
	private RubriqueService rubriqueService;

	private long idRubrique;
	/**
	 *
	 */
	private String designation;
	/**
	 *
	 */
	private String type;
	private BigDecimal ordre;

	/**
	 *
	 */
	@Test
	public final void addRubrique() {
		final Rubrique rubrique = new Rubrique();
		rubrique.setIdRubrique(idRubrique);
		rubrique.setDesignation(designation);
		rubrique.setOrdre(ordre);
		rubrique.setType(type);

		try {
			final Rubrique newRubrique = rubriqueService.addRubrique(rubrique);
			Assert.assertNotNull(newRubrique.getIdRubrique());
			// Assert.fail();
		} catch (final Exception ex) {
			Assert.assertEquals("la Rubrique que vous souhaitez ajouter exsite déja ", ex.getMessage());
		}

	}


	@Test
	public final void deleteRubrique() {
		final long id = 1995;
		try {
			rubriqueService.deleteRubrique(id);
			Assert.fail();
		} catch (final Exception ex) {
			Assert.assertEquals("Cant delete Rubrique", ex.getMessage());
		}
	}


	@Test
	public final void getRubrique() {
		final Rubrique rubrique = rubriqueService.getRubrique(this.idRubrique);
		Assert.assertNotNull(rubrique);
		Assert.assertEquals(this.designation, rubrique.getDesignation());
	}

	@Test
	public final void getRubriqueNotExiste() {
		final Rubrique rubrique = rubriqueService.getRubrique(1995);
		Assert.assertNotNull(rubrique);
		Assert.assertEquals(this.designation, rubrique.getDesignation());
		Assert.assertEquals(this.ordre, rubrique.getOrdre());
	}

	/**
	 *
	 */
	@Before
	public final void init() {
		// this.business = new GreetingBusinessImpl();
		this.idRubrique = 1995;
		this.designation = "COURS";
		int i = 96;

		BigDecimal ordre = new BigDecimal(i);
		this.ordre = ordre;
	}


}
