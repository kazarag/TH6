package com.example.th6;

public class product {
    // Khai báo các thuộc tính của lớp product
    private String name;
    private String ID;
    private double price;

    // Tạo một hàm khởi tạo không tham số
    public product() {}

    // Tạo một hàm khởi tạo có tham số
    public product(String ID, String name,double Price ) {
        // Gán các giá trị cho các thuộc tính theo tham số truyền vào
        this.name = name;
        this.ID = ID;
        this.price=Price;
    }

    // Tạo các phương thức get và set cho các thuộc tính

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return ID;
    }

    public void setId(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double Price) {
        this.price = Price;
    }
    @Override
    public String toString() {
        return new String(this.name);
    }
}
