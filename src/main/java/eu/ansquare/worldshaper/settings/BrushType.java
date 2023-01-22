package eu.ansquare.worldshaper.settings;

public enum BrushType {
    CUBOID(true, true, true, true, true, true, "A cuboid with the specified dimensions"),
    SPHERE(true, true, true, true, true, true, "A sphere with the specified radius"),
    CYLINDER(true, true, true, true, true, true, "A cylinder with the specified radius and height"),
    SURFACE(false, true, true, false, true, false, "The surface you aim on in a specified radius"),
    CLUSTER(false, true, true, false, true, false, "The 3d surface you aim on in a specified radius"),
    SINGLEBLOCK(true, true, true, false, false,false, "A single block");

    private final boolean supportsPlace;
    private final boolean supportsReplace;
    private final boolean supportsClear;
    private final boolean supportsAirPlace;
    private final boolean supportsOverlay;
    private final boolean supportsFlatten;

    private final String lore;

    BrushType(boolean supportsPlace, boolean supportsReplace, boolean supportsClear,boolean supportsAirPlace, boolean supportsOverlay, boolean supportsFlatten, String lore) {
        this.supportsPlace = supportsPlace;
        this.supportsReplace = supportsReplace;
        this.supportsClear = supportsClear;
        this.supportsAirPlace = supportsAirPlace;
        this.supportsOverlay = supportsOverlay;
        this.supportsFlatten = supportsFlatten;
        this.lore = lore;

    }

    public boolean isSupportsPlace() {
        return supportsPlace;
    }

    public boolean isSupportsReplace() {
        return supportsReplace;
    }

    public boolean isSupportsClear() {
        return supportsClear;
    }
    public boolean isSupportsAirPlace() {
        return supportsAirPlace;
    }

    public boolean isSupportsOverlay() {
        return supportsOverlay;
    }

    public boolean isSupportsFlatten() {
        return supportsFlatten;
    }
    public String getLore() { return lore;}

}
