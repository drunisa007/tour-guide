package tourguide.lightidea.com.tourguide.model.HotelModel;

public class FacModel {
    private Boolean dinner;
    private Boolean wifi;
    private Boolean pool;
    private Boolean pet;
    private Boolean fit;
    private Boolean bar;
    private Boolean baby;
    private Boolean car;
    private Boolean bike;

    public Boolean getDinner() {
        return dinner;
    }

    public void setDinner(Boolean dinner) {
        this.dinner = dinner;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getPool() {
        return pool;
    }

    public void setPool(Boolean pool) {
        this.pool = pool;
    }

    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    public Boolean getFit() {
        return fit;
    }

    public void setFit(Boolean fit) {
        this.fit = fit;
    }

    public Boolean getBar() {
        return bar;
    }

    public void setBar(Boolean bar) {
        this.bar = bar;
    }

    public Boolean getBaby() {
        return baby;
    }

    public void setBaby(Boolean baby) {
        this.baby = baby;
    }

    public Boolean getCar() {
        return car;
    }

    public void setCar(Boolean car) {
        this.car = car;
    }

    public Boolean getBike() {
        return bike;
    }

    public void setBike(Boolean bike) {
        this.bike = bike;
    }

    public FacModel(Boolean dinner, Boolean wifi, Boolean pool, Boolean pet, Boolean fit, Boolean bar, Boolean baby, Boolean car, Boolean bike) {
        this.dinner = dinner;
        this.wifi = wifi;
        this.pool = pool;
        this.pet = pet;
        this.fit = fit;
        this.bar = bar;
        this.baby = baby;
        this.car = car;
        this.bike = bike;
    }

    public FacModel(){

    }


}
