package model;

import java.util.List;

public class PlatformType {
    private int id;
    private String type;
    List<Platform> platforms;

    public PlatformType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public PlatformType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlatformType)) return false;

        PlatformType that = (PlatformType) o;

        if (getId() != that.getId()) return false;
        if (!getType().equals(that.getType())) return false;
        return getPlatforms() != null ? getPlatforms().equals(that.getPlatforms()) : that.getPlatforms() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getType().hashCode();
        result = 31 * result + (getPlatforms() != null ? getPlatforms().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlatformType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", platforms=" + platforms +
                '}';
    }
}
