package net.jeffcoat.starships.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping(value = "/filterbyprice", method = RequestMethod.GET)
    public ModelAndView filterByPrice(
    		@RequestParam(value="filterByPriceLow") Float filterByPriceLow,
    		@RequestParam(value="filterByPriceHigh") Float filterByPriceHigh,
    		Model model,
    		RedirectAttributes redir) {
    	System.out.println("Entered filterByPrice");
    	if(filterByPriceLow == null || filterByPriceLow == null){
    		ModelAndView mavm = new ModelAndView("redirect:/");
    		redir.addFlashAttribute("filterError","You must input values for both high and low prices");
    		return mavm;
    	}
    	SWModelList<Starship> ships = service.filterListByPrice(filterByPriceLow,filterByPriceHigh);
    	ModelAndView mav = new ModelAndView("index");
    	mav.addObject("count", ships.count);
    	model.addAttribute("starships", ships.results);
    	mav.addObject("starships", ships.results);
    	System.out.println("Exiting filterByPrice");
    	return mav;
    }
    
}
