package net.jeffcoat.starships;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

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
	}

}
