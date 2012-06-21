package edu.bcm.dldcc.big.nursa.dkcoin12.example;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import edu.bcm.dldcc.big.nursa.dkcoin12.DkCoinService;
import edu.bcm.dldcc.big.nursa.dkcoin12.exceptions.DkCoinServiceNotRunning;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeCollection;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeCollectionResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeDeleteCollection;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeDeleteResource;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResource;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceSearchResult;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceType;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeResourceTypesResults;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchKeywordParams;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchResourceParams;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSearchResultEntity;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSource;
import edu.bcm.dldcc.big.nursa.dkcoin12.soap.TypeSourcesResults;

public class TestDkCoinService {
	
	private static String name = "name";
	private static String password = "password";
	private static String site = "http://www.dkcoin.org/service/wsdl";
	
	public static void main(String[] args) {
		TestDkCoinService runme = new TestDkCoinService();
		try {

			// Start Session
			runme.startSession();

			
			// Public Tests
			runme.searchKeywordTest();
			runme.searchResourceTest();
			System.out.println("-----------------------------------------");
			runme.getSourcesTest();
			System.out.println("-----------------------------------------");
			runme.getResourceTypesTest();
			System.out.println("-----------------------------------------");
			
			// Private Tests

			System.out.println("-----------------------------------------");
			runme.updateCollectionTest();
			System.out.println("-----------------------------------------");
			runme.deleteCollectionTest();
			System.out.println("-----------------------------------------");
			System.out.println("-----------------------------------------");
			runme.updateResourceTest();
			runme.searchResourceTest();
			System.out.println("-----------------------------------------");
			runme.appendResourceTest();
			runme.searchResourceTest();
			System.out.println("-----------------------------------------");
			runme.deleteResourceTest();
			runme.searchResourceTest();
			System.out.println("-----------------------------------------");

			// Clean Up
			runme.stopSession();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteResourceTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();
		Vector<TypeDeleteResource> collections = new Vector<TypeDeleteResource>();

		TypeDeleteResource resource = new TypeDeleteResource();
		resource.setCollectionName("Jeremy Display mice");
		resource.setName("Jeremy Was Here");
		collections.add(resource);

		List<TypeResourceResult> results = service.deleteResouce(collections);
		for (TypeResourceResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Action: " + result.getAction());
			System.out.println("Internal Id: " + result.getInternalId());
			System.out.println("Collection Name: " + result.getCollectionName());
			List<String> messages = result.getMessages();
			for (String message : messages) {
				System.out.println("\tMessage: " + message);
			}

		}
	}

	private void appendResourceTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();
		Vector<TypeResource> collections = new Vector<TypeResource>();

		TypeResource resource = new TypeResource();
		resource.setName("Jeremy Was Here");
		resource.getGeneId().add(254L);
		resource.setInternalId(1L);
		resource.setResourcetype("mouse");
		resource.setCollectionName("mice should be deleted");
		resource.setDescription("Charlie Sheen ain't got nothing on me");
		collections.add(resource);

		List<TypeResourceResult> results = service.appendResource(collections);

		for (TypeResourceResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Action: " + result.getAction());
			System.out.println("Internal Id: " + result.getInternalId());
			System.out.println("Collection Name: " + result.getCollectionName());
			List<String> messages = result.getMessages();
			for (String message : messages) {
				System.out.println("\tMessage: " + message);
			}

		}
	}

	private void updateResourceTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();
		Vector<TypeResource> collections = new Vector<TypeResource>();

		TypeResource resource = new TypeResource();
		resource.setName("Jeremy Was Here");
		resource.setInternalId(1L);
		resource.setInternalUrl("http://www.awesome.com");
		resource.setResourcetype("mouse");
		resource.setCollectionName("mice should be deleted");
		resource.getGeneId().add(100L);
		resource.setDescription("Charlie Sheen doesn't have anything on me");

		collections.add(resource);

		List<TypeResourceResult> results = service.updateResource(collections);

		for (TypeResourceResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Action: " + result.getAction());
			System.out.println("Internal Id: " + result.getInternalId());
			System.out.println("Collection Name: " + result.getCollectionName());
			List<String> messages = result.getMessages();
			for (String message : messages) {
				System.out.println("\tMessage: " + message);
			}

		}
	}

	private void deleteCollectionTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();
		Vector<TypeDeleteCollection> collections = new Vector<TypeDeleteCollection>();

		TypeDeleteCollection collection = new TypeDeleteCollection();
		collection.setName("mice should be deleted");

		collections.add(collection);

		List<TypeCollectionResult> results = service.deleteCollection(collections);

		for (TypeCollectionResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Action: " + result.getAction());
		}
	}

	private void updateCollectionTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();

		Vector<TypeCollection> collections = new Vector<TypeCollection>();

		TypeCollection collection1 = new TypeCollection();
		collection1.setDisplayname("Jeremy Display mice");
		collection1.setName("mice should be deleted");
		collection1.setUrltemplate("http://jeremy.mice.is.fm");
		collections.add(collection1);

		List<TypeCollectionResult> results = service.updateCollection(collections);

		for (TypeCollectionResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Action: " + result.getAction());
		}
	}

	private void searchKeywordTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();

		Vector<TypeSearchKeywordParams> params = new Vector<TypeSearchKeywordParams>();
		TypeSearchKeywordParams param1 = new TypeSearchKeywordParams();
		param1.setQuery("co");
		params.add(param1);

		List<TypeSearchResultEntity> results = service.searchKeyword(params);

		System.out.println("Returned: " + results.size());

//		for (TypeSearchResultEntity result : results) {
//			System.out.println("*****************************************");
//			System.out.println("Name: " + result.getName());
//			System.out.println("Type: " + result.getType());
//			System.out.println("Description: " + result.getDescription());
//			System.out.println("Rank: " + result.getRank());
//			System.out.println("URL: " + result.getUrl());
//		}
	}

	private void searchResourceTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();

		Vector<TypeSearchResourceParams> params = new Vector<TypeSearchResourceParams>();
		TypeSearchResourceParams param1 = new TypeSearchResourceParams();
		param1.getGeneId().add(16334l);
		params.add(param1);

		List<TypeResourceSearchResult> results = service.searchResource(params);

		System.out.println("Returned: " + results.size());

		HashSet<Long> resourceIdHash = new HashSet<Long>();
		HashSet<Long> collectionIdHash = new HashSet<Long>();

		for (TypeResourceSearchResult result : results) {
			System.out.println("*****************************************");
			System.out.println("Resource ID: " + result.getResourceId());
			resourceIdHash.add(result.getResourceId());
			System.out.println("Collection ID: " + result.getCollectionId());
			collectionIdHash.add(result.getCollectionId());
			System.out.println("Name: " + result.getName());
			System.out.println("Internal ID: " + result.getInternalId());
			System.out.println("Internal URL: " + result.getInternalUrl());
			System.out.println("Created Date: " + result.getCreatedDate());
			System.out.println("Modified Date: " + result.getModifiedDate());
			System.out.println("ResourceType Name: " + result.getResourcetypeName());
			System.out.println("ResourceType Title: " + result.getResourcetypeTitle());
			System.out.println("Collection Name: " + result.getCollectionName());
			System.out.println("Collection Title: " + result.getCollectionTitle());
			System.out.println("Source ID: " + result.getSourceId());
			System.out.println("Source Abbrev: " + result.getSourceAbbrev());
			System.out.println("Source Name: " + result.getSourceName());
			System.out.println("Description: " + result.getDescription());
			System.out.println("Internal Create Date: " + result.getCreatedDate());
		}
	}

	private void getSourcesTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();
		TypeSourcesResults results = service.getSources();
		System.out.println("Results Size: " + results.getSource().size());

		for (int i = 0; i < results.getSource().size(); i++) {
			System.out.println("*****************************************");
			TypeSource source = results.getSource().get(i);
			System.out.println("Name: " + source.getName());
			System.out.println("Abbreviation: " + source.getAbbreviation());
			System.out.println("URL: " + source.getUrl());
		}

	}

	private void getResourceTypesTest() throws DkCoinServiceNotRunning {
		DkCoinService service = DkCoinService.getService();

		TypeResourceTypesResults results = service.getResourcesTypes();

		for (TypeResourceType result : results.getResourceType()) {
			System.out.println("*****************************************");
			System.out.println("Name: " + result.getName());
			System.out.println("Display Name: " + result.getDisplayName());
		}
	}

	private void startSession() {
		DkCoinService service = DkCoinService.getService();
		service.start(name, password);
	}

	private void startRealSession() throws MalformedURLException {
		DkCoinService service = DkCoinService.getService();
		service.start(name, password, site);
	}

	private void stopSession() {
		DkCoinService service = DkCoinService.getService();
		System.out.println(service.stop());
	}
}
