package com.hammertime.hammertime2.database;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import com.hammertime.hammertime2.domain.client.Client;
import com.hammertime.hammertime2.domain.client.ClientRepository;
import com.hammertime.hammertime2.domain.job.Job;
import com.hammertime.hammertime2.domain.job.JobRepository;
import com.hammertime.hammertime2.domain.jobApplication.JobApplication;
import com.hammertime.hammertime2.domain.jobApplication.JobApplicationRepository;
import com.hammertime.hammertime2.domain.professional.Professional;
import com.hammertime.hammertime2.domain.professional.ProfessionalRepository;
import com.hammertime.hammertime2.domain.rating.Rating;
import com.hammertime.hammertime2.domain.rating.RatingRepository;
import com.hammertime.hammertime2.domain.transaction.TransactionRepository;
import com.hammertime.hammertime2.service.IBackendService;

import net.datafaker.Faker;

@Configuration
class LoadDatabase {
    
    @Autowired
	IBackendService backendService;

    public Long getRandomId(String url, RestTemplate restTemplate){
        Random random = new Random();
        Long[] ids = restTemplate.getForObject(url, Long[].class);
        Integer randMax = ids.length - 1;
        Long rand;
        if (randMax > 0) {
            rand = ids[random.nextInt(randMax)];
        } else {
            rand = ids[randMax];
        }
        return rand;
    }
    
    public Long getRandomId(List<Long> list){
        Random random = new Random();
        Integer randMax = list.size();
        Long rand;
        rand = list.get(random.nextInt(randMax));
        return rand;
    }

    @Profile("!test")
    @Bean
    CommandLineRunner initDatabase(
            ClientRepository cRepository, 
            ProfessionalRepository pRepository, 
            RatingRepository rRepository, 
            JobRepository jRepository, 
            TransactionRepository tRepository, 
            JobApplicationRepository jaRepository,
                RestTemplateBuilder restTemplateBuilder) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        Logger log = LoggerFactory.getLogger(LoadDatabase.class);
        Random random = new Random();
        Faker faker = new Faker();
        
        return args -> {
            //Creates a new Client and logs it.
            log.info("Preloading " + cRepository.save(new Client("Demo", "Dan", "Wollongong", "+61999999999", "client@ht.com", "client")));

            log.info("Preloading " + cRepository.save(new Client("Andreas", "Sukardi Teja", "Wollongong", "+61999999999", "ast678@uowmail.edu.au", "password")));
            log.info("Preloading " + cRepository.save(new Client("Captain", "Jack Sparrow", "Tortuga", "+61696969696", "captainjack@gmail.com", "avastye")));
            log.info("Preloading " + cRepository.save(new Client("Barack", "O'Bama", "Washington DC", "+61987654321", "bobama@whitehouse.gov", "president123")));
            log.info("Preloading " + cRepository.save(new Client("Harry", "Potter", "Hogwarts", "+61123456789", "hpotter@hogwarts.edu", "wingardiumleviosa")));
            log.info("Preloading " + cRepository.save(new Client("Homer", "Simpson", "Springfield", "+61888888888", "hsimpson@springfield.com", "doh123")));
            log.info("Preloading " + cRepository.save(new Client("Tony", "Stark", "New York", "+61444444444", "ironman@starkindustries.com", "iloveyou3000")));
            log.info("Preloading " + cRepository.save(new Client("Donald", "Duck", "Duckburg", "+61777777777", "dduck@disney.com", "quackquack")));
            log.info("Preloading " + cRepository.save(new Client("Willy", "Wonka", "Chocolate Factory", "+61666666666", "wwonka@chocolatefactory.com", "goldenTicket1")));
            log.info("Preloading " + cRepository.save(new Client("Bugs", "Bunny", "Looney Tunes", "+61432109876", "bbunny@looneytunes.com", "whatsupdoc")));
            log.info("Preloading " + cRepository.save(new Client("Elle", "Woods", "Harvard Law School", "+61234567890", "ellewoods@harvard.edu", "bendandsnap")));
            log.info("Preloading " + cRepository.save(new Client("Sherlock", "Holmes", "221B Baker Street", "+61111111111", "sholmes@bakerstreet.com", "elementarymydear")));
            log.info("Preloading " + cRepository.save(new Client("Gandalf", "The Grey", "Middle Earth", "+61333333333", "gandalf@middleearth.com", "youshallnotpass")));
            log.info("Preloading " + cRepository.save(new Client("Marge", "Simpson", "Springfield", "+61876543210", "msimpson@springfield.com", "mumhair")));
            log.info("Preloading " + cRepository.save(new Client("Buzz", "Lightyear", "Andy's Room", "+61999991111", "buzz@toystory.com", "toinfinityandbeyond")));
            log.info("Preloading " + cRepository.save(new Client("Dr.", "House", "Princeton Plainsboro", "+61765432109", "dhouse@ppmc.edu", "everybodylies")));
            log.info("Preloading " + cRepository.save(new Client("Spongebob", "Squarepants", "Bikini Bottom", "+61123456789", "spongebob@bikinibottom.com", "krabbypatty")));
            log.info("Preloading " + cRepository.save(new Client("Frodo", "Baggins", "The Shire", "+61456789012", "fbaggins@theshire.com", "onering")));
            log.info("Preloading " + cRepository.save(new Client("Chandler", "Bing", "New York", "+61876543210", "cbing@friends.com", "coulditbeanyfunnier")));
            log.info("Preloading " + cRepository.save(new Client("Dory", "", "The Ocean", "+61987654321", "dory@findingnemo.com", "justkeepswimming")));
            log.info("Preloading " + cRepository.save(new Client("Yoda", "", "Dagobah", "+61321345678", "yoda@jediorder.com", "maytheforcebewithyou")));
            log.info("Preloading " + cRepository.save(new Client("Olaf", "", "Arendelle", "+61234567890", "olaf@frozen.com", "insummer")));
            log.info("Preloading " + cRepository.save(new Client("Nemo", "", "The Ocean", "+61432109876", "nemo@findingnemo.com", "justkeepswimming")));
            log.info("Preloading " + cRepository.save(new Client("Phoebe", "Buffay", "New York", "+61765432109", "pbuffay@friends.com", "smellycat")));
            log.info("Preloading " + cRepository.save(new Client("Hermione", "Granger", "Hogwarts", "+61111111111", "hgranger@hogwarts.edu", "leviosa")));
            log.info("Preloading " + cRepository.save(new Client("Winnie", "The Pooh", "Hundred Acre Wood", "+61333333333", "winniethepooh@disney.com", "honey123")));
            log.info("Preloading " + cRepository.save(new Client("Austin", "Powers", "London", "+61999999999", "apowers@mi6.co.uk", "yeahbaby")));
            log.info("Preloading " + cRepository.save(new Client("Mickey", "Mouse", "Disneyland", "+61456789012", "mmouse@disney.com", "ohboy")));
            log.info("Preloading " + cRepository.save(new Client("Bart", "Simpson", "Springfield", "+61876543210", "bsimpson@springfield.com", "ayecaramba")));
            log.info("Preloading " + cRepository.save(new Client("Gru", "", "Despicable Me", "+61234567890", "gru@despicableme.com", "minions123")));
            log.info("Preloading " + cRepository.save(new Client("Dumbledore", "", "Hogwarts", "+61123456789", "dumbledore@hogwarts.edu", "alohomora")));
            log.info("Preloading " + cRepository.save(new Client("Lara", "Croft", "Archaeological Sites", "+61432109876", "lcroft@tombraider.com", "raidersofthelostark")));
            log.info("Preloading " + cRepository.save(new Client("Bilbo", "Baggins", "The Shire", "+61987654321", "bbaggins@theshire.com", "secondbreakfast")));
            log.info("Preloading " + cRepository.save(new Client("Scooby", "Doo", "Mystery Machine", "+61765432109", "sdoo@mysteryinc.com", "ruhroh")));
            log.info("Preloading " + cRepository.save(new Client("Elsa", "", "Arendelle", "+61321345678", "elsa@frozen.com", "letitgo")));
            log.info("Preloading " + cRepository.save(new Client("Mufasa", "", "Pride Rock", "+61111111111", "mufasa@lionking.com", "rememberwhoiam")));
            log.info("Preloading " + cRepository.save(new Client("Sonic", "The Hedgehog", "Green Hill Zone", "+61333333333", "sonic@sega.com", "gottagofast")));
            log.info("Preloading " + cRepository.save(new Client("Moana", "", "Motunui Island", "+61456789012", "moana@disney.com", "howfarillgo")));
            log.info("Preloading " + cRepository.save(new Client("Woody", "", "Andy's Room", "+61234567890", "woody@toystory.com", "theresasnakeinmyboot")));
            log.info("Preloading " + cRepository.save(new Client("Charlie", "Brown", "Peanuts", "+61123456789", "cbrown@peanuts.com", "goodgrief")));
            log.info("Preloading " + cRepository.save(new Client("Donkey", "", "Far Far Away", "+61432109876", "donkey@shrek.com", "imakeawaffles")));

            //Creates a new Professional and logs it.
            log.info("Preloading " + pRepository.save(new Professional("Byron", "Butterworth Banzhaf", "Byron Inc.", "Wollongong", "+6121212121", "tradie@ht.com", "tradie")));

            log.info("Preloading " + pRepository.save(new Professional("Byron", "Butterworth Banzhaf", "Byron Inc.", "Wollongong", "+6121212121", "bb429@uowmail.edu.au", "BBB")));
            log.info("Preloading " + pRepository.save(new Professional("Harry", "Potterhead", "Hogwarts Inc.", "London", "+44123456789", "harry@hogwarts.edu", "AccioPassword")));
            log.info("Preloading " + pRepository.save(new Professional("Barry", "Barnacle", "Barnacle Inc.", "New York", "+1212121212", "barry@barnacle.com", "Barnacle123")));
            log.info("Preloading " + pRepository.save(new Professional("Mickey", "Mouseketeer", "Disney Inc.", "Orlando", "+14071234567", "mickey@disney.com", "Minnie123")));
            log.info("Preloading " + pRepository.save(new Professional("Johnny", "Bravo", "Cartoon Network Inc.", "Hollywood", "+13102020202", "johnny@cartoonnetwork.com", "HeyMama123")));
            log.info("Preloading " + pRepository.save(new Professional("Bruce", "Wayne", "Wayne Enterprises", "Gotham City", "+44123456789", "bruce@wayneenterprises.com", "Bat123")));
            log.info("Preloading " + pRepository.save(new Professional("Peter", "Parker", "Daily Bugle Inc.", "New York", "+1212121212", "peter@dailybugle.com", "Spidey123")));
            log.info("Preloading " + pRepository.save(new Professional("Clark", "Kent", "Daily Planet Inc.", "Metropolis", "+14071234567", "clark@dailyplanet.com", "Superman123")));
            log.info("Preloading " + pRepository.save(new Professional("Sherlock", "Holmes", "Baker Street Inc.", "London", "+44123456789", "sherlock@bakerstreet.com", "Elementary123")));
            log.info("Preloading " + pRepository.save(new Professional("Homer", "Simpson", "Springfield Inc.", "Springfield", "+1212121212", "homer@springfield.com", "Doh123")));
            log.info("Preloading " + pRepository.save(new Professional("Donald", "Duck", "Disney Inc.", "Orlando", "+14071234567", "donald@disney.com", "Daisy123")));
            log.info("Preloading " + pRepository.save(new Professional("Bugs", "Bunny", "Warner Bros. Inc.", "Hollywood", "+13102020202", "bugs@warnerbros.com", "WhatsUpDoc123")));
            log.info("Preloading " + pRepository.save(new Professional("Kermit", "Frog", "Muppets Inc.", "New York", "+1212121212", "kermit@muppets.com", "Rainbow123")));
            log.info("Preloading " + pRepository.save(new Professional("Scooby", "Doo", "Hanna-Barbera Inc.", "Los Angeles", "+13102020202", "scooby@hannabarbera.com", "Scooby123")));
            log.info("Preloading " + pRepository.save(new Professional("Mario", "Brothers", "Nintendo Inc.", "Tokyo", "+81312345678", "mario@nintendo.com", "Mushroom123")));
            log.info("Preloading " + pRepository.save(new Professional("Bart", "Simpson", "Springfield Inc.", "Springfield", "+1212121212", "bart@springfield.com", "AyCaramba123")));
            log.info("Preloading " + pRepository.save(new Professional("Wile", "Coyote", "Warner Bros. Inc.", "Hollywood", "+13102020202", "wile@warnerbros.com", "MeepMeep123")));
            log.info("Preloading " + pRepository.save(new Professional("Betty", "Boop", "Fleischer Studios Inc.", "New York", "+1212121212", "betty@fleischerstudios.com", "BoopBoop123")));
            log.info("Preloading " + pRepository.save(new Professional("Mickey", "Mouse", "Disney Inc.", "Orlando", "+14071234567", "mickey@disney.com", "Mickey123")));
            // Loading test data for jobs
            // (String jobTitle, String jobCategory, String jobDescription, Long clientID)

            log.info("Preloading" + jRepository.save(new Job("Constructing Cat Castles", "CARPENTRY", "Building Luxury Fortresses for Feline Royalty", 1L)));

            log.info("Preloading" + jRepository.save(new Job("Installing Disco Ball Lighting", "ELECTRICAL", "Creating the Perfect Dance Party Atmosphere", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Fixing Leaky Faucets for Mermaids", "PLUMBING", "Ensuring Smooth Water Flow for Underwater Royalty", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing Air Conditioning for Dragons", "HVAC", "Keeping Fire-Breathing Reptiles Cool and Comfortable", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting Rainbows on Unicorn Stables", "PAINTING", "Adding a Touch of Magic to Mythical Horse Homes", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing Solar Panels on Leprechaun Houses", "ROOFING", "Harnessing the Power of the Sun for Lucky Irish Folks", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Gingerbread House", "MASONRY", "Creating a Sweet and Spacious Dwelling for the Holidays", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing Heated Floors for Polar Bears", "FLOORING", "Ensuring Toasty Toes for Arctic Wildlife", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Planting Magic Beans for Jack's Beanstalk", "LANDSCAPING", "Creating a Stunning Garden to Reach the Sky", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Managing a Construction Site on Mars", "OTHER", "Overseeing the Building of Humanity's Future Home", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Roller Coaster for Penguins", "CARPENTRY", "Creating an Extreme Thrill Ride for Flightless Birds", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Designing a Disco Ball for the Sun", "ELECTRICAL", "Bringing Glitter and Glam to Our Solar System's Star", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing Moat Bridges for Medieval Castles", "PLUMBING", "Ensuring Safe Passage for Knights and Princesses", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing Air Filtration Systems for Fire-Breathing Dragons", "HVAC", "Protecting the Lungs of Mythical Creatures", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting Murals of Dinosaurs for a Jurassic Park", "PAINTING", "Adding Prehistoric Charm to an Amusement Park", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing Solar-Powered Elevators for Aliens", "ROOFING", "Ascending Extraterrestrial Passengers to the Top of the Universe", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Lego Castle", "MASONRY", "Creating a Colorful Fortress for Kids of All Ages", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Swimming Pool for Mermaids", "FLOORING", "Providing a Place for Oceanic Maidens to Take a Dip", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Creating a Garden of Giant Mushrooms", "LANDSCAPING", "Bringing a Little Whimsy to a Backyard Paradise", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Managing a Construction Site for a Haunted House", "OTHER", "Overseeing the Building of a Spooky Abode for Ghosts and Goblins", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building an Indoor Ski Slope for Penguins", "CARPENTRY", "Providing Snowy Fun for Arctic Birds", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Designing a Tanning Bed for Vampires", "ELECTRICAL", "Helping Bloodsuckers Achieve That Perfect Glow", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing the Water Slide for a Water Park for Otters", "PLUMBING", "Ensuring Smooth and Splashing Fun for Aquatic Mammals", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Sauna for Yetis", "HVAC", "Providing a Warm and Cozy Retreat for Hairy Monsters", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Portrait of Bigfoot", "PAINTING", "Capturing the Elusive Creature on Canvas", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Wind Turbine for a Unicorn Sanctuary", "ROOFING", "Harnessing the Power of the Wind for Mythical Creatures", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Sand Castle for Beach-Going Lobsters", "MASONRY", "Providing a Luxurious Vacation Spot for Crustaceans", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Heated Pool for Polar Bears", "FLOORING", "Keeping Arctic Predators Comfortable During the Off-Season", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Creating a Garden of Edible Flowers for a Caterpillar Sanctuary", "LANDSCAPING", "Growing a Delicious Buffet for Hungry Larvae", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Managing a Construction Site for a Giant Robot Factory", "OTHER", "Overseeing the Building of Massive Mechanical Menaces", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Designing a Haunted House for Ghosts Who Want to Scare People", "CARPENTRY", "Creating a Spooky Dwelling for the Living-Impaired", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing an Elevator for Squirrels", "ELECTRICAL", "Helping Our Furry Friends Get Up and Down Trees with Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Rainbow for a Unicorn Stable", "PAINTING", "Making Sure Our Mythical Horses Are Living in Style", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Spaceship for Alien Tourists", "HVAC", "Keeping Our Interstellar Guests Comfortable During Their Stay", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Hamster Wheel for a Fitness-Obsessed Sloth", "CARPENTRY", "Helping Our Lazy Friends Stay in Shape", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Fireman's Pole for a Cat Tower", "ELECTRICAL", "Making Sure Our Feline Friends Can Get Down Safely in Emergencies", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Giant Water Slide for Penguins", "OTHER", "Providing a Thrilling Water Ride for Our Aquatic Birds", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Fixing a Time Machine for a Time-Traveling Dinosaur", "PLUMBING", "Making Sure Our Prehistoric Guests Can Get Back Home", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Treehouse for a Family of Flying Squirrels", "CARPENTRY", "Providing a Cozy Home in the Treetops for Our Gliding Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Portrait of a Selfie-Obsessed Llama", "PAINTING", "Capturing the Beauty of Our Fashionable Camelids", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Ski Lift for a Colony of Polar Bears", "OTHER", "Helping Our Arctic Residents Get Around More Easily", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Traveling DeLorean for a Crazy Scientist", "HVAC", "Making Sure Our Eccentric Clients Can Visit the Past in Style", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Luxury Cat Hotel for Feline Socialites", "MASONRY", "Creating a Lavish Retreat for Our High-Society Kitties", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Zipline for a Community of Flying Squirrels", "ELECTRICAL", "Providing an Exciting Adventure for Our Aerial Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Mural of a Mountain Landscape for a Herd of Goats", "PAINTING", "Bringing the Beauty of the Outdoors Inside for Our Horned Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Hamster Wheel for a Fitness-Obsessed Turtle", "CARPENTRY", "Helping Our Slow-Moving Friends Stay in Shape", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Broken Time-Turner for a Wizard", "PLUMBING", "Ensuring Our Clients Can Visit the Past or Future Without a Hitch", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Hot Tub for a Group of Penguins", "HVAC", "Providing a Relaxing Soak for Our Chilly Birds", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Water Park for a Community of Otters", "OTHER", "Giving Our Aquatic Mammals a Fun Place to Play", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Fixing a Hoverboard for a Time-Traveling Marty McFly", "ELECTRICAL", "Making Sure Our Clients Can Ride in Style Across Time and Space", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Portrait of a Self-Absorbed Monkey", "PAINTING", "Capturing the Unique Personality of Our Primate Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Treadmill for a Lazy Sloth", "CARPENTRY", "Helping Our Slow-Moving Friends Stay Healthy", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Wheel for a Community of Hamsters", "OTHER", "Providing a Fun Activity for Our Small Furry Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Hovercraft for a Time-Traveling Doctor", "HVAC", "Ensuring Our Clients Can Float Through Time and Space Safely", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Treehouse for a Colony of Bats", "CARPENTRY", "Providing a Safe and Cozy Home for Our Winged Mammals", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Painting a Mural of a Jungle Landscape for a Family of Monkeys", "PAINTING", "Bringing the Beauty of Nature to Our Primate Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Zip Line for a Group of Sloths", "OTHER", "Providing a Thrilling Adventure for Our Slow-Moving Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Giant Ball Pit for a Group of Ferrets", "MASONRY", "Providing a Fun and Safe Place to Play for Our Furry Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Turner for a Time-Traveling Cat", "PLUMBING", "Ensuring Our Clients Can Visit the Past and Future on a Whim", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Catapult for a Colony of Meerkats", "CARPENTRY", "Providing a Fun and Safe Way to Launch Our Small Mammal Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Fixing a Time Machine for a Time-Traveling Robot", "ELECTRICAL", "Ensuring Our Clients Can Travel Through Time and Space with Precision", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Treehouse for a Family of Squirrels", "CARPENTRY", "Providing a Safe and Comfortable Home for Our Acrobatic Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Flying Carpet for a Wizard", "PLUMBING", "Ensuring Our Clients Can Soar Through the Skies with Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Tower for a Group of Giraffes", "MASONRY", "Providing a High-Altitude Home for Our Tallest Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Custom Aquarium for a Family of Otters", "OTHER", "Providing a Fun and Stimulating Environment for Our Aquatic Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Nest for a Group of Eagles", "CARPENTRY", "Providing a Secure and Comfortable Home for Our Feathered Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Fixing a Time-Turner for a Time-Traveling Dragon", "PLUMBING", "Ensuring Our Clients Can Visit Different Eras with Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Custom Doghouse for a Family of Wolves", "CARPENTRY", "Providing a Cozy and Secure Home for Our Wild Canine Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Jetpack for a Time-Traveling Alien", "ELECTRICAL", "Ensuring Our Clients Can Blast Through Space and Time with Precision", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Rabbit Hutch for a Colony of Rabbits", "CARPENTRY", "Providing a Comfortable and Safe Home for Our Furry Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Aviary for a Family of Parrots", "OTHER", "Providing a Stimulating and Secure Home for Our Colorful Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Custom Bathtub for a Family of Hippos", "PLUMBING", "Providing a Relaxing and Refreshing Experience for Our Large Aquatic Mammals", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Playground for a Colony of Prairie Dogs", "CARPENTRY", "Providing a Fun and Safe Place to Play for Our Small Mammal Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Turner for a Time-Traveling Unicorn", "PLUMBING", "Ensuring Our Clients Can Visit Different Eras with Ease and Magic", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Maze for a Colony of Rats", "MASONRY", "Providing a Fun and Challenging Environment for Our Small Mammal Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Lighthouse for a Colony of Seagulls", "CARPENTRY", "Providing a Safe and Secure Home for Our Seafaring Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time Machine for a Time-Traveling Penguin", "ELECTRICAL", "Ensuring Our Clients Can Visit Different Eras and Climates with Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Custom Waterfall for a Family of Otters", "LANDSCAPING", "Providing a Serene and Stimulating Environment for Our Aquatic Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Burrow for a Colony of Badgers", "MASONRY", "Providing a Safe and Comfortable Home for Our Burrowing Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Rocket Ship for a Space-Traveling Elephant", "HVAC", "Ensuring Our Clients Can Explore the Galaxy with Optimal Comfort and Climate Control", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Bridge for a Colony of Monkeys", "CARPENTRY", "Providing a Safe and Convenient Way to Cross Over Troubled Waters", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Garden for a Family of Butterflies", "LANDSCAPING", "Providing a Beautiful and Nourishing Habitat for Our Winged Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Jetpack for a Time-Traveling Hamster", "ELECTRICAL", "Ensuring Our Clients Can Visit Different Eras with Precision and Speed", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom House for a Colony of Ants", "CARPENTRY", "Providing a Secure and Functional Home for Our Small Insect Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Hibernation Den for a Family of Bears", "MASONRY", "Providing a Safe and Cozy Home for Our Hibernating Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Turner for a Time-Traveling Octopus", "PLUMBING", "Ensuring Our Clients Can Visit Different Eras and Oceans with Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Hammock for a Family of Sloths", "CARPENTRY", "Providing a Comfortable and Relaxing Home for Our Slow-Moving Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Greenhouse for a Family of Chameleons", "OTHER", "Providing a Stimulating and Nourishing Environment for Our Color-Changing Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time Machine for a Time-Traveling Gorilla", "ELECTRICAL", "Ensuring Our Clients Can Visit Different Eras with Strength and Precision", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Hovercraft for a Traveling Herd of Elephants", "HVAC", "Ensuring Our Clients Can Traverse Different Terrains with Optimal Comfort and Climate Control", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Nesting Box for a Family of Blue Jays", "CARPENTRY", "Providing a Safe and Secure Home for Our Feathered Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Treehouse for a Family of Raccoons", "CARPENTRY", "Providing a Fun and Adventurous Home for Our Mischievous Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Traveling Phone Booth for a Time-Traveling Cat", "ELECTRICAL", "Ensuring Our Clients Can Visit Different Eras with Feline Grace and Precision", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Habitat for a Family of Praying Mantises", "LANDSCAPING", "Providing a Serene and Stimulating Environment for Our Insect Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Aquarium for a Family of Clownfish", "PLUMBING", "Providing a Beautiful and Nourishing Habitat for Our Aquatic Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time Machine for a Time-Traveling Dog", "HVAC", "Ensuring Our Clients Can Visit Different Eras with Optimal Comfort and Climate Control", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Toy Box for a Family of Ferrets", "CARPENTRY", "Providing a Fun and Safe Place for Our Playful Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Den for a Family of Foxes", "MASONRY", "Providing a Safe and Comfortable Home for Our Wily Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Traveling Phone Booth for a Time-Traveling Platypus", "ELECTRICAL", "Ensuring Our Clients Can Visit Different Eras with Precision and Diversity", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Terrarium for a Family of Geckos", "LANDSCAPING", "Providing a Beautiful and Nourishing Habitat for Our Reptile Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Hammock for a Family of Lemurs", "CARPENTRY", "Providing a Comfortable and Relaxing Home for Our Agile Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time-Turner for a Time-Traveling Parrot", "PLUMBING", "Ensuring Our Clients Can Visit Different Eras with Tropical Flair and Ease", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Building a Custom Nesting Box for a Family of Owls", "CARPENTRY", "Providing a Safe and Secure Home for Our Nocturnal Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Installing a Secret Batcave for a Caped Crusader", "MASONRY", "Providing a Safe and Secure Lair for Our Dark Knight", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Constructing a Custom Sandbox for a Family of Meerkats", "LANDSCAPING", "Providing a Fun and Safe Playground for Our Inquisitive Friends", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));
            log.info("Preloading" + jRepository.save(new Job("Repairing a Time Machine for a Time-Traveling Sloth", "HVAC", "Ensuring Our Clients Can Visit Different Eras with Relaxed Ease and Comfort", getRandomId("http://localhost:8080/dataRequest/clients/ids", restTemplate))));


            // - - - - - Auto Assign Tradies to Jobs - - - - -

            String jobUrl = "http://localhost:8080/dataRequest/jobs/ids";
            Long[] jobIds = restTemplate.getForObject(jobUrl, Long[].class);

            String profURL = "http://localhost:8080/dataRequest/professionals/ids";
            Long[] profIds = restTemplate.getForObject(profURL, Long[].class);
            
            Integer randMax = profIds.length - 1;
            for (Long x : jobIds) {

                // REMOVE ME AFTER TESTING BYRON
                if(x == 1){
                    continue;
                }

                

                Integer randProf = random.nextInt(randMax);
                Integer randChoice = random.nextInt(3);

                for(int i = 0; i < random.nextInt(10); i++){
                    jaRepository.save(new JobApplication(profIds[random.nextInt(randMax)], x, random.nextInt(1000) + 100));
                }

                if(randChoice == 1){
                    backendService.updateJobProfessional(x, profIds[randProf]);
                } else if(randChoice == 2){
                    backendService.updateJobProfessional(x, profIds[randProf]);
                    backendService.updateJobStatus(x, "COMPLETE");

                    
                    CreateRating cr = new CreateRating();
                    TempReview tr = cr.getRandomRev();
                    log.info("Preloading" + rRepository.save(new Rating(backendService.getJob(x).getClientId(), profIds[randProf], tr.getRating(), "Sample Rating", tr.getDescription())));
                    
                }
            }

            // DELETE AFTER TESTING
            for(int i = 0; i < random.nextInt(8)+2; i++){
                jaRepository.save(new JobApplication(profIds[random.nextInt(randMax)], 1L, random.nextInt(1000) + 100));
            }





            // String firstName;
            // String lastName;
            // String businessName;
            // String address;
            // String phone;
            // String email;
            // String password;
            // String lorem1;
            // String lorem2;
            // String jobCategory;
            // Long clientId;

            // // How much dummy data for each
            // Integer dummy = 1;

            // System.out.println(
            //     "- - - - - - - - - -" +
            //     "| |   Clients   | |" +
            //     "- - - - - - - - - -"
            // );

            // // Generate dummy data for Client
            // for (int i = 0; i < dummy; i++) {
            //     firstName = faker.name().firstName();
            //     lastName = faker.name().lastName();
            //     address = faker.address().fullAddress();
            //     phone = faker.phoneNumber().phoneNumber();
            //     email = faker.internet().emailAddress();
            //     password = faker.internet().password();
            //     log.info("Preloading " + backendService.createClient(new Client(firstName, lastName, address, phone, email, password)));
            // }

            // System.out.println(
            //     "- - - - - - - - - - - - -\n" +
            //     "| |   Professionals   | |\n" +
            //     "- - - - - - - - - - - - -\n"
            // );

            // //log.info("Preloading " + backendService.createClient(new Client("Andreas", "S.T.", "Somewhere", "1", "email", "password")));

            // // Generate dummy data for Professional
            // for (int i = 0; i < dummy; i++) {
            //     firstName = faker.name().firstName();
            //     lastName = faker.name().lastName();
            //     businessName = faker.company().name();
            //     address = faker.address().fullAddress();
            //     phone = faker.phoneNumber().phoneNumber();
            //     email = faker.internet().emailAddress();
            //     password = faker.internet().password();
            //     log.info("Preloading " + backendService.createProfessional(new Professional(firstName, lastName, businessName, address, phone, email, password)));
            // }

            // System.out.println(
            //     "- - - - - - - - \n" +
            //     "| |   Jobs   | |\n" +
            //     "- - - - - - - - \n"
            // );

            // // Generate dummy data for Job
            // for (int i = 0; i < dummy; i++) {
            //     lorem1 = faker.lorem().sentence(5, 5);
            //     List<Job.JobCategory> VALUES = Collections.unmodifiableList(Arrays.asList(Job.JobCategory.values()));
            //     int SIZE = VALUES.size();
            //     jobCategory = VALUES.get(random.nextInt(SIZE)).toString();
            //     lorem2 = faker.lorem().paragraph(3);
            //     clientId = getRandomId(backendService.getClientIds());
            //     log.info("Preloading " + backendService.createJob(new Job(lorem1, jobCategory, lorem2, clientId)));
            // }

            // List<Long> proIds = backendService.getProfessionalIds();
            // List<Long> jobIds = backendService.getJobIds();
            // Integer randMax = proIds.size();
            // for (Long x : jobIds) {
            //     Integer randPro = random.nextInt(randMax);
            //     Integer randChoice = random.nextInt(3);

            //     if(randChoice == 1){
            //         backendService.updateJobProfessional(x, proIds.get(randPro));
            //         log.info("Preloading " + backendService.createJobApplication(new JobApplication(Long.valueOf(randPro), x, 0 + (1000 - 0) * random.nextDouble())));
            //         log.info("Preloading " + backendService.getJob(x));
            //         List<Long> jobAppIds = backendService.getJobApplicationIds();
            //         backendService.updateJobJobApplication(x, backendService.getJobApplication(Long.valueOf(jobAppIds.size())).getId());
            //     } else if(randChoice == 2){
            //         backendService.updateJobProfessional(x, proIds.get(randPro));
            //         log.info("Preloading " + backendService.createJobApplication(new JobApplication(Long.valueOf(randPro), x, 0 + (1000 - 0) * random.nextDouble())));
            //         backendService.updateJobStatus(x, "COMPLETE");
            //         log.info("Preloading " + backendService.getJob(x));
            //         List<Long> jobAppIds = backendService.getJobApplicationIds();
            //         backendService.updateJobJobApplication(x, backendService.getJobApplication(Long.valueOf(jobAppIds.size())).getId());

            //         lorem1 = faker.lorem().sentence(3, 2);
            //         CreateRating cr = new CreateRating();
            //         TempReview tr = cr.getRandomRev();
            //         log.info("Preloading " + rRepository.save(new Rating(backendService.getJob(x).getClientId(), proIds.get(randPro), tr.getRating(), lorem1, tr.getDescription())));
            //     }
            // }
        };
    }
}