package net.jeffcoat.starships;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.models.Starship;

import net.jeffcoat.starships.StarshipsApplication;
import net.jeffcoat.starships.web.StarShipsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StarshipsApplication.class)
@WebAppConfiguration
public class StarshipsApplicationTests {
	
	@Autowired
	StarShipsService service;
	
	@Test
	public void contextLoads() {
		SWModelList<Starship> ships = service.listAllShips();
		assertEquals(37,ships.count);		
		
		ships = service.seeIndividualStarship(5);
		assertEquals(1,ships.results.size());

    	People pilot = service.infoOnPilotByShip(13);
    	assertEquals("Chewbacca",pilot.getName());

    	ships = service.searchForStarshipByName("Sentinel-class landing craft");
    	assertEquals("Sentinel-class landing craft",ships.results.get(0).getName());
    	
    	ships = service.filterListByPrice(300000F,500000F);
    	assertEquals(1,ships.results.size());

    	ships = service.sortListByPrice(false);
    	assertEquals("1000000000000",ships.results.get(0).getCostInCredits());
    	
	}

}
