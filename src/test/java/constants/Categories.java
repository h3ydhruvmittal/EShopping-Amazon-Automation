package constants;

public enum Categories {
    ALL_CATEGORIES("All Categories", "search-alias=aps"),
    ALEXA_SKILLS("Alexa Skills", "search-alias=alexa-skills"),
    AMAZON_DEVICES("Amazon Devices", "search-alias=amazon-devices"),
    AMAZON_FASHION("Amazon Fashion", "search-alias=fashion"),
    AMAZON_FRESH("Amazon Fresh", "search-alias=nowstore"),
    AMAZON_PHARMACY("Amazon Pharmacy", "search-alias=amazon-pharmacy"),
    APPLIANCES("Appliances", "search-alias=appliances"),
    APPS_AND_GAMES("Apps & Games", "search-alias=mobile-apps"),
    AUDIBLE_AUDIOBOOKS("Audible Audiobooks", "search-alias=audible"),
    BABY("Baby", "search-alias=baby"),
    BEAUTY("Beauty", "search-alias=beauty"),
    BOOKS("Books", "search-alias=stripbooks"),
    CAR_AND_MOTORBIKE("Car & Motorbike", "search-alias=automotive"),
    CLOTHING_AND_ACCESSORIES("Clothing & Accessories", "search-alias=apparel"),
    COLLECTIBLES("Collectibles", "search-alias=collectibles"),
    COMPUTERS_AND_ACCESSORIES("Computers & Accessories", "search-alias=computers"),
    ELECTRONICS("Electronics", "search-alias=electronics"),
    FURNITURE("Furniture", "search-alias=furniture"),
    GARDEN_AND_OUTDOORS("Garden & Outdoors", "search-alias=lawngarden"),
    GIFT_CARDS("Gift Cards", "search-alias=gift-cards"),
    GROCERY_AND_GOURMET_FOODS("Grocery & Gourmet Foods", "search-alias=grocery"),
    HEALTH_AND_PERSONAL_CARE("Health & Personal Care", "search-alias=hpc"),
    HOME_AND_KITCHEN("Home & Kitchen", "search-alias=kitchen"),
    INDUSTRIAL_AND_SCIENTIFIC("Industrial & Scientific", "search-alias=industrial"),
    JEWELLERY("Jewellery", "search-alias=jewelry"),
    KINDLE_STORE("Kindle Store", "search-alias=digital-text"),
    LUGGAGE_AND_BAGS("Luggage & Bags", "search-alias=luggage"),
    LUXURY_BEAUTY("Luxury Beauty", "search-alias=luxury-beauty"),
    MOVIES_AND_TV("Movies & TV", "search-alias=movies-tv"),
    MUSIC("Music", "search-alias=popular"),
    MUSICAL_INSTRUMENTS("Musical Instruments", "search-alias=mi"),
    OFFICE_PRODUCTS("Office Products", "search-alias=office-products"),
    PET_SUPPLIES("Pet Supplies", "search-alias=pets"),
    PRIME_VIDEO("Prime Video", "search-alias=instant-video"),
    SHOES_AND_HANDBAGS("Shoes & Handbags", "search-alias=shoes"),
    SOFTWARE("Software", "search-alias=software"),
    SPORTS_FITNESS_AND_OUTDOORS("Sports, Fitness & Outdoors", "search-alias=sporting"),
    TOYS_AND_GAMES("Toys & Games", "search-alias=toys"),
    VIDEO_GAMES("Video Games", "search-alias=videogames"),
    WATCHES("Watches", "search-alias=watches");

    private final String displayName;
    private final String value;
    Categories(String displayName, String value) {
        this.displayName = displayName;
        this.value = value;
    }
    public String getDisplayName() {
        return displayName;
    }
    public String getValue() {
        return value;
    }
}
