package com.sap.javaspringtrainingpoc.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.javaspringtrainingpoc.models.Restaurant;
import com.sap.javaspringtrainingpoc.models.RestaurantData;
import com.sap.javaspringtrainingpoc.models.User;
import com.sap.javaspringtrainingpoc.models.VoteHistory;
import com.sap.javaspringtrainingpoc.services.RestaurantService;
import com.sap.javaspringtrainingpoc.services.UserService;
import com.sap.javaspringtrainingpoc.services.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * Created by I863409 on 24/07/2017.
 */
@Controller
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    @Resource
    private RestaurantService restaurantService;

    @Resource
    private UserService userService;

    @Resource
    private VoteService voteService;

    @RequestMapping(value = "/")
    public String listRestaurants(Model model, Principal principal) {

        List<Restaurant> restaurants = restaurantService.listRestaurants();
        List<VoteHistory> voteHistoryToday = voteService.getTodayVoteHistory();
        List<RestaurantData> restaurantsDataList = new ArrayList<>();

        restaurantsDataList = voteService.getTodayRestaurantsDataByVotes(restaurants,voteHistoryToday);
        int restaurantUserVotedToday = voteService.getTodayRestaurantIdVotedByUserEmail(voteHistoryToday,principal.getName());


        ObjectMapper mapper = new ObjectMapper();
        JsonNode restaurantDataJSON = mapper.convertValue(restaurantsDataList, JsonNode.class);

        model.addAttribute("restaurants",restaurantsDataList);
        model.addAttribute("restaurantUserVotedToday", restaurantUserVotedToday);

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


    @ResponseBody//in order to return a json to ajax request on the view
    @RequestMapping(value = "/getTodayVoteHistory", method = RequestMethod.GET)
    public  List<VoteHistory> getTodayVoteHistory(){
        List<VoteHistory> voteHistoryList = voteService.getTodayVoteHistory();
        return voteHistoryList;
    }



    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String addVote(@RequestParam("restaurantId") int restaurantId, Principal principal, Model model){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        User user =  userService.getUserByEmail(principal.getName());   //Get logged user email

        List<VoteHistory> voteHistoryToday = voteService.getTodayVoteHistory();
        boolean loggedUserAlreadyVoted = false;

        for (VoteHistory vote : voteHistoryToday) {
            if(vote.getUser().getEmail().equals(principal.getName())){
                loggedUserAlreadyVoted = true;
            }
        }

        if(loggedUserAlreadyVoted){
            try {
                voteService.removeUserVoteToRestaurant(user,restaurant);
            } catch (Exception e) {
                e.printStackTrace();
                //return "Couldn't register the vote";
            }
        }
        else{
            try {
                voteService.addUserVoteToRestaurant(user,restaurant);
            } catch (Exception e) {
                e.printStackTrace();
                //return "Couldn't register the vote";
            }
        }

        //@ResponseBody
        return "redirect:/restaurants/";
    }

    @ResponseBody
    @RequestMapping(value = "/getLoggedUserEmail", method = RequestMethod.POST)
    public String getLoggedUserEmail(Principal principal){
        return principal.getName();
    }


    @RequestMapping(value = "/pollHistory", method = RequestMethod.GET)
    public String pollHistory(Model model){

        model.addAttribute("voteHistory",voteService.getCompleteVoteHistory());
        return "pollhistory";
    }

    @ResponseBody
    @RequestMapping(value = "/searchRestaurantName", method = RequestMethod.POST)
    public List<Restaurant> searchRestaurantName(@RequestParam("nameToSearch") String nameToSearch){
        //Enumeration<String> e = req.getParameterNames();
        List<Restaurant> lista = restaurantService.searchRestaurantsByName(nameToSearch);
        return lista;
    }



}
