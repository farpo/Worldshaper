package eu.ansquare.worldshaper.settings;

public enum AttachType {
    ATTACH("Work in progress"),
    CENTER("Centers selection on targeted block"),
    INSERT("Places selection behind the targeted block");

    private final String lore;
    AttachType(String lore){
        this.lore = lore;
    }
    public String getLore() { return lore;}
}
