package net.jeffcoat.starships.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.models.Starship;

@Controller
public class IndexController {
	@Autowired
	public StarShipsService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
    	System.out.println("Entered index ");
    	String filterError = (String) model.asMap().get("filterError");
    	SWModelList<Starship> json = service.listAllShips();
    	ModelAndView mav = new ModelAndView("index");
    	mav.addObject("count", json.count);
    	mav.addObject("starships", json.results);
    	mav.addObject("asc", true);
    	mav.addObject("filterError",filterError);
    	return mav;
    }

    @RequestMapping(value = "/viewss", method = RequestMethod.GET)
    public ModelAndView viewStarShip(@RequestParam(value="id") int id,Model model) {
    	System.out.println("Entered viewStarShip");
    	System.out.println("id: " +id);
    	SWModelList<Starship> json = service.seeIndividualStarship(id);
    	ModelAndView mav = new ModelAndView("starship");
    	mav.addObject("starships", json.results);
    	model.addAttribute("starships", json.results);
    	System.out.println("Exiting viewStarShip");
    	return mav;
    }
    
    @RequestMapping(value = "/viewpilots", method = RequestMethod.GET)
    public ModelAndView viewPilots(@RequestParam(value="id") int id,Model model) {
    	System.out.println("Entered viewPilots");
    	People pilot = service.infoOnPilotByShip(id);
    	ModelAndView mav = new ModelAndView("pilot");
    	model.addAttribute("pilot", pilot);
    	mav.addObject("pilot", pilot);
    	return mav;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchShips(@RequestParam(value="name") String shipName,Model model) {
    	System.out.println("Entered searchShips");
    	SWModelList<Starship> ships = service.searchForStarshipByName(shipName);
    	ModelAndView mav = new ModelAndView("index");
    	mav.addObject("count", ships.count);
    	model.addAttribute("starships", ships.results);
    	mav.addObject("starships", ships.results);
    	System.out.println("Exiting searchShips");
    	return mav;
    }    
}
