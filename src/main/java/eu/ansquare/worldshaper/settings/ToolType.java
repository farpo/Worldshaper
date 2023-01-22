package eu.ansquare.worldshaper.settings;

public enum ToolType {
    PLACE("Fill the whole selected area"),
    REPLACE("Replaces non-air blocks"),
    CLEAR("Fills the are with air"),
    AIRPLACE("Replaces air"),
    OVERLAY("Places blocks above topmost blocks in selection"),
    FLATTEN("Replaces topmost blocks with air");

    private final String lore;
    ToolType(String lore){
        this.lore = lore;
    }
    public String getLore() { return lore;}


}
