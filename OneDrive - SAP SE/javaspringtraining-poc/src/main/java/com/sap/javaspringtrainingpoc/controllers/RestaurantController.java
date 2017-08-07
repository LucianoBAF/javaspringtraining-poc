package com.sap.javaspringtrainingpoc.controllers;

import com.sap.javaspringtrainingpoc.daos.RestaurantDao;
import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.services.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by I863409 on 24/07/2017.
 */
@Controller
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Resource
    private RestaurantService restaurantService;


    @RequestMapping(value = "/")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.listRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "list-restaurants";
    }

    @RequestMapping(value = "/add-restaurant")
    public String createRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "add-restaurant";
    }

    @RequestMapping(value = "/add-restaurant", method = RequestMethod.POST)
    public String createRestaurant(@Valid Restaurant restaurant,BindingResult bindingResult,RedirectAttributes redirectAttributes, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            return "add-restaurant";
        }

        if(restaurantService.restaurantExists(restaurant.getId())){
            restaurantService.updateRestaurant(restaurant);
        }
        else {
            restaurantService.saveRestaurant(restaurant);
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/restaurants/";
    }

    @RequestMapping(value = "/update-restaurant", method = RequestMethod.GET)
    public String updateRestaurant(@RequestParam("restaurantId") int restaurantId, Model model){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        model.addAttribute("restaurant",restaurant);

        return "add-restaurant";
    }

    @RequestMapping(value = "/delete-restaurant", method = RequestMethod.GET)
    public String deleteRestaurant(@RequestParam("restaurantId") int restaurantId){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        restaurantService.deleteRestaurant(restaurantId);

        return "redirect:/restaurants/";
    }
}
