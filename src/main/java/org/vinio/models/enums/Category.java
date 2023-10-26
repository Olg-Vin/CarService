package org.vinio.models.enums;
// jakarta converter
public enum Category {
    Car(100), Buss(200), Truck(300), Motorcycle(400);
    private int category;
    Category(int category) {
        this.category = category;
    }
    public int getCategory() {
        return category;
    }
}
