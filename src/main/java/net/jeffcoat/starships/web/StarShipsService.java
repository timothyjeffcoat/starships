package net.jeffcoat.starships.web;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.swapi.APIConstants;
import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.models.Starship;


@Service
public class StarShipsService {
	private RestTemplate restTemplate;
	private SWModelList<Starship> cachedShips;
	
	public StarShipsService(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);        
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
        
        restTemplate = new RestTemplate(requestFactory);
		
	}
	
	
	/**
	 * Retrieve all star ships
	 * @return
	 */
	public SWModelList<Starship> listAllShips() {
		int page = 1;
		SWModelList<Starship> all_ships = null;
		if(getCachedShips()==null){
			all_ships = listOfShips(page); 
		}else{
			all_ships = getCachedShips(); 
		}
		
		return all_ships;
	}
	
	/**
	 * Grab all ships located in the Star Wars API 
	 * 
	 * @param page
	 * @return
	 */
	public SWModelList<Starship> listOfShips(int page) {
		System.out.println("Entered listOfShips");
		SWModelList<Starship> list_of_ships = new SWModelList<Starship>();
		try {
	        // get all ships
	        ResponseEntity<String>  json_results = restTemplate.getForEntity(APIConstants.BASE_SHIPS_URL+page, String.class);
	        String json_of_ships = json_results.getBody().toString();
	        
			Type collectionType = new TypeToken<List<Starship>>() {}.getType();

			JsonObject json = new JsonParser().parse(json_of_ships).getAsJsonObject();
			JsonElement count = json.get("count");
			list_of_ships.count = count.getAsInt();
			
			JsonElement next = json.get("next");
			if(next!=null && !next.isJsonNull()){
				String page_number = next.getAsString();
				page_number = page_number.substring(page_number.length()-1, page_number.length());
				list_of_ships.next = page_number;
				
				SWModelList<Starship> more_ships = listOfShips(page+1);
				addShipsToList(list_of_ships, more_ships.results);				
			}
			// convert json to java object
			JsonArray jarr = json.getAsJsonObject().getAsJsonArray("results");
			Gson gson = new Gson();
			List<Starship> navigation = gson.fromJson(jarr, collectionType);
			ArrayList<Starship> ships = new ArrayList<>();
			for(Starship ss : navigation) {
				System.out.println("ship name: " + ss.getName());
				for(String piloturls : ss.getPilotsUrls()){
					System.out.println("pilot url: " + piloturls);
				}
	        	ships.add(ss);
	        }
			
			addShipsToList(list_of_ships, ships);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		if(page==1){
			setCachedShips(list_of_ships);
		}
		System.out.println("Exiting listOfShips");
		return list_of_ships;
	}	
	
	/**
	 * safely adding contents from one array list to another 
	 * 
	 * @param list_of_ships
	 * @param ships
	 */
	private void addShipsToList(SWModelList<Starship> list_of_ships, ArrayList<Starship> ships) {
		if(list_of_ships.results == null){
			list_of_ships.results = ships;
		}else{
			list_of_ships.results.addAll(ships);	
		}
	}
	
	/**
	 * Return the star ship with the given id
	 * 
	 * @param shipId
	 * @return
	 */
	public SWModelList<Starship>  seeIndividualStarship(int shipId) {
		// INDIVIDUAL SHIP
		System.out.println("Entered seeIndividualStarship");
		SWModelList<Starship> found_ship = new SWModelList<Starship>() ; 
		if(getCachedShips()!=null){
			// find ship in array list with given ship id
			for (Starship ship : getCachedShips().results) {
				if (ship.getUrl() != null && ship.getUrl().contains("/" + shipId + "/")) {
					ArrayList<Starship> s = new ArrayList<Starship>();
					s.add(ship);
					found_ship.results = s;
					System.out.println("Exiting seeIndividualStarship with a ship"+ship.getName());
					return found_ship;
				}
			}
		}
		System.out.println("Exiting seeIndividualStarship without a ship");
		return found_ship;
	}
	
	/**
	 * Retrieve pilot for given pilot id
	 * 
	 * @param pilotId
	 * @return
	 */
	public People infoOnPilotByShip(int pilotId) {
        // get individual pilot
		System.out.println("Entered infoOnPilotByShip");
        ResponseEntity<String>  json_results = restTemplate.getForEntity(APIConstants.BASE_PEOPLE_URL+pilotId, String.class);
        String json_of_pilot = json_results.getBody().toString();
        
        Type collectionType = new TypeToken<People>() {}.getType();
        
		// convert json to java object
        JsonObject json = new JsonParser().parse(json_of_pilot).getAsJsonObject();
        
		Gson gson = new Gson();
		People pilot = gson.fromJson(json, collectionType);
        
        System.out.println("name of pilot: "+pilot.getName());
		System.out.println("Pilot: "+json_of_pilot);
		System.out.println("Exiting infoOnPilotByShip");
		return pilot;
	}
	
	/**
	 * Search for ship by name
	 * @param shipName
	 * @return
	 */
	public SWModelList<Starship> searchForStarshipByName(String shipName) {
		System.out.println("Entered searchForStarshipByName");
		SWModelList<Starship> found_ship = new SWModelList<Starship>() ; 
		if(getCachedShips()!=null){
			// find ship in array list with given ship name somewhere in its name
			for (Starship ship : getCachedShips().results) {
				if (ship.getName() != null && ship.getName().contains("" + shipName + "")) {
					ArrayList<Starship> s = new ArrayList<Starship>();
					s.add(ship);
					addShipsToList(found_ship,s);
				}
			}
		}
		if(found_ship.results.size()>0){
			System.out.println("count of searched ships: " + found_ship.results.size());
			found_ship.count = found_ship.results.size(); 
			System.out.println("Exiting searchForStarshipByName with a ship ");
			return found_ship;
			
		}
		System.out.println("Exiting searchForStarshipByName without a ship");
		return found_ship;
	}
	
	/**
	 * Returns a list of star ships filtered by indicated price
	 * 
	 * @param priceToFilterOn
	 * @return
	 */
	public SWModelList<Starship> filterListByPrice(Float priceToFilterLow,Float priceToFilterHigh) {
		System.out.println("Entered filterListByPrice");
		System.out.println("priceToFilterLow " + priceToFilterLow);
		System.out.println("priceToFilterHigh " + priceToFilterHigh);
		SWModelList<Starship> found_ship = new SWModelList<Starship>() ;
		
		if(priceToFilterLow == null || priceToFilterHigh == null){
			return getCachedShips();
		}
		
		if(getCachedShips()!=null){
			// find ship in array list within the price range indicated
			for (Starship ship : getCachedShips().results) {
				if (ship.getCostInCredits() != null && !ship.getCostInCredits().equalsIgnoreCase("unknown")) {
					Float costInCredits = Float.parseFloat(ship.getCostInCredits());
					if(costInCredits >= priceToFilterLow && costInCredits <= priceToFilterHigh){
						ArrayList<Starship> s = new ArrayList<Starship>();
						s.add(ship);
						addShipsToList(found_ship,s);
					}
				}
			}
		}
		
		if(found_ship.results.size()>0){
			System.out.println("count of filtered ships: " + found_ship.results.size());
			found_ship.count = found_ship.results.size(); 
			System.out.println("Exiting filterListByPrice with a ship ");
			return found_ship;
			
		}
		
		System.out.println("Exiting filterListByPrice without a ship");
		return found_ship;
	}	
	
	/**
	 * provide a sorted list of starships based on price
	 * @return
	 */
	public SWModelList<Starship> sortListByPrice(boolean asc) {
		
		ArrayList<Starship> ships = getCachedShips().results;
		
		Comparator<Starship> comparator = new Comparator<Starship>() {
			@Override
			public int compare(Starship e1, Starship e2) {
				Float costInCreditsE1 = 0F;
				Float costInCreditE2 = 0F;
				if (e1.getCostInCredits() != null && !e1.getCostInCredits().equalsIgnoreCase("unknown")) {
					costInCreditsE1 = Float.parseFloat(e1.getCostInCredits());
				}
				if (e2.getCostInCredits() != null && !e2.getCostInCredits().equalsIgnoreCase("unknown")) {
					costInCreditE2 = Float.parseFloat(e2.getCostInCredits());
				}
				return costInCreditsE1.compareTo(costInCreditE2);

			}
		};
		Comparator<Starship> comparatorRev = new Comparator<Starship>() {
			@Override
			public int compare(Starship e1, Starship e2) {
				Float costInCreditsE1 = 0F;
				Float costInCreditE2 = 0F;
				if (e1.getCostInCredits() != null && !e1.getCostInCredits().equalsIgnoreCase("unknown")) {
					costInCreditsE1 = Float.parseFloat(e1.getCostInCredits());
				}
				if (e2.getCostInCredits() != null && !e2.getCostInCredits().equalsIgnoreCase("unknown")) {
					costInCreditE2 = Float.parseFloat(e2.getCostInCredits());
				}
				return costInCreditE2.compareTo(costInCreditsE1);

			}
		};
		if(asc){
			Collections.sort(ships,comparator);
		}else{
			Collections.sort(ships,comparatorRev);
		}
		return getCachedShips();
	}	
	
	
	
	
	
	
	
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public SWModelList<Starship> getCachedShips() {
		return cachedShips;
	}

	public void setCachedShips(SWModelList<Starship> cachedShips) {
		this.cachedShips = cachedShips;
	}
	
}
