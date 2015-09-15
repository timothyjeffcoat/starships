package net.jeffcoat.starships.web;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
