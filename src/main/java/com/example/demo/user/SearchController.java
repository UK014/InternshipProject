package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.Integer;
@Controller
public class SearchController {
    @Autowired
    private final PassengerRepository passengerRepository;
    @Autowired
    private final ReservationsRepository reservationsRepository;
    @Autowired
    private final FlightService flightService;
    @Autowired
    private final PassengerService passengerService;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final FlightsRepository flightsRepository;
    @Autowired
    private final pnrRepository pnrRepository;

    public SearchController(PassengerRepository passengerRepository, ReservationsRepository reservationsRepository, FlightService flightService, PassengerService passengerService, UserRepository userRepository, FlightsRepository flightsRepository, com.example.demo.user.pnrRepository pnrRepository) {
        this.passengerRepository = passengerRepository;
        this.reservationsRepository = reservationsRepository;
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.userRepository = userRepository;
        this.flightsRepository = flightsRepository;
        this.pnrRepository = pnrRepository;
    }
    Flight flight = new Flight();
    Flight flight1 = new Flight();
    Passenger passenger = new Passenger();
    Reservations reservations = new Reservations();
    User user = new User();
    pnr pnr = new pnr();
    count passcount = new count();
    String[] pnrs = new String[10];
    List<Passenger> passengers = new ArrayList<>();

    @PostMapping("/AvailFlights")
    public String login(@RequestParam(value = "oneway",required = false) String oneway, @RequestParam(value = "roundtrip",required = false) String roundtrip, @RequestParam("Departure") String departure, @RequestParam("Arrival") String arrival, @RequestParam("depdate") String depdate, @RequestParam(value = "retdate",required = false) String retdate, @RequestParam("adultcount") Integer adultcount, @RequestParam("childcount") Integer childcount, @RequestParam("infantcount") Integer infantcount, Model model){
        Integer TotalPassenger = adultcount + childcount + infantcount;
        passcount.totalcount = TotalPassenger;
        passcount.setTotalcount(TotalPassenger);
        pnr.setRetdate(retdate);
        pnr.setDepdate(depdate);
        try {
            Thread.sleep(2000);
            Integer BusinessCost = flightService.findFlight(departure,arrival).getBusiness();
            Integer EconomyCost = flightService.findFlight(departure,arrival).getEconomy();
            flight.setBusiness(BusinessCost);
            flight.setEconomy(EconomyCost);
            flight.setDeparture(flightService.findFlight(departure,arrival).getDeparture());
            flight.setArrival(flightService.findFlight(departure,arrival).getArrival());
            flight.setDate(flightService.findFlight(departure,arrival).getDate());
            flight.setFlightno(flightService.findFlight(departure,arrival).getFlightno());
            flight.setFlighttime(flightService.findFlight(departure,arrival).getFlighttime());
            List<Flight> flights = flightsRepository.findFlightsByArrivalAndDeparture(arrival, departure);
            if (!flights.isEmpty()) {
                flight1.setFlightno(flights.get(0).getFlightno());
                flight1.setFlighttime(flights.get(0).getFlighttime());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/AvailFlights";
    }
    @GetMapping(path = "/AvailFlights")
    public String displayCost(Model model){
        Integer bcost = flight.getBusiness();
        Integer ecost= flight.getEconomy();
        String dep = flight.getDeparture();
        String arr = flight.getArrival();
        model.addAttribute("bcost", bcost*passcount.getTotalcount());
        model.addAttribute("ecost", ecost*passcount.getTotalcount());
        model.addAttribute("dep",dep);
        model.addAttribute("arr",arr);
        model.addAttribute("date",pnr.getDepdate());
        model.addAttribute("retdate",pnr.getRetdate());
        model.addAttribute("flightno1",flight.getFlightno());
        model.addAttribute("flightno2",flight1.getFlightno());
        model.addAttribute("flighttime1",flight.getFlighttime());
        model.addAttribute("flighttime2",flight1.getFlighttime());
        return "AvailFlights";
    }
    @GetMapping(path = "/Payment")
    public String displayInfo(Model model){
        Integer j = 0;
        for(;j < passcount.getTotalcount();j++) {
            model.addAttribute("passengers", passengers);
            model.addAttribute("totalPassenger", passcount.getTotalcount());
            model.addAttribute("pnr", pnr.getPnr());
            model.addAttribute("dep", flight.getDeparture());
            model.addAttribute("arr", flight.getArrival());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");
            String formatteddepdate = outputFormat.format(flight.getDate());
            model.addAttribute("depdate", formatteddepdate);
            model.addAttribute("name",passengers.get(j).getName());
            model.addAttribute("sname", passengers.get(j).getSurname());
            model.addAttribute("gender",passengers.get(j).getGender());
            model.addAttribute("cost", reservations.getCost());
            model.addAttribute("flighttime1",flight.getFlighttime());
            model.addAttribute("flighttime2",flight1.getFlighttime());
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("tr", "TR"));
            LocalDate date = LocalDate.parse(pnr.getRetdate(), inputFormatter);
            String outputDate = date.format(outputFormatter);
            model.addAttribute("arrdate",outputDate);
        }
        return "Payment";
    }
    @GetMapping(path = "/ReservationInfo")
    public String Displayresinfo(Model model){
            model.addAttribute("passengers", passengers);
            model.addAttribute("pnr",pnr.getPnr());
            model.addAttribute("dep",flight.getDeparture());
            model.addAttribute("PNR",reservations.getPNR());
            model.addAttribute("arr",flight.getArrival());
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");
            String formatteddepdate = outputFormat.format(flight.getDate());
            model.addAttribute("depdate",formatteddepdate);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("tr", "TR"));
            LocalDate date = LocalDate.parse(pnr.getRetdate(), inputFormatter);
            String outputDate = date.format(outputFormatter);
            model.addAttribute("arrdate",outputDate);
            model.addAttribute("flightno1",flight.getFlightno());
            model.addAttribute("flightno2",flight1.getFlightno());
            model.addAttribute("flighttime1",flight.getFlighttime());
            model.addAttribute("flighttime2",flight1.getFlighttime());

        return "ReservationInfo";
    }
    @PostMapping(path = "Cancel")
    public String CancelFlight(Model model){
        reservations.setStatus("canceled");
        reservationsRepository.save(reservations);
        return "SelectPorts";
    }
    @PostMapping(path = "/ReservationInfo")
    public String ReservationInfo(Model model){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        reservations.setStatus("ticketed");
        return "redirect:/ReservationInfo";
    }
    @PostMapping(path = "/PassengerInfo")
    public String PassengerInfo(@RequestParam(value = "business",required = false) String business, @RequestParam(value = "economy",required = false) String economy,@RequestParam(value = "business2",required = false) String business2, @RequestParam(value = "economy2",required = false) String economy2, Model model){

        model.addAttribute("totalPassenger",passcount.getTotalcount());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (business!= null && business.equals("on") && business2!= null && business2.equals("on")){
            reservations.setCost((flight.getBusiness()+flight.getBusiness())*passcount.getTotalcount());
        }
        else if(business!= null && business.equals("on") && economy2!= null && economy2.equals("on")){
            reservations.setCost((flight.getBusiness()+flight.getEconomy())*passcount.getTotalcount());
        }
        else if(business2!= null && business2.equals("on") && economy!= null && economy.equals("on")){
            reservations.setCost((flight.getBusiness()+flight.getEconomy())*passcount.getTotalcount());
        }
        else {
            reservations.setCost((flight.getEconomy() + flight.getEconomy())*passcount.getTotalcount());
        }

        reservationsRepository.save(reservations);
        return "PassengerInfo";
    }
    @PostMapping(path = "/Payment")
    public String SavePassengerInfo(@RequestParam("name") String[] name, @RequestParam("surname") String[] surname, @RequestParam("passport") String[] passport, @RequestParam("birthDate") String[] birthDateStr, @RequestParam("SSN") String[] ssn, @RequestParam("phone") String[] phone, @RequestParam("email") String[] email, @RequestParam("gender") String[] gender, Model model) {
        Integer i = 0;
        model.addAttribute("totalPassenger",passcount.getTotalcount());
        String p = generateRandomText();
        for(;i < name.length;i++){
            pnr pnr1 = new pnr();
            Passenger passenger = new Passenger();
            passenger.setName(name[i]);
            passenger.setSurname(surname[i]);
            passenger.setPassportno(passport[i]);
            passenger.setSSN(ssn[i]);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String modifiedString = birthDateStr[i];
            modifiedString = modifiedString.replace("TRT","").replace(" 00:00:00","");
            passenger.setBirthdate(modifiedString);

            passenger.setPNR(p);
            pnrs[i] = p;
            flight.setPNR(p);
            passenger.setPhone(phone[i]);
            passenger.setEmail(email[i]);
            passenger.setGender(gender[i]);
            passengers.add(passenger);
            passengerRepository.save(passenger);
            reservations.setStatus("ticketed");
            reservations.setPNR(p);
            flightsRepository.updatePNRByArrivalAndDeparture(flight.getArrival(),flight.getDeparture(),flight.getPNR());
            reservationsRepository.save(reservations);
            pnr1.setArrival(flight.getArrival());
            pnr1.setDeparture(flight.getDeparture());
            pnr1.setDepdate(pnr.getDepdate());
            pnr1.setName(passenger.getName());
            pnr1.setSurname(passenger.getSurname());
            pnr1.setBirthdate(passenger.getBirthdate());
            pnr1.setGender(passenger.getGender());
            pnr1.setPnr(p);
            pnrRepository.save(pnr1);
            pnr.setPnr(p);
        }

        return "redirect:/Payment";
    }
    @PostMapping(path = "/SearchInfo")
    public String SearchInfo(@RequestParam("search") String search,Model model){
        List<pnr> foundPnrs = pnrRepository.findAllByPnr(search);
        for (int i = 0; i< foundPnrs.size();i++){
            pnr.setName(foundPnrs.get(i).getName());
            pnr.setSurname(foundPnrs.get(i).getSurname());
            pnr.setGender(foundPnrs.get(i).getGender());
            pnr.setBirthdate(String.valueOf(foundPnrs.get(i).getBirthdate()));
            pnr.setDepdate(foundPnrs.get(i).getDepdate());
            pnr.setPnr(foundPnrs.get(i).getPnr());
            pnr.setDeparture(foundPnrs.get(i).getDeparture());
            pnr.setArrival(foundPnrs.get(i).getArrival());
        }
        return "redirect:/ReservationInfo";
    }
    @PostMapping(path = "/userAdd")
    public String addUser(@RequestParam("username") String name, @RequestParam("password") String password,@RequestParam("passwordagain") String passwordagain,Model model){
        user.setUsername(name);
        if(password.equals(passwordagain)){
            user.setPassword(password);
            userRepository.save(user);
        }
        else{
            String errorMessage = "Invalid username or password. Please try again.";
            model.addAttribute("error", "Passwords are not the same");
        }

        return "UserManagement";
    }
    @PostMapping(path = "/userdelete")
    public String deleteUser(@RequestParam("deleteusername") String name, Model model){

        userRepository.delete(name);

        return "UserManagement";
    }
    @GetMapping("/PnrSearch.html")
    public String showPnrSearchPage() {
        return "PnrSearch";
    }
    @GetMapping("/SelectPorts")
    public String showSelectPortsPage() {
        return "SelectPorts";
    }
    @GetMapping("/UserManagement.html")
    public String showUserManagementPage() {
        return "UserManagement";
    }
    public static String generateRandomText() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomText = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int index = new Random().nextInt(characters.length());
            randomText.append(characters.charAt(index));
        }

        return randomText.toString();
    }

}
