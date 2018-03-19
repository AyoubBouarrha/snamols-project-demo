package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.univbrest.dosi.spi.bean.Rubrique;
import fr.univbrest.dosi.spi.service.RubriqueService;
import junit.framework.Assert;

public class RubriqueControllerTest {
	private RubriqueService rubriqueService;

	private final Rubrique rubrique = new Rubrique();

	@Test
	public void addserviceTest() throws ClientProtocolException, IOException {

		final Rubrique rubrique = new Rubrique();
		rubrique.setIdRubrique(1986);
		rubrique.setDesignation("COURS");
		int i = 59;

		BigDecimal ordre = new BigDecimal(i);
		rubrique.setOrdre(ordre);
		rubrique.setType("RBS");
		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost mockRequestPost = new HttpPost("http://localhost:8090/rubrique");
		final ObjectMapper mapper = new ObjectMapper();
		final com.fasterxml.jackson.databind.ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		final String jsonInString = ow.writeValueAsString(rubrique);
		mockRequestPost.addHeader("content-type", "application/json");
		mockRequestPost.setEntity(new StringEntity(jsonInString));


		final HttpResponse mockResponse = client.execute(mockRequestPost);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

	}

	/*
	 * public void makeHTTPPOSTRequest() { try { HttpClient c = new DefaultHttpClient(); HttpPost p = new HttpPost(this.apiURL);
	 *
	 * p.setEntity(new StringEntity("{\"username\":\"" + this.apiusername + "\",\"password\":\"" + this.apipassword + "\"}", ContentType.create("application/json")));
	 *
	 * HttpResponse r = c.execute(p);
	 *
	 * BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent())); String line = ""; while ((line = rd.readLine()) != null) { //Parse our JSON response JSONParser j =
	 * new JSONParser(); JSONObject o = (JSONObject)j.parse(line); Map response = (Map)o.get("response");
	 *
	 * System.out.println(response.get("somevalue")); } } catch(ParseException e) { System.out.println(e); } catch(IOException e) { System.out.println(e); } }
	 */

	@Test
	public final void getEnseignantTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/rub/1");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		// Enseignant ens = mapper.readValue(rd, Enseignant.class);

		// Assert.assertNotNull(ens);

	}

	@Test
	public final void listEnseignantTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/rub");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<Rubrique> rub = mapper.readValue(rd, Iterable.class);

		Assert.assertNotNull(rub);

	}



	@Test
	public final void deleteRubriqueTest() throws ClientProtocolException, IOException {

		// Création du client et éxécution d'une requete GET
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet mockRequest = new HttpGet("http://localhost:8090/deleteRubrique/3");
		final HttpResponse mockResponse = client.execute(mockRequest);

		// Le code retour HTTP doit être un succès (200)
		Assert.assertEquals(200, mockResponse.getStatusLine().getStatusCode());

		final BufferedReader rd = new BufferedReader(new InputStreamReader(mockResponse.getEntity().getContent()));
		final ObjectMapper mapper = new ObjectMapper();
		final Iterable<Rubrique> rub = mapper.readValue(rd, Iterable.class);

		Assert.assertNotNull(rub);

	}
}
