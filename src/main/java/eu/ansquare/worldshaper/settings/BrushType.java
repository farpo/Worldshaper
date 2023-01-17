package eu.ansquare.worldshaper.settings;

public enum BrushType {
    CUBOID(true, true, true, true, true),
    SPHERE(true, true, true, true, true),
    CYLINDER(true, true, true, true, true),
    SURFACE(false, true, true, true, false),
    SINGLEBLOCK(true, true, true, false, false);

    private final boolean supportsPlace;
    private final boolean supportsReplace;
    private final boolean supportsClear;
    private final boolean supportsOverlay;
    private final boolean supportsFlatten;

    BrushType(boolean supportsPlace, boolean supportsReplace, boolean supportsClear, boolean supportsOverlay, boolean supportsFlatten) {
        this.supportsPlace = supportsPlace;
        this.supportsReplace = supportsReplace;
        this.supportsClear = supportsClear;
        this.supportsOverlay = supportsOverlay;
        this.supportsFlatten = supportsFlatten;

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

    public boolean isSupportsOverlay() {
        return supportsOverlay;
    }

    public boolean isSupportsFlatten() {
        return supportsFlatten;
    }

}
